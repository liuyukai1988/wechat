<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>应用商店后台管理系统</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <script src="https://cdn.bootcss.com/jquery/2.2.4/jquery.min.js"></script>
    <link rel="stylesheet" href="../js/layui/css/layui.css?t=1575889601624"  media="all">
    <link rel="stylesheet" href="../css/admin.css" media="all">
</head>
<body>
<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-card-header layuiadmin-card-header-auto">

        </div>
        <div class="layui-card-body">
            <table id="member" lay-filter="test"></table>
        </div>
    </div>
</div>
<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <!--<button class="layui-btn layui-btn-sm" lay-event="add">添加</button>
        <button class="layui-btn layui-btn-sm" lay-event="import">导入</button>-->
    </div>
</script>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="detail">查看</a>
    <!--<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>-->
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script src="../js/layui/layui.js?t=1575889601624" charset="utf-8"></script>
<script type="text/javascript">
    //JavaScript代码区域
    layui.use('element', function(){
        var element = layui.element;
    });

    layui.use('table', function(){
        var table = layui.table;
        //第一个实例
        table.render({
            elem: '#member'
            ,height: 450
            ,url: '/getallmember' //数据接口
            ,page: false //开启分页
            ,cols: [[ //表头
                {field: 'id', title: 'ID', width:80, sort: true, fixed: 'left'}
                ,{field: 'openId', title: 'OpenId', width:180}
                ,{field: 'nickName', title: '用户昵称', width:100}
                ,{field: 'avatarUrl', title: '用户头像', width:100}
                ,{field: 'gender', title: '性别', width:100}
                ,{field: 'country', title: '国家', width:100}
                ,{field: 'province', title: '省份', width:100}
                ,{field: 'city', title: '城市', width:100}
                ,{field: 'language', title: '语种', width:100}
                ,{field: 'cTime', title: '注册时间', width: 177}
                ,{field: 'mobile', title: '手机号码', width: 150}
                ,{field: 'createTime', title: '创建时间', width: 177}
                ,{fixed: 'right', title: '操作', width:150, align:'center', toolbar: '#barDemo'} //这里的toolbar值是模板元素的选择器
            ]]
            ,toolbar: '#toolbarDemo'
        });

        //监听事件
        table.on('toolbar(test)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id);
            switch(obj.event){
                case 'add':
                    //弹出即全屏
                    var index = layer.open({
                        type: 1,
                        content: $('#memberedit'),
                        area: ['550px', '600px'],
                        maxmin: false,
                        title:'会员信息',
                        offset: 'auto',
                        shade: [0.8, '#393D49']
                    });
                    break;
            };
        });

        //监听事件
        table.on('tool(test)', function(obj){
            var data = obj.data;
            switch(obj.event){
                case 'del':
                    layui.use('layer',function(){
                        var layer = layui.layer;
                        layer.msg("真的删除此会员吗?", {
                            time: false ,//不自动关闭
                            btn: ['确定', '取消'],
                            yes: function(index){
                                $.ajax({
                                    url:"/deletemember",
                                    data:{'id':data.id},
                                    type:"Post",
                                    dataType:"json",
                                    success:function(data){
                                        layer.msg("删除会员成功");
                                        location.reload(); //删除成功后再刷新
                                    },
                                    error:function(data){
                                        layer.msg('删除会员失败');
                                    }
                                });
                                layer.close(index);
                            }
                        });
                    });
                    break;
                case 'edit':
                    //表单值
                    var form = layui.form;
                    form.val("membereditform",{
                        "id":data.id,
                        "openId":data.openId,
                        "nickName":data.nickName,
                        "avatarUrl":data.avatarUrl,
                        "gender":data.gender,
                        "country":data.country,
                        "province":data.province,
                        "city":data.city,
                        "language":data.language,
                        "cTime":data.cTime,
                        "mobile":data.mobile
                    });
                    layer.open({
                        type: 1,
                        area: ['500px', '400px'],
                        title: "修改会员信息",
                        fixed: false, //不固定
                        maxmin: true,
                        maxmin: false,
                        shadeClose:false,
                        content: $('#memberedit')
                    });
                    break;
                case 'detail'://查看
                    layer.alert('昵称：'+data.nickName+'<br>手机号码:'+data.mobile+'<br>注册时间:'+data.cTime+'<br>省份:'+data.province);
                    break;
            };
        });
    });

    layui.use('form', function(){
        var form = layui.form;
        //监听提交
        form.on('submit(formDemo)', function(data){
            var typeInfo = data.field;
            var url = "/savemember";
            $.ajax({
                url:url,
                type:'post',
                data:typeInfo,
                beforeSend:function () {
                    this.layerIndex = layer.load(0, { shade: [0.5, '#393D49'] });
                },
                success:function(data){
                    if(data.code == '201'){
                        layer.msg(data.msg,{icon: 5});//失败的表情
                        return;
                    }else if(data.code == '200'){
                        layer.msg(data.msg, {
                            icon: 6,//成功的表情
                            time: 1000 //1秒关闭（如果不配置，默认是3秒）
                        }, function(){
                            location.reload();
                        });
                    }
                },
                complete: function () {
                    layer.close(this.layerIndex);
                    location.reload();
                },
            });
            return false;//阻止表单跳转
        });
    });
</script>
<div id="topicedit" style="display: none;padding-top: 20px;margin-right: 20px;">
    <form class="layui-form" name="membereditform" id="membereditform" lay-filter="membereditform">
        <div class="layui-form-item">
            <label class="layui-form-label">试题类型</label>
            <div class="layui-input-block">
                <input type="hidden" name="id" id="id" value="0"/>
                <select name="type" id="type" lay-verify="required">
                    <option value="0">根级类型</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">标题</label>
            <div class="layui-input-block">
                <input type="text" name="title" id="title" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">最优答案</label>
            <div class="layui-input-block">
                <textarea id="answer1" name="answer1" type="text/plain" style="width:99%;height:250px" placeholder="请输入最优答案" class="layui-input"></textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">备选答案一</label>
            <div class="layui-input-block">
                <textarea id="answer2" name="answer2" type="text/plain" style="width:99%;height:250px" placeholder="请输入备选答案一" class="layui-input"></textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">备选答案二</label>
            <div class="layui-input-block">
                <textarea id="answer3" name="answer3" type="text/plain" style="width:99%;height:250px" placeholder="请输入备选答案二" class="layui-input"></textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="formDemo">保存</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>