<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>

<style>

    #hh3{
        margin-top: -18px;
    }
</style>
<script>
    $(function () {
        $("#bannaerList").jqGrid({
            url:"${pageContext.request.contextPath}/banner/queryBypage",      //用来加载远程数据
            editurl:"${pageContext.request.contextPath}/banner/edit",     //增删改的路径
            datatype:"json", //json返回
            styleUI:"Bootstrap",
            colNames:["编号","标题","图片","上传时间","状态"],
            pager:"#bannerPager",     //分页
            rowNum:2,              //每页显示多少条
            rowList:[2,4,8],       //展示几条的下拉框
            viewrecords:true,     //是否显示的总记录
            autowidth:true,         //自适应父容器
            multiselect:true,      //是否多选
            height:"300px",
            colModel:[
                {name:"id"},
                {name:"title",editable:true},
                {name:"img",editable:true,edittype: 'file',
                    formatter:function (cellvalue, options, rowObject) {
                        return "<img style='width:100%;height:100px' src='${pageContext.request.contextPath}/admin/img/"+cellvalue+"'/>";
                    }

                    },
                {name:"create_date",editable:true,edittype:"date"},
                {name:"status",editable:true, width:50, edittype:"select", editoptions:{
                        value:"冻结:冻结;激活:激活"
                    }
                }
            ],
        }).jqGrid("navGrid","#bannerPager",{search:false,addtext:"添加",edittext:"修改",deltext:"删除"},

            /*
            * 修改
            * */
            {closeAfterAdd:true,
                afterSubmit:function (response) {
                    var id = response.responseJSON.bannerId;
                    $.ajaxFileUpload({
                        url:"${pageContext.request.contextPath}/banner/bannerUpload",
                        fileElementId:'img',
                        data:{bannerId:id},
                        type:"post",
                        success:function () {
                            $("#bannaerList").jqGrid().trigger("reloadGrid");
                            $("#msgDiv").html(msg).show();
                            setTimeout(function () {
                                $("#msgDiv").hide();
                            },3000)
                        }
                    })
                    return response;
                }
            },
            /*
            * 添加
            * */
            {
                closeAfterAdd:true,
                afterSubmit:function (response) {
                    var id = response.responseJSON.bannerId;
                    $.ajaxFileUpload({
                        url:"${pageContext.request.contextPath}/banner/bannerUpload",
                        fileElementId:'img',
                        data:{bannerId:id},
                        type:"post",
                        success:function () {
                            //刷新数据表格
                            $("#bannaerList").jqGrid().trigger("reloadGrid");
                            $("#msgDiv").html(msg).show();
                            setTimeout(function () {
                                $("#msgDiv").hide();
                            },3000)
                        }
                    })
                    return response;
                }
            },

            /*
            * 删除
            * */
            {}

        )
    })
</script>

<div class="page-header" id="hh3">
    <h3>轮播图管理</h3>
</div>

<ul class="nav nav-tabs" role="tablist">
    <li role="presentation" class="active">
        <a href="#home" aria-controls="home" role="tab" data-toggle="tab">专辑信息与章节信息</a>
    </li>
</ul>


<table id="bannaerList"  >
</table>
<div id="bannerPager" style="height: 50px"></div>
<div class="alert alert-success" style="display:none" id="msgDiv">

</div>