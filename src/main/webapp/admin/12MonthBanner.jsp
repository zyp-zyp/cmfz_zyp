<%@page contentType="text/html; utf-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script src="${pageContext.request.contextPath}/admin/echarts/echarts.min.js"></script>
    <script src="${pageContext.request.contextPath}/admin/echarts/china.js"></script>
    <script type="text/javascript" src="https://cdn.goeasy.io/goeasy-1.0.3.js"></script>
</head>
<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="12month" style="width: 800px;height:400px;"></div>
</body>
</html>
<script>
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('12month'));
    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '轮播图近12月上传数'
        },
        xAxis: {
            type: 'category',
            data: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月']
        },
        yAxis: {
            type: 'value'
        },
        series: [{
            type: 'line'

        }]
    };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);

    $.ajax({
        url:"${pageContext.request.contextPath}/banner/selectMonth12",
        datatype:"json",
        success:function (data) {
            myChart.setOption({
                series: [{
                    data: data
                }]
            })
        }
    });

    var goEasy = new GoEasy({
        host:'hangzhou.goeasy.io', //应用所在的区域地址: 【hangzhou.goeasy.io |singapore.goeasy.io】
        appkey: "BC-caac2a998f3044c1a91cb1ce88f67093", //替换为您的应用appkey
        forceTLS:false,
    });
    goEasy.subscribe({
        channel: "zypMonth12", //替换为您自己的channel
        onMessage: function (message) {
            myChart.setOption({
                series: [{
                    data: JSON.parse(message.content)
                }]
            })
        }
    });
</script>