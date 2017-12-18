<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>登录界面</title>
    <!-- Bootstrap -->
    <link href="/statics/bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet">
    <script src="/statics/js/jquery1.12.4.min.js"></script>
    <script src="/statics/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container"  >
    <form id="form1" class="form-signin" method="post" action="/dologin" onsubmit="checkUser()" >
        <h2 class="form-signin-heading">Please sign in</h2>

        <label for="username" class="sr-only">用户名称</label>
        <input type="text" class="form-control" style="width: 100%" name="username" id="username" placeholder="用户名称" required autofocus>
        <label for="password" class="sr-only">密码</label>
        <input type="password" class="form-control" style="width: 100%" name="password" id="password" placeholder="密码" required>

        <div class="checkbox">
            <label>
                <input type="checkbox" value="remember-me"> 记住密码
            </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">提交</button>
        <button class="btn btn-lg btn-primary btn-block" type="button" onclick="register()">注册</button>
    </form>
</div>
</body>
<script>
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
