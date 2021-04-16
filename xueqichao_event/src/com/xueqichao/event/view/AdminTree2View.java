package com.xueqichao.event.view;

import com.xueqichao.event.entity.Scenic;
import com.xueqichao.event.entity.Ticket;
import com.xueqichao.event.service.ScenicService;
import com.xueqichao.event.service.TicketService;
import com.xueqichao.event.util.ScreenUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class AdminTree2View extends Box
{
    final int WIDTH = 850;

    TicketService ticketService = new TicketService();
    ScenicService scenicService = new ScenicService();
    Vector v1 = new Vector();

    int row = -1;
    int tid;
    String scenicName;
    String time;
    int number;
    int price;
    Ticket ticket1 = null;

    JPanel jPanel = new JPanel();
    JButton jButton1 = new JButton("添加");
    JButton jButton2 = new JButton("删除");
    JButton jButton3 = new JButton("修改");

    JTable jTable;

    public AdminTree2View() {

        //垂直布局
        super(BoxLayout.Y_AXIS);

        jPanel.setMaximumSize(new Dimension(WIDTH, 80));
        jPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        jPanel.add(jButton1);
        jPanel.add(jButton2);
        jPanel.add(jButton3);


        jButton1.addActionListener(new AdminTree2View.MyListener());
        jButton2.addActionListener(new AdminTree2View.MyListener());
        jButton3.addActionListener(new AdminTree2View.MyListener());


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

    /**
     * 整个界面按钮的监听器
     */

    private class MyListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == jButton1){
                new AdminTree2View().initAdd(jTable);
            }
            if(e.getSource() == jButton2){
                if(row == -1){
                    JOptionPane.showMessageDialog(null,"请选择要删除的信息！");
                }
                else{
                    int a = JOptionPane.showConfirmDialog(null,"确认删除吗?","确认",JOptionPane.YES_NO_OPTION);
                    if(a == 0){
                        if(ticketService.deleteTicketMysql(tid) == 1){
                            JOptionPane.showMessageDialog(null,"删除成功！");
                            new AdminTree2View().updateTable(jTable);
                        }
                        else{
                            JOptionPane.showMessageDialog(null,"该票有用户订购，不能删除！");
                        }

                    }
                }
            }
            if(e.getSource() == jButton3){
                if(row == -1){
                    JOptionPane.showMessageDialog(null,"请选择要修改的信息！");
                }
                else{
                    new AdminTree2View().initUpdate(ticketService.getInstance(tid),scenicName,jTable);
                }
            }

        }
    }


    /**
     * “添加”按钮界面
     */

    JFrame jFrame1 = new JFrame("添加界面");
    JLabel jLabel1 = new JLabel("景点名称:");
    JLabel jLabel2 = new JLabel("参观时间:");
    JLabel jLabel3 = new JLabel("票数总量:");
    JLabel jLabel4 = new JLabel("单票价格:");
    JTextField jTextField1 = new JTextField(15);
    JTextField jTextField2 = new JTextField(15);
    JTextField jTextField3 = new JTextField(15);
    JTextField jTextField4 = new JTextField(15);
    JButton jButton4 = new JButton("添加");
    JButton jButton5 = new JButton("取消");
    Box box1 = Box.createHorizontalBox();
    Box box2 = Box.createHorizontalBox();
    Box box3 = Box.createHorizontalBox();
    Box box4 = Box.createHorizontalBox();
    Box box5 = Box.createHorizontalBox();
    Box box6 = Box.createVerticalBox();

    private void initAdd(JTable jTable1){

        jTable = jTable1;

        jFrame1.setBounds((ScreenUtil.getScreenWidth() - 450)/2,(ScreenUtil.getScreenHeight() - 400)/2,450,400);

        box1.add(jLabel1);
        box1.add(Box.createHorizontalStrut(20));
        box1.add(jTextField1);

        box2.add(jLabel2);
        box2.add(Box.createHorizontalStrut(20));
        box2.add(jTextField2);

        box3.add(jLabel3);
        box3.add(Box.createHorizontalStrut(20));
        box3.add(jTextField3);

        box4.add(jLabel4);
        box4.add(Box.createHorizontalStrut(20));
        box4.add(jTextField4);

        box5.add(jButton4);
        box5.add(Box.createHorizontalStrut(20));
        box5.add(jButton5);

        box6.add(Box.createVerticalStrut(50));
        box6.add(box1);
        box6.add(Box.createVerticalStrut(25));
        box6.add(box2);
        box6.add(Box.createVerticalStrut(25));
        box6.add(box3);
        box6.add(Box.createVerticalStrut(25));
        box6.add(box4);
        box6.add(Box.createVerticalStrut(25));
        box6.add(box5);
        box6.add(Box.createVerticalStrut(50));

        jFrame1.add(Box.createHorizontalStrut(70),BorderLayout.WEST);
        jFrame1.add(Box.createHorizontalStrut(70),BorderLayout.EAST);
        jFrame1.add(box6);

        jButton4.addActionListener(new Listener1());
        jButton5.addActionListener(new Listener1());

        jFrame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame1.setVisible(true);

    }

    /**
     * “添加”界面按钮的监听器
     */

    private class Listener1 implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == jButton4){
                String scenicName1 = jTextField1.getText();
                Scenic scenic1 = scenicService.isExist(scenicName1);
                if(scenic1 == null){
                    JOptionPane.showMessageDialog(null,"请输入正确的景点！");
                }
                else if("".equals(jTextField2.getText())){
                    JOptionPane.showMessageDialog(null,"参观时间不能为空！");
                }
                else if("".equals(jTextField3.getText())){
                    JOptionPane.showMessageDialog(null,"票数总量不能为空！");
                }
                else if("".equals(jTextField4.getText())){
                    JOptionPane.showMessageDialog(null,"单票价格不能为空！");
                }
                else if(ticketService.getInstanceMysql(scenic1.getSid(),jTextField2.getText()) != null){
                    JOptionPane.showMessageDialog(null,"该景点在此时间点已有门票！");
                }
                else{
                    String time1 = jTextField2.getText();
                    int number1 = Integer.parseInt(jTextField3.getText());
                    int price1 = Integer.parseInt(jTextField4.getText());
                    ticketService.keepTicketMysql(time1,number1,scenic1.getSid(),price1);
                    JOptionPane.showMessageDialog(null,"添加成功！");
                    new AdminTree2View().updateTable(jTable);
                    jFrame1.dispose();
                }
            }
            if(e.getSource() == jButton5){
                jFrame1.dispose();
            }
        }
    }


    JFrame jFrame2 = new JFrame("修改界面");
    JButton jButton6 = new JButton("修改");

    /**
     *
     * @param ticket  选中的信息封装的实体
     * @param scienceName  选中的信息对应的景点名称
     */

    private void initUpdate(Ticket ticket,String scienceName,JTable jTable1){
        jFrame2.setBounds((ScreenUtil.getScreenWidth() - 450)/2,(ScreenUtil.getScreenHeight() - 400)/2,450,400);

        jTable = jTable1;

        tid = ticket.getTid();
        ticket1 = ticket;

        jTextField1.setText(scienceName);
        jTextField1.setEditable(false);

        jTextField2.setText(ticket.getTime());
        jTextField3.setText(String.valueOf(ticket.getNumber()));
        jTextField4.setText(String.valueOf(ticket.getPrice()));


        box1.add(jLabel1);
        box1.add(Box.createHorizontalStrut(20));
        box1.add(jTextField1);

        box2.add(jLabel2);
        box2.add(Box.createHorizontalStrut(20));
        box2.add(jTextField2);

        box3.add(jLabel3);
        box3.add(Box.createHorizontalStrut(20));
        box3.add(jTextField3);

        box4.add(jLabel4);
        box4.add(Box.createHorizontalStrut(20));
        box4.add(jTextField4);

        box5.add(jButton6);
        box5.add(Box.createHorizontalStrut(20));
        box5.add(jButton5);

        box6.add(Box.createVerticalStrut(50));
        box6.add(box1);
        box6.add(Box.createVerticalStrut(25));
        box6.add(box2);
        box6.add(Box.createVerticalStrut(25));
        box6.add(box3);
        box6.add(Box.createVerticalStrut(25));
        box6.add(box4);
        box6.add(Box.createVerticalStrut(25));
        box6.add(box5);
        box6.add(Box.createVerticalStrut(50));

        jFrame2.add(Box.createHorizontalStrut(70),BorderLayout.WEST);
        jFrame2.add(Box.createHorizontalStrut(70),BorderLayout.EAST);
        jFrame2.add(box6);

        jButton6.addActionListener(new Listener2());
        jButton5.addActionListener(new Listener2());

        jFrame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame2.setVisible(true);

    }

    /**
     * 修改界面的按钮的监听器
     */

    private class Listener2 implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == jButton6){
                String time = jTextField2.getText();
                int number = Integer.parseInt(jTextField3.getText());
                int price = Integer.parseInt(jTextField4.getText());
                if("".equals(time)){
                    JOptionPane.showMessageDialog(null,"参观时间不能为空！");
                }
                else if("".equals(jTextField3.getText())){
                    JOptionPane.showMessageDialog(null,"票数总量不能为空！");
                }
                else if("".equals(jTextField4.getText())){
                    JOptionPane.showMessageDialog(null," 单票价格不能为空！");
                }
                else if(ticket1.getTime().equals(time) && ticket1.getNumber() == number && ticket1.getPrice() == price){
                    JOptionPane.showMessageDialog(null,"您并未作出修改！");
                }
                else{
                    JOptionPane.showMessageDialog(null,"修改成功！");
                    jFrame2.dispose();
                    ticketService.upDateTicket(tid,time,number,price);
                    new AdminTree2View().updateTable(jTable);
                }

            }
            if(e.getSource() == jButton5){
                jFrame2.dispose();
            }

        }
    }

    public void updateTable(JTable jTable){
        DefaultTableModel dtm = (DefaultTableModel) jTable.getModel();
        dtm.setRowCount(0);
        for(Vector v : ticketService.lookTicket()){
            dtm.addRow(v);
            row = -1;
        }
    }




}
