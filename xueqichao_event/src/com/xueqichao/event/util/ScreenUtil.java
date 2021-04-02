package com.xueqichao.event.util;

import java.awt.*;

/**
 *
 * @author 薛启超
 *
 * 这是一个获取屏幕大小的工具类
 */
public class ScreenUtil
{
    static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static int getScreenWidth(){
        return (int) screenSize.getWidth();
    }

    public static int getScreenHeight(){
    return (int) screenSize.getHeight();
    }

}
