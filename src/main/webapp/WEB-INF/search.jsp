<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>iOS 7登录和注册应用程序响应小部件设计</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="application/x-javascript">
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 
</script>
<meta name="keywords"
	content="iOS 7 Login And Register App Responsive Templates, Iphone Widget Template, Smartphone login forms,Login form, Widget Template, Responsive Templates, a Ipad 404 Templates, Flat Responsive Templates" />
<link href="css/style.css" rel='stylesheet' type='text/css' />
<!--webfonts-->
<link
	href='http://fonts.googleapis.com/css?family=Lato:100,300,400,700,900,300italic'
	rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Calligraffitti'
	rel='stylesheet' type='text/css'>

<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
<!--//webfonts-->
</head>
<body>

	<!--SIGN IN-->
	<div class="login-form-1">
		<form id="userfrom"  action="search">
			姓名：<input  name="name" type="text" class="text" /><br/>
				手机号: <input  name="phone" 
				type="text" class="text" /> </br>
				<input
				type="submit" id="commit" onClick="return myFunction()" value="查询">
				<a href="/hospital">去录入</a>
		</form>
	</div>
	<div>
	<table border=1>
			<tr>
				<th>姓名</th>
				<th>手机号</th>
				<th>特征</th>
				<th>住址</th>
				<th>缴费(分)</th>
				<th>治疗情况</th>
				<th>时间</th>
			</tr>
		<c:forEach items="${users}" var="user" varStatus="vs"> 
			<tr>
				<td>${user.name}</td>
				<td>${user.phone}</td>
				<td>${user.tezheng}</td>
				<td>${user.addr}</td>
				<td>${user.money}</td>
				<td>${user.yiliao}</td>
				<td><fmt:formatDate value="${user.createtime}" pattern="yyyy年MM月dd日HH点mm分ss秒" /> </td>
			</tr>
		</c:forEach>
	</table>
	</div>

</body>
</html>