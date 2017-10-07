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
        },
        listUI:function(){
        },
        list:function(){
            return ctx+"/user/list";
        },
    },
    list:{
        init:function(){
            SsmUser.list.initComponet();
            SsmUser.list.initList();
        },
        initComponet:function(){
            SsmUserList=$("#SsmUserList");
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
        }
    }

}





