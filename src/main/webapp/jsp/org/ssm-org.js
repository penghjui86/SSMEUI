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

        },
        initComponent:function () {

        },
        initForm:function () {

        },
        submitForm:function () {

        },
        close:function () {

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
          SsmOrgEdit.dialog({
              href:SsmOrg.URL.inputUI(),
              onLoad:function () {
                  parentOrg.combotree({
                      url:SsmOrg.URL.tree(),
                      method:'get',
                      panelHeight:'auto'
                  }).dialog("open");
              },
          })
        },

    }
};

