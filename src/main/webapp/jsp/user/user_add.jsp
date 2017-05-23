<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加用户信息</title>
</head>
<body>
	<s:form action="/user_addUser" method="POST">
		<s:hidden name="type" value="%{#request.type}"/>
		<s:hidden name="id" value="%{#request.user.id}"/>
		<s:textfield label="用户名" name="name" value="%{#request.user.name}"/>
		<s:textfield label="年龄" name="age" value="%{#request.user.age}"/>
		<s:radio list="#{'0':'男','1':'女'}" name="sex" label="性别" value="%{#request.user.sex}"></s:radio>
		<s:file name="myfile"/>
		<s:submit value="确定"/>
	</s:form>
</body>
</html>