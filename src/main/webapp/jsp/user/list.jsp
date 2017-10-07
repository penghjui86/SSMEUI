<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<meta charset="utf-8">



<div id="toolbar">
    <a href="#" class="easyui-linkbutton easyui-tooltip" title="添加" data-options="iconCls:'icon-add',plain:true">添加</a>
    <a href="#" class="easyui-linkbutton easyui-tooltip" title="删除" data-options="iconCls:'icon-remove',plain:true">删除</a>
    <a href="#" class="easyui-linkbutton easyui-tooltip" title="修改" data-options="iconCls:'icon-edit',plain:true">修改</a>
</div>

<div id="searchbar" class="easyui-panel" style="width:100%;padding:10px 10px;">
            <form id="ff" action="form1_proc.php" method="post" enctype="multipart/form-data">
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
        <table id="SsmUserList" class="easyui-datagrid" style="width:100%" >

        </table>
      </div>
</div>
<script src="/jsp/user/ssm-user.js"></script>
<script type="text/javascript">
SsmUser.list.init();
//function changeP(){
    //var dg = $('#SsmUserList');
    //dg.datagrid('loadData',[]);
    //dg.datagrid({pagePosition:$('#p-pos').val()});
    //dg.datagrid('getPager').pagination({
        //layout:['list','sep','first','prev','sep',$('#p-style').val(),'sep','next','last','sep','refresh','info']
    //});
//}
</script>