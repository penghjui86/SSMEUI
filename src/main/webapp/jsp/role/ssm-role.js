var ctx="";//
var SsmRoleList;//
var SsmRoleEdit;//
var SsmRoleForm;//

var SsmRole={
    URL:{
        inputUI:function(){
            return ctx+"/role/ui/input";
        },
        list:function(){
          return ctx+"/role/list";
        },
        save:function(){
            return ctx+"/role/save";
        },
        get:function(id){
            return ctx+"/role/get/"+id;
        },
        delete:function(ids){
            return ctx+"/role/delete/"+ids;
        }
    },
    input:{
        init:function(ct){
            ctx=ct;
            SsmRole.input.initComponent();
            SsmRole.input.initForm();
        },
        initComponent:function(){
            SsmRoleForm=$("#SsmRoleForm");

        },
        initForm:function(){
            SsmRoleForm.form({
                url:SsmRole.URL.save(),
                onSubmit:function(){
                    //check,
                    //return false 阻止submit
                },
                success:function(data){
                    var data = eval('(' + data + ')');
                    if(data.code==200){
                        SsmRole.input.close();
                        SsmRole.list.reload();
                    }
                }
            })
        },
        submitForm:function(){
            SsmRoleForm.submit();
        },
        close:function(){
            SsmRoleEdit.dialog('close');
        },
    },
    list:{
        init:function(){
            SsmRole.list.initComponent();
            SsmRole.list.initList();

        },
        initComponent:function(){
            SsmRoleList=$("#SsmRoleList");
            SsmRoleEdit=$("#SsmRoleEdit");
        },
        initList:function(){
            SsmRoleList.datagrid({
                url:SsmRole.URL.list(),
                method:'get',
                pagination:true,
                pageSize:10,
                singleSelect:false,
                collapsible:true,
                columns:[[
                    {field:'ok',checkbox:true},
                    {field:'id',title:'主键id',hidden:true},
                    {field:'name',title:'名称',hidden:false},
                    {field:'seq',title:'序号',hidden:false},
                    {field:'description',title:'描述',hidden:false},
                    {field:'status',title:'角色状态',hidden:false,formatter:function(value,row,index){return value==0?'启用':'停用';}},
                    {field:'updateTime',title:'更新时间',hidden:false},
                    {field:'createTime',title:'创建时间',hidden:false},
                ]],
            })
        },

        add:function(){
            SsmRoleEdit.dialog({
                href:SsmRole.URL.inputUI(),
                onLoad:function(){
                }
            }).dialog("open");

        },

        edit:function(){
            var ids=SsmRoleList.datagrid("getSelections");
            if(ids.length<1){
                $.messager.alert("对话框","至少选择一行");
                return;
            }
           if(ids.length>1){
               $.messager.alert("对话框","只能选择一行");
               return;
           }

           SsmRoleEdit.dialog({
                href:SsmRole.URL.inputUI(),
                onLoad:function(){
                    $.ajax({
                        type:'GET',
                        url:SsmRole.URL.get(ids[0].id),
                        success:function(data){
                            if(data.code==200){
                                //方案一：使用Form的load去load数据
                                //SysRoleForm.form("load", SysRole.URL.get(sels[0].id));
                                //方案二：直接使用列表的row数据
                                //SysRoleForm.form("load",sels[0]);
                                //方案三：使用Ajax请求数据
                                SsmRoleForm.form("load",data.rows);
                            }
                        }
                    })
                }
           }).dialog("open");
        },

        delete:function(){
            var ids=SsmRole.list.getSelectionsIds();
            if(ids.length==0){
                $.messager.alert("对话框","至少选择一行");
                return;
            }
            $.messager.confirm({
                title:'确认提示框',
                msg:'你确定要删除吗？',

                fn:function(r){
                    if(r){
                        $.ajax({
                            type:'DELETE',
                            url:SsmRole.URL.delete(ids),
                            success:function(){
                                SsmRole.list.reload();
                                SsmRole.list.clearSelectionsAndChecked();
                            }
                        })

                    }
                }
            })
        },

        getSelectionsIds:function(){
            var sels=SsmRoleList.datagrid("getSelections");
            var ids=[];
            for(var i in sels){
                ids.push(sels[i].id);
            }
            ids=ids.join(",");
            return ids;
        },

        clearSelectionsAndChecked:function(){
            SsmRoleList.datagrid("clearChecked");
            SsmRoleList.datagrid("clearSelections");
        },

        reload:function(){
            SsmRoleList.datagrid('reload');
        },
    }

}
