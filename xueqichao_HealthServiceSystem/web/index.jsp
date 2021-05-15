<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
    <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="ueditor/lang/zh-cn/zh-cn.js"></script>
  </head>
  <body>
  <textarea cols="30" rows="20" name="content" id="editor"></textarea>

  <script type="text/javascript">
    window.UEDITOR_HOME_URL = "/xueqichao_HealthServiceSystem/web/ueditor";
    var ue = UE.getEditor('editor');
  </script>
  </body>
</html>
