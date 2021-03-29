package com.xueqichao.event.view;

import com.xueqichao.event.service.AdminService;
import com.xueqichao.event.service.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author lenovo
 * 这个类是显示登录界面的类以及程序的入口
 */
public class LoginView
{
    /*声明登录界面
       用到的组件
                  */

  UserService userService = new UserService();
  AdminService adminService = new AdminService();
  private String username = null;
  private String password = null;
  private String position = null;
  private JFrame jf = new JFrame("登录界面");
  private JLabel jLabel1 = new JLabel("账 号:");
  private JLabel jLabel2 = new JLabel("密 码:");
  private JTextField jTextField = new JTextField(15);
  private JPasswordField jPasswordField = new JPasswordField(15);
  private JButton jButton1 = new JButton("登录");
  private JButton jButton2 = new JButton("注册");
  private JRadioButton jRadioButton1 = new JRadioButton("用户",false);
  private JRadioButton jRadioButton2 = new JRadioButton("管理员",false);
  private ButtonGroup buttonGroup = new ButtonGroup();
  private Box box1 = Box.createHorizontalBox();
  private Box box2 = Box.createHorizontalBox();
  private Box box3 = Box.createHorizontalBox();
  private Box box4 = Box.createHorizontalBox();
  private Box box5 = Box.createVerticalBox();

  /**
   * 声明登录按钮以及身份选择的监听器
   */
 private class LoginListener implements ActionListener{

     @Override
     public void actionPerformed(ActionEvent e) {
       if(e.getSource() == jRadioButton1 || e.getSource() == jRadioButton2){
         position = e.getActionCommand();
       }
       if(e.getSource() == jButton1){
         username = jTextField.getText();
         password = String.valueOf(jPasswordField.getPassword());
         check(username,password,position);
       }
     }
   }

  /**
   * 声明注册按钮的监听器
   */
  private class RegisterListener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
      jf.setVisible(false);
      new RegisterView().init();
    }
  }


  /**
   *
   * @param name      页面传来的用户名
   * @param password  页面传来的密码
   * @param position  页面传来的身份信息
   */
  private void check(String name,String password,String position){
    if("".equals(name) || "".equals(password)){
      JOptionPane.showMessageDialog(null,"账号或密码不能为空");
    }
    else if(position == null){
      JOptionPane.showMessageDialog(null,"请选择你的身份");
    }

    //用户登录判断
    else if(position == "用户"){
      if(userService.isExist(name,password)){
        // TODO new UserView();
        System.out.println("登录成功");

      }
      else{
        JOptionPane.showMessageDialog(null,"账号或密码或身份错误");
      }
    }

    //管理员登录判断
    else if(position == "管理员"){
      if(adminService.isExist(name,password)){
        //TODO new AdminView();
        System.out.println("登录成功");
      }
      else {
        JOptionPane.showMessageDialog(null, "账号或密码或身份错误");
      }
    }

  }

  /**
   * 组装登录界面
   */
  public void init(){
    jTextField.setPreferredSize(new Dimension(250,30));
    jPasswordField.setPreferredSize(new Dimension(250,30));
    box1.add(jLabel1);
    box1.add(Box.createHorizontalStrut(10));
    box1.add(jTextField);
    box2.add(jLabel2);
    box2.add(Box.createHorizontalStrut(10));
    box2.add(jPasswordField);
    box3.add(jButton1);
    box3.add(Box.createHorizontalStrut(30));
    box3.add(jButton2);
    buttonGroup.add(jRadioButton1);
    buttonGroup.add(jRadioButton2);
    box4.add(jRadioButton1);
    box4.add(Box.createHorizontalStrut(20));
    box4.add(jRadioButton2);
    box5.add(Box.createVerticalStrut(40));
    box5.add(box1);
    box5.add(Box.createVerticalStrut(20));
    box5.add(box2);
    box5.add(Box.createVerticalStrut(20));
    box5.add(box3);
    box5.add(Box.createVerticalStrut(20));
    box5.add(box4);
    jf.add(Box.createHorizontalStrut(100),BorderLayout.EAST);
    jf.add(Box.createHorizontalStrut(100),BorderLayout.WEST);
    jf.add(box5);
    jf.setBounds(650, 250, 900, 450);
    jf.pack();
    jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jf.setResizable(false);
    jf.setVisible(true);


    jButton1.addActionListener(new LoginListener());
    jButton2.addActionListener(new RegisterListener());
    jRadioButton1.addActionListener(new LoginListener());
    jRadioButton2.addActionListener(new LoginListener());
  }

    public static void main(String[] args) {
      new LoginView().init();
    }
/**
 * 哈哈哈哈哈哈哈哈哈
 */
}