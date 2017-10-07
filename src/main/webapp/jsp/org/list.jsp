<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="SsmOrgToolbar" style="padding:5px;height:auto">
    <div>
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" plain="true" onclick="javascript:SsmOrg.list.add()">增加</a>
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" plain="true" onclick="javascript:SsmOrg.list.delete()">删除</a>
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" plain="true" onclick="javascript:SsmOrg.list.edit()">编辑</a>
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" plain="true" onclick="javascript:SsmOrg.list.reload()">刷新</a>
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-standard-plugin-delete'" plain="true" onclick="javascript:SsmOrg.list.collapseAll()">折叠</a>
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-standard-plugin-add'" plain="true" onclick="javascript:SsmOrg.list.expandAll()">展开</a>
    </div>
</div>

<!-- 列表 -->
<table id="SsmOrgList" data-options="border:false"  style="width: 100%;" title="组织机构"></table>

<!-- 弹窗  --> <!-- inline:true 不然多次打开tab会重复提交表单 -->
<div id="SsmOrgEdit" title="组织机构" style="width:500px;height:400px;top: 100px;padding: 10px;display: none" data-options="iconCls: 'icon-save',closed: true,modal: true,inline:true,buttons:[{text:'保存',iconCls:'icon-save',handler:function(){SsmOrg.input.submitForm()}},{text:'取消',iconCls:'icon-cancel',handler:function(){SsmOrg.input.close()}}]"  ></div>
<script src="<%=request.getContextPath()%>/jsp/org/ssm-org.js"></script>
<script>
    SsmOrg.list.init('<%=request.getContextPath()%>');
</script>
