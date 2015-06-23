<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title></title>

       	<script src="../../js/jquery-1.7.1.js"></script>
		<script src="../../js/jquery.formalize.js"></script>


		<script type="text/javascript">


		</script>

</head>

<body>
	<div>
	<select class="fr" id="oldDept" name="oldDept">
		<s:iterator value="#session.oldDeptList" id="d">
			<option <s:if test="#session.oldDeptId==#d.id">selected</s:if>
			value="<s:property value="#d.id" />"><s:property value="#d.name" /></option>
		</s:iterator>

    </select>
	</div>
</body>
</html>
