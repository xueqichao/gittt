package com.xueqichao.event.view;


import com.xueqichao.event.entity.User;
import com.xueqichao.event.service.UserService;
import com.xueqichao.event.util.ScreenUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class AdminTree3View extends Box
{
    final int WIDTH = 850;

    Vector v1 = new Vector();

    int row = -1;
    int uid;
    String userName;
    String password;
    int balance;
    int state;
    User user = null;

    UserService userService = new UserService();


    JPanel jPanel = new JPanel();
    JButton jButton1 = new JButton("封号");
    JButton jButton2 = new JButton("解封");
    JButton jButton3 = new JButton("充值");

    JTable jTable;

    public AdminTree3View() {

        //垂直布局
        super(BoxLayout.Y_AXIS);

        jPanel.setMaximumSize(new Dimension(WIDTH, 80));
        jPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        jPanel.add(jButton1);
        jPanel.add(jButton2);
        jPanel.add(jButton3);

        jButton1.addActionListener(new Listener1());
        jButton2.addActionListener(new Listener1());
        jButton3.addActionListener(new Listener1());


        v1.add("序   号");
        v1.add("账   号");
        v1.add("密   码");
        v1.add("金   额");
        v1.add("帐号状态(1表示正常)");


        jTable = new JTable(userService.lookUser(),v1);
        jTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                row = jTable.getSelectedRow();
                uid = (int) jTable.getValueAt(row,0);
                userName = (String) jTable.getValueAt(row,1);
                password = (String) jTable.getValueAt(row,2);
                balance = (int) jTable.getValueAt(row,3);
                state = (int) jTable.getValueAt(row,4);
            }
        });

        //设置只能选择一行
        jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        this.add(jPanel);
        this.add(new JScrollPane(jTable));

    }


    private class Listener1 implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == jButton1){
                if(row == -1){
                    JOptionPane.showMessageDialog(null,"请选择用户！");
                }
                else if(userService.look(uid).getState() == 0){
                    JOptionPane.showMessageDialog(null,"此号已经被封禁！");
                }
                else{
                    int a = JOptionPane.showConfirmDialog(null,"确认封禁该用户吗?","确认",JOptionPane.YES_NO_OPTION);
                    if(a == 0){
                        userService.closeUser(uid);
                        JOptionPane.showMessageDialog(null,"封禁成功!");
                        new AdminTree3View().updateTable(jTable);
                    }
                }
            }
            if(e.getSource() == jButton2){
                if(row == -1){
                    JOptionPane.showMessageDialog(null,"请选择用户！");
                }
                else if(userService.look(uid).getState() == 1){
                    JOptionPane.showMessageDialog(null,"此号已经正常！");
                }
                else{
                    int a = JOptionPane.showConfirmDialog(null,"确认解封该用户吗?","确认",JOptionPane.YES_NO_OPTION);
                    if(a == 0){
                        userService.openUser(uid);
                        JOptionPane.showMessageDialog(null,"解封成功!");
                        new AdminTree3View().updateTable(jTable);
                    }
                }
            }
            if(e.getSource() == jButton3){
                if(row == -1){
                    JOptionPane.showMessageDialog(null,"请选择要充值的用户！");
                }
                else{
                    new AdminTree3View().initCharge(uid,jTable);
                }
            }

        }
    }


    JFrame jFrame = new JFrame("充值界面");
    JLabel jLabel = new JLabel("充值金额:");
    JTextField jTextField = new JTextField(10);
    JButton jButton4 = new JButton("充值");
    JButton jButton5 = new JButton("取消");
    Box box1 = Box.createHorizontalBox();
    Box box2 = Box.createHorizontalBox();
    Box box3 = Box.createVerticalBox();

    private void initCharge(int uid,JTable jTable1){
        jTable = jTable1;

        jFrame.setBounds((ScreenUtil.getScreenWidth() - 300)/2,(ScreenUtil.getScreenHeight() - 200)/2,300,200);

        user = userService.look(uid);

        box1.add(jLabel);
        box1.add(Box.createHorizontalStrut(20));
        box1.add(jTextField);

        box2.add(jButton4);
        box2.add(Box.createHorizontalStrut(40));
        box2.add(jButton5);

        box3.add(Box.createVerticalStrut(50));
        box3.add(box1);
        box3.add(Box.createVerticalStrut(25));
        box3.add(box2);
        box3.add(Box.createVerticalStrut(50));

        jFrame.add(Box.createHorizontalStrut(50),BorderLayout.WEST);
        jFrame.add(Box.createHorizontalStrut(50),BorderLayout.EAST);
        jFrame.add(box3);

        jButton4.addActionListener(new Listener2());
        jButton5.addActionListener(new Listener2());

        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setVisible(true);
    }

    private class Listener2 implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == jButton4){
                if("".equals(jTextField.getText())){
                    JOptionPane.showMessageDialog(null,"充值金额不能为空！");
                }
                else{
                    int b = Integer.parseInt(jTextField.getText());
                    JOptionPane.showMessageDialog(null,"充值成功！");
                    jFrame.dispose();
                    int ba = b + user.getBalance();
                    userService.chargeUser(user.getUid(),ba);
                    new AdminTree3View().updateTable(jTable);
                }
            }
            if(e.getSource() == jButton5){
                jFrame.dispose();
            }
        }
    }


    public void updateTable(JTable jTable){
        DefaultTableModel dtm = (DefaultTableModel) jTable.getModel();
        dtm.setRowCount(0);
        for(Vector v : userService.lookUser()){
            dtm.addRow(v);
            row = -1;
        }
    }




}
