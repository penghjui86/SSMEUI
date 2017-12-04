<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common/tag.jsp" %>
<!DOCTYPE html>
<html>
<head>
 <meta charset="UTF-8">
<title>盈繁科技框架搭建</title>
<%@include file="common/header.jsp"%>
</head>
<body class="easyui-layout">

<div data-options="region:'north',border:false" style="height: 70px;padding:0px"><h1>盈繁科技管理系统后台</h1></div>

	<div data-options="region:'west',split:true,title:'West'" style="width:150px;">
           <div id="aa" class="easyui-accordion" data-options="border:true">
                    <div title="About" data-options="iconCls:'icon-ok'" style="overflow:auto;padding:10px;">
                       <a href="javascript:void()" class="easyui-link" onclick="addTab('百度','http://www.baidu.com')">百度</a>
                       <a href="javascript:void()" class="easyui-link" onclick="addTabAjax('用户管理','/user/ui/list')">用户</a>
                       <a href="javascript:void()" class="easyui-link" onclick="addTabAjax('角色管理','/role/ui/list')">角色</a>
                    </div>

                    <div title="About" data-options="iconCls:'icon-ok'" style="overflow:auto;padding:10px;">
                        <a href="javascript:void()" class="easyui-link" onclick="addTabAjax('组织机构','/org/ui/list')">组织机构</a>
						<a href="javascript:void()" class="easyui-link" onclick="addTabAjax('资源列表','/resource/ui/list')">资源列表</a>
                    </div>

                    <div title="About" data-options="iconCls:'icon-ok'" style="overflow:auto;padding:10px;">
                        <h3 style="color:#0099FF;">Accordion for jQuery</h3>
                        <p>Accordion is a part of easyui framework for jQuery. It lets you define your accordion component on web page more easily.</p>
                    </div>

            </div>
	</div>

	<div data-options="region:'east',split:true,collapsed:true,title:'East'" style="width:100px;padding:10px;">east region</div>

	<div data-options="region:'south',border:true" style="height:50px;padding:3px;">
	    <p style="text-align:center">纷橙e购版权</p>
	</div>

<div  data-options="region:'center',title:'',iconCls:'icon-ok',border:false">
	<div id="main-tabs" class="easyui-tabs" data-options="fit:true" >
                <div title="About" style="padding:0px">
        			<p style="font-size:14px">jQuery EasyUI framework helps you build your web pages easily.</p>
        			<ul>
        				<li>easyui is a collection of user-interface plugin based on jQuery.</li>
        				<li>easyui provides essential functionality for building modem, interactive, javascript applications.</li>
        				<li>using easyui you don't need to write many javascript code, you usually defines user-interface by writing some HTML markup.</li>
        				<li>complete framework for HTML5 web page.</li>
        				<li>easyui save your time and scales while developing your products.</li>
        				<li>easyui is very easy but powerful.</li>
        			</ul>
        		</div>

        		<div title="Help"  data-options="iconCls:'icon-help',closable:true" style="padding:0px">
        			<table id="dg" title="Custom DataGrid Pager"
                    					   data-options="fit:true,rownumbers:true,singleSelect:true,pagination:true,url:'/resources/easyui/jquery-easyui-1.5.3/demo/datagrid/datagrid_data1.json',method:'get'">
                    					<thead>
                    					<tr>
                    						<th data-options="field:'itemid',width:80">Item ID</th>
                    						<th data-options="field:'productid',width:100">Product</th>
                    						<th data-options="field:'listprice',width:80,align:'right'">List Price</th>
                    						<th data-options="field:'unitcost',width:80,align:'right'">Unit Cost</th>
                    						<th data-options="field:'attr1',width:240">Attribute</th>
                    						<th data-options="field:'status',width:60,align:'center'">Status</th>
                    					</tr>
                    					</thead>
                    				</table>
                    				<script type="text/javascript">
                    					$(function(){
                    						var pager = $('#dg').datagrid().datagrid('getPager');    // get the pager of datagrid
                    						pager.pagination({
                    							buttons:[{
                    								iconCls:'icon-search',
                    								handler:function(){
                    									alert('search');
                    								}
                    							},{
                    								iconCls:'icon-add',
                    								handler:function(){
                    									alert('add');
                    								}
                    							},{
                    								iconCls:'icon-edit',
                    								handler:function(){
                    									alert('edit');
                    								}
                    							}]
                    						});
                    					})

                                        function addTab(title, url){
                                            if ($('#main-tabs').tabs('exists', title)){
                                                $('#main-tabs').tabs('select', title);
                                            } else {
                                                var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
                                                $('#main-tabs').tabs('add',{
                                                    title:title,
                                                    content:content,
                                                    closable:true
                                                });
                                            }
                                        }

                                          function addTabAjax(title, url){
                                                if ($('#main-tabs').tabs('exists', title)){
                                                    $('#main-tabs').tabs('select', title);
                                                } else {
                                                    //var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';

                                                    $('#main-tabs').tabs('add',{
                                                        title:title,
                                                        href:url,
                                                        //content:content,
                                                        //content: '<div id="tab'+title+'"></div>',
														closable:true,
														border:false
                                                    });

//                                                    $('#main-tabs').tabs({
//														onAdd: function(title,title){
//															$.get(url,function(data){
//																$('#tab'+title).html(data);
//															});
//														}
//													})

                                                }
                                           }

                    				</script>
        		</div>
	</div>
</div>
</body>
</html>
