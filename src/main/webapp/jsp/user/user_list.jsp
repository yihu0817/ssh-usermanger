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
	        window.location = "${pageContext.request.contextPath}/user_deleteUser?userIds="+ userIds+"&username=${userName}&sex=${sexs}";
		}
	}
	
</script>
<style type="text/css">
    table{
    	width: 100%;
    	border: 0;
    	background-color: #cccccc;
    }
	 /*奇数行*/
	table  tr:nth-child(odd) td { background-color: #ffffff;}
	/*偶数行*/
	table  tr:nth-child(even) td { background-color: #efefef;}
</style>
</head>
<body>
	<div class="emp_contaner">
		<form action="${pageContext.request.contextPath }//user_list.servlet" method="post">
			性别:
			   <c:if test="${sexs == -1}">
				   <select name="sex">
						<option value="-1" selected="selected">---</option>
						<option value="0">男</option>
						<option value="1">女</option>
					</select>
				</c:if>
				
				 <c:if test="${sexs == 1}">
				   <select name="sex">
						<option value="-1" >---</option>
						<option value="0">男</option>
						<option value="1" selected="selected">女</option>
					</select>
				</c:if>
				 <c:if test="${sexs == 0}">
				   <select name="sex">
						<option value="-1">---</option>
						<option value="0" selected="selected">男</option>
						<option value="1">女</option>
					</select>
				</c:if>
			用户名: <input type="text" name="username"  value="${userName}"size="10"/>
			<input type="submit" value="查询"/>
		</form>
		<br>
		<table>
			<tr>
				<th>全选<input type="checkbox" id="allcheckbox" onclick="allCheckboxOne()" /></th>
				<th>编号</th>
				<th>头像</th>
				<th>名称</th>
				<th>年龄</th>
				<th>性别</th>
				<th colspan="2">操作</th>
			</tr>

			<c:forEach items="${userLists}" var="users">
		
				<tr>
					<td><input type="checkbox" name="checkbox" onclick="selectSingle();" value="${users.id}" /></td>
					<td>${users.id}</td>
					<td>
						<c:if test="${!empty users.headerUri}">
							<img src = "${pageContext.request.contextPath}/${users.headerUri}" width="80px" heigth="80px"/>
						</c:if>
						<c:if test="${empty users.headerUri}">
							<img src = "${pageContext.request.contextPath}/images/y.jpg" width="80px" heigth="80px"/>
						</c:if>
					</td>
					<td>${users.name }</td>
					<td>${users.age }</td>
					<td>
						<c:if test="${users.sex == 0 }">男</c:if>
						<c:if test="${users.sex == 1 }">女</c:if>
					</td>
					<td><a href="${pageContext.request.contextPath}/user_deleteUser?userIds=${users.id}&username=${userName}&sex=${sexs}">删除</a></td>
					<td><a href="${pageContext.request.contextPath}/user_updateUser?userId=${users.id}">修改</a></td>
				</tr>
				
			</c:forEach>
			<tr>
				<td colspan="7"><a href="#" onclick="batchDelete()">批量删除</a></td>
			</tr>
		</table>
		<br>
		${pagerUri}
	</div>
	
</body>
</html>