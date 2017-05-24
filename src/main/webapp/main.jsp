<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>后台管理中心</title>  
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/pintuer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/admin.css">
    <script src="${pageContext.request.contextPath }/static/js/jquery.js"></script>   
	<script type="text/javascript">
		$(function(){
		  $(".leftnav h2").click(function(){
			  $(this).next().slideToggle(200);	
			  $(this).toggleClass("on"); 
		  })
		  $(".leftnav ul li a").click(function(){
				$("#a_leader_txt").text($(this).text());
				$(".leftnav ul li a").removeClass("on");
				$(this).addClass("on");
		  })
		});
</script>
</head>
<body>

	<div class="header bg-main">
	  <div class="logo margin-big-left fadein-top">
		<h1><img src="${pageContext.request.contextPath }/static/images/y.jpg" class="radius-circle rotate-hover" height="50" alt="" />后台管理中心</h1>
	  </div>
	  <div class="head-l"><a class="button button-little bg-red" href="${pageContext.request.contextPath }/logout"><span class="icon-power-off"></span> 退出登录</a> </div>
	</div>
	
	<div class="leftnav">
	  <div class="leftnav-title"><strong><span class="icon-list"></span>菜单列表</strong></div>
	  <h2><span class="icon-user"></span>用户设置</h2>
	  <ul style="display:block">
		<li><a href="jsp/user/user_add.jsp" target="right"><span class="icon-caret-right"></span>添加用户</a></li>
		<li><a href="user_listUser" target="right"><span class="icon-caret-right"></span>用户列表</a></li>
	  </ul>   
	  <h2><span class="icon-pencil-square-o"></span>会员管理</h2>
	  <ul>
		<li><a href="info.html" target="right"><span class="icon-caret-right"></span>内容管理</a></li>
		<li><a href="info.html" target="right"><span class="icon-caret-right"></span>添加内容</a></li>
		<li><a href="info.html" target="right"><span class="icon-caret-right"></span>分类管理</a></li>        
	  </ul>  
	</div>
	
	<div class="rightContent">
		<ul class="bread">
		  <li><a href="jsp/user/user_add.jsp" target="right" class="icon-home"> 首页</a></li>
		  <li><a href="##" id="a_leader_txt">网站信息</a></li>
		</ul>
		<div class="admin">
		  <iframe scrolling="auto" rameborder="0" src="jsp/user/user_add.jsp" name="right" width="100%" height="100%"></iframe>
		</div>
	</div>

</body>
</html>