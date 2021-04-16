package com.xueqichao.event.view;

import com.xueqichao.event.entity.DealFeedback;
import com.xueqichao.event.service.DealFeedbackService;
import com.xueqichao.event.util.ScreenUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class AdminTree6View extends Box
{
    final int WIDTH = 850;

    Vector v1 = new Vector();


    DealFeedbackService dealFeedbackService = new DealFeedbackService();
    DealFeedback dealFeedback = null;
    int row = -1;


    JPanel jPanel = new JPanel();

    JButton jButton1 = new JButton("查看内容");

    JTable jTable;

    public AdminTree6View() {

        //垂直布局
        super(BoxLayout.Y_AXIS);


        jPanel.setMaximumSize(new Dimension(WIDTH, 80));
        jPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        jPanel.add(jButton1);

        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == jButton1){
                    if(row == -1){
                        JOptionPane.showMessageDialog(null,"请选择要查看的信息！");
                    }
                    else{
                        new AdminTree6View().initLook(dealFeedback);
                    }
                }
            }
        });


        v1.add("投诉号");
        v1.add("回复用户");
        v1.add("回复内容");

        jTable = new JTable(dealFeedbackService.lookDealFeedbackMysql(),v1);
        jTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                row = jTable.getSelectedRow();
                dealFeedback = new DealFeedback();
                dealFeedback.setFid((Integer) jTable.getValueAt(row,0));
                dealFeedback.setUser((String) jTable.getValueAt(row,1));
                dealFeedback.setContent((String) jTable.getValueAt(row,2));
            }
        });

        //设置只能选择一行
        jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        this.add(jPanel);
        this.add(new JScrollPane(jTable));

    }

    /**
     * 查看界面
     */
    JFrame jFrame = new JFrame("查看界面");
    JLabel jLabel2 = new JLabel("回复用户:");
    JLabel jLabel3 = new JLabel("回复内容:");
    JTextField jTextField1 = new JTextField(15);
    JTextArea jTextArea1 = new JTextArea(5,15);
    JButton jButton5 = new JButton("取消");
    Box box4 = Box.createHorizontalBox();
    Box box5 = Box.createHorizontalBox();
    Box box7 = Box.createVerticalBox();

    private void initLook(DealFeedback dealFeedback1){

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


        jTextField1.setText(dealFeedback1.getUser());
        jTextArea1.setText(dealFeedback1.getContent());
        jTextField1.setEditable(false);
        jTextArea1.setEditable(false);

        jButton5.addActionListener(e -> {if(e.getSource() == jButton5){
            jFrame.dispose();}
        });


        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setVisible(true);

    }
}
