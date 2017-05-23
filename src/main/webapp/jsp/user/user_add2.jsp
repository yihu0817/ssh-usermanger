<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加用户信息</title>
</head>
<body>
	<%-- <form action="${pageContext.request.contextPath }/user_add" method="post" enctype="multipart/form-data"> --%>
	<form action="${pageContext.request.contextPath }/user_addUser" method="post">
		<input type="hidden" name="type" value="${type}"/>
		<input type="hidden" name="id" value="${user.id}"/><br>
		用户名: <input type="text" name="name" value="${user.name }"/><br><br>
		年&nbsp;&nbsp;&nbsp;&nbsp;龄: <input type="text" name="age" value="${user.age }"/><br><br>
		性&nbsp;&nbsp;&nbsp;&nbsp;别:
		<c:if test="${empty user}">
			男: <input type="radio" name="sex" value="0" checked="checked"/>
			女: <input type="radio" name="sex" value="1"/><br>
		</c:if>
		<c:if test="${user.sex == 0 }">
			男: <input type="radio" name="sex" value="0" checked="checked"/>
			女: <input type="radio" name="sex" value="1"/><br>
		</c:if>
		<c:if test="${user.sex == 1 }">
			男: <input type="radio" name="sex" value="0" />
			女: <input type="radio" name="sex" value="1" checked="checked"/><br>
		</c:if>
		<br>
		<input type="file" name="myfile"/>
		<br><br>
		<input type="submit" value="确定"/>
	</form>
</body>
</html>