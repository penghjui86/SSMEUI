<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/10/30
  Time: 9:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form id="SsmUserForm" method="post">
    <table class="com_table" align="center">
        <input type="hidden" name="id">
        <tr>
            <td></td>
            <td><label>登录名:</label></td>
            <td><input class="easyui-textbox com_input" type="text" name="loginName" data-options="required:true"/></td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td><label>用户名:</label></td>
            <td><input class="easyui-textbox com_input" type="text" name="name" data-options="required:true"/></td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td><label>密码:</label></td>
            <td><input class="easyui-textbox com_input" type="text" name="password" data-options="required:true"/></td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td><label>性别:</label></td>
            <td>
                <select class="easyui-combobox com_input" name="sex" data-options="panelHeight:'auto',value:'0'">
                    <option value="0">男</option>
                    <option value="1">女</option>
                </select>
            </td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td><label>年龄:</label></td>
            <td><input class="easyui-textbox com_input" type="text" name="age" data-options="required:false"/></td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td><label>手机号:</label></td>
            <td><input class="easyui-textbox com_input" type="text" name="phone" data-options="required:false"/></td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td><label>用户类别:</label></td>
            <td>
                <select class="easyui-combobox com_input" name="userType" data-options="panelHeight:'auto',value:'0'" >
                    <option value="0">用户</option>
                    <option value="1">管理员</option>
                </select>
            </td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td><label>用户状态:</label></td>
            <td>
                <select class="easyui-combobox com_input" name="status" data-options="panelHeight:'auto',value:'0'" >
                    <option value="0">启用</option>
                    <option value="1">停用</option>
                </select>
            </td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td><label>所属部门:</label></td>
            <td><select id="SsmUserOrg" name="orgIds" class="easyui-combotree com_input" data-options="textField:'name',valueField:'id',multiple:true,onlyLeafCheck:true,required:true"></select></td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td><label>所有角色:</label></td>
            <td><select id="SsmUserRole" name="roleIds" class="easyui-combobox com_input" data-options="textField:'name',valueField:'id',multiple:true,onlyLeafCheck:true,required:true" ></select></td>
            <td></td>
        </tr>
    </table>
</form>

<script type="text/javascript" src="/jsp/user/ssm-user.js"></script>
<script>
    //$.ready(function () {
        SsmUser.input.init();
    //})
</script>


