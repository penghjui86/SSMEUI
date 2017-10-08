var ctx="";//项目部署路径
var SsmOrgList;
var SsmOrgEdit;
var SsmOrgForm;

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
            return ctx+"/org/tree":
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

    }
};

