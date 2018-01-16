<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>注册页面</title>
    <!-- Bootstrap -->

    <link href="//cdn.bootcss.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="/statics/plugins/bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet">
    <script src="/statics/js/jquery1.12.4.min.js"></script>
    <script src="/statics/plugins/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <style type="text/css">
        body {
            background:url(/statics/image/timg.jpg) no-repeat;background-size:cover;font-size: 16px;
        }
        .form{background: rgba(255,255,255,0.2);width:400px;margin:200px auto;}

      /*  .fa{display: inline-block;top: 27px;left: 6px;position: relative;color: #ccc;}
        input[type="text"],input[type="password"]{padding-left:26px;}*/
    </style>

</head>
<body>
<div class="container">
    <div class="form row">
        <form id="form1" class="form-signin" style="margin-top: 20px" <%--method="post" action="/doregister" onsubmit="checkUser()" --%>>
            <fieldset>
                <center>用户注册</center>
            </fieldset>
            <div class="form-group ">
                <label for="username" class="col-md-3 " style="text-align: right" >用户名称:</label>
                <div class="col-md-9">
                    <input type="text" class="form-control"  name="username" id="username" placeholder="用户名称" required autofocus>
                </div>
            </div>
            <div class="form-group">
                <label for="password" class="col-md-3" style="text-align: right">密码:</label>
                <div class="col-md-9">
                    <input type="password" class="form-control"  name="password" id="password" placeholder="密码" required>
                </div>
            </div>
            <div class="form-group">
                <label for="realname" class="col-md-3" style="text-align: right" >真实姓名:</label>
                <div class="col-md-9">
                    <input type="text" class="form-control" name="realname" id="realname" placeholder="真实姓名" >
                </div>
            </div>

            <div class="form-group" style="text-align: center">
                <input type="reset" class="btn btn-info " value="重置 ">
                <input class="btn btn-success " <%--type="submit"--%> type="button" onclick="checkUser()"  value="注册"/>
            </div>
            <div class="form-group">
                <a href="javascript:;" id="login_btn" class="pull-right"  style="padding-right:20px " onclick="login()">登录</a>
                <span id="content" class="text-center" style="padding-left: 20px"> 你好呀 </span>
            </div>
        </form>
    </div>
</div>

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
