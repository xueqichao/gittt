package com.xueqichao.HealthServiceSystem.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

public class ChangeEncodeUtil
{
    public static void changeEncode(HttpServletRequest request, HttpServletResponse response){
        try {
            request.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setContentType("text/html;charset=utf-8");
    }

}
