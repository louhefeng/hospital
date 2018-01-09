<%@ page language="java" pageEncoding="UTF-8"%>
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
<script type="text/javascript">
	function myFunction() {
		var reSubmit = $("#commit");
		//防止重复提交
		reSubmit.attr('disabled', 1);
		$.ajax({
			type : "POST",
			url : "test/saveUser",
			data : $("#userfrom").serialize(),
			error : function(request) {
				alert("失败");
				reSubmit.removeAttr('disabled');
			},
			success : function(data) {
				alert("成功");
				window.location.reload();
				reSubmit.removeAttr('disabled');
			}
		});
		return false;
	}
	
	function  gosearch(){
		
		window.location.href="test/search";
		return false;
	}
</script>
<!--//webfonts-->
</head>
<body>

	<!--SIGN IN-->
	<div class="login-form-1">
		<div class="head-info">
			<h2>信息登记</h2>
			<h3>根据国家规定进行人员信息等级，外部保密。</h3>
		</div>
		<div class="add-photo">
			<h4>
				保<br />密
			</h4>
			<input type="hidden" value="密">
		</div>
		<form id="userfrom">
			<input  name="name" type="text" class="text" value="姓名" onFocus="this.value = '';"
				onBlur="if (this.value == '') {this.value = '姓名';}">
				 <input  name="phone" 
				type="text" class="text" value="手机号" onFocus="this.value = '';"
				onBlur="if (this.value == '') {this.value = '手机号';}"> 
				<input  name="tezheng" 
				type="text" class="text" value="特征 " onFocus="this.value = '';"
				onBlur="if (this.value == '') {this.value = '特征';}">
				 <input  name="addr" 
				type="text" value="住址" onFocus="this.value = '';"
				onBlur="if (this.value == '') {this.value = '住址';}"> 
				<input  name="money" 
				type="text" value="缴费" onFocus="this.value = '';"
				onBlur="if (this.value == '') {this.value = '缴费';}"> 
				<input  name="yiliao" 
				type="text" value="治疗情况" onFocus="this.value = '';"
				onBlur="if (this.value == '') {this.value = '治疗情况';}"> 
				<input
				type="submit" id="commit" onClick="return myFunction()" value="登入信息">
				<br/>
				<br/>
				<input
				type="submit" id="commit" onClick="return gosearch()" value="去查询">
		</form>
	</div>
	<!--/SIGN IN-->
	<div class="copy-rights">
		<p>Copyright &copy; 2015</p>
	</div>

</body>
</html>