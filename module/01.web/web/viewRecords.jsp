<%@ page import="java.util.List" %>
<%@ page import="com.gxx.stare.entities.Fetch" %>
<%@ page import="com.gxx.stare.dao.FetchDao" %>
<%@ page import="com.gxx.stare.dao.RecordDao" %>
<%@ page import="com.gxx.stare.entities.Record" %>
<%@ page import="com.gxx.stare.utils.DateUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="headerWithOutCheckLogin.jsp" %>
<%
    //批次id
    String fetchId = StringUtils.trimToEmpty(request.getParameter("fetchId"));
    //批次
    Fetch fetch;
    //操作类型
    try {
        fetch = FetchDao.getFetchById(Integer.parseInt(fetchId));
    } catch (Exception e) {
        fetch = null;
    }

    //当前页数
    int pageNum = 0;
    //总页数
    int pageCount = 0;
    //是否为空
    boolean isEmpty = false;
    //战绩记录列表
    List<Record> recordList = null;

    if(fetch != null){
        //当前页数
        String pageStr = StringUtils.trimToEmpty(request.getParameter("pageNum"));
        try {
            pageNum = Integer.parseInt(pageStr);
        } catch (Exception e) {
            pageNum = 1;
        }

        //战绩记录列表每页大小
        int pageSize = Integer.parseInt(PropertyUtil.getInstance().getProperty(BaseInterface.RECORD_PAGE_SIZE));
        //战绩记录总数
        int count = RecordDao.countRecordsByFetchId(Integer.parseInt(fetchId));
        //是否为空
        isEmpty = count == 0;
        //总页数
        pageCount = (count - 1) / pageSize + 1;
        //删除最后一条，可能会少掉一页
        if(pageNum > pageCount){
            pageNum = pageCount;
        }
        //战绩记录列表
        recordList = RecordDao.queryRecordsByFetchId(Integer.parseInt(fetchId), pageNum);
    }

    List<Fetch> fetchList = FetchDao.queryAllFetches();
