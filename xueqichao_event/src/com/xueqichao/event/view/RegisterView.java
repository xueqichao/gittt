package com.xueqichao.event.view;

import com.xueqichao.event.service.UserService;
import com.xueqichao.event.util.ScreenUtil;

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
    UserService userService = new UserService();
    private final JFrame jFrame = new JFrame("注册页面");
    private final JLabel jLabel1 = new JLabel("账号:");
    private final JTextField jTextField1 = new JTextField(15);
    private final JLabel jLabel2 = new JLabel("密码:");
    private final JTextField jTextField2 = new JTextField(15);
    private final JButton jButton1 = new JButton("注册");
    private final JButton jButton2 = new JButton("返回");
    private final Box box1 = Box.createHorizontalBox();
    private final Box box2 = Box.createHorizontalBox();
    private final Box box3 = Box.createHorizontalBox();
    private final Box box4 = Box.createVerticalBox();


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
                String userName = jTextField1.getText();
                String passWord = jTextField2.getText();
                check(userName, passWord);
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
        jFrame.setBounds((ScreenUtil.getScreenWidth() - 900)/2 + 300, (ScreenUtil.getScreenHeight() - 450)/2 + 80, 900, 450);
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
           int a = JOptionPane.showConfirmDialog(null,"确定注册吗？","确定信息正确",JOptionPane.YES_NO_OPTION);
           if(a == 0 ){JOptionPane.showMessageDialog(null,"注册成功");
               jFrame.dispose();
               new LoginView().init();
               userService.keepUser(userName,passWord);}

       }





    }

















}
