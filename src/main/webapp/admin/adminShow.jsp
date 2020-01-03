<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<c:set var="app" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <%--1.bootstrap 核心 css--%>
    <link rel="stylesheet" href="${app}/admin/boot/css/bootstrap.min.css">
    <%--2.jqgrid相关css--%>
    <link rel="stylesheet" href="${app}/admin/jqgrid/css/trirand/ui.jqgrid-bootstrap.css">
    <%--3.jquery核心js--%>
    <script src="${app}/admin/boot/js/jquery-2.2.1.min.js"></script>
    <%--4.bootstrap相关js--%>
    <script src="${app}/admin/boot/js/bootstrap.min.js"></script>
    <%--5.jqgrid相关js--%>
    <script src="${app}/admin/jqgrid/js/trirand/jquery.jqGrid.min.js"></script>
    <script src="${app}/admin/jqgrid/js/trirand/i18n/grid.locale-cn.js"></script>
    <%--上传--%>
    <script src="${app}/admin/boot/js/ajaxfileupload.js"></script>
    <%--ajax--%>
    <script src="${app}/admin\boot\js\ajaxfileupload.js"></script>
    <%--kindeditor相关--%>
    <script charset="utf-8" src="${app}/admin/kindeditor/kindeditor-all-min.js"></script>
    <script charset="utf-8" src="${app}/admin/kindeditor/lang/zh-CN.js"></script>

    <script src="${pageContext.request.contextPath}/admin/echarts/echarts.min.js"></script>
    <script src="${pageContext.request.contextPath}/admin/echarts/china.js"></script>
    <script src="https://cdn.goeasy.io/goeasy-1.0.3.js"></script>

    <title>Document</title>
</head>
<body>
<%--导航条--%>
<nav class="navbar navbar-default ">
    <%--全屏--%>
    <div class="container-fluid">
        <%--设置头部--%>
        <div class="navbar-header">
            <%--三条杠--%>                                                                     <%--相当指于与哪一个展示折叠内容连接的--%>
            <button type="button" class="navbar-toggle collapse" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">持明法州管理系统</a>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#">欢迎   ${sessionScope.login.username}</a></li>
                <li><a href="#">推出登录 <span class="glyphicon glyphicon-log-out"></span></a></li>
            </ul>
        </div>
    </div>
</nav>


