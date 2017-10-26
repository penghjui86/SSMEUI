var ctx="";//项目部署路径
var SsmOrgList;
var SsmOrgEdit;
var SsmOrgForm;

var parentOrg;

var SsmOrg={
    URL:{
        inputUI:function () {
            return ctx+"/org/ui/input";
        },
        listUI:function () {
            return ctx+  "/org/ui/list";
        },
        list:function () {
            return ctx+"/org/list";
        },
        tree:function () {
            return ctx+"/org/tree";
        },
        save:function () {
            return ctx+"/org/save";
        },
        delete:function (ids) {
            return ctx+"/org/delete/"+ids;
        },
        get:function (id) {
            return ctx+"/org/get/"+id;
        }
    },
    input:{
        init:function () {
            SsmOrg.input.initComponent();
            SsmOrg.input.initForm();
        },
        initComponent:function () {
            SsmOrgForm=$("#SsmOrgForm");
            parentOrg=$("#parentOrg");
        },
        initForm:function () {
            SsmOrgForm.form({
                url:SsmOrg.URL.save(),
                onSubmit:function () {
                    
                },
                success:function (data) {
                    var data=eval('('+data+')');
                    if(data.code==200){
                        SsmOrg.input.close();
                        SsmOrg.list.reload();
                    }
                }
            });
        },
        submitForm:function () {
            SsmOrgForm.submit();
        },
        close:function () {
            SsmOrgEdit.dialog('close');
        }
    },
    list:{
        init:function (ct) {
            ctx=ct;
            SsmOrg.list.initComponent();
            SsmOrg.list.initList();
        },
        initComponent:function () {
            SsmOrgList=$("#SsmOrgList");
            SsmOrgEdit=$("#SsmOrgEdit");
        },
        initList:function () {
            SsmOrgList.treegrid({
                url:SsmOrg.URL.list(),
                method:'get',
                pagination:true,
                pageSize:10,
                toolbar:'#SsmOrgToolbar',
                singleSelect:false,
                collapsible:false,
                idField:'id',
                treeField:'name',
                parentField:'pid',
                columns:[[
                    {field:'ok',checkbox:true},
                    {field:'id',title:'主键id',hidden:true},
                    {field:'name',title:'组织名',hidden:false},
                    {field:'pid',title:'父级主键id',hidden:true},
                ]],
                onClickRow:function () {
                    
                },
                loadFilter:function (data,parentId) {
                    var opt=$(this).data().treegrid.options;
                    var parentField;

                    if(opt.parentField){
                        parentField=opt.parentField;
                        //console.log(parentField);
                        var jsonStr=JSON.stringify(data);//将json对象转化为json字符串
                        //console.log(jsonStr);
                        jsonStr=jsonStr.replace(new RegExp(parentField,"gm"),"_parentId");
                        //console.log(jsonStr);
                        return JSON.parse(jsonStr);//将json字符串转化为json对象
                    }
                }
                
            });
        },
        reload:function () {
            SsmOrgList.treegrid("reload");
        },
        collapseAll:function () {
            var roots=SsmOrgList.treegrid("getRoots");
            for (var i in roots){
                SsmOrgList.treegrid("collapseAll",roots[i].id);
            }
        },
        expandAll:function () {
            var roots=SsmOrgList.treegrid("getRoots");
            for(var i in roots){
               SsmOrgList.treegrid("expandAll",roots[i].id);
            }
        },

        add:function () {
            console.log(2222);
              SsmOrgEdit.dialog({
                  href:SsmOrg.URL.inputUI(),
                  onLoad:function () {
                      console.log(333);
                      parentOrg.combotree({
                          url:SsmOrg.URL.tree(),
                          method:'get',
                          textField:'name',
                          panelHeight:'auto',
                          //panelWidth:'auto',
                          loadFilter:function(data){
                              var jsonStr=JSON.stringify(data);//将json对象转化为json字符串
                              jsonStr=jsonStr.replace(new RegExp('name','gm'),'text');//正则表达式处理jsonStr中的字符串全局替换
                              return JSON.parse(jsonStr);//将jsonStr字符串转换为json对象后返回显示
                          }
                      })
                  },
              }).dialog("open");
        },
        edit:function () {
            var sels=SsmOrgList.treegrid("getSelections");
            if(sels.length<1){
                $.messager.alert('对话框','至少选择一行');
                return;
            }

            if(sels.length>1){
                $.messager.alert('对话框','只能选择一行');
                return;
            }

            SsmOrgEdit.dialog({
                href:SsmOrg.URL.inputUI(),
                onLoad:function () {
                    //方案一：使用Form的load去load数据
                    //SsmOrgForm.form("load",SsmOrg.URL.get(sels[0].id));
                    //方案二：直接使用列表的row数据
                    //SsmOrgForm.form('load',sels[0]);
                    $.ajax({
                        type: "GET",
                        url: SsmOrg.URL.get(sels[0].id),
                        success: function (data) {
                            if (data.code == 200) {
                                console.log(data)
                                SsmOrgForm.form("load", data.rows);
                                parentOrg.combotree({
                                    url: SsmOrg.URL.tree(),
                                    method: 'get',
                                    panelHeight: 'auto',
                                    onLoadSuccess: function () {
                                        console.log(data.rows.pid)
                                        parentOrg.combotree('setValue', data.rows.pid);
                                    },
                                    loadFilter:function(data){
                                        var jsonStr=JSON.stringify(data);//将json对象转化为字符串
                                        jsonStr=jsonStr.replace(new RegExp('name','gm'),'text');
                                        return JSON.parse(jsonStr);
                                    }
                                });
                            }
                        }
                    });
                }
            }).dialog("open");
        },



    }
};

