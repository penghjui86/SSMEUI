<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/10/25
  Time: 16:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form id="SsmOrgForm" method="post">
    <table class="com_table"  align="center">
        <input type="hidden" name="id">
        <input type="hidden" name="isLeaf">
        <tr>
            <td></td>
            <td><label>组织名:</label></td>
            <td><input class="easyui-textbox com_input" name="name" data-options="required:true"/></td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td><label>地址:</label></td>
            <td><input class="easyui-textbox com_input" name="address" data-options="required:false"/></td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td><label>编号:</label></td>
            <td><input class="easyui-textbox com_input" name="code" data-options="required:false"/></td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td><label>图标:</label></td>
            <td><input class="easyui-textbox com_input" name="icon" data-options="required:false"/></td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td><label>上级部门:</label></td>
            <td><select class="easyui-combobox com_input"  id="parentOrg"  name="pid" data-options="textField:'text',valueField:'id'"></select></td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td><label>排序:</label></td>
            <td><input class="easyui-numberspinner" name="seq" data-options="min:0,max:10000,editable:true,required:false,value:0"/></td>
            <td></td>
        </tr>
    </table>
</form>
<script src="<%=request.getContextPath()%>/jsp/org/ssm-org.js"></script>
<script>
    SsmOrg.input.init();
</script>
