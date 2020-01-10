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
<form class="layui-form" action="">
    <div class="layui-form-item">
        <label class="layui-form-label">父级</label>
        <div class="layui-input-block">
            <select name="pid" lay-verify="required">
                <option value="0">根级节点</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">名称</label>
        <div class="layui-input-block">
            <input type="text" name="typeName" required  lay-verify="required" placeholder="请输入名称" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">排序</label>
        <div class="layui-input-block">
            <input type="text" name="typeOrder" required  lay-verify="required" placeholder="请输入排序" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
</body>
</html>
<script src="../js/layui/layui.js?t=1575889601624" charset="utf-8"></script>
<script type="text/javascript">
    //Demo
    layui.use('form', function(){
        var form = layui.form;
        //监听提交
        form.on('submit(formDemo)', function(data){
            //var dataVal = JSON.stringify();
            var typeInfo = data.field;
            var url = "/savetype";
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
                    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                    parent.layer.close(index);  // 关闭当前layer层
                    parent.location.reload();
                },
            });
            return false;//阻止表单跳转
        });
    });
</script>