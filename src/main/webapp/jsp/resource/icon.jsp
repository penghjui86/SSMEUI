<%--
  Created by IntelliJ IDEA.
  User: 彭军辉
  Date: 2017/11/28
  Time: 9:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="iconDiv" style="background: #eeeeee">

</div>

<script type="text/javascript">
    function clickIcon(url) {
        SsmResource.input.setIcon(url);
    }

    $(function(){
        $.ajax({
           type:"GET",
           url:"/icons",
            success:function (data) {
                $("#iconDiv").empty();
                for (var i=0;i<data.length;i++){
                    $("#iconDiv").append('<img onclick="clickIcon(\'' + data[i] + '\')"  style="margin: 5px;" src="' + data[i] + '"/>');
                }
           }
        });
    })
</script>