%>
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
    <script type="text/javascript" src="scripts/viewRecords.js"></script>
    <script type="text/javascript">
        //当前页数
        var pageNum = <%=pageNum%>;
    </script>
    <style type="text/css">
        th{
            text-align: center;
        }
        td{
            text-align: center;
        }
    </style>
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
                        <li><a href="<%=baseUrl%>createFetch.jsp">创建批次</a></li>
                        <li><a href="<%=baseUrl%>createRecord.jsp">记录战绩</a></li>
                        <li><a href="<%=baseUrl%>viewRecords.jsp" class="current">查看战绩</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
    <div id="main-content">
        <form action="<%=baseUrl%>viewRecords.jsp" name="viewRecordsForm" method="post" style="display: none;">
            <input type="hidden" name="fetchId" id="viewRecordsFetchId" value="<%=fetchId%>">
            <input type="hidden" name="pageNum" id="pageNum" value="1">
        </form>
        <form style="text-align: center;">
            <span>批次号</span>&nbsp;&nbsp;
            <select class="small-input" id="fetchId">
                <%
                    for(Fetch tempFetch : fetchList){
                %>
                <option value="<%=tempFetch.getId()%>"<%=fetch!=null&&fetch.getId()==tempFetch.getId()?" SELECTED":""%>
                        ><%="F_" + tempFetch.getId() + "_" + tempFetch.getDate() + "_" + tempFetch.getTime()%></option>
                <%
                    }
                %>
            </select>
            <input class="button" type="button" onclick="queryRecords();" value="查询"/>
            <br/>
            <br/>
        </form>
        <%
            if(fetch != null){
        %>
        <div class="content-box">
            <div class="content-box-header">
                <h3>批次信息</h3>
                <ul class="content-box-tabs">
                    <li><a href="#tab1" class="default-tab">Table</a></li>
                </ul>
                <div class="clear"></div>
            </div>
            <div class="content-box-content">
                <div class="tab-content default-tab" id="tab1">
                    <form>
                        <span>批次号码</span>&nbsp;&nbsp;
                        <input class="text-input" type="text" id="maxTimes" value="<%="F" + "_" + fetch.getId() + "_" + fetch.getDate() + "_" + fetch.getTime()%>"/>
                        <span>开始日期</span>&nbsp;&nbsp;
                        <input class="text-input" type="text" value="<%=DateUtil.getLongDate(DateUtil.getDate(fetch.getDate()))%>"/>
                        <span>开始时间</span>&nbsp;&nbsp;
                        <input class="text-input" type="text" value="<%=DateUtil.getLongTime(DateUtil.getDateTime(fetch.getDate(), fetch.getTime()))%>"/>
                        <span>最大倍数</span>&nbsp;&nbsp;
                        <input class="text-input" type="text"value="<%=fetch.getMaxTimes()%>"/>
                        <span>每张牌值</span>&nbsp;&nbsp;
                        <input class="text-input" type="text" id="eachMoney" value="<%=fetch.getEachMoney()%>"/>元
                        <span>手持牌数</span>&nbsp;&nbsp;
                        <input class="text-input" type="text" id="pieces" value="<%=fetch.getPieces()%>"/>
                        <span>玩家姓名</span>&nbsp;&nbsp;
                        <input class="text-input medium-input" type="text" id="users" value="<%=fetch.getUsers()%>"/>
                    </form>
                </div>
            </div>
        </div>
        <div class="content-box">
            <div class="content-box-header">
                <h3>战绩统计</h3>
                <ul class="content-box-tabs">
                    <li><a href="#tab1" class="default-tab">Table</a></li>
                </ul>
                <div class="clear"></div>
            </div>
            <div class="content-box-content">
                <div class="tab-content default-tab" id="tab2">
                    <table>
                        <thead>
                        <tr>
                            <th>玩家姓名</th>
                            <th>赢的次数</th>
                            <th>输的次数</th>
                            <th>战绩统计</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%=BaseUtil.calculateRecordResult(fetchId)%>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div class="content-box">
            <div class="content-box-header">
                <h3>战绩记录列表</h3>
                <ul class="content-box-tabs">
                    <li><a href="#tab1" class="default-tab">Table</a></li>
                </ul>
                <div class="clear"></div>
            </div>
            <div class="content-box-content">
                <div class="tab-content default-tab" id="tab3">
                    <div id="message_id" class="notification information png_bg" style="display: none;">
                        <a href="#" class="close">
                            <img src="images/icons/cross_grey_small.png" title="关闭" alt="关闭"/>
                        </a>

                        <div id="message_id_content"> 提示信息！</div>
                    </div>
                    <table>
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>日期</th>
                            <th>时间</th>
                            <th>先出姓名</th>
                            <th>炸弹翻倍</th>
                            <th>赢家姓名</th>
                            <th>赢金额</th>
                            <th>输家明细</th>
                            <th>查看详情</th>
                        </tr>
                        </thead>
                        <tfoot>
                        <tr>
                            <td colspan="9">
                                <div class="pagination">
                                    <a href="javascript: jump2page(1)" title="首页">&laquo; 首页</a>
                                    <%
                                        if(pageNum > 1){
                                    %>
                                    <a href="javascript: jump2page(<%=pageNum-1%>)" title="上一页">&laquo; 上一页</a>
                                    <%
                                        }
                                    %>
                                    <%
                                        //显示前2页，本页，后2页
                                        for(int i=pageNum-2;i<pageNum+3;i++){
                                            if(i >= 1 && i <= pageCount){
                                    %>
                                    <a href="javascript: jump2page(<%=i%>)" class="number<%=(i==pageNum)?" current":""%>" title="<%=i%>"><%=i%></a>
                                    <%
                                            }
                                        }
                                    %>
                                    <%
                                        if(pageNum < pageCount){
                                    %>
                                    <a href="javascript: jump2page(<%=pageNum+1%>)" title="下一页">下一页 &raquo;</a>
                                    <%
                                        }
                                    %>
                                    <a href="javascript: jump2page(<%=pageCount%>)" title="尾页">尾页 &raquo;</a>
                                </div>
                                <div class="clear"></div>
                            </td>
                        </tr>
                        </tfoot>
                        <tbody>
                        <%
                            //判是否为空
                            if (isEmpty) {
                        %>
                        <tr>
                            <td colspan="9" style="text-align: center;">
                                没找到符合条件的战绩记录
                            </td>
                        </tr>
                        <%
                        } else {//非空
                            for (Record tempRecord : recordList) {
                                String detail = tempRecord.getDetail();
                                String[] detailArray = detail.split("\\|");
                                String detailDesc = StringUtils.EMPTY;
                                for(String tempDetail : detailArray){
                                    if(StringUtils.isNotBlank(detailDesc)){
                                        detailDesc += ";";
                                    }
                                    detailDesc += tempDetail.substring(0, tempDetail.indexOf(",")) + ":-";
                                    tempDetail = tempDetail.substring(tempDetail.indexOf("=")+1);
                                    detailDesc += tempDetail;
                                }
                                String alertDetail = StringUtils.EMPTY;
                                for(String tempDetail : detailArray){
                                    if(StringUtils.isNotBlank(alertDetail)){
                                        alertDetail += "|";
                                    }
                                    String[] tempDetailArray = tempDetail.split(",");
                                    alertDetail += tempDetailArray[0] + ":" + tempDetailArray[2];
                                }
                        %>
                        <tr>
                            <td><%=tempRecord.getId()%></td>
                            <td><%=tempRecord.getDate()%></td>
                            <td><%=tempRecord.getTime()%></td>
                            <td><%=tempRecord.getFirst()%></td>
                            <td><%=tempRecord.getTimes()%>倍</td>
                            <td><%=tempRecord.getWinner()%></td>
                            <td><%=tempRecord.getWinMoney()%>元</td>
                            <td><%=detailDesc%></td>
                            <td><input class="button" type="button" onclick="alert(replaceAll('<%=alertDetail%>', '|','\n'))" value="查看详情" /></td>
                        </tr>
                        <%
                                }
                            }
                        %>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <%
            }
        %>
        <div id="footer">
            <small>
                &#169; Copyright 2014 Suncare | Powered by 关向辉
            </small>
        </div>
    </div>
</div>
</body>
</html>
