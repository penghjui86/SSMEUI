<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="common/tag.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title>框架搭建</title>
<%@include file="common/header.jsp"%>
</head>
<body class="easyui-layout">


	<div data-options="region:'north',border:true" style="height:60px;padding:10px">north region</div>

	<div data-options="region:'west',split:true,title:'West'" style="width:150px;">
           <div id="aa" class="easyui-accordion" data-options="border:true">
                    <div title="About" data-options="iconCls:'icon-ok'" style="overflow:auto;padding:10px;">
                        <h3 style="color:#0099FF;">Accordion for jQuery</h3>
                        <p>Accordion is a part of easyui framework for jQuery. It lets you define your accordion component on web page more easily.</p>
                    </div>

                    <div title="About" data-options="iconCls:'icon-ok'" style="overflow:auto;padding:10px;">
                        <h3 style="color:#0099FF;">Accordion for jQuery</h3>
                        <p>Accordion is a part of easyui framework for jQuery. It lets you define your accordion component on web page more easily.</p>
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

	<div data-options="region:'center',title:'Center'"></div>

</body>
</html>
