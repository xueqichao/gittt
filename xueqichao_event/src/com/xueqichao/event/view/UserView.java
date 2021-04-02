package com.xueqichao.event.view;

import com.xueqichao.event.util.ScreenUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author lenovo
 */
public class UserView
{

    private final JFrame jFrame = new JFrame();

    private final JMenuBar jMenuBar = new JMenuBar();
    private final JMenu jMenu = new JMenu("设置");
    private final JMenuItem jMenuItem1 = new JMenuItem("切换账号");
    private final JMenuItem jMenuItem2 = new JMenuItem("退出程序");

    private final JSplitPane jSplitPane = new JSplitPane();












    public void init(String name){

        jFrame.setBounds((ScreenUtil.getScreenWidth() - 1000)/2,(ScreenUtil.getScreenHeight() - 600)/2,1000,600);

        jMenu.add(jMenuItem1);
        jMenu.add(jMenuItem2);
        jMenuBar.add(jMenu);
        jFrame.setJMenuBar(jMenuBar);

        jSplitPane.setContinuousLayout(true);
        jSplitPane.setDividerLocation(150);
        jSplitPane.setDividerSize(5);

        jFrame.add(jSplitPane);





        jMenuItem1.addActionListener(new MyListener());
        jMenuItem2.addActionListener(new MyListener());


        jFrame.setTitle(name + "用户，欢迎您！");
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    /**
     * 切换账号和退出程序按钮的监听器
     */


    private class MyListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == jMenuItem1){
                jFrame.dispose();
                new LoginView().init();
            }
            if(e.getSource() == jMenuItem2){
                int a = JOptionPane.showConfirmDialog(jFrame,"确定要退出程序吗？");
                if(a == 0){
                    System.exit(0);
                }

            }

        }
    }


}
