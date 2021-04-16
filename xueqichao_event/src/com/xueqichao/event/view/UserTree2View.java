package com.xueqichao.event.view;

import com.xueqichao.event.entity.Ticket;
import com.xueqichao.event.entity.User;
import com.xueqichao.event.service.RecordService;
import com.xueqichao.event.service.TicketService;
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

public class UserTree2View extends Box
{
    final int WIDTH = 850;

    TicketService ticketService = new TicketService();
    UserService userService = new UserService();
    RecordService recordService = new RecordService();
    Vector v1 = new Vector();

    int row = -1;
    int tid;
    String scenicName;
    String time;
    int number;
    int price;
    int buyNum;
    String userName;
    Ticket ticket1 = null;
    User user = null;

    JPanel jPanel = new JPanel();
    JButton jButton1 = new JButton("购票");
    JButton jButton2 = new JButton("搜索");
    JTextField jTextField = new JTextField(15);

    JTable jTable;

    public UserTree2View(String userName1) {

        //垂直布局
        super(BoxLayout.Y_AXIS);

        userName = userName1;

        jPanel.setMaximumSize(new Dimension(WIDTH, 80));

        jPanel.add(jTextField);
        jPanel.add(jButton2);
        jPanel.add(Box.createHorizontalStrut(500));
        jPanel.add(jButton1);

        jTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String information = jTextField.getText();
                DefaultTableModel dtm = (DefaultTableModel) jTable.getModel();
                dtm.setRowCount(0);
                for(Vector v : ticketService.lookTicket(information)){
                    dtm.addRow(v);
                    row = -1;
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String information = jTextField.getText();
                DefaultTableModel dtm = (DefaultTableModel) jTable.getModel();
                dtm.setRowCount(0);
                for(Vector v : ticketService.lookTicket(information)){
                    dtm.addRow(v);
                    row = -1;
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });


        jButton1.addActionListener(new MyListener());
        jButton2.addActionListener(new MyListener());


        v1.add("序   号");
        v1.add("景点名称");
        v1.add("参观时间");
        v1.add("剩余票数");
        v1.add("单票价格(元)");


        jTable = new JTable(ticketService.lookTicket(),v1);
        jTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                row = jTable.getSelectedRow();
                tid = (int) jTable.getValueAt(row,0);
                scenicName = (String) jTable.getValueAt(row,1);
                time = (String) jTable.getValueAt(row,2);
                number = (int) jTable.getValueAt(row,3);
                price = (int) jTable.getValueAt(row,4);
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
                    if(row == -1){
                        JOptionPane.showMessageDialog(null,"请选择要购买的票！");
                    }
                    else if(userService.getInstance(userName).getState() == 0){
                        JOptionPane.showMessageDialog(null,"您的账号已被封禁！请联系管理员！");
                    }
                    else{
                        new UserTree2View(userName).initBuy(userName,tid,jTable);
                    }
                }
            }
            if(e.getSource() == jButton2){
                String information = jTextField.getText();
                DefaultTableModel dtm = (DefaultTableModel) jTable.getModel();
                dtm.setRowCount(0);
                for(Vector v : ticketService.lookTicket(information)){
                    dtm.addRow(v);
                    row = -1;
                }
            }

        }
    }


    /**
     * 购票选择购买的数量界面
     */
    JFrame jFrame = new JFrame("选择购票数量");
    JLabel jLabel = new JLabel("购票数量:");
    JComboBox jComboBox;
    JButton jButton3 = new JButton("确定");
    JButton jButton4 = new JButton("取消");
    Box box1 = Box.createHorizontalBox();
    Box box2 = Box.createHorizontalBox();
    Box box3 = Box.createVerticalBox();

    public void initBuy(String userName1,int tid1,JTable jTable1){

        jFrame.setBounds((ScreenUtil.getScreenWidth() - 450)/2,(ScreenUtil.getScreenHeight() - 300)/2,450,300);

        userName = userName1;
        tid = tid1;
        jTable = jTable1;

        Vector vector1 = new Vector();
        vector1.add(1);
        vector1.add(2);
        vector1.add(3);
        vector1.add(4);
        vector1.add(5);
        jComboBox = new JComboBox(vector1);



        jComboBox.setSelectedIndex(-1);
        jComboBox.addItemListener(e -> {
            buyNum = (int) jComboBox.getSelectedItem();
        });

        box1.add(jLabel);
        box1.add(Box.createHorizontalStrut(20));
        box1.add(jComboBox);

        box2.add(jButton3);
        box2.add(Box.createHorizontalStrut(20));
        box2.add(jButton4);

        box3.add(Box.createVerticalStrut(50));
        box3.add(box1);
        box3.add(Box.createVerticalStrut(40));
        box3.add(box2);
        box3.add(Box.createVerticalStrut(50));

        jFrame.add(box3);
        jFrame.add(Box.createHorizontalStrut(70),BorderLayout.WEST);
        jFrame.add(Box.createHorizontalStrut(70),BorderLayout.EAST);

        jButton3.addActionListener(new Listener1());
        jButton4.addActionListener(new Listener1());

        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setVisible(true);




    }

    private class Listener1 implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == jButton3){
                user = userService.getInstance(userName);
                ticket1 = ticketService.getInstance(tid);
                if(user.getBalance() < buyNum *ticket1.getPrice()){
                    JOptionPane.showMessageDialog(null,"您的余额不足,请联系管理员充值！");
                }
                else if(buyNum > ticket1.getNumber()){
                    JOptionPane.showMessageDialog(null,"余票不足！");
                }
                else{
                    int a = JOptionPane.showConfirmDialog(null,"您确定要购买"+buyNum+"张票吗？","确认",JOptionPane.YES_NO_OPTION);
                    if(a == 0){
                        JOptionPane.showMessageDialog(null,"购票成功！");
                        jFrame.dispose();
                        userService.updateUserbalance(user.getBalance() - buyNum*ticket1.getPrice(),user.getUid());
                        ticketService.updateTicketNum(ticket1.getTid(),ticket1.getNumber() - buyNum);
                        recordService.keepRecord(buyNum,user.getUid(),ticket1.getTid());
                        DefaultTableModel dtm = (DefaultTableModel) jTable.getModel();
                        dtm.setRowCount(0);
                        for(Vector v : ticketService.lookTicket()){
                            dtm.addRow(v);
                            row = -1;
                        }
                    }
                }

            }

            if(e.getSource() == jButton4){
                jFrame.dispose();
            }










        }
    }

























}
