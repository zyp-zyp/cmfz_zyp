<%@page contentType="text/html; utf-8" pageEncoding="UTF-8" isELIgnored="false" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script src="${pageContext.request.contextPath}/admin/boot/js/jquery-2.2.1.min.js"></script>
    <script type="text/javascript" src="https://cdn.goeasy.io/goeasy-1.0.3.js"></script>
    <script src="${pageContext.request.contextPath}/admin/kindeditor/kindeditor-all-min.js"></script>
    <script>
        $(function () {

            KindEditor.ready(function (K) {
                window.editor = K.create('#editor_id', {
                    items: [
                        'emoticons', 'subscript', 'superscript', 'forecolor', 'fontname', 'fontsize', 'hilitecolor'
                    ]
                });
            });
            var goEasy = new GoEasy({
                host: 'hangzhou.goeasy.io', //应用所在的区域地址: 【hangzhou.goeasy.io |singapore.goeasy.io】
                appkey: "BC-caac2a998f3044c1a91cb1ce88f67093", //替换为您的应用appkey
            });
            goEasy.subscribe({
                channel: "aaa", //替换为您自己的channel
                onMessage: function (message) {
                    var m = new Date().getMinutes()
                    var s = new Date().getSeconds()
                    var h = new Date().getHours()
                    var time = h + ":" + m + ":" + s;
                    $("#1").append("<p style=\"color:  hotpink\">好可爱哦"+time+"</p>"+
                        "<p style=\"margin-left: 160px;color: #1b6d85\">"+message.content+"</p>")
                }
            });
            $("#submit1").click(function () {
                var m = new Date().getMinutes()
                var s = new Date().getSeconds()
                var h = new Date().getHours()
                var time = h + ":" + m + ":" + s;
                html = editor.html();
                $("#1").append("<p id=\"3\" style=\"margin-left: 580px;color: #00bbff\">隐痛潜行"+time+"</p>"+
                    "<p style=\"margin-left: 620px;color: #1b6d85\">"+html+"</p>")
                goEasy.publish({
                    channel: "bbb", //替换为您自己的channel
                    message: html //替换为您想要发送的消息内容
                });
                editor.html("");
            })
        })

    </script>
</head>
<body>
<div>
    <div style="margin-left: 300px">
        <div style="border: 1px #9A9A9A solid;width:700px;height:300px;overflow-y:auto" id="1">
        </div>
        <textarea id="editor_id" name="content" style="width:700px;height:100px;">

        </textarea>
        <br/>
        <button class="" style="margin-left: 650px" id="submit1">发送</button>
    </div>
</div>
</body>
</html>