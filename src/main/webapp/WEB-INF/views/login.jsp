<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>登录界面</title>
    <!-- Bootstrap -->

    <!--font-awesome 核心我CSS 文件-->
    <link href="//cdn.bootcss.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">

    <link href="/statics/plugins/bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet">
    <script src="/statics/js/jquery1.12.4.min.js"></script>
    <script src="/statics/plugins/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <style type="text/css">
        body {
            background:url(/statics/image/timg.jpg) no-repeat;background-size:cover;font-size: 16px;
        }
        .form{background: rgba(255,255,255,0.2);width:400px;margin:200px auto;}
        .fa{display: inline-block;top: 27px;left: 6px;position: relative;color: #ccc;}
        input[type="text"],input[type="password"]{padding-left:26px;}
        .checkbox{padding-left:21px;}
    </style>
</head>
<body>

<div class="container"  >
    <div class="form row">
        <form class="form-horizontal col-sm-offset-3 col-md-offset-3" id="login_form" method="post" action="/dologin" onsubmit="checkUser()">
            <h3 class="form-title">Login to your account</h3>
            <div class="col-sm-9 col-md-9">
                <div class="form-group">
                    <i class="fa fa-user fa-lg"></i>
                    <input class="form-control " type="text" placeholder="用户名称" name="username" id="username" autofocus="autofocus" maxlength="20" required/>
                </div>
                <div class="form-group">
                    <i class="fa fa-lock fa-lg"></i>
                    <input class="form-control " type="password" placeholder="密码" name="password" id="password" <%--maxlength="8"--%> required/>
                </div>
                <div class="form-group">
                    <label class="checkbox">
                        <input type="checkbox" name="remember" value="1"/> 记住密码
                    </label>
                    <hr />
                    <a href="javascript:;" id="register_btn" class="" onclick="register()">注册</a>
                </div>
                <div class="form-group">
                    <input type="submit" class="btn btn-success pull-right" value="登录"/>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
<script>
    $(function() {
       /* $("#login_form").validate({
            rules: {
                username: "required",
                password: {
                    required: true,
                    minlength: 5
                },
            },
            messages: {
                username: "请输入姓名",
                password: {
                    required: "请输入密码",
                    minlength: "aaaa"
                },
            }
        });*/

    });
    function checkUser() {
        if(""==$('#username').val().trim()){
            return false;
        }
        ////向模态框中传值
        //$('#username').val(stuno);
        //document.getElementById("formid").submit();
        // document.getElementById("form1").submit();
    }
    //注册页面
    function register() {
        var usernamenode=$('#username');
        alert($('#username').val().trim());
        console.log(usernamenode);
        window.location.href = "/register";

        //window.parent.location.href=地址
        // 这种方式可以跳出框架转到其它界面。例如说点击“注销登录”返回到登录界面。
    }
</script>
</html>
