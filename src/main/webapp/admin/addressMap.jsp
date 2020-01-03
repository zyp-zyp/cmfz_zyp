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
        title : {
            text: '用户地理分布图',
            left: 'center'
        },
        tooltip : {
            trigger: 'item'
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data:['人数']
        },
        visualMap: {
            min: 0,
            max: 2500,
            left: 'left',
            top: 'bottom',
            text:['高','低'],           // 文本，默认为数值文本
            calculable : true
        },
        toolbox: {
            show: true,
            orient : 'vertical',
            left: 'right',
            top: 'center',
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        series : [
            {
                name: '人数',
                type: 'map',
                mapType: 'china',
                label: {
                    normal: {
                        show: false
                    },
                    emphasis: {
                        show: true
                    }
                },
            },
        ]
    };
    myChart.setOption(option);

    $.ajax({
        url:"${pageContext.request.contextPath}/account/selectAddress",
        datatype:"json",
        success:function (data) {
            myChart.setOption({
                series: [{
                    data: data
                }]
            })
        }
    });
</script>