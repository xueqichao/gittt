package com.xueqichao.event.view;

import com.xueqichao.event.entity.Scenic;
import com.xueqichao.event.entity.ScenicStatement;
import com.xueqichao.event.entity.User;
import com.xueqichao.event.service.ScenicService;
import com.xueqichao.event.service.StatementService;
import com.xueqichao.event.service.UserService;
import com.xueqichao.event.util.ScreenUtil;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class UserTree3View extends Box
{
    final int WIDTH = 850;
    StatementService statementService = new StatementService();
    ScenicService scenicService = new ScenicService();

    Vector v1 = new Vector();


    int row = -1;
    int staid;
    ScenicStatement scenicStatement = null;
    String userName = null;
    String scenicName = null;


    JPanel jPanel = new JPanel();

    JTextField jTextField = new JTextField(10);
    JButton jButton = new JButton("搜索");

    JButton jButton1 = new JButton("发布评论");
    JButton jButton2 = new JButton("查看评论");
    JButton jButton3 = new JButton("查看我的评论");
    JButton jButton4 = new JButton("查看所有评论");

    JTable jTable;

    public UserTree3View(String username) {

        //垂直布局
        super(BoxLayout.Y_AXIS);

        userName = username;

        jPanel.setMaximumSize(new Dimension(WIDTH, 80));
        jPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        jPanel.add(jButton1);
        jPanel.add(jButton2);
        jPanel.add(Box.createHorizontalStrut(100));
        jPanel.add(jTextField);
        jPanel.add(jButton);
        jPanel.add(Box.createHorizontalStrut(100));
        jPanel.add(jButton3);
        jPanel.add(jButton4);


        jButton1.addActionListener(new MyListener());
        jButton2.addActionListener(new MyListener());
        jButton3.addActionListener(new MyListener());
        jButton4.addActionListener(new MyListener());

        jTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String information = jTextField.getText();
                DefaultTableModel dtm = (DefaultTableModel) jTable.getModel();
                dtm.setRowCount(0);
                for(Vector v : statementService.lookStatementMysql(information)){
                    dtm.addRow(v);
                    row = -1;
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String information = jTextField.getText();
                DefaultTableModel dtm = (DefaultTableModel) jTable.getModel();
                dtm.setRowCount(0);
                for(Vector v : statementService.lookStatementMysql(information)){
                    dtm.addRow(v);
                    row = -1;
                }

            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });



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
                if("游客".equals(userName)){
                    JOptionPane.showMessageDialog(null,"您是游客！请注册账号！");
                }
                else{
                    if(new UserService().getInstance(userName).getState() == 0){
                        JOptionPane.showMessageDialog(null,"您的账号已被封禁！请联系管理员！");
                    }
                    else {
                        new UserTree3View(userName).initSubmit(userName, jTable);
                    }
                }

            }
            if(e.getSource() == jButton2){
                if(row == -1){
                    JOptionPane.showMessageDialog(null,"请选择要查看的信息！");
                }
                else{
                    new UserTree3View(userName).initLook(userName,scenicName,scenicStatement);
                }
            }
            if(e.getSource() == jButton3){
                if("游客".equals(userName)){
                    JOptionPane.showMessageDialog(null,"您是游客！请注册账号！");
                }
                else{
                    User user = new UserService().getInstance(userName);
                    DefaultTableModel dtm = (DefaultTableModel) jTable.getModel();
                    dtm.setRowCount(0);
                    for(Vector v : statementService.lookStatementMysql(user.getUid())){
                        dtm.addRow(v);
                        row = -1;
                    }
                }

            }
            if(e.getSource() == jButton4){
                DefaultTableModel dtm = (DefaultTableModel) jTable.getModel();
                dtm.setRowCount(0);
                for(Vector v : statementService.lookStatementMysql()){
                    dtm.addRow(v);
                    row = -1;
                }
            }
            if(e.getSource() == jButton){
                String information = jTextField.getText();
                DefaultTableModel dtm = (DefaultTableModel) jTable.getModel();
                dtm.setRowCount(0);
                for(Vector v : statementService.lookStatementMysql(information)){
                    dtm.addRow(v);
                    row = -1;
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
    JButton jButton5 = new JButton("取消");
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
        box4.add(jButton5);
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

        jButton5.addActionListener(e -> {if(e.getSource() == jButton5){
            jFrame.dispose();}
        });


        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setVisible(true);

    }


    /**
     * 发布界面
     */
    JFrame jFrame1 = new JFrame("发布界面");
    JLabel jLabel4 = new JLabel("评论景点");
    JLabel jLabel5 = new JLabel("评论内容");
    JTextField jTextField3 = new JTextField(10);
    JTextArea jTextArea = new JTextArea(5,10);
    JButton jButton6 = new JButton("发布");
    JButton jButton7 = new JButton("取消");
    Box box5 = Box.createHorizontalBox();
    Box box6 = Box.createHorizontalBox();
    Box box7 = Box.createHorizontalBox();
    Box box8 = Box.createVerticalBox();




    public void initSubmit(String userName1,JTable jTable1){

        jFrame1.setBounds((ScreenUtil.getScreenWidth() - 350)/2,(ScreenUtil.getScreenHeight() - 350)/2,350,320);

        userName = userName1;
        jTable = jTable1;

        box5.add(jLabel4);
        box5.add(Box.createHorizontalStrut(20));
        box5.add(jTextField3);

        box6.add(jLabel5);
        box6.add(Box.createHorizontalStrut(20));
        box6.add(new JScrollPane(jTextArea));

        box7.add(jButton6);
        box7.add(Box.createHorizontalStrut(40));
        box7.add(jButton7);

        box8.add(Box.createVerticalStrut(50));
        box8.add(box5);
        box8.add(Box.createVerticalStrut(30));
        box8.add(box6);
        box8.add(Box.createVerticalStrut(30));
        box8.add(box7);
        box8.add(Box.createVerticalStrut(50));

        jButton6.addActionListener(new Listener1());
        jButton7.addActionListener(new Listener1());

        jFrame1.add(Box.createHorizontalStrut(70),BorderLayout.WEST);
        jFrame1.add(Box.createHorizontalStrut(70),BorderLayout.EAST);
        jFrame1.add(box8);


        jFrame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame1.setVisible(true);




    }

    private class Listener1 implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == jButton6){
                User user = new UserService().getInstance(userName);
                Scenic scenic = new ScenicService().isExist(jTextField3.getText());
                if("".equals(jTextField3.getText())){
                    JOptionPane.showMessageDialog(null,"景点名称不能为空！");
                }
                else if("".equals(jTextArea.getText())){
                    JOptionPane.showMessageDialog(null,"景点评论不能为空！");
                }
                else if(scenic == null){
                    JOptionPane.showMessageDialog(null,"不存在此景点！");
                }
                else{
                    JOptionPane.showMessageDialog(null,"发布成功！");
                    jFrame1.dispose();
                    statementService.keepStatementMysql(jTextArea.getText(),scenic.getSid(),user.getUid());
                    scenicService.updateScenicMysql(scenic.getSid(),scenic.getStaNum() + 1);
                    DefaultTableModel dtm = (DefaultTableModel) jTable.getModel();
                    dtm.setRowCount(0);
                    for(Vector v : statementService.lookStatementMysql()){
                        dtm.addRow(v);
                        row = -1;
                    }
                }

            }
            if(e.getSource() == jButton7){
                jFrame1.dispose();
            }
        }
    }

}
