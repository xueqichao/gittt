package com.xueqichao.event.view;

import com.xueqichao.event.service.AdminService;
import com.xueqichao.event.service.UserService;
import com.xueqichao.event.util.ScreenUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author 薛启超
 * 实现登录界面
 */
public class LoginView
{
    /*声明登录界面
       用到的组件
                  */

  UserService userService = new UserService();
  AdminService adminService = new AdminService();
  private String position = null;
  private String keepPass = "";
  private String auto = "";
  private final JFrame jf = new JFrame("登录界面");
  private final JLabel jLabel1 = new JLabel("账 号:");
  private final JLabel jLabel2 = new JLabel("密 码:");
  private final JComboBox jComboBox = new JComboBox(userService.lookUsernameMysql());
  private final JPasswordField jPasswordField = new JPasswordField(15);
  private final JButton jButton1 = new JButton("登录");
  private final JButton jButton2 = new JButton("注册");
  private final JRadioButton jRadioButton1 = new JRadioButton("用户",false);
  private final JRadioButton jRadioButton2 = new JRadioButton("管理员",false);
  private final JRadioButton jRadioButton3 = new JRadioButton("游客",false);
  private final JRadioButton jRadioButton4 = new JRadioButton("保存密码",false);
  private final JRadioButton jRadioButton5 = new JRadioButton("自动登录",false);
  private final ButtonGroup buttonGroup = new ButtonGroup();
  private final Box box1 = Box.createHorizontalBox();
  private final Box box2 = Box.createHorizontalBox();
  private final Box box3 = Box.createHorizontalBox();
  private final Box box4 = Box.createHorizontalBox();
  private final Box box6 = Box.createHorizontalBox();
  private final Box box5 = Box.createVerticalBox();
  String username = null;

  /**
   *
   * 声明登录按钮以及身份选择的监听器
   */
 private class LoginListener implements ActionListener{

     @Override
     public void actionPerformed(ActionEvent e) {
       if(e.getSource() == jRadioButton1 || e.getSource() == jRadioButton2 || e.getSource() == jRadioButton3){
         position = e.getActionCommand();
       }
       if(e.getSource() == jRadioButton4){
         if(jRadioButton4.isSelected()){
           keepPass = e.getActionCommand();
         }
         else{
           keepPass = "不保存密码";
         }
       }
       if(e.getSource() == jRadioButton5){
         if(jRadioButton5.isSelected()){
           auto = e.getActionCommand();
         }
         else{
           auto = "不自动登录";
         }
       }
       if(e.getSource() == jButton1){
         String password = String.valueOf(jPasswordField.getPassword());
         check(username, password,position);
       }
     }
   }

  /**
   * 声明注册按钮的监听器
   */
  private class RegisterListener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
      jf.dispose();
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
    if("游客".equals(position)){
     new VisitorView().init();
     System.out.println("游客登录成功");
     jf.dispose();
    }
    else if("".equals(name) || "".equals(password)){
      JOptionPane.showMessageDialog(null,"账号或密码不能为空");
    }
    else if(position == null){
      JOptionPane.showMessageDialog(null,"请选择你的身份");
    }

    //用户登录判断
    else if("用户".equals(position)){
      if(userService.isExist(name,password)){
        new UserView().init(name);
        System.out.println("用户登录成功");
        jf.dispose();
        if(keepPass.equals("保存密码")){
          userService.keepPass(1,name);
        }
        if(keepPass.equals("不保存密码")) {
          userService.keepPass(0,name);
        }
        if(auto.equals("自动登录")){
          userService.updateAutoMysql(name);
        }
        if(auto.equals("不自动登录")){
          userService.updateAutoMysql();
        }

      }
      else{
        JOptionPane.showMessageDialog(null,"账号或密码或身份错误");
      }
    }

    //管理员登录判断
    else if("管理员".equals(position)){
      if(adminService.isExist(name,password)){
        new AdminView().init();
        jf.dispose();
        System.out.println("管理员登录成功");
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
    jComboBox.setPreferredSize(new Dimension(250,30));
    jPasswordField.setPreferredSize(new Dimension(250,30));
    box1.add(jLabel1);
    box1.add(Box.createHorizontalStrut(10));
    box1.add(jComboBox);
    box2.add(jLabel2);
    box2.add(Box.createHorizontalStrut(10));
    box2.add(jPasswordField);
    box3.add(jButton1);
    box3.add(Box.createHorizontalStrut(30));
    box3.add(jButton2);
    buttonGroup.add(jRadioButton1);
    buttonGroup.add(jRadioButton2);
    buttonGroup.add(jRadioButton3);
    box4.add(jRadioButton1);
    box4.add(Box.createHorizontalStrut(20));
    box4.add(jRadioButton2);
    box4.add(Box.createHorizontalStrut(20));
    box4.add(jRadioButton3);
    box6.add(jRadioButton4);
    box6.add(Box.createHorizontalStrut(20));
    box6.add(jRadioButton5);
    box5.add(Box.createVerticalStrut(20));
    box5.add(box1);
    box5.add(Box.createVerticalStrut(20));
    box5.add(box2);
    box5.add(Box.createVerticalStrut(20));
    box5.add(box3);
    box5.add(Box.createVerticalStrut(20));
    box5.add(box6);
    box5.add(Box.createVerticalStrut(20));
    box5.add(box4);
    jf.add(Box.createHorizontalStrut(100),BorderLayout.EAST);
    jf.add(Box.createHorizontalStrut(100),BorderLayout.WEST);
    jf.add(Box.createVerticalStrut(50),BorderLayout.NORTH);
    jf.add(Box.createVerticalStrut(50),BorderLayout.SOUTH);

    jf.add(box5);
    jf.setBounds((ScreenUtil.getScreenWidth() - 900)/2 + 250, (ScreenUtil.getScreenHeight() - 450)/2 , 900, 700);
    jf.pack();
    jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    jComboBox.addItemListener(e ->{
      username = (String) jComboBox.getSelectedItem();
      if(userService.keepPasswordMysql(username) == 1){
        jPasswordField.setText(userService.lookPasswordMysql(username));
        jRadioButton4.setSelected(true);
      }
      else{
        jPasswordField.setText("");
        jRadioButton4.setSelected(false);
      }
      if(userService.lookAutoMysql(username) == 1){
        jRadioButton5.setSelected(true);
      }
      else{
        jRadioButton5.setSelected(false);
      }

    });


    jComboBox.setEditable(true);
    jComboBox.setSelectedIndex(-1);


    jf.setResizable(false);
    jf.setVisible(true);


    jButton1.addActionListener(new LoginListener());
    jButton2.addActionListener(new RegisterListener());
    jRadioButton1.addActionListener(new LoginListener());
    jRadioButton2.addActionListener(new LoginListener());
    jRadioButton3.addActionListener(new LoginListener());
    jRadioButton4.addActionListener(new LoginListener());
    jRadioButton5.addActionListener(new LoginListener());

  }


    public static void main(String[] args) {
    UserService userService1 = new UserService();
    if(userService1.lookautoMysql() != null){
      new UserView().init(userService1.lookautoMysql());
    }else{
      new LoginView().init();
    }

  }

}
