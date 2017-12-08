var ctx="";//
var SsmRoleList;//
var SsmRoleEdit;//
var SsmRoleForm;//

//其它组件
var SsmRoleResourceEdit;
var SsmResourceTree;

var SsmRole={
    URL:{
        inputUI:function(){
            return ctx+"/role/ui/input";
        },
        resourceUI:function () {
          return ctx+"/role/ui/roleResource";
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
        },
        resourceTree: function () {
            return ctx + "/resource/tree";
        },
        getResources: function (id) {
            return ctx + "/role/" + id + "/resources";
        },
        saveResources: function (id) {
            return ctx + "/role/" + id + "/resources";
        },
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

    resource: {
        init: function (ct, id) {
            ctx = ct;
            SsmRole.resource.initComponent();
            SsmRole.resource.initList(id);
        },
        initComponent: function () {
            SsmResourceTree = $("#SsmResourceTree");
        },
        initList: function (id) {
            SsmResourceTree.tree({
                method: 'get',
                url: SsmRole.URL.resourceTree(),
                checkbox: function (node) {
                    return true;
                },
                loadFilter:function(data){
                    var jsonStr=JSON.stringify(data);//将json对象转化为字符串
                    console.log(jsonStr)
                    jsonStr=jsonStr.replace(new RegExp('name','gm'),'text');
                    return JSON.parse(jsonStr);//将json字符串转化为json对象
                },
                onLoadSuccess: function (node, data) {
                    console.log(node)
                    console.log(data)
                    //回显资源列表
                    $.ajax({
                        type: "GET",
                        url: SsmRole.URL.getResources(id),
                        success: function (data) {
                            console.log(data)
                            console.log(data.code)
                            if (data.code == 200) {
                                //回显已有的权限

                                var root = SsmResourceTree.tree('getRoots'); // 取到树的根节点
                                for (var i in root) {
                                    for (var j in data.rows) {
                                        console.log(data.rows[j])
                                        console.info(data.rows[j]);
                                        SsmRole.resource.checkTreeNode(root, data.rows[j]);
                                    }
                                }

                            }
                        }
                    });
                }
            });
        },
        save: function () {
            var nodes = SsmResourceTree.tree('getChecked', ['checked', 'indeterminate']);
            if (nodes != null) {
                var ids = [];
                for (var i in nodes) {
                    ids.push(nodes[i].id);
                }
                ids = ids.join(",");

                $.ajax({
                    type: "POST",
                    url: SsmRole.URL.saveResources(SsmRoleList.datagrid("getSelections")[0].id),
                    data: {ids: ids},
                    success: function (data) {
                        if (data.code == 200) {
                            SsmRole.resource.close();
                        }
                    }
                });
            }
            SsmRole.resource.close();
        },
        cancel: function () {
            SsmRole.resource.close();
        },
        close: function () {
            SsmRoleResourceEdit.dialog('close');
        },
        // 递归遍历所有节点并选中
        checkTreeNode: function (children, id) {
            if (children) {
                for (var i = 0; i < children.length; i++) {
                    if (children[i].id == id) {
                        var node = SsmResourceTree.tree('find', children[i].id).target;
                        if (SsmResourceTree.tree('isLeaf', node)) {
                            SsmResourceTree.tree('check', node); // 选中树叶子节点
                        }
                        break;
                    } else {
                        if (children[i].children != null) {
                            SsmRole.resource.checkTreeNode(children[i].children, id); // 没有找到则接着遍历
                        }
                    }
                }
            }
        }
    },
    list:{
        init:function(){
            SsmRole.list.initComponent();
            SsmRole.list.initList();

        },
        initComponent:function(){
            SsmRoleList=$("#SsmRoleList");
            SsmRoleEdit=$("#SsmRoleEdit");
            SsmRoleResourceEdit = $('#SsmRoleResourceEdit');
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
        //授权
        authorize: function () {
            var sels=SsmRoleList.datagrid("getSelections");
            if(sels.length<1){
                $.messager.alert("对话框","至少选择一行");
                return ;
            }
            if(sels.length>1){
                $.messager.alert("对话框","只能选择一行");
                return ;
            }
            SsmRoleResourceEdit.dialog({
                href:SsmRole.URL.resourceUI(),
                buttons:[{
                    text:'保存',
                    iconCls:'icon-save',
                    handler:function () {
                        SsmRole.resource.save();
                    }
                },{
                    text:'取消',
                    iconCls:'icon-cancle',
                    handler:function () {
                        //SsmRole.resource.cancle();
                    }
                }],
                onLoad:function () {
                    SsmRole.resource.init(ctx, sels[0].id);
                }
            }).dialog("open");
        }

    }

}
