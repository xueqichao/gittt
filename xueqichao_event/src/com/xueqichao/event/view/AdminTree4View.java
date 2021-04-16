package com.xueqichao.event.view;

import com.xueqichao.event.entity.Scenic;
import com.xueqichao.event.entity.ScenicStatement;
import com.xueqichao.event.service.ScenicService;
import com.xueqichao.event.service.StatementService;
import com.xueqichao.event.util.ScreenUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class AdminTree4View extends Box
{
    final int WIDTH = 850;
    StatementService statementService = new StatementService();
    ScenicService scenicService = new ScenicService();

    Vector v1 = new Vector();


    int row = -1;
    int staid;
    ScenicStatement scenicStatement = null;
    Scenic scenic = null;
    String userName = null;
    String scenicName = null;


    JPanel jPanel = new JPanel();

    JButton jButton1 = new JButton("删除");
    JButton jButton2 = new JButton("查看");

    JTable jTable;

    public AdminTree4View() {

        //垂直布局
        super(BoxLayout.Y_AXIS);

        jPanel.setMaximumSize(new Dimension(WIDTH, 80));
        jPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        jPanel.add(jButton1);
        jPanel.add(jButton2);


        jButton1.addActionListener(new AdminTree4View.MyListener());
        jButton2.addActionListener(new AdminTree4View.MyListener());



        v1.add("序号");
        v1.add("发布者");
        v1.add("评论景点");
        v1.add("评论内容");

        jTable = new JTable(statementService.lookStatementMysql(),v1);
        jTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                row = jTable.getSelectedRow();
                staid = (int) jTable.getValueAt(row,0);
                userName = (String) jTable.getValueAt(row,1);
                scenicName = (String) jTable.getValueAt(row,2);
                scenicStatement = statementService.getInstance(staid);
            }
        });

        //设置只能选择一行
        jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        this.add(jPanel);
        this.add(new JScrollPane(jTable));

    }

    private class MyListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == jButton1){
                if(row == -1){
                    JOptionPane.showMessageDialog(null,"请选择您要删除的信息！");
                }
                else{
                    int a = JOptionPane.showConfirmDialog(null,"确认删除吗?","确认",JOptionPane.YES_NO_OPTION);
                    if(a == 0){
                        if(statementService.deleteStatement(scenicStatement.getStaid()) == 1){
                            JOptionPane.showMessageDialog(null,"删除成功！");
                            DefaultTableModel dtm = (DefaultTableModel) jTable.getModel();
                            dtm.setRowCount(0);
                            for(Vector v : statementService.lookStatementMysql()){
                                dtm.addRow(v);
                                row = -1;
                            }
                            scenic = scenicService.lookExistMysql(scenicStatement.getSid());
                            int num = scenic.getStaNum() - 1;
                            scenicService.updateScenicMysql(scenic.getSid(),num);
                        }
                        else{
                            JOptionPane.showMessageDialog(null,"删除失败！");
                        }
                    }

                }
            }
            if(e.getSource() == jButton2){
                if(row == -1){
                    JOptionPane.showMessageDialog(null,"请选择要查看的信息！");
                }
                else{
                    new AdminTree4View().initLook(userName,scenicName,scenicStatement);
                }

            }
        }
    }


    /**
     * 查看界面
     */
    JFrame jFrame = new JFrame("查看界面");
    JLabel jLabel1 = new JLabel("发布者  :");
    JLabel jLabel2 = new JLabel("评论景点:");
    JLabel jLabel3 = new JLabel("评论内容:");
    JTextField jTextField1 = new JTextField(15);
    JTextField jTextField2 = new JTextField(15);
    JTextArea jTextArea1 = new JTextArea(5,15);
    JButton jButton3 = new JButton("取消");
    Box box1 = Box.createHorizontalBox();
    Box box2 = Box.createHorizontalBox();
    Box box3 = Box.createHorizontalBox();
    Box box4 = Box.createVerticalBox();

    private void initLook(String userName,String scenicName,ScenicStatement scenicStatement){

        jFrame.setBounds((ScreenUtil.getScreenWidth() - 350)/2,(ScreenUtil.getScreenHeight() - 350)/2,350,320);

        box1.add(jLabel1);
        box1.add(Box.createHorizontalStrut(20));
        box1.add(jTextField1);

        box2.add(jLabel2);
        box2.add(Box.createHorizontalStrut(20));
        box2.add(jTextField2);

        box3.add(jLabel3);
        box3.add(Box.createHorizontalStrut(20));
        box3.add(new JScrollPane(jTextArea1));

        box4.add(Box.createVerticalStrut(20));
        box4.add(box1);
        box4.add(Box.createVerticalStrut(20));
        box4.add(box2);
        box4.add(Box.createVerticalStrut(20));
        box4.add(box3);
        box4.add(Box.createVerticalStrut(20));
        box4.add(jButton3);
        box4.add(Box.createVerticalStrut(20));

        jFrame.add(Box.createHorizontalStrut(30),BorderLayout.WEST);
        jFrame.add(Box.createHorizontalStrut(30),BorderLayout.EAST);
        jFrame.add(box4);


        jTextField1.setText(userName);
        jTextField2.setText(scenicName);
        jTextArea1.setText(scenicStatement.getStatement());
        jTextField1.setEditable(false);
        jTextField2.setEditable(false);
        jTextArea1.setEditable(false);

        jButton3.addActionListener(e -> {if(e.getSource() == jButton3){
            jFrame.dispose();}
        });


        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setVisible(true);

    }


}
