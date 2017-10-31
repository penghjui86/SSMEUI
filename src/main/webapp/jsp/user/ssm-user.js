var ctx="";//项目部署工程路名

var SsmUserList;//列表
var SsmUserEdit;//编辑
var SsmUserForm;//表单

var SsmUserOrgTree;//用户机构树
var SsmUserOrg;//
var SsmUserRole;//

var SsmUser={
    URL:{
        inputUI:function(){
            return "/user/ui/input";
        },
        listUI:function(){
        },
        list:function(){
            return ctx+"/user/list";
        },
        orgTree:function () {
            return "/org/tree";
        },
        roleListAll:function () {
            return "/role/all";
        },
        save:function () {
            return "/user/save";
        },
        get:function (id) {
            return "/user/get/"+id;
        },
        delete:function (ids) {
            return "/user/delete/"+ids;
        }
    },
    input:{
          init:function () {
              SsmUser.input.initComponent();
              SsmUser.input.initForm();
          },
          initComponent:function () {
              SsmUserForm=$("#SsmUserForm");
              SsmUserOrg=$("#SsmUserOrg");
              SsmUserRole=$("#SsmUserRole");
          },
          initForm:function () {
              SsmUserForm.form({
                  url:SsmUser.URL.save(),
                  onSubmit:function () {
                        //check
                      //return false 阻止submit
                  },
                  success:function (data) {
                        var data=eval('('+data+')')
                        if(data.code==200){
                            SsmUser.input.close();
                            SsmUser.list.reload();
                        }
                  }
              });
          },
          submitForm:function () {
                 console.log('1233');
                SsmUserForm.submit();
          },
          close:function () {
                SsmUserEdit.dialog("close");
          }
    },
    list:{
        init:function(){
            SsmUser.list.initComponet();
            SsmUser.list.initOrgTree();
            SsmUser.list.initList();
        },
        initComponet:function(){
            SsmUserList=$("#SsmUserList");
            SsmUserEdit=$("#SsmUserEdit");
            SsmUserOrgTree=$("#SsmUserOrgTree");
        },
        initList:function(){
            SsmUserList.datagrid({
                url:SsmUser.URL.list(),
                method:'get',
                pagination:true,
                pageSize:10,
                singleSelect:false,
                collapsible:true,
                columns:[[
                    {field:'ok',checkbox:true},
                    {field:'id',title:'主键id',hidden:true},
                    {field:'loginName',title:'登录名',hidden:false},
                    {field:'name',title:'用户名',hidden:false},
                    {field:'password',title:'密码',hidden:true},
                    {field:'sex',title:'性别',hidden:false,formatter:function(value,row,index){return value==0?'男':'女';}},
                    {field:'age',title:'年龄',hidden:false},
                    {field:'phone',title:'手机',hidden:false},
                    {field:'userType',title:'用户类别',hidden:false,formatter:function(value,row,index){return value==0?'用户':'管理员';}},
                    {field:'status',title:'用户状态',hidden:false,formatter:function(value,row,index){return value==0?'启用':'停用';}},
                    {field:'updateTime',title:'更新时间',hidden:false},
                    {field:'createTime',title:'创建时间',hidden:false},
                ]],
            });
        },
        initOrgTree:function () {
            SsmUserOrgTree.tree({
                method:'get',
                url:SsmUser.URL.orgTree(),
                loadFilter:function (data) {
                    var jsonStr=JSON.stringify(data);
                    jsonStr = jsonStr.replace(new RegExp("name","gm"),"text");
                    return JSON.parse(jsonStr);
                },
                onSelect:function (node) {
                    if(SsmUserOrgTree.tree('isLeaf',node.target)){
                        SsmUserList.datagrid({
                            queryParams:{orgId:node.id}
                        })
                    }
                },
            })
        },
        reload:function () {
          SsmUserList.datagrid("reload");
        },
        //添加
        add:function () {
            SsmUserEdit.dialog({
                href:SsmUser.URL.inputUI(),
                onLoad:function () {
                    SsmUserOrg.combotree({
                        url:SsmUser.URL.orgTree(),
                        method:'get',
                        panelHeight:'auto',
                        loadFilter:function (data) {
                            //console.log(data)
                            var jsonStr=JSON.stringify(data);
                            jsonStr=jsonStr.replace(new RegExp("name","gm"),"text");
                            return JSON.parse(jsonStr);
                        }
                    });
                    SsmUserRole.combobox({
                        url:SsmUser.URL.roleListAll(),
                        method:'get',
                        panelHeight:'auto'
                    });
                }
            }).dialog("open");
        },
        //修改
        edit:function () {
            var sels=SsmUserList.datagrid("getSelections");
            if(sels.length<1){
                $.messager.alert("对话框","至少选则一行");
                return;
            }
            if(sels.length>1){
                $.messager.alert("对话框","只能选择一行");
                return;
            }
            SsmUserEdit.dialog({
                href:SsmUser.URL.inputUI(),
                onLoad:function () {
                    //方案一：使用Form的load去load数据
                   // SsmUserForm.form('load',SsmUser.URL.get(sels[0].id));
                    //方案二：直接使用列表的row数据
                    //SsmUserForm.form('load',sels[0]);
                    //方案三：使用Ajax请求数据

                    $.ajax({
                        type:'GET',
                        url:SsmUser.URL.get(sels[0].id),
                        success:function (data) {
                            console.log(data);
                            if(data.code==200){
                                SsmUserForm.form("load",data.rows);
                                SsmUserOrg.combotree({
                                    url:SsmUser.URL.orgTree(),
                                    method:'get',
                                    panelHeight:'auto',
                                    loadFilter:function (data) {
                                        var jsonStr=JSON.stringify(data);
                                        jsonStr=jsonStr.replace(new RegExp("name","gm"),"text");
                                        return JSON.parse(jsonStr);
                                    },
                                    onLoadSuccess:function () {
                                        if(data.rows.orgIds!=null){
                                            SsmUserOrg.combotree('setValues',data.rows.orgIds);
                                        }

                                    }
                                });
                                SsmUserRole.combobox({
                                    url:SsmUser.URL.roleListAll(),
                                    method:'get',
                                    panelHeight:'auto',
                                    onLoadSuccess:function () {
                                        if(data.rows.roleIds!=null){
                                            SsmUserRole.combobox('setValues',data.rows.roleIds);
                                        }

                                    }
                                });
                            }

                        }
                    })

                }
            }).dialog("open");
        },
        delete:function () {
            var ids=SsmUser.list.getSelectionsIds();
            //console.log(ids);
            if (ids.length==0){
                $.messager.alert("对话框","至少选择一行");
                return;
            }
            $.messager.confirm({
               title:'确认提示框',
                msg:'你确定要删除吗？',
                fn:function (r) {
                    if(r){
                       $.ajax({
                           type:'DELETE',
                           url:SsmUser.URL.delete(ids),
                           success:function () {
                               SsmUser.list.reload();
                               SsmUser.list.clearSelectionsAndChecked();
                           }
                       })
                    }
                }
            });
        },
        getSelectionsIds:function () {
            var sels=SsmUserList.datagrid("getSelections");
            var ids=[];
            for (var i in sels){
                ids.push(sels[i].id);
            }
            ids=ids.join(",");
            return ids;
        },
        clearSelectionsAndChecked:function () {
            SsmUserList.datagrid("clearChecked");
            SsmUserList.datagrid("clearSelections");
        }
    }

}





