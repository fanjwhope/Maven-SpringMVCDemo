<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>主页</title>
    <!-- Bootstrap -->
    <!--font-awesome 核心我CSS 文件-->
    <link href="//cdn.bootcss.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="/statics/plugins/bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet">
    <link href="/statics/css/blogAdmin/style.min.css" rel="stylesheet">

    <script src="/statics/js/jquery1.12.4.min.js"></script>
    <script src="/statics/plugins/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

    <style type="text/css">

    </style>
</head>
<body>
<div th:fragment="header-body">
    <div class="topbar">
        <div class="topbar-left">
            <div class="text-center p-t-10" style="margin: 0 auto;">
                <div class="pull-left" style="padding-left: 10px;">
                    <a href="/admin/index">
                        <img src="/admin/images/unicorn.png" width="50" height="50"/>
                    </a>
                </div>
                <div class="pull-left" style="padding-left: 10px;">
                <span style="font-size: 28px; color: #2f353f; line-height: 50px;">DeserveL</span>
            </div>
            </div>
        </div>
        <div class="navbar navbar-default" role="navigation">
            <div class="container">
                <div class="">
                    <div class="pull-left">
                        <button type="button" class="button-menu-mobile open-left">
                            <i class="fa fa-bars"></i>
                        </button>
                        <span class="clearfix"></span>
                    </div>

                    <ul class="nav navbar-nav navbar-right pull-right">
                        <li class="dropdown">
                            <a href="index.html" class="dropdown-toggle profile" data-toggle="dropdown"
                               aria-expanded="true"><img th:src="${commons.gravatar(session.login_user.email)}" alt="user-img" class="img-circle"/> </a>
                            <ul class="dropdown-menu">
                                <!--TODO 查看网址-->
                                <li><a th:href="${commons.site_url()}" target="_blank"><i class="fa fa-eye" aria-hidden="true"></i> 查看网站</a></li>
                                <li><a href="/admin/profile"><i class="fa fa-sun-o"></i> 个人设置</a></li>
                                <li><a href="/logout"><i class="fa fa-sign-out"></i> 注销</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div class="left side-menu">
        <div class="sidebar-inner slimscrollleft">
            <div id="sidebar-menu">
                <ul>
                    <li th:class="${active}=='home'?'active':''">
                        <a href="#" th:class="${active}=='home'?'waves-effect active':'waves-effect'"><i class="fa fa-dashboard" aria-hidden="true"></i><span> 仪表盘 </span></a>
                    </li>
                    <li th:class="${active}=='publish'?'active':''">
                        <a href="#" th:class="${active}=='publish'?'waves-effect active':'waves-effect'"><i class="fa fa-pencil-square-o" aria-hidden="true"></i><span> 发布文章 </span></a>
                    </li>
                    <li th:class="${active}=='article'?'active':''">
                        <a href="#" th:class="${active}=='article'?'waves-effect active':'waves-effect'"><i class="fa fa-list" aria-hidden="true"></i><span> 文章管理 </span></a>
                    </li>

                    <li th:class="${active}=='page'?'active':''">
                        <a href="#" th:class="${active}=='page'?'waves-effect active':'waves-effect'"><i class="fa fa-file-text" aria-hidden="true"></i><span> 页面管理 </span></a>
                    </li>

                    <li th:class="${active}=='attach'?'active':''">
                        <a href="#" th:class="${active}=='attach'?'waves-effect active':'waves-effect'"><i class="fa fa-cloud-upload" aria-hidden="true"></i><span> 文件管理 </span></a>
                    </li>

                    <li class="has_sub">
                        <a href="javascript:void(0)" th:class="${has_sub}=='other'?'waves-effect active subdrop':'waves-effect'"><i class="fa fa-cubes"></i><span> 其他管理 </span><span class="pull-right"><i class="fa fa-plus"></i></span></a>
                        <ul class="list-unstyled">
                            <li th:class="${active}=='comments'?'active':''">
                                <a href="#" th:class="${active}=='comments'?'waves-effect active':'waves-effect'"><i class="fa fa-comments" aria-hidden="true"></i><span> 评论管理 </span></a>
                            </li>
                            <li th:class="${active}=='category'?'active':''">
                                <a href="#" th:class="${active}=='category'?'waves-effect active':'waves-effect'"><i class="fa fa-tags" aria-hidden="true"></i><span> 分类/标签 </span></a>
                            </li>
                            <li th:class="${active}=='template'?'active':''">
                                <a href="#" th:class="${active}=='template'?'waves-effect active':'waves-effect'"><i class="fa fa-hashtag"></i><span> 编辑模板 </span></a>
                            </li>
                        </ul>
                    </li>

                    <li th:class="${active}=='themes'?'active':''">
                        <a href="#" th:class="${active}=='themes'?'waves-effect active':'waves-effect'"><i class="fa fa-diamond" aria-hidden="true"></i><span> 主题设置 </span></a>
                    </li>

                    <li th:class="${active}=='setting'?'active':''">
                        <a href="#" th:class="${active}=='setting'?'waves-effect active':'waves-effect'"><i class="fa fa-gear" aria-hidden="true"></i><span> 系统设置 </span></a>
                    </li>
                </ul>
                <div class="clearfix"></div>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
</div>

</body>
</html>
