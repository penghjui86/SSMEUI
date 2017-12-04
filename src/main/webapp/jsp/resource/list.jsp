<%--
  Created by IntelliJ IDEA.
  User: 彭军辉
  Date: 2017/11/21
  Time: 17:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- 工具栏 -->
<div id="SsmResourceToolbar" style="padding:5px;height:auto">
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" plain="true" onclick="javascript:SsmResource.list.add()">增加</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" plain="true" onclick="javascript:SsmResource.list.delete()">删除</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" plain="true" onclick="javascript:SsmResource.list.edit()">编辑</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" plain="true" onclick="javascript:SsmResource.list.reload()">刷新</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-standard-plugin-delete'" plain="true" onclick="javascript:SsmResource.list.collapseAll()">折叠</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-standard-plugin-add'" plain="true" onclick="javascript:SsmResource.list.expandAll()">展开</a>
</div>

<!-- 列表 -->
<table id="SsmResourceList" data-options="border:false" style="width: 100%;" title="资源"></table>

<!-- 弹窗  --> <!-- inline:true 不然多次打开tab会重复提交表单 -->
<div id="SsmResourceEdit" title="资源" style="width:500px;height:500px;top: 100px;padding: 10px;display: none" data-options="iconCls: 'icon-save',closed: true,modal: true,inline:true,buttons:[{text:'保存',iconCls:'icon-save',handler:function(){SsmResource.input.submitForm()}},{text:'取消',iconCls:'icon-cancel',handler:function(){SsmResource.input.close()}}]"></div>

<script src="/jsp/resource/ssm-resource.js"></script>

<script>
    SsmResource.list.init('<%=request.getContextPath()%>');
</script>




