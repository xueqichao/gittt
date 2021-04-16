package com.xueqichao.event.view;

import com.xueqichao.event.entity.Feedback;
import com.xueqichao.event.service.DealFeedbackService;
import com.xueqichao.event.service.FeedbackService;
import com.xueqichao.event.util.ScreenUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class AdminTree5View extends Box
{
    final int WIDTH = 850;

    Vector v1 = new Vector();


    FeedbackService feedbackService = new FeedbackService();
    DealFeedbackService dealFeedbackService = new DealFeedbackService();
    Feedback feedback = null;
    int row = -1;


    JPanel jPanel = new JPanel();

    JButton jButton1 = new JButton("处理投诉");
    JButton jButton2 = new JButton("查看内容");

    JTable jTable;

    public AdminTree5View() {

        //垂直布局
        super(BoxLayout.Y_AXIS);


        jPanel.setMaximumSize(new Dimension(WIDTH, 80));
        jPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        jPanel.add(jButton1);
        jPanel.add(Box.createHorizontalStrut(20));
        jPanel.add(jButton2);

        jButton1.addActionListener(new MyListener());
        jButton2.addActionListener(new MyListener());


        v1.add("投诉号");
        v1.add("投诉用户");
        v1.add("投诉内容");
        v1.add("状态(0表示未回复)");

        jTable = new JTable(feedbackService.lookFeedbackMysql(),v1);
        jTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                row = jTable.getSelectedRow();
                feedback = new Feedback();
                feedback.setFid((Integer) jTable.getValueAt(row,0));
                feedback.setUsername((String) jTable.getValueAt(row,1));
                feedback.setContent((String) jTable.getValueAt(row,2));

            }
        });

        //设置只能选择一行
        jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        this.add(jPanel);
        this.add(new JScrollPane(jTable));

    }

    private class MyListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == jButton1){
                if(row == -1){
                    JOptionPane.showMessageDialog(null,"请选择要处理的投诉！");
                }
                else if(feedbackService.getInstanceMysql(feedback.getFid()).getState() == 1){
                    System.out.println(feedback.getState());
                    JOptionPane.showMessageDialog(null,"此投诉已经处理过了");
                }
                else{
                    new AdminTree5View().initAdd(jTable,feedback);
                }
            }
            if(e.getSource() == jButton2){
                if(row == -1){
                    JOptionPane.showMessageDialog(null,"请选择要查看的信息！");
                }
                else{
                    new AdminTree5View().initLook(feedback);
                }
            }
        }
    }




    JFrame jFrame1 = new JFrame("处理界面");
    JLabel jLabel1 = new JLabel("回复内容");
    JTextArea jTextArea = new JTextArea(5,10);
    JButton jButton3 = new JButton("回复");
    JButton jButton4 = new JButton("取消");
    Box box1 = Box.createHorizontalBox();
    Box box2 = Box.createHorizontalBox();
    Box box3 = Box.createVerticalBox();


    /**
     *
     * 投诉界面
     *
     */
    public void initAdd(JTable jTable1,Feedback feedback1){

        feedback = feedback1;

        jFrame1.setBounds((ScreenUtil.getScreenWidth() - 350)/2,(ScreenUtil.getScreenHeight() - 350)/2,350,320);

        jTable = jTable1;

        box1.add(jLabel1);
        box1.add(Box.createHorizontalStrut(20));
        box1.add(jTextArea);

        box2.add(jButton3);
        box2.add(Box.createHorizontalStrut(20));
        box2.add(jButton4);


        box3.add(Box.createVerticalStrut(50));
        box3.add(box1);
        box3.add(Box.createVerticalStrut(30));
        box3.add(box2);
        box3.add(Box.createVerticalStrut(30));

        jButton3.addActionListener(new Listener1());
        jButton4.addActionListener(new Listener1());

        jFrame1.add(Box.createHorizontalStrut(70),BorderLayout.WEST);
        jFrame1.add(Box.createHorizontalStrut(70),BorderLayout.EAST);
        jFrame1.add(box3);


        jFrame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame1.setVisible(true);


    }

    private class Listener1 implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == jButton3){
                String content = jTextArea.getText();
                if("".equals(content)){
                    JOptionPane.showMessageDialog(null,"内容不能为空！");
                }
                else{
                    JOptionPane.showMessageDialog(null,"处理成功！");
                    jFrame1.dispose();
                    dealFeedbackService.keepDealFeedbackMysql(feedback.getUsername(),content,feedback.getFid());
                    feedbackService.backMysql(feedback.getFid());
                    DefaultTableModel dtm = (DefaultTableModel) jTable.getModel();
                    dtm.setRowCount(0);
                    for(Vector v : feedbackService.lookFeedbackMysql()){
                        dtm.addRow(v);
                        row = -1;
                    }

                }
            }
            if(e.getSource() == jButton4){
                jFrame1.dispose();
            }
        }
    }




    /**
     * 查看界面
     */
    JFrame jFrame = new JFrame("查看界面");
    JLabel jLabel2 = new JLabel("投诉用户:");
    JLabel jLabel3 = new JLabel("投诉内容:");
    JTextField jTextField1 = new JTextField(15);
    JTextArea jTextArea1 = new JTextArea(5,15);
    JButton jButton5 = new JButton("取消");
    Box box4 = Box.createHorizontalBox();
    Box box5 = Box.createHorizontalBox();
    Box box7 = Box.createVerticalBox();

    private void initLook(Feedback feedback){

        jFrame.setBounds((ScreenUtil.getScreenWidth() - 350)/2,(ScreenUtil.getScreenHeight() - 350)/2,350,320);

        box4.add(jLabel2);
        box4.add(Box.createHorizontalStrut(20));
        box4.add(jTextField1);

        box5.add(jLabel3);
        box5.add(Box.createHorizontalStrut(20));
        box5.add(new JScrollPane(jTextArea1));


        box7.add(Box.createVerticalStrut(20));
        box7.add(box4);
        box7.add(Box.createVerticalStrut(20));
        box7.add(box5);
        box7.add(Box.createVerticalStrut(20));
        box7.add(jButton5);
        box7.add(Box.createVerticalStrut(20));

        jFrame.add(Box.createHorizontalStrut(30),BorderLayout.WEST);
        jFrame.add(Box.createHorizontalStrut(30),BorderLayout.EAST);
        jFrame.add(box7);


        jTextField1.setText(feedback.getUsername());
        jTextArea1.setText(feedback.getContent());
        jTextField1.setEditable(false);
        jTextArea1.setEditable(false);

        jButton5.addActionListener(e -> {if(e.getSource() == jButton5){
            jFrame.dispose();}
        });


        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setVisible(true);

    }

}
