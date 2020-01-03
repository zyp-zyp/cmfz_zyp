<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<style>
    #btn1 {border-radius: 40%;}
    #btn2 {border-radius: 40%;}

    .modal-body{   position: relative;
                    padding: 14px 155PX;
                }
    #h3{
        margin-top: -18px;
        }
</style>
<script>

    $(function () {
        $("#albumList").jqGrid({
            url:"${pageContext.request.contextPath}/album/queryBypage",      //用来加载远程数据
            editurl:"${pageContext.request.contextPath}/album/edit",     //增删改的路径
            datatype:"json", //json返回
            styleUI:"Bootstrap",
            colNames:["id","专辑","图片","分数","作者","播音员","章节数","专辑简介","状态","发行时间"],
            pager:"#albumPager",     //分页
            rowNum:2,              //每页显示多少条
            rowList:[2,4,8],       //展示几条的下拉框
            viewrecords:true,     //是否显示的总记录
            autowidth:true,    //自适应父容器
            multiselect:true,      //是否多选
            records:true,
            height:"300px",

            colModel:[
                {name:"id"},
                {name:"title","editable":true},
                {name:"img","editable":true,edittype: 'file',
                    formatter:function (cellvalue, options, rowObject) {
                        return "<img style='width:100%;height:100px' src='${pageContext.request.contextPath}/admin/img/"+cellvalue+"'/>";
                    }

                },
                {name:"score","editable":true},
                {name:"author","editable":true},
                {name:"broadcaster","editable":true},
                {name:"count","editable":true},
                {name:"brief","editable":true},
                {name:"status",editable:true, edittype:"select", editoptions:{
                        value:"冻结:冻结;激活:激活"
                    }
                },
                {name:"create_date","editable":true,edittype:"date"}
            ],

            subGrid:true,    //添加子表格
            subGridRowExpanded:function (subGridId,albumId) {
                //调用添加子表格的方法
                addSubGrid(subGridId,albumId,);
            }
        }).jqGrid("navGrid","#albumPager",{search:false,addtext:"添加",edittext:"修改",deltext:"删除"},
            //修改
            {
                closeAfterEdit:true,
                afterSubmit:function (response) {
                    var id = response.responseJSON.albumId;
                    $.ajaxFileUpload({
                        url:"${pageContext.request.contextPath}/album/albumUpload",
                        fileElementId:'img',
                        data:{albumId:id},
                        type:"post",
                        success:function () {
                            //刷新数据表格
                            $("#albumList").jqGrid().trigger("reloadGrid");
                            $("#msgDiv2").html(msg).show();
                            setTimeout(function () {
                                $("#msgDiv2").hide();
                            },3000)
                        }
                    })
                    return response;
                }
            },
            //添加
            {
                closeAfterAdd:true,
                afterSubmit:function (response) {
                    var id = response.responseJSON.albumId;
                    $.ajaxFileUpload({
                        url:"${pageContext.request.contextPath}/album/albumUpload",
                        fileElementId:'img',
                        data:{albumId:id},
                        type:"post",
                        success:function () {
                            //刷新数据表格
                            $("#albumList").jqGrid().trigger("reloadGrid");
                            $("#msgDiv2").html(msg).show();
                            setTimeout(function () {
                                $("#msgDiv2").hide();
                            },3000)
                        }
                    })
                    return response;
                }
            },
            //删除
            {}
        )
    });

    /*
    *  添加子表格的方法
    * */
    function addSubGrid(subGridId,albumId) {
        //动态 table id
        var subGridTableId = subGridId+"table";
        //动态 div id
        var subGridDivId = subGridId+"div";
        //动态添加子表格
        $("#"+subGridId).html("<table id='"+subGridTableId+"'></table>"+
                "<div id='"+subGridDivId+"' style='height: 50px'> </div>"
        )
        $("#"+subGridTableId).jqGrid({
            url:"${pageContext.request.contextPath}/chapter/queryBypage?albumId="+albumId,
            editurl:"${pageContext.request.contextPath}/chapter/edit",
            styleUI:"Bootstrap",
            datatype:"json",
            autowidth:true,
            records:true,
            rowNum:3,
            caption:"章节",
            toolbar:[true,"top"],
            pager:"#"+subGridDivId,
            rowList:[3,6,9],
            multiselect:true,
            colNames: [
                "名字","文件","专辑id","大小","时长","发布时间","状态"
            ],
            colModel: [

                {name:"title","editable":true},
                {name:"src","editable":true,edittype: 'file'},
                {name:"album_Id"},
                {name:"size"},
                {name:"duration"},
                {name:"create_date","editable":true,edittype:"date"},
                {name:"status",editable:true, width:50, edittype:"select", editoptions:{
                        value:"冻结:冻结;激活:激活"
                    }
                },
            ]
        }).jqGrid("navGrid","#"+subGridDivId,{search:false,addtext:"添加",edittext:"修改",deltext:"删除"},

            /*
            * 修改
            * */

            {closeAfterEdit:true,
                afterSubmit:function (response) {
                    var id = response.responseJSON.albumId;
                    $.ajaxFileUpload({
                        url:"${pageContext.request.contextPath}/album/chapterUpload",
                        fileElementId:'src',
                        data:{albumId:id},
                        type:"post",
                        success:function () {
                            $("#").jqGrid().trigger("reloadGrid");
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
                    var id = response.responseJSON.chapterId;
                    $.ajaxFileUpload({
                        url:"${pageContext.request.contextPath}/chapter/chapterUpload?albumId="+albumId,
                        fileElementId:'src',
                        data:{chapterId:id},
                        type:"post",
                        success:function () {
                            //刷新数据表格
                            $("#"+subGridTableId).jqGrid().trigger("reloadGrid");
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

        /*
    * 添加按钮
    * */
        $("#t_"+subGridTableId).html(
            "<button id='btn1' class='btn btn-danger' style='margin-left: 150px ' onclick=\"play('"+subGridTableId+"')\">播放<span class='glyphicon glyphicon-play'></span></button>"+
            "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
            "<button id='btn2' class='btn btn-danger' style='margin-left: 400px' onclick=\"download('"+subGridTableId+"')\">下载<span class='glyphicon glyphicon-arrow-down'></span></button>"
        );
    }
    /*
    * 播放的方法
    * */
    function play(subGridTableId) {
        var gr = $("#"+subGridTableId).jqGrid('getGridParam', 'selrow');
        if(gr == null){
            alert("请选中要播放的音频");
        }else{
            //1.请求后台
            //2.jqgrid 提供的方法 根据id拿到对应的值
            var data = $("#"+subGridTableId).jqGrid('getRowData', gr);
            $('#myModal').modal('show');
            $("#myAudio").attr("src","${pageContext.request.contextPath}/admin/MP3/"+data.src);
        }
    };

    /*
    * 下载的方法
    * */
    function download(subGridTableId) {
        // 判断 用户是否选中一行  未选中->null         选中->被选中行的id
        var gr = $("#"+subGridTableId).jqGrid('getGridParam', 'selrow');
        if(gr == null){
            alert("请选中要下载的音频");
        }else{
            var data = $("#"+subGridTableId).jqGrid('getRowData', gr);
            var src = data.src;
            location.href = '${pageContext.request.contextPath}/chapter/download?fileName='+src;
        }
    }

    function closePlay() {
        var myAuto = document.getElementById('myAudio');
        myAuto.pause();
    }



</script>

<div class="page-header" id="h3">
    <h3>专辑与章节管理</h3>
</div>

<ul class="nav nav-tabs" role="tablist">
    <li role="presentation" class="active">
        <a href="#home" aria-controls="home" role="tab" data-toggle="tab">专辑信息与章节信息</a>
    </li>
</ul>

<div class="tab-content">
    <table id="albumList" ></table>
</div>

<div id="albumPager" style="height: 50px"></div>

<div class="alert alert-success" style="display:none" id="msgDiv2">

</div>

<div class="modal fade" tabindex="-1" role="dialog" id="myModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">播放器</h4>
            </div>
            <div class="modal-body">
                <audio autoplay controls src="" id="myAudio"  loop="false" ></audio>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="stop" onclick="closePlay()" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>



