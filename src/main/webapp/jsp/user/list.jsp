<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<meta charset="utf-8">

<div class="easyui-layout" data-options="fit:true"  style="display: none" id="tt">

    <div data-options="region:'west',title:'组织机构',split:true,border:false" style="width:15%;">
        <!-- 模块列表 -->
        <ul id="SsmUserOrgTree"></ul>
    </div>

    <div data-options="region:'center',border:false">
        <div id="toolbar" class="easyui-panel">
            <a href="#" class="easyui-linkbutton easyui-tooltip" title="添加" data-options="iconCls:'icon-add',plain:true" onclick="SsmUser.list.add()">添加</a>
            <a href="#" class="easyui-linkbutton easyui-tooltip" title="删除" data-options="iconCls:'icon-remove',plain:true" onclick="SsmUser.list.delete()">删除</a>
            <a href="#" class="easyui-linkbutton easyui-tooltip" title="修改" data-options="iconCls:'icon-edit',plain:true" onclick="SsmUser.list.edit()">修改</a>
        </div>

        <div id="searchbar" class="easyui-panel" style="width:100%;padding:10px 10px;">
                    <form id="ff" action="form1_proc.php" method="post" enctype="multipart/form-data">
                        <table>
                            <tr>
                                <td>Name:</td>
                                <td><input lang="searchSsmUser" name="name" class="f1 easyui-textbox"></input></td>

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
                    </form>
        </div>
        <div class="easyui-panel" data-options="fit:true,border:false">
                 <div data-options="region:'center',border:false">
                    <table id="SsmUserList" class="easyui-datagrid" style="width:100%" >

                    </table>
                  </div>

        </div>
    </div>
</div>
<%--弹出窗口--%>
<div id="SsmUserEdit" title="用户"  style="width:500px;height:500px;top: 100px;padding: 10px;display: none" data-options="iconCls: 'icon-save',closed: true,modal: true,inline:true,buttons:[{text:'保存',iconCls:'icon-save',handler:function(){SsmUser.input.submitForm()}},{text:'取消',iconCls:'icon-cancel',handler:function(){SsmUser.input.close()}}]"></div>
<script src="/jsp/user/ssm-user.js"></script>
<script type="text/javascript">
    $(function(){
        $("#tt").show();
        SsmUser.list.init();
    })

//function changeP(){
    //var dg = $('#SsmUserList');
    //dg.datagrid('loadData',[]);
    //dg.datagrid({pagePosition:$('#p-pos').val()});
    //dg.datagrid('getPager').pagination({
        //layout:['list','sep','first','prev','sep',$('#p-style').val(),'sep','next','last','sep','refresh','info']
    //});
//}
</script>