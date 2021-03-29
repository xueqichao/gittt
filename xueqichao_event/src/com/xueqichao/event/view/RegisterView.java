package com.xueqichao.event.view;

import com.xueqichao.event.service.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *   这是注册页面
 * @author lenovo
 */
public class RegisterView
{
    private String userName = null;
    private String passWord = null;
    UserService userService = new UserService();
    private JFrame jFrame = new JFrame("注册页面");
    private JLabel jLabel1 = new JLabel("账号:");
    private JTextField jTextField1 = new JTextField(15);
    private JLabel jLabel2 = new JLabel("密码:");
    private JTextField jTextField2 = new JTextField(15);
    private JButton jButton1 = new JButton("注册");
    private JButton jButton2 = new JButton("返回");
    private Box box1 = Box.createHorizontalBox();
    private Box box2 = Box.createHorizontalBox();
    private Box box3 = Box.createHorizontalBox();
    private Box box4 = Box.createVerticalBox();


    /**
     * 定义两个按钮的监听器
     */
   private class RegisterListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == jButton2){
                jFrame.setVisible(false);
                new LoginView().init();
            }
            if(e.getSource() == jButton1){
                userName = jTextField1.getText();
                passWord = jTextField2.getText();
                check(userName,passWord);
            }
        }
    }











    public void init(){
        box1.add(jLabel1);
        box1.add(Box.createHorizontalStrut(20));
        box1.add(jTextField1);

        box2.add(jLabel2);
        box2.add(Box.createHorizontalStrut(20));
        box2.add(jTextField2);

        box3.add(jButton1);
        box3.add(Box.createHorizontalStrut(60));
        box3.add(jButton2);

        box4.add(Box.createVerticalStrut(50));
        box4.add(box1);
        box4.add(Box.createVerticalStrut(30));
        box4.add(box2);
        box4.add(Box.createVerticalStrut(30));
        box4.add(box3);
        box4.add(Box.createVerticalStrut(30));

        jFrame.add(box4);
        jFrame.add(Box.createHorizontalStrut(100), BorderLayout.EAST);
        jFrame.add(Box.createHorizontalStrut(100),BorderLayout.WEST);
        jFrame.add(Box.createVerticalStrut(50),BorderLayout.SOUTH);
        jFrame.setBounds(650, 250, 900, 450);
        jFrame.setResizable(false);
        jFrame.pack();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);

        jButton1.addActionListener(new RegisterListener());
        jButton2.addActionListener(new RegisterListener());

    }


    private void check(String userName,String passWord){

       if("".equals(userName)){
           JOptionPane.showMessageDialog(null,"账号不能为空");
       }
       else if("".equals(passWord)){
           JOptionPane.showMessageDialog(null,"密码不能为空");
        }
       else if(userService.isExist(userName)){
           JOptionPane.showMessageDialog(null,"该账号已被注册");
       }
       else {
           JOptionPane.showMessageDialog(null,"注册成功");
           jFrame.setVisible(false);
           new LoginView().init();
           userService.keepUser(userName,passWord);
       }





    }

















}
