<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="ch">
<head>
    <title>应用商店后台管理系统</title>
    <script src="https://cdn.bootcss.com/jquery/2.2.4/jquery.min.js"></script>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="../js/layer/layer.js"></script>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
<div class="container">
    <div class="form row">
        <div class="form-horizontal col-md-offset-3" id="login_form">
            <h3 class="form-title">LOGIN</h3>
            <div class="col-md-9">
                <div class="form-group">
                    <i class="fa fa-user fa-lg"></i>
                    <input class="form-control required" type="text" placeholder="Username" id="username" name="username" autofocus="autofocus" maxlength="20"/>
                </div>
                <div class="form-group">
                    <i class="fa fa-lock fa-lg"></i>
                    <input class="form-control required" type="password" placeholder="Password" id="password" name="password" maxlength="8"/>
                </div>
                <div class="form-group">
                    <label class="checkbox">
                        <input type="checkbox" name="remember" value="1"/>记住我
                    </label>
                </div>
                <div class="form-group col-md-offset-9">
                    <button type="submit" class="btn btn-success pull-right" name="submit" onclick="login()">登录</button>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    function login() {
        var username = $("#username").val();
        var password = $("#password").val();
        if(username==null||username==undefined||username==""){
            layer.msg('账号不能为空');
            return;
        }
        if(password==null||password==undefined||password==""){
            layer.msg('密码不能为空');
            return;
        }
        $.ajax({
            url: "/login",
            data: {username:username,password:password},
            type: "POST",
            dataType: "json",
            success: function(data) {
                var code = data.code;
                if(200 == code){
                    window.location.href = "/main";
                }else{
                    layer.msg('登录失败');
                    return;
                }
            }
        });
    }
</script>
</body>
</html>