<%@ page import="com.gxx.stare.interfaces.BaseInterface" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="com.gxx.stare.utils.TokenUtil" %>
<%@ page import="com.gxx.stare.utils.PropertyUtil" %>
<%@ page import="com.gxx.stare.utils.BaseUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    //域名链接
    String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/";
    //md5 key
    String md5Key = PropertyUtil.getInstance().getProperty(BaseInterface.MD5_KEY);
    //token串
    String token = TokenUtil.createToken(request);
    //消息
    String message = StringUtils.trimToEmpty((String)request.getAttribute("message"));
%>
<script type="text/javascript">
    //域名链接
    var baseUrl = "<%=baseUrl%>";
    //md5 key
    var md5Key = "<%=md5Key%>";
    //token穿
    var token = "<%=token%>";
    //弹出消息框
    var message = '<%=message%>';
</script>
<!-- 图标 -->
<link rel="shortcut icon" type="image/x-icon" href="images/suncare-file-little-logo.png" />