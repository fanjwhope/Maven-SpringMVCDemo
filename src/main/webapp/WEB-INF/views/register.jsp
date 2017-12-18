<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>注册页面</title>
    <!-- Bootstrap -->
    <link href="/statics/bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet">
    <script src="/statics/js/jquery1.12.4.min.js"></script>
    <script src="/statics/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

</head>
<body>
<form id="form1" class="form-signin" <%--method="post" action="/doregister" onsubmit="checkUser()" --%>>
    <h2 class="form-signin-heading">注册页面</h2>

    <label for="username" class="sr-only">用户名称</label>
    <input type="text" class="form-control" style="width: 100%" name="username" id="username" placeholder="用户名称" required autofocus>
    <label for="password" class="sr-only">密码</label>
    <input type="password" class="form-control" style="width: 100%" name="password" id="password" placeholder="密码" required>

    <label for="realname" class="sr-only">真实姓名</label>
    <input type="text" class="form-control" style="width: 100%" name="realname" id="realname" placeholder="真实姓名" >

    <button class="btn btn-lg btn-primary btn-block" type="reset">重置</button>
    <button class="btn btn-lg btn-primary btn-block" <%--type="submit"--%> type="button" onclick="checkUser()">注册</button>
    <button class="btn btn-lg btn-primary btn-block"  type="button" onclick="login()">登录页面</button>

    <span id="content" class="text-center"> 你好呀 </span>
</form>
</body>
<script>
    function checkUser(){
        $.ajax({
            cache: true,
            type: "POST",
            url:"/doregister",
            data:$('#form1').serialize(),
            async: false,
            error: function(request) {
                alert("Connection error");
            },
            success: function(data) {
                //取span 标签的值：
                console.log($("#content").html());
                console.log(document.getElementById('content').innerText);
                //为span 标签赋值
                $("#content").html(JSON.stringify(data));
               // document.getElementById('content').innerText=data.msg;
               // alert(JSON.stringify(data))
                if(data.msg){
                   alert(data.user.username+"注册成功!,请登录");
                }else{
                    alert(data.user.username+"用户注册失败！");
                }
            }
        });
    }
    //返回登录页面
    function login() {
        window.location.href = "/login";
    }
</script>
</html>
