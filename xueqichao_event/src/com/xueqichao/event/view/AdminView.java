package com.xueqichao.event.view;

import com.xueqichao.event.util.ScreenUtil;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author 薛启超
 */
public class AdminView
{
    private final JFrame jFrame = new JFrame("管理员,欢迎您");

    /**
     * 菜单栏设计
     */
    private final JMenuBar jMenuBar = new JMenuBar();
    private final JMenu jMenu = new JMenu("设置");
    private final JMenuItem jMenuItem1 = new JMenuItem("切换账号");
    private final JMenuItem jMenuItem2 = new JMenuItem("退出程序");

    private final JSplitPane jSplitPane = new JSplitPane();

    /**
     * 左边树的设计
     */

    DefaultMutableTreeNode root = new DefaultMutableTreeNode("系统管理");
    DefaultMutableTreeNode treeNode1 = new DefaultMutableTreeNode("景点管理");
    DefaultMutableTreeNode treeNode2 = new DefaultMutableTreeNode("门票管理");
    DefaultMutableTreeNode treeNode3 = new DefaultMutableTreeNode("用户管理");
    DefaultMutableTreeNode treeNode4 = new DefaultMutableTreeNode("景评管理");


    /**
     * 切换账号和退出程序按钮的监听器
     */
    private class MyListener implements ActionListener{

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

    /**
     * 组装视图
     */
    public void init(){

        jFrame.setBounds((ScreenUtil.getScreenWidth() - 1000)/2,(ScreenUtil.getScreenHeight() - 600)/2,1000,600);

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

        JTree jTree = new JTree(root);

        jSplitPane.setLeftComponent(jTree);


        /**
         * 为树添加点击事件监听器
         */
        jTree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                //得到当前选中的节点对象
                Object lastPathComponent = e.getNewLeadSelectionPath().getLastPathComponent();

                if(treeNode1.equals(lastPathComponent)){
                    jSplitPane.setRightComponent(new AdminTree1View());
                }
                else if(treeNode2.equals(lastPathComponent)){
                    jSplitPane.setRightComponent(new AdminTree2View());
                }
                else if(treeNode3.equals(lastPathComponent)){
                    jSplitPane.setRightComponent(new AdminTree3View());
                }
                else if(treeNode4.equals(lastPathComponent)){
                    jSplitPane.setRightComponent(new JLabel("这里进行景评管理。。。"));
                }
            }
        });


        jMenuItem1.addActionListener(new MyListener());
        jMenuItem2.addActionListener(new MyListener());

        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }






















}
