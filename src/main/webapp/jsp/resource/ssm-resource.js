var ctx="";//项目部署路径
var SsmResourceList;
var SsmResourceEdit;
var SsmResourceForm;

var parentResource;

//选择图片对话框
var SsmResourceChooseIcon;
//图片textbox
var iconText;

var SsmResource={
    URL:{
        inputUI:function () {
            return ctx+"/resource/ui/input";
        },
        listUI:function () {
            return ctx+  "/resource/ui/list";
        },
        list:function () {
            return ctx+"/resource/list";
        },
        iconUI:function () {
            return ctx+"/resource/ui/icon";
        },
        tree:function () {
            return ctx+"/resource/tree";
        },
        save:function () {
            return ctx+"/resource/save";
        },
        delete:function (ids) {
            return ctx+"/resource/delete/"+ids;
        },
        get:function (id) {
            return ctx+"/resource/get/"+id;
        }
    },
    input:{
        init:function () {
            SsmResource.input.initComponent();
            SsmResource.input.initForm();
        },
        initComponent:function () {
            SsmResourceForm=$("#SsmResourceForm");
            parentResource=$("#parentResource");
            iconText=$("input[name='icon']");
            SsmResourceChooseIcon=$('#SsmResourceChooseIcon');
        },
        initForm:function () {
            SsmResourceForm.form({
                url:SsmResource.URL.save(),
                onSubmit:function () {
                    
                },
                success:function (data) {
                    var data=eval('('+data+')');
                    if(data.code==200){
                        SsmResource.input.close();
                        SsmResource.list.reload();
                    }
                }
            });
        },
        submitForm:function () {
            SsmResourceForm.submit();
        },
        close:function () {
            SsmResourceEdit.dialog('close');
        },
        setIcon:function (value) {
            iconText.textbox("setValue",value);
            globalWindow.dialog("close");
        },
        chooseIcon:function(){
            globalWindow.dialog({
                title:'选择图标',
                width:605,
                height:400,
                modal:true,
                href:SsmResource.URL.iconUI(),
                cache:false
            })
        }
    },
    list:{
        init:function (ct) {
            ctx=ct;
            SsmResource.list.initComponent();
            SsmResource.list.initList();
        },
        initComponent:function () {
            SsmResourceList=$("#SsmResourceList");
            SsmResourceEdit=$("#SsmResourceEdit");
        },
        initList:function () {
            SsmResourceList.treegrid({
                url:SsmResource.URL.list(),
                method:'get',
                //pagination:false,
                //pageSize:10,
                toolbar:'#SsmResourceToolbar',
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
                    {field: 'url', title: '资源路径', width: '10%', hidden: false},
                    {
                        field: 'openMode',
                        title: '打开方式',
                        width: '7.917%',
                        hidden: false,
                        formatter: function (value, row, index) {
                            return value == '0' ? 'ajax' : 'iframe';
                        }
                    },
                    {field: 'description', title: '资源介绍', width: '7.917%', hidden: false},
                    {
                        field: 'icon', title: '资源图标', width: '7.917%', hidden: false,
                        formatter: function (value, row, index) {
                            if (value) {
                                return '<img src="'+value+'" style="margin:0px;width: 20px;height: 20px;"/>';
                            }
                            return value;
                        }
                    },
                    {field: 'seq', title: '排序', width: '7.917%', hidden: false},
                    {
                        field: 'status',
                        title: '状态',
                        width: '7.917%',
                        hidden: false,
                        formatter: function (value, row, index) {
                            return value == 0 ? '启用' : '停用';
                        }
                    },
                    {
                        field: 'resourceType',
                        title: '资源类别',
                        width: '7.917%',
                        hidden: false,
                        formatter: function (value, row, index) {
                            return value == 0 ? '菜单' : '按钮';
                        }
                    },
                    {field: 'delFlag', title: '删除标记', width: '7.917%', hidden: true},
                    {field: 'updateTime', title: '更新时间', width: '9%', hidden: false},
                    {field: 'createTime', title: '创建时间', width: '9%', hidden: false},
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
            SsmResourceList.treegrid("reload");
        },
        collapseAll:function () {
            var roots=SsmResourceList.treegrid("getRoots");
            for (var i in roots){
                SsmResourceList.treegrid("collapseAll",roots[i].id);
            }
        },
        expandAll:function () {
            var roots=SsmResourceList.treegrid("getRoots");
            for(var i in roots){
               SsmResourceList.treegrid("expandAll",roots[i].id);
            }
        },

        add:function () {
            console.log(2222);
              SsmResourceEdit.dialog({
                  href:SsmResource.URL.inputUI(),
                  onLoad:function () {
                      console.log(333);
                      parentResource.combotree({
                          url:SsmResource.URL.tree(),
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
            var sels=SsmResourceList.treegrid("getSelections");
            if(sels.length<1){
                $.messager.alert('对话框','至少选择一行');
                return;
            }

            if(sels.length>1){
                $.messager.alert('对话框','只能选择一行');
                return;
            }

            SsmResourceEdit.dialog({
                href:SsmResource.URL.inputUI(),
                onLoad:function () {
                    //方案一：使用Form的load去load数据
                    //SsmResourceForm.form("load",SsmResource.URL.get(sels[0].id));
                    //方案二：直接使用列表的row数据
                    //SsmResourceForm.form('load',sels[0]);
                    $.ajax({
                        type: "GET",
                        url: SsmResource.URL.get(sels[0].id),
                        success: function (data) {
                            if (data.code == 200) {
                                console.log(data)
                                SsmResourceForm.form("load", data.rows);
                                parentResource.combotree({
                                    url: SsmResource.URL.tree(),
                                    method: 'get',
                                    panelHeight: 'auto',
                                    onLoadSuccess: function () {
                                        console.log(data.rows.pid)
                                        parentResource.combotree('setValue', data.rows.pid);
                                    },
                                    loadFilter:function(data){
                                        var jsonStr=JSON.stringify(data);//将json对象转化为字符串
                                        jsonStr=jsonStr.replace(new RegExp('name','gm'),'text');
                                        return JSON.parse(jsonStr);//将json字符串转化为json对象
                                    }
                                });
                            }
                        }
                    });
                }
            }).dialog("open");
        },
        delete:function () {
            var ids=SsmResource.list.getSelectionsIds();

            if(ids.length<1){
                $.messager.alert('对话框','至少选择一行');
                retrun;
            }

            $.messager.confirm({
                title:'确认提示框',
                msg:'你确定要删除吗？',
                fn:function (r) {
                    if(r){
                        $.ajax({
                            type:'DELETE',
                            url:SsmResource.URL.delete(ids),
                            success:function () {
                                SsmResource.list.reload();
                                SsmResource.list.clearSelectionsAndChecked();
                            }
                        })
                    }
                }
            })


        },
        clearSelectionsAndChecked:function () {
          SsmResourceList.treegrid("clearChecked");
          SsmResourceList.treegrid("clearSelections");
        },
        getSelectionsIds:function(){
            var sels=SsmResourceList.datagrid("getSelections");
            var ids=[];
            for (var i in sels){
                ids.push(sels[i].id);
            }
            ids=ids.join(",");
            return ids;
        }

        
    }
};

