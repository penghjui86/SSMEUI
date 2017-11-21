<%--
  Created by IntelliJ IDEA.
  User: 彭军辉
  Date: 2017/11/7
  Time: 20:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>盈繁科技</title>
   <%@include file="/template/layui/common/header.jsp"%>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">盈繁科技 后台</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item"><a href="">控制台</a></li>
            <li class="layui-nav-item"><a href="">商品管理</a></li>
            <li class="layui-nav-item"><a href="">用户</a></li>
            <li class="layui-nav-item">
                <a href="javascript:;">其它系统</a>
                <dl class="layui-nav-child">
                    <dd><a href="">邮件管理</a></dd>
                    <dd><a href="">消息管理</a></dd>
                    <dd><a href="">授权管理</a></dd>
                </dl>
            </li>
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
                    贤心
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="">基本资料</a></dd>
                    <dd><a href="">安全设置</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="">退了</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;">系统管理</a>
                    <dl class="layui-nav layui-nav-tree layui-nav-child">

                        <dd><a href="javascript:;" data-url="/template/layui/role/list.jsp" content="">用户管理</a></dd>
                        <dd><a href="javascript:;" data-url="/template/layui/demo/Demo2.html">角色管理</a></dd>
                        <dd><a href="javascript:;">组织机构管理</a></dd>
                        <ul class="layui-nav layui-nav-tree"  lay-filter="test2">
                            <li class="layui-nav-item layui-nav-itemed">
                                <a class="" href="javascript:;">系统管理2</a>
                                <dl class="layui-nav layui-nav-tree layui-nav-child">

                                    <dd><a href="javascript:;" data-url="/template/layui/role/list.jsp" content="">用户管理2</a></dd>
                                    <dd><a href="javascript:;" data-url="/template/layui/demo/Demo2.html">角色管理2</a></dd>
                                    <dd><a href="javascript:;">组织机构管理2</a></dd>
                                </dl>
                            </li>
                        </ul>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">解决方案</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">列表一</a></dd>
                        <dd><a href="javascript:;">列表二</a></dd>
                        <dd><a href="">超链接</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a href="">云市场</a></li>
                <li class="layui-nav-item"><a href="">发布商品</a></li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;" >内容主体区域4444
           <%-- <ul class="demo"></ul>--%>
        </div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © 盈繁科技 - 瀚迪科技
    </div>
</div>

<script>
//    //JavaScript代码区域
    layui.config({
        base: 'build/js/'
    }).use(['element','app', 'message','tree'], function(){

        var app = layui.app,
            $ = layui.jquery,
            layer = layui.layer;
        //将message设置为全局以便子页面调用
        message = layui.message;

       // var element = layui.element;
        //
       // var $=layui.$,layer=layui.layer;
//        $(".layui-nav a,.layui-nav-tree a").click(function(event){
//            event.preventDefault();//阻止超链接的默认事件
//            $(".layui-body").load(this.href);
//
//        });



});

layui.use('tree', function(){
    layui.tree({
        elem: '.demo' //传入元素选择器
        ,nodes: [{ //节点
            name: '父节点1'
            ,children: [{
                name: '子节点11'
            },{
                name: '子节点12'
            }]
        },{
            name: '父节点2（可以点左侧箭头，也可以双击标题）'
            ,children: [{
                name: '子节点21'
                ,children: [{
                    name: '子节点211'
                }]
            }]
        }]
    });
});

</script>