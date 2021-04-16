package com.xueqichao.event.view;

import com.xueqichao.event.util.ScreenUtil;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author lenovo
 */
public class UserView
{

    String userName;

    private final JFrame jFrame = new JFrame();

    private final JMenuBar jMenuBar = new JMenuBar();
    private final JMenu jMenu = new JMenu("设置");
    private final JMenuItem jMenuItem1 = new JMenuItem("切换账号");
    private final JMenuItem jMenuItem2 = new JMenuItem("退出程序");

    private final JSplitPane jSplitPane = new JSplitPane();

    DefaultMutableTreeNode root = new DefaultMutableTreeNode("用户功能");
    DefaultMutableTreeNode treeNode1 = new DefaultMutableTreeNode("景点查询");
    DefaultMutableTreeNode treeNode2 = new DefaultMutableTreeNode("门票查询");
    DefaultMutableTreeNode treeNode3 = new DefaultMutableTreeNode("景点评论");
    DefaultMutableTreeNode treeNode4 = new DefaultMutableTreeNode("购票记录");
    DefaultMutableTreeNode treeNode5 = new DefaultMutableTreeNode("个人信息");
    DefaultMutableTreeNode treeNode6 = new DefaultMutableTreeNode("进行投诉");
    DefaultMutableTreeNode treeNode7 = new DefaultMutableTreeNode("收到回复");


    public void init(String name){

        jFrame.setBounds((ScreenUtil.getScreenWidth() - 1000)/2,(ScreenUtil.getScreenHeight() - 600)/2,1000,600);

        userName = name;

        jMenu.add(jMenuItem1);
        jMenu.add(jMenuItem2);
        jMenuBar.add(jMenu);
        jFrame.setJMenuBar(jMenuBar);

        jSplitPane.setContinuousLayout(true);
        jSplitPane.setDividerLocation(150);
        jSplitPane.setDividerSize(5);

        jFrame.add(jSplitPane);

        root.add(treeNode1);
        root.add(treeNode2);
        root.add(treeNode3);
        root.add(treeNode4);
        root.add(treeNode5);
        root.add(treeNode6);
        root.add(treeNode7);

        JTree jTree = new JTree(root);

        jSplitPane.setLeftComponent(jTree);

        jTree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                //得到当前选中的节点对象
                Object lastPathComponent = e.getNewLeadSelectionPath().getLastPathComponent();
                if(treeNode1.equals(lastPathComponent)){
                    jSplitPane.setRightComponent(new UserTree1View());
                }
                else if(treeNode2.equals(lastPathComponent)){
                    jSplitPane.setRightComponent(new UserTree2View(name));
                }
                else if(treeNode3.equals(lastPathComponent)){
                    jSplitPane.setRightComponent(new UserTree3View(name));
                }
                else if(treeNode4.equals(lastPathComponent)){
                    jSplitPane.setRightComponent(new UserTree4View(name));
                }
                else if(treeNode5.equals(lastPathComponent)){
                    jSplitPane.setRightComponent(new UserTree5View(name));
                }
                else if(treeNode6.equals(lastPathComponent)){
                    jSplitPane.setRightComponent(new UserTree6View(name));
                }
                else if(treeNode7.equals(lastPathComponent)){
                    jSplitPane.setRightComponent(new UserTree7View(name));
                }
            }
        });

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
