<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<meta charset="utf-8">

<div id="roletoolbar" data-options="fit:true">
    <a href="#" class="easyui-linkbutton easyui-tooltip" title="添加" data-options="iconCls:'icon-add',plain:true" onclick="javascript:SsmRole.list.add()">添加</a>
    <a href="#" class="easyui-linkbutton easyui-tooltip" title="删除" data-options="iconCls:'icon-remove',plain:true" onclick="javascript:SsmRole.list.delete()">删除</a>
    <a href="#" class="easyui-linkbutton easyui-tooltip" title="修改" data-options="iconCls:'icon-edit',plain:true" onclick="javascript:SsmRole.list.edit()">修改</a>
</div>

<div id="rolesearchbar" class="easyui-panel" style="width:100%;padding:10px 10px;">
            <form id="roleForm" action="form1_proc.php" method="post" enctype="multipart/form-data">
                <table>
                    <tr>
                        <td>Name:</td>
                        <td><input name="name" class="f1 easyui-textbox"></input></td>

                        <td>Email:</td>
                        <td><input name="email" class="f1 easyui-textbox"></input></td>

                        <td>Phone:</td>
                        <td><input name="phone" class="f1 easyui-textbox"></input></td>

                        <td>File:</td>
                        <td><input name="file" class="f1 easyui-filebox"></input></td>

                        <td></td>
                        <td><a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px">Search</a></td>
                    </tr>
                </table>
</div>

<div class="easyui-panel" data-options="fit:true,border:false">
     <div data-options="region:'center',border:false">
        <table id="SsmRoleList" class="easyui-datagrid" style="width:100%" >

        </table>
      </div>
</div>

<div id="SsmRoleEdit" title="角色" style="width:500px;height:300px;top: 100px;padding: 10px;display: none" data-options="iconCls: 'icon-save',closed: true,modal: true,inline:true,buttons:[{text:'保存',iconCls:'icon-save',handler:function(){SsmRole.input.submitForm()}},{text:'取消',iconCls:'icon-cancel',handler:function(){SsmRole.input.close()}}]"></div>

<script src="/jsp/role/ssm-role.js"></script>

<script type="text/javascript">
       SsmRole.list.init();
</script>

