<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户列表</title>
<!-- <link rel="stylesheet" type="text/css" href="css/list.css"> -->
<script type="text/javascript" src="js/check_all.js"></script>
<script type="text/javascript">
	function batchDelete(){
		if(isSelectDeleteData() == 0){
			alert("请选择要删除的数据");
			return false;
		}
		
		if(confirm("确定要删除吗?")){
			var names = document.getElementsByName("checkbox");
			var k = 0;
			var userIds = "";
			for (var i = 0; i < names.length; i++) {
				if (names[i].checked == true) {
					userIds += names[i].value+",";
				} 
			}
			if(userIds.length > 0){
				userIds = userIds.substring(0, userIds.length - 1); //把最后一个逗号去掉
			}
	        window.location = "${pageContext.request.contextPath}/batch_delete.servlet?userIds="+ userIds;
		}
	}
	
</script>
</head>
<body>
	<div class="emp_contaner">
		<table id="tab">
			<tr>
				<th>全选<input type="checkbox" id="allcheckbox" onclick="allCheckboxOne()" /></th>
				<th>编号</th>
				<th>名称</th>
				<th>年龄</th>
				<th>性别</th>
				<th colspan="2">操作</th>
			</tr>
			<c:forEach items="${userLists}" var="users">
				<tr>
					<td><input type="checkbox" name="checkbox" onclick="selectSingle();" value="${users.id}" /></td>
					<td>${users.id}</td>
					<td>${users.name }</td>
					<td>${users.age }</td>
					<td>
						<c:if test="${users.sex == 0 }">男</c:if>
						<c:if test="${users.sex == 1 }">女</c:if>
					</td>
					<td><a href="${pageContext.request.contextPath}/batch_delete.servlet?userIds=${users.id}">删除</a></td>
					<td><a href="${pageContext.request.contextPath}/user_update.servlet?userId=${users.id}">修改</a></td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="7"><a href="#" onclick="batchDelete()">批量删除</a></td>
			</tr>
		</table>
		<br>
		<!-- <div id="pagediv">
			<a href="#" onclick="firstPage()" style="width: 100px; display: inline; background-color: green">首页</a>
			<a href="#" onclick="prepage()" style="width: 100px; display: inline; background-color: green">上一页</a>
			<a href="#" onclick="nextPage()" style="width: 100px; display: inline; background-color: green">下一页</a>
			<a href="#" onclick="endPage()" style="width: 100px; display: inline; background-color: green">尾页</a>
		</div> -->
		${pagerUri}
	</div>
	
</body>
</html>