<%--设置栅格栏--%>
<div class="container-fluid">
    <div class="row">
        <%--
            <%--手风琴--%>

        <div class="col-sm-2 ">
            <%--面板组--%>   <%--管理那个面板--%>
            <div class="panel-group" id="parent">

                <%--用户--%>

                <div class="panel panel-default" style="text-align: center">
                    <%--头部--%>
                    <div class="panel-heading">
                        <h3 class="panel-title">
                            <%--折叠或者打开的是哪一个面板--%>
                            <a href="#yonghu" data-toggle="collapse" data-parent="#parent">
                                <center>用户管理</center>
                            </a>
                        </h3>
                    </div>

                    <%--主体--%>
                    <div id="yonghu" class="panel-collapse collapse">
                        <div class="panel-body">
                            <%--
                               javascript:void(0)  阻止页面提交
                           --%>
                            <center><a class="btn btn-danger" href="javascript:$('#myContent').load('../admin/artice.jsp')">用户列表</a></center>

                        </div>
                        <div class="panel-body">
                            <center><a class="btn btn-danger" href="javascript:$('#myContent').load('../admin/addressMap.jsp')">地图分布图</a></center>
                        </div>
                    </div>
                </div>


                <%--商品--%>

                <div class="panel panel-default">
                    <%--头部--%>
                    <div class="panel-heading">
                        <h3 class="panel-title">
                            <%--折叠或者打开的是哪一个面板--%>
                            <a href="#shangshi" data-toggle="collapse" data-parent="#parent">
                                <center>上师管理</center>
                            </a>
                        </h3>
                    </div>

                    <%--主题--%>
                    <div id="shangshi" class="panel-collapse collapse">
                        <div class="panel-body">
                            <center><button class="btn btn-danger">上师列表</button></center>
                        </div>
                    </div>
                </div>


                <%--订单--%>

                <div class="panel panel-default">
                    <%--头部--%>
                    <div class="panel-heading">
                        <h3 class="panel-title">
                            <%--折叠或者打开的是哪一个面板--%>
                            <a href="#wenzhang" data-toggle="collapse" data-parent="#parent">
                                <center>文章管理</center>
                            </a>
                        </h3>
                    </div>

                    <%--主题--%>
                    <div id="wenzhang" class="panel-collapse collapse">
                        <div class="panel-body">
                            <center><a class="btn btn-danger" href="javascript:$('#myContent').load('../admin/article.jsp')">文章列表</a></center>
                        </div>
                    </div>
                </div>


                    <%--专辑--%>

                    <div class="panel panel-default">
                        <%--头部--%>
                        <div class="panel-heading">
                            <h3 class="panel-title">
                                <%--折叠或者打开的是哪一个面板--%>
                                <a href="#zhunji" data-toggle="collapse" data-parent="#parent">
                                    <center>专辑管理</center>
                                </a>
                            </h3>
                        </div>

                        <%--主题--%>
                        <div id="zhunji" class="panel-collapse collapse">
                            <div class="panel-body">
                                <center><a class="btn btn-danger" href="javascript:$('#myContent').load('../admin/album.jsp')">专辑列表</a></center>
                            </div>
                        </div>
                    </div>


                    <%--轮播图--%>

                    <div class="panel panel-default">
                        <%--头部--%>
                        <div class="panel-heading">
                            <h3 class="panel-title">
                                <%--折叠或者打开的是哪一个面板--%>
                                <a href="#lunbotu" data-toggle="collapse" data-parent="#parent">
                                    <center>轮播图管理</center>
                                </a>
                            </h3>
                        </div>

                        <%--主题--%>
                        <div id="lunbotu" class="panel-collapse collapse">
                            <div class="panel-body">
                                <center><a class="btn btn-danger" href="javascript:$('#myContent').load('../admin/slideshow.jsp')">轮播图列表</a></center>
                            </div>

                            <div class="panel-body">
                                <center><a class="btn btn-danger" href="javascript:$('#myContent').load('../admin/7dayBanner.jsp')">近七天上传数</a></center>
                            </div>

                            <div class="panel-body">
                                <center><a class="btn btn-danger" href="javascript:$('#myContent').load('../admin/12MonthBanner.jsp')">近12个月上传数</a></center>
                            </div>
                        </div>
                    </div>
            </div>
        <div>
            <form action="${pageContext.request.contextPath}/poiAdmin/outPoi" method="post">
                <input type="submit" value="poi导出">
            </form>
            <form action="${pageContext.request.contextPath}/easypoibanner/outEasypoi" method="post">
                <input type="submit" value="easypoi导出">
            </form>

        </div>

        </div>

        <%--
            主题
        --%>
        <div class="col-sm-10" id="myContent" >

            <div class="jumbotron" style="margin-bottom: 13px">
                <h3>欢饮来到持名法州后台管理系统</h3>
            </div>



            <%--面板--%>
            <div class="panel panel-default">
                <%--轮播图--%>
                <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                    <!-- Indicators -->
                    <ol class="carousel-indicators">
                        <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                        <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                        <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                    </ol>

                    <!-- Wrapper for slides -->
                    <div class="carousel-inner" >
                        <div class="item active">
                            <img src="${pageContext.request.contextPath}/admin/images/shouye.jpg"  >
                            <div class="carousel-caption">
                                ...
                            </div>
                        </div>
                        <div class="item">
                            <img src="${pageContext.request.contextPath}/admin/images/shouye.jpg" >
                            <div class="carousel-caption">
                                ...
                            </div>
                        </div>
                        <div class="item">
                            <img src="${pageContext.request.contextPath}/admin/images/shouye.jpg" >
                            <div class="carousel-caption">
                                ...
                            </div>
                        </div>
                    </div>

                    <!-- Controls -->
                    <a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
                        <span class="glyphicon glyphicon-chevron-left" ></span>
                    </a>
                    <a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
                        <span class="glyphicon glyphicon-chevron-right" ></span>
                    </a>
                </div>
            </div>
        </div>
    </div>


<%--底部--%>
    <nav class="navbar navbar-default navbar-fixed-bottom" style="line-height: 50px">
        <div class="container">
            <center><font>@百知教育 baizhi@cmfz.com.cn</font></center>
        </div>
    </nav>

</div>
</body>
</html>
