<%@ page import="com.gxx.stare.interfaces.BaseInterface"
        %><%@ page import="com.gxx.stare.utils.BaseUtil"
        %><%@ page import="com.gxx.stare.entities.User"
        %><%@ page import="com.gxx.stare.interfaces.OperateLogInterface"
        %><%@ page import="com.gxx.stare.utils.DateUtil"
        %><%@ page import="com.gxx.stare.utils.IPAddressUtil"
        %><%@ page contentType="text/html;charset=UTF-8" language="java"
        %><%
    //如果处于登陆状态
    if(BaseUtil.isLogin(request)){
        User user = (User)request.getSession().getAttribute(BaseInterface.USER_KEY);
        //创建操作日志
        BaseUtil.createOperateLog(user.getName(), OperateLogInterface.TYPE_LOG_OUT, "成功退出",
                DateUtil.getNowDate(), DateUtil.getNowTime(), IPAddressUtil.getIPAddress(request));
    }
    String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/";
    request.getSession().setAttribute(BaseInterface.USER_KEY, null);
    String resp = "{isSuccess:true,message:'退出成功！',isRedirect:true,redirectUrl:'" + baseUrl + "login.jsp'}";
    response.getWriter().write(resp);
%>