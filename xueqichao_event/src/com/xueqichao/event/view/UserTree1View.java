package com.xueqichao.event.view;

import com.xueqichao.event.service.ScenicService;
import com.xueqichao.event.util.ScreenUtil;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class UserTree1View extends Box
{
    final int WIDTH = 850;
    ScenicService scenicService = new ScenicService();

    Vector v1 = new Vector();


    int row = -1;
    String scenicName;
    String scenicDesc;
    int sid;

    JPanel jPanel = new JPanel();
    JButton jButton1 = new JButton("查看");
    JButton jButton2 = new JButton("搜索");
    JTextField jTextField = new JTextField(15);

    JTable jTable;

    public UserTree1View() {

        //垂直布局
        super(BoxLayout.Y_AXIS);

        jPanel.setMaximumSize(new Dimension(WIDTH, 80));
        jPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        jPanel.add(jTextField);
        jPanel.add(jButton2);
        jPanel.add(Box.createHorizontalStrut(500));
        jPanel.add(jButton1);

        /**
         * 为搜索框添加监听器
         */
        Document document = jTextField.getDocument();
        document.addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String information = jTextField.getText();
                DefaultTableModel dtm = (DefaultTableModel) jTable.getModel();
                dtm.setRowCount(0);
                for(Vector v : scenicService.lookScenicMysql(information)){
                    dtm.addRow(v);
                    row = -1;
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String information = jTextField.getText();
                DefaultTableModel dtm = (DefaultTableModel) jTable.getModel();
                dtm.setRowCount(0);
                for(Vector v : scenicService.lookScenicMysql(information)){
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



        v1.add("序号");
        v1.add("景点名称");
        v1.add("景点描述");
        v1.add("评论数量");

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

    private class MyListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == jButton1){
                if(row == -1){
                    JOptionPane.showMessageDialog(null,"请选择要查看的信息！");
                }
                else{
                    new UserTree1View().initLook(scenicName,scenicDesc);
                }
            }
            if(e.getSource() == jButton2){
                String information = jTextField.getText();
                DefaultTableModel dtm = (DefaultTableModel) jTable.getModel();
                dtm.setRowCount(0);
                for(Vector v : scenicService.lookScenicMysql(information)){
                    dtm.addRow(v);
                    row = -1;
                }

            }

        }
    }


    /**
     * 查看界面的组装
     */
    JFrame jFrame1 = new JFrame("查看界面");
    JLabel jLabel1 = new JLabel("景点名称:");
    JLabel jLabel2 = new JLabel("景点描述:");
    JTextField jTextField1 = new JTextField(15);
    JTextArea jTextArea1 = new JTextArea(5,15);
    JButton jButton3 = new JButton("取消");
    Box box1 = Box.createHorizontalBox();
    Box box2 = Box.createHorizontalBox();
    Box box3 = Box.createHorizontalBox();
    Box box4 = Box.createVerticalBox();

    private void initLook(String scenicName,String scenicDesc){

        jFrame1.setBounds((ScreenUtil.getScreenWidth() - 350)/2,(ScreenUtil.getScreenHeight() - 350)/2,350,320);

        box1.add(jLabel1);
        box1.add(Box.createHorizontalStrut(20));
        box1.add(jTextField1);

        box2.add(jLabel2);
        box2.add(Box.createHorizontalStrut(20));
        box2.add(new JScrollPane(jTextArea1));

        box3.add(jButton3);

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

        jTextField1.setText(scenicName);
        jTextArea1.setText(scenicDesc);
        jTextField1.setEditable(false);
        jTextArea1.setEditable(false);

        jButton3.addActionListener(e -> {if(e.getSource() == jButton3){
            jFrame1.dispose();}
        });


        jFrame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame1.setVisible(true);

    }


























}
