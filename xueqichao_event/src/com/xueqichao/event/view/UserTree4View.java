package com.xueqichao.event.view;

import com.xueqichao.event.service.RecordService;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class UserTree4View extends Box
{
    final int WIDTH = 850;

    Vector v1 = new Vector();
    RecordService recordService = new RecordService();



    JPanel jPanel = new JPanel();
    JTextField jTextField = new JTextField(15);

    JTable jTable;

    public UserTree4View(String userName) {

        //垂直布局
        super(BoxLayout.Y_AXIS);

        jPanel.setMaximumSize(new Dimension(WIDTH, 100));
        jPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        jTextField.setText("   个人购票记录");
        jPanel.add(Box.createHorizontalStrut(300));
        jPanel.add(jTextField);
        jPanel.add(Box.createHorizontalStrut(300));


        jTextField.setEditable(false);


        v1.add("购票数量");
        v1.add("购票用户");
        v1.add("参观景点");
        v1.add("参观时间");

        jTable = new JTable(recordService.lookRecord(userName),v1);

        //设置只能选择一行
        jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        this.add(jPanel);
        this.add(new JScrollPane(jTable));

    }






}
