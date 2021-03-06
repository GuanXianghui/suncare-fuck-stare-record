<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="headerWithOutCheckLogin.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
    <title>干瞪眼战绩系统</title>
    <link rel="stylesheet" href="css/reset.css" type="text/css" media="screen"/>
    <link rel="stylesheet" href="css/style.css" type="text/css" media="screen"/>
    <link rel="stylesheet" href="css/invalid.css" type="text/css" media="screen"/>
    <script type="text/javascript" src="scripts/jquery-1.3.2.min.js"></script>
    <script type="text/javascript" src="scripts/simpla.jquery.configuration.js"></script>
    <script type="text/javascript" src="scripts/facebox.js"></script>
    <script type="text/javascript" src="scripts/jquery.wysiwyg.js"></script>
    <script type="text/javascript" src="scripts/jquery.datePicker.js"></script>
    <script type="text/javascript" src="scripts/jquery.date.js"></script>
    <script type="text/javascript" src="scripts/base.js"></script>
    <script type="text/javascript" src="scripts/createFetch.js"></script>
</head>
<body>
<div id="body-wrapper">
<div id="sidebar">
    <div id="sidebar-wrapper">
        <h1 id="sidebar-title"><a href="#">干瞪眼战绩系统</a></h1>
        <img id="logo" src="images/suncare-files-logo.png" alt="Simpla Admin logo"/>

        <div id="profile-links"> Hello, 欢迎进入干瞪眼战绩系统！</div>
        <ul id="main-nav">
            <li><a href="#" class="nav-top-item current"> 干瞪眼 </a>
                <ul>
                    <li><a href="<%=baseUrl%>newestFetch.jsp">当前批次</a></li>
                    <li><a href="<%=baseUrl%>createFetch.jsp" class="current">创建批次</a></li>
                    <li><a href="<%=baseUrl%>createRecord.jsp">记录战绩</a></li>
                    <li><a href="<%=baseUrl%>viewRecords.jsp">查看战绩</a></li>
                </ul>
            </li>
        </ul>
    </div>
</div>
<div id="main-content">
<div class="content-box">
<div class="content-box-header">
    <h3>创建批次</h3>
    <ul class="content-box-tabs">
        <li><a href="#tab1" class="default-tab">Forms</a></li>
    </ul>
    <div class="clear"></div>
</div>
<div class="content-box-content">
<div class="tab-content default-tab" id="tab1">
    <div id="message_id" class="notification information png_bg" style="display: none;">
        <a href="#" class="close">
            <img src="images/icons/cross_grey_small.png" title="关闭" alt="关闭"/>
        </a>
        <div id="message_id_content"> 提示信息！ </div>
    </div>
    <form>
        <fieldset>
            <p>
                <span>最大倍数</span>&nbsp;&nbsp;
                <input class="text-input small-input" type="text" id="maxTimes" value="2"/><br>
                <span>每张牌值</span>&nbsp;&nbsp;
                <input class="text-input small-input" type="text" id="eachMoney" value="0.5"/>元<br>
                <span>手持牌数</span>&nbsp;&nbsp;
                <input class="text-input small-input" type="text" id="pieces" value="5"/><br>
                <span>玩家姓名</span>&nbsp;&nbsp;
                <input class="text-input medium-input" type="text" id="users" value=""/><br>
                <input class="button" type="button" onclick="createFetch();" value="创建" />
            </p>
        </fieldset>
        <div class="clear"></div>
    </form>
</div>
</div>
</div>
<div id="footer">
    <small>
        &#169; Copyright 2014 Suncare | Powered by 关向辉
    </small>
</div>
</div>
</div>
</body>
</html>
