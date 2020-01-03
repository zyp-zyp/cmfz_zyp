<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>演示：清爽简洁的登录页面</title>

    <style>

        #div1 {
            height: 130px;
        }
        * { margin: 0; padding: 0; }
        html { height: 100%; }
        body { height: 100%; background: #fff url(images/backgroud.png) 50% 50% no-repeat; background-size: cover;}
        .dowebok { position: absolute; left: 50%; top: 50%; width: 430px; height: 550px; margin: -300px 0 0 -215px; border: 1px solid #fff; border-radius: 20px; overflow: hidden;}
        .logo { width: 104px; height: 104px; margin: 17px auto 39px; background: url(images/login.png) 0 0 no-repeat; }
        .form-item { position: relative; width: 360px; margin: 0 auto; padding-bottom: 30px;}
        .form-item input { width: 288px; height: 48px; padding-left: 70px; border: 1px solid #fff; border-radius: 25px; font-size: 18px; color: #fff; background-color: transparent; outline: none;}
        .form-item button { width: 360px; height: 50px; border: 0; border-radius: 25px; font-size: 18px; color: #1f6f4a; outline: none; cursor: pointer; background-color: #fff; }
        #username { background: url(images/emil.png) 20px 14px no-repeat; }
        #password { background: url(images/password.png) 23px 11px no-repeat; }
        #code { background: url(images/password.png) 23px 11px no-repeat; }
        .tip { display: none; position: absolute; left: 20px; top: 52px; font-size: 14px; color: #f50; }
        .reg-bar { width: 360px; margin: 0px auto 0; font-size: 14px; overflow: hidden;}
        .reg-bar a { color: #fff; text-decoration: none; }
        .reg-bar a:hover { text-decoration: underline; }
        .reg-bar .reg { float: left; }
        .reg-bar .forget { float: right; }
        .dowebok ::-webkit-input-placeholder { font-size: 18px; line-height: 1.4; color: #fff;}
        .dowebok :-moz-placeholder { font-size: 18px; line-height: 1.4; color: #fff;}
        .dowebok ::-moz-placeholder { font-size: 18px; line-height: 1.4; color: #fff;}
        .dowebok :-ms-input-placeholder { font-size: 18px; line-height: 1.4; color: #fff;}

        @media screen and (max-width: 500px) {
            * { box-sizing: border-box; }
            .dowebok { position: static; width: auto; height: auto; margin: 0 30px; border: 0; border-radius: 0; }
            .logo { margin: 50px auto; }
            .form-item { width: auto; }
            .form-item input, .form-item button, .reg-bar { width: 100%; }
        }
        #code{
            width: 160px;
        }
    </style>

    <script src="js/jquery.min.js"></script>
    <script>

        $(function () {

            /*
            * 登录
            * */
            $('#submit').click(function () {
                var username = $("#username").val();
                var password = $("#password").val();
                var code = $("#code").val();
                $.ajax({
                    url:"${pageContext.request.contextPath}/admin/login",
                    datatype:"json",
                    data:{"username":username,"password":password,"code":code},
                    success:function (tate) {
                        if (tate!=null){
                            $("#useryz").html("")
                            $("#useryz").append(
                                "<font color='red'>"+tate+"<font>"
                            )
                        }if(tate==""){
                            location.href="${pageContext.request.contextPath}/admin/adminShow.jsp"
                        }
                    }
                })
            })


            /*
            * 换验证码
            * */
            $("#codeImage").click(function () {
                $("#codeImage").prop("src","${pageContext.request.contextPath}/code/getCode?time="+new Date().getTime())
            })


            /*
            * 表单验证
            *
            * */
        })
    </script>

</head>
<body>
    <div class="dowebok">
        <form method="post" class="form-horizontal" id="login" onsubmit="return false">
            <div class="logo" id="div1"></div>

            <div class="form-item">
                <h3  id="useryz"></h3>
                <input id="username" name="username" type="text" autocomplete="off" placeholder="用户名" required="required" min="0" />

            </div>
            <div class="form-item">
                <input id="password" name="password" type="password" autocomplete="off" placeholder="登录密码" required="required" min="0"/>
                <p class="tip">邮箱或密码不正确</p>
            </div>
            <div class="form-item">
                <input id="code" name="code" type="text" autocomplete="off" placeholder="验证码" required="required" min="0"/>&nbsp;&nbsp;&nbsp;&nbsp;
                <img id="codeImage" style="height: 45px" class="captchaImage"
                     src="${pageContext.request.contextPath}/code/getCode">
            </div>
            <div class="form-item"><button id="submit" style="background: #5bc0de">登 录</button></div>
        </form>
        <div class="reg-bar">
            <a class="reg" href="#" target="_blank">立即注册</a>
            <a class="forget" href="#" target="_blank">忘记密码</a>
        </div>
    </div>

</body>
</html>