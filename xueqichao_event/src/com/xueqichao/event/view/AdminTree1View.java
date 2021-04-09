package com.xueqichao.event.view;

import com.xueqichao.event.entity.Scenic;
import com.xueqichao.event.service.ScenicService;
import com.xueqichao.event.util.ScreenUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class AdminTree1View extends Box {
    final int WIDTH = 850;
    ScenicService scenicService = new ScenicService();

    Vector v1 = new Vector();


    int row = -1;
    String scenicName;
    String scenicDesc;
    int sid;
    Scenic scenic;

    JPanel jPanel = new JPanel();
    JButton jButton1 = new JButton("添加");
    JButton jButton2 = new JButton("删除");
    JButton jButton3 = new JButton("修改");
    JButton jButton4 = new JButton("查看");
    JButton update = new JButton("刷新");

    JTable jTable;

    public AdminTree1View() {

        //垂直布局
        super(BoxLayout.Y_AXIS);

        jPanel.setMaximumSize(new Dimension(WIDTH, 80));
        jPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        jPanel.add(jButton1);
        jPanel.add(jButton2);
        jPanel.add(jButton3);
        jPanel.add(jButton4);
        jPanel.add(update);

        // TODO 监听器
        jButton1.addActionListener(new MyListener());
        jButton2.addActionListener(new MyListener());
        jButton3.addActionListener(new MyListener());
        jButton4.addActionListener(new MyListener());
        update.addActionListener(new MyListener());



        v1.add("序号");
        v1.add("景点名称");
        v1.add("景点描述");

        jTable = new JTable(scenicService.lookScenic(),v1);
        jTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                row = jTable.getSelectedRow();
                sid = (int) jTable.getValueAt(row,0);
                scenicName = (String) jTable.getValueAt(row,1);
                scenicDesc = (String) jTable.getValueAt(row,2);

            }
        });

        //设置只能选择一行
        jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        this.add(jPanel);
        this.add(new JScrollPane(jTable));

    }

    /**
     * 整个Tree1 界面上按钮的监听器
     */

    private class MyListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
           if(e.getSource() == jButton1){
               new AdminTree1View().initAdd();
           }
           if(e.getSource() == jButton2){
               if(row == -1){
                   JOptionPane.showMessageDialog(null,"请选择要删除的信息！");
               }
               else{
                   int a = JOptionPane.showConfirmDialog(null,"确认删除吗?","确认",JOptionPane.YES_NO_OPTION);
                   if(a == 0){
                       if(scenicService.deleteScenic(scenicName,scenicDesc) == 1){
                           JOptionPane.showMessageDialog(null,"删除成功！请刷新！");
                       }
                       else{
                           JOptionPane.showMessageDialog(null,"所要删除的信息不存在！请刷新！");
                       }
                       row = -1;
                   }

               }
           }
           if(e.getSource() == jButton3){
               if(row == -1){
                   JOptionPane.showMessageDialog(null,"请选择要修改的信息！");
               }
               else{
                   new AdminTree1View().initUpdate(scenicName,scenicDesc,sid);
               }

           }
           if(e.getSource() == jButton4){
               if(row == -1){
                   JOptionPane.showMessageDialog(null,"请选择要查看的信息！");
               }
               else{
                   new AdminTree1View().initLook(scenicName,scenicDesc);
               }

           }
           if(e.getSource() == update){
               DefaultTableModel dtm = (DefaultTableModel) jTable.getModel();
               dtm.setRowCount(0);
               for(Vector v : scenicService.lookScenic()){
                   dtm.addRow(v);
                   row = -1;
               }
           }

        }
    }









    /**
     * “添加”按钮 的界面
     */
    JFrame jFrame1 = new JFrame("添加界面");
    JLabel jLabel1 = new JLabel("景点名称:");
    JLabel jLabel2 = new JLabel("景点描述:");
    JTextField jTextField1 = new JTextField(15);
    JTextArea jTextArea1 = new JTextArea(5,15);
    JButton jButton5 = new JButton("添加");
    JButton jButton6 = new JButton("取消");
    Box box1 = Box.createHorizontalBox();
    Box box2 = Box.createHorizontalBox();
    Box box3 = Box.createHorizontalBox();
    Box box4 = Box.createVerticalBox();

    private void initAdd(){
        jFrame1.setBounds((ScreenUtil.getScreenWidth() - 350)/2,(ScreenUtil.getScreenHeight() - 350)/2,350,320);

        box1.add(jLabel1);
        box1.add(Box.createHorizontalStrut(20));
        box1.add(jTextField1);

        box2.add(jLabel2);
        box2.add(Box.createHorizontalStrut(20));
        box2.add(new JScrollPane(jTextArea1));

        box3.add(jButton5);
        box3.add(Box.createHorizontalStrut(20));
        box3.add(jButton6);

        box4.add(Box.createVerticalStrut(60));
        box4.add(box1);
        box4.add(Box.createVerticalStrut(20));
        box4.add(box2);
        box4.add(Box.createVerticalStrut(20));
        box4.add(box3);
        box4.add(Box.createVerticalStrut(40));

        jFrame1.add(Box.createHorizontalStrut(50),BorderLayout.WEST);
        jFrame1.add(Box.createHorizontalStrut(20),BorderLayout.EAST);
        jFrame1.add(box4);

        jButton5.addActionListener(new Listener1());
        jButton6.addActionListener(new Listener1());


        jFrame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame1.setVisible(true);


    }

    private class Listener1 implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == jButton5){
                String scenicName = jTextField1.getText();
                String scenicDesc = jTextArea1.getText();
                if("".equals(scenicName)){
                    JOptionPane.showMessageDialog(null,"景点名称不能为空！");
                }
                else if("".equals(scenicDesc)){
                    JOptionPane.showMessageDialog(null,"景点描述不能为空！");
                }
                else {
                    Scenic s = scenicService.isExist(scenicName);
                    if(s != null){
                        JOptionPane.showMessageDialog(null,"添加的景点信息已存在！");
                    }
                    else{
                        JOptionPane.showMessageDialog(jFrame1,"添加成功！请刷新！");
                        scenicService.keepScenic(scenicName,scenicDesc);
                        jFrame1.dispose();
                    }
                }
            }
            if(e.getSource() == jButton6){
                jFrame1.dispose();
            }

        }
    }



    /**
     * “修改”按钮 的界面
     */
    JFrame jFrame2 = new JFrame("修改界面");
    JLabel jLabel3 = new JLabel("修改后景点名称:");
    JLabel jLabel4 = new JLabel("修改后景点描述:");
    JTextField jTextField2 = new JTextField(15);
    JTextArea jTextArea2 = new JTextArea(5,15);
    JButton jButton7 = new JButton("修改");
    JButton jButton8 = new JButton("取消");
    Box box5 = Box.createHorizontalBox();
    Box box6 = Box.createHorizontalBox();
    Box box7 = Box.createHorizontalBox();
    Box box8 = Box.createVerticalBox();

    private void initUpdate(String scenicName,String scenicDesc,int sid){

        scenic = new Scenic(scenicService.lookExistMysql(sid).getSid(),scenicService.lookExistMysql(sid).getScenicName(),
                scenicService.lookExistMysql(sid).getScenicDesc());

        jFrame2.setBounds((ScreenUtil.getScreenWidth() - 350)/2,(ScreenUtil.getScreenHeight() - 350)/2,350,320);

        box5.add(jLabel3);
        box5.add(Box.createHorizontalStrut(20));
        box5.add(jTextField2);

        box6.add(jLabel4);
        box6.add(Box.createHorizontalStrut(20));
        box6.add(new JScrollPane(jTextArea2));

        box7.add(jButton7);
        box7.add(Box.createHorizontalStrut(20));
        box7.add(jButton8);

        box8.add(Box.createVerticalStrut(60));
        box8.add(box5);
        box8.add(Box.createVerticalStrut(20));
        box8.add(box6);
        box8.add(Box.createVerticalStrut(20));
        box8.add(box7);
        box8.add(Box.createVerticalStrut(40));

        jFrame2.add(Box.createHorizontalStrut(50),BorderLayout.WEST);
        jFrame2.add(Box.createHorizontalStrut(20),BorderLayout.EAST);
        jFrame2.add(box8);

        jButton7.addActionListener(new Listener2());
        jButton8.addActionListener(new Listener2());

        jTextField2.setText(scenicName);
        jTextArea2.setText(scenicDesc);


        jFrame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame2.setVisible(true);


    }

    /**
     * 修改界面按钮的监听器
     */

    private class Listener2 implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == jButton7){
                String scenicName = jTextField2.getText();
                String scenicDesc = jTextArea2.getText();
                if("".equals(scenicName)){
                    JOptionPane.showMessageDialog(null,"景点名称不能为空！");
                }
                else if("".equals(scenicDesc)){
                    JOptionPane.showMessageDialog(null,"景点描述不能为空！");
                }
                else if(scenicName.equals(scenic.getScenicName()) &&
                        scenicDesc.equals(scenic.getScenicDesc())){
                    JOptionPane.showMessageDialog(null,"您并未做出修改！");
                }
                else {
                    JOptionPane.showMessageDialog(jFrame2,"修改成功！请刷新！");
                    scenicService.updateScenicMysql(scenicName,scenicDesc,scenic.getSid());
                    jFrame2.dispose();
                }
            }
            if(e.getSource() == jButton8){
                jFrame2.dispose();
            }

        }
    }


    /**
     * “查看”按钮 的界面
     */
    JFrame jFrame3 = new JFrame("查看界面");
    JLabel jLabel5 = new JLabel("景点名称:");
    JLabel jLabel6 = new JLabel("景点描述:");
    JTextField jTextField3 = new JTextField(15);
    JTextArea jTextArea3 = new JTextArea(5,15);
    JButton jButton9 = new JButton("取消");
    Box box9 = Box.createHorizontalBox();
    Box box10 = Box.createHorizontalBox();
    Box box11 = Box.createHorizontalBox();
    Box box12 = Box.createVerticalBox();

    private void initLook(String scenicName,String scenicDesc){

        jFrame3.setBounds((ScreenUtil.getScreenWidth() - 350)/2,(ScreenUtil.getScreenHeight() - 350)/2,350,320);

        box9.add(jLabel5);
        box9.add(Box.createHorizontalStrut(20));
        box9.add(jTextField3);

        box10.add(jLabel6);
        box10.add(Box.createHorizontalStrut(20));
        box10.add(new JScrollPane(jTextArea3));

        box11.add(jButton9);

        box12.add(Box.createVerticalStrut(60));
        box12.add(box9);
        box12.add(Box.createVerticalStrut(20));
        box12.add(box10);
        box12.add(Box.createVerticalStrut(20));
        box12.add(box11);
        box12.add(Box.createVerticalStrut(40));

        jFrame3.add(Box.createHorizontalStrut(50),BorderLayout.WEST);
        jFrame3.add(Box.createHorizontalStrut(20),BorderLayout.EAST);
        jFrame3.add(box12);

        jTextField3.setText(scenicName);
        jTextArea3.setText(scenicDesc);
        jTextField3.setEditable(false);
        jTextArea3.setEditable(false);

        jButton9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == jButton9){
                    jFrame3.dispose();
                }
            }
        });


        jFrame3.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame3.setVisible(true);


    }

}