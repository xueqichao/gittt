package com.xueqichao.event.view;

import com.xueqichao.event.entity.User;
import com.xueqichao.event.service.UserService;

import javax.swing.*;
import java.awt.*;

public class UserTree5View extends Box
{
    UserService userService = new UserService();

    JPanel jPanel = new JPanel();
    JLabel jLabel1 = new JLabel("账   号 :");
    JLabel jLabel2 = new JLabel("密   码 :");
    JLabel jLabel3 = new JLabel("金   额 :");
    JLabel jLabel4 = new JLabel("帐号状态:");
    JLabel jLabel5 = new JLabel("(1表示正常)");
    JTextField jTextField1 = new JTextField(15);
    JTextField jTextField2 = new JTextField(15);
    JTextField jTextField3 = new JTextField(15);
    JTextField jTextField4 = new JTextField(15);
    Box box1 = Box.createHorizontalBox();
    Box box2 = Box.createHorizontalBox();
    Box box3 = Box.createHorizontalBox();
    Box box4 = Box.createHorizontalBox();
    Box box5 = Box.createVerticalBox();



    public UserTree5View(String userName){

        //垂直结构
        super(BoxLayout.Y_AXIS);

        User user = userService.getInstance(userName);

        jPanel.setMaximumSize(new Dimension(850, 500));

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

        box5.add(Box.createVerticalStrut(130));
        box5.add(box1);
        box5.add(Box.createVerticalStrut(30));
        box5.add(box2);
        box5.add(Box.createVerticalStrut(30));
        box5.add(box3);
        box5.add(Box.createVerticalStrut(30));
        box5.add(box4);
        box5.add(jLabel5);
        box5.add(Box.createVerticalStrut(40));

        jPanel.add(box5);

        jTextField1.setEditable(false);
        jTextField2.setEditable(false);
        jTextField3.setEditable(false);
        jTextField4.setEditable(false);
        jTextField1.setText(user.getUserName());
        jTextField2.setText(user.getUserPassword());
        jTextField3.setText(String.valueOf(user.getBalance()));
        jTextField4.setText(String.valueOf(user.getState()));


        this.add(jPanel);

    }


}
