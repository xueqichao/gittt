package com.xueqichao.event.view;

import com.xueqichao.event.service.ScenicService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class AdminTree1View extends Box {
    final int WIDTH = 850;
    ScenicService scenicService = new ScenicService();

    Vector v1 = new Vector();


    TableModel tableModel;

    JPanel jPanel = new JPanel();
    JButton jButton1 = new JButton("添加");
    JButton jButton2 = new JButton("删除");
    JButton jButton3 = new JButton("修改");
    JButton jButton4 = new JButton("查看");

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

        this.add(jPanel);

        v1.add("序号");
        v1.add("景点名称");
        v1.add("景点描述");

        tableModel = new DefaultTableModel(scenicService.lookScenic(),v1);
        jTable = new JTable(tableModel){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };


        //设置只能选择一行
        jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        this.add(new JScrollPane(jTable));


    }




}