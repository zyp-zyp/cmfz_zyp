<%@page contentType="text/html; utf-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>


</head>
<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="7day" style="width: 600px;height:400px;"></div>
</body>
</html>
<script>

    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('7day'));
    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '轮播图近7天上传数'
        },
        tooltip: {},
        legend: {
            data:['上传数']
        },
        xAxis: {
            data: ["day1","day2","day3","day4","day5","day6","day7"]
        },
        yAxis: {},
        series: [{
            name: '上传数',
            type: 'bar',

        }]
    };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);

    $.ajax({
        url:"${pageContext.request.contextPath}/banner/selectDay7",
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
        host:'hangzhou.goeasy.io',
        appkey: "BC-caac2a998f3044c1a91cb1ce88f67093",
    });

    goEasy.subscribe({
        channel: "zypDay7", //替换为您自己的channel
        onMessage: function (message) {
            var parse = JSON.parse(message.content);
            myChart.setOption({
                series: [{
                    data: parse
                }]
            })
        }
    });

</script>