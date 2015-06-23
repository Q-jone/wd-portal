<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<script>
function check_all(flag){
	$("#selectZone").find(":checkbox").attr("checked",flag);
}

</script>
<div id="peopleSelectZone" title="部门人员" style="font-size:15px;">
    <div style="margin-bottom:10px;">
    <input class="btn" type='button' value='全选' onclick='check_all(true)'/>
    <input class="btn" type='button' value='清除' onclick='check_all(false)'/>
    </div>
    <div id="selectZone">
	
	</div>
</div>

    