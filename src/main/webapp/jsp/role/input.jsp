<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form id="SsmRoleForm" method="post">
    <table class="com_table" align="center">
        <input type="hidden" name="id">
        <tr>
            <td></td>
            <td><label>角色名:</label></td>
            <td><input class="easyui-textbox  com_input" type="text" name="name" data-options="required:true"/></td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td><label>排序号:</label></td>
            <td><input class="easyui-numberspinner" type="text" name="seq" data-options="min:0,max:10000,editable:true,required:false,value:0"/></td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td><label>简介:</label></td>
            <td><input class="easyui-textbox  com_input" type="text" name="description" data-options="required:false"/></td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td><label>状态:</label></td>
            <td>
                <select class="easyui-combobox  com_input" name="status" data-options="panelHeight:'auto',value:'0'" style="width:173px;">
                    <option value="0">启用</option>
                    <option value="1">停用</option>
                </select>
            </td>
            <td></td>
        </tr>
    </table>
</form>

<script src="<%=request.getContextPath()%>/jsp/role/ssm-role.js"></script>

<script>
    SsmRole.input.init('<%=request.getContextPath()%>');
</script>