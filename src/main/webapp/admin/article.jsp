<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8"%>
<script>
    $(function () {
        $("#articleList").jqGrid({
            url:"${pageContext.request.contextPath}/article/queryBypage",      //用来加载远程数据
            editurl:"${pageContext.request.contextPath}/article/edit",     //增删改的路径
            datatype:"json", //json返回
            styleUI:"Bootstrap",
            colNames:["id","标题","发布时间","状态","操作"],
            pager:"#articlePager",     //分页
            rowNum:2,              //每页显示多少条
            rowList:[2,4,8],       //展示几条的下拉框
            viewrecords:true,     //是否显示的总记录
            autowidth:true,         //自适应父容器
            multiselect:true,      //是否多选
            height:"300px",
            colModel:[
                {name:"id"},
                {name:"title","editable":true},
                {name:"create_date","editable":true,edittype:"date"},
                {name:"status",editable:true, width:50, edittype:"select", editoptions:{
                        value:"冻结:冻结;激活:激活"}
                },
                {name:"","editable":true,
                    formatter:function (cellvalue, options, rowObject) {
                        return "<button id='btn1' data-toggle='modal' data-target='#detailsModal' class='btn btn-danger'  onclick=\"details('"+rowObject.id+"')\">详情</button>"
                        +"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
                        "<button id='btn1' class='btn btn-primary' data-toggle='modal' data-target='#updateModal' onclick=\"update1('"+rowObject.id+"')\">修改</button>";
                    }
                },

            ],
        }).jqGrid("navGrid","#articlePager",{search:false,add: false, edit: false,deltext:"删除"})
    })


    $(function () {
        var newVar = KindEditor.create("#editor_id",{
            width:'565px',
            height:'200px',
            minHeight:200,
            minWidth:565,
            resizeType:1,
            allowFileManager:true,    //是否展示 图片空间
            filePostName:'img',       //上传是后台接收的名字
            uploadJson:'${pageContext.request.contextPath}/article/articleUpload', //上传后台的路径
            fileManagerJson:"${pageContext.request.contextPath}/article/getAllImgs",
            afterBlur: function(){  //利用该方法处理当富文本编辑框失焦之后，立即同步数据
                KindEditor.sync("#editor_id") ;
            }
        });
    })

    /*
    * 添加
    * */
    function add() {
        var serialize = $("#addform").serialize();
        $.ajax({
            url: "${pageContext.request.contextPath}/article/edit?oper=add",
            data:serialize,
            datatype:"json",
            success:function (data) {

            }
        })
    }

    /*
    * 查看详情
    * */
    function details(id) {
        $.ajax({
            url: "${pageContext.request.contextPath}/article/details?id="+id,
            datatype:"json",
            success:function (data) {
                $("#details2").html(data).show();
            }
        })
    }


    /*
    * 修改编译器
    * */
    $(function () {
        var newVar = KindEditor.create("#editor_id2",{
            width:'565px',
            height:'200px',
            minHeight:200,
            minWidth:565,
            resizeType:1,
            allowFileManager:true,    //是否展示 图片空间
            filePostName:'img',       //上传是后台接收的名字
            uploadJson:'${pageContext.request.contextPath}/article/articleUpload', //上传后台的路径
            fileManagerJson:"${pageContext.request.contextPath}/article/getAllImgs",
            afterBlur: function(){  //利用该方法处理当富文本编辑框失焦之后，立即同步数据
                KindEditor.sync("#editor_id2") ;
            }
        });
    })

    /*
    * 修改回显
    * */
    function update1(id) {
        $.ajax({
            url: "${pageContext.request.contextPath}/article/update?id="+id,
            datatype:"json",
            success:function (data) {
                $("#id").val(data.id)
                $("#loginname2").val(data.title);
                $("#status2").val(data.status);
                KindEditor.html('#editor_id2',data.content);
            }
        })
    }

    /*
    * 修改
    * */
    function update2() {
        var serialize = $("#updateform").serialize();
        $.ajax({
            url: "${pageContext.request.contextPath}/article/edit?oper=edit",
            data:serialize,
            datatype:"json",
            success:function (data) {

            }
        })
    }



</script>

<div>

    <!-- Nav tabs -->
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">文章信息</a></li>
        <li role="presentation"><a href="#" data-toggle="modal" data-target="#addModal">添加信息</a></li>
    </ul>

    <!-- Tab panes -->
    <div class="tab-content">
        <div role="tabpanel" class="tab-pane active" id="home">
            <table id="articleList"></table>
        </div>
        <div id="articlePager" style="height: 50px"></div>
        <div class="alert alert-success" style="display:none" id="msgDiv">
            添加成功
        </div>
    </div>

</div>





<%--
       添加的模态框
--%>
<div class="modal fade" id="addModal" data-keyboard="false" data-keyboard="false" >
    <div class="modal-dialog" >
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" >
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    添加文章
                </h4>
            </div>
            <div class="modal-body">
                <form id="addform" class="form-horizontal">
                    <div class="form-group">
                        <div>
                            <label for="loginname" class="col-sm-2 control-label" >
                                标题:
                            </label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" id="loginname" name="title">
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-2">
                               状态：
                        </label>
                        <div class="col-sm-3">
                            <select name="status" id="status" class="form-control">
                                <option value="激活">&nbsp;&nbsp;激活&nbsp;&nbsp;</option>
                                <option value="冻结">&nbsp;&nbsp;冻结&nbsp;&nbsp;</option>
                            </select>
                        </div>
                    </div>

                    <center>
                        <div class="form-group" >
                            <div>
                                <textarea id="editor_id" name="content" >
                                    请输入内容
                                </textarea>
                            </div>
                        </div>
                    </center>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="add();">提交</button>
                <button type="button" class="btn btn-danger" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>


<%--
       详情的模态框
--%>
<div class="modal fade" id="detailsModal" data-keyboard="false" data-keyboard="false" >
    <div class="modal-dialog" >
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" >
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel1" >
                    文章详情
                </h4>
            </div>
            <div class="modal-body" id="details2">

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>


<%--
       详情的模态框
--%>
<%--
       修改的模态框
--%>
<div class="modal fade" id="updateModal" data-keyboard="false" data-keyboard="false" >
    <div class="modal-dialog" >
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" >
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabe3">
                    修改文章
                </h4>
            </div>
            <div class="modal-body">

                <form id="updateform" class="form-horizontal">
                    <div class="form-group" style="display:none">
                        <div>
                            <label for="id" class="col-sm-2 control-label" >
                                id:
                            </label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" id="id" name="id">
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div>
                            <label for="loginname" class="col-sm-2 control-label" >
                                标题:
                            </label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control"id="loginname2" name="title">
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-2">
                            状态：
                        </label>
                        <div class="col-sm-3">
                            <select id="status2" name="status" class="form-control">
                                <option value="激活">&nbsp;&nbsp;激活&nbsp;&nbsp;</option>
                                <option value="冻结">&nbsp;&nbsp;冻结&nbsp;&nbsp;</option>
                            </select>
                        </div>
                    </div>
                    <center>
                        <div class="form-group">
                            <div>
                            <textarea id="editor_id2" name="content" >
                                请输入内容
                            </textarea>
                            </div>
                        </div>
                    </center>
                </form>



            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="update2();">提交</button>
                <button type="button" class="btn btn-danger" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

