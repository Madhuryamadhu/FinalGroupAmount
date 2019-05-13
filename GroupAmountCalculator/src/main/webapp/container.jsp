<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Amount Calculator</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="Login and Registration Form with HTML5 and CSS3" />
<meta name="keywords" content="html5, css3, form, switch, animation, :target, pseudo-class" />
<meta name="author" content="Codrops" />
<link rel="shortcut icon" href="../favicon.ico">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/demo.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style3.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/animate-custom.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
<link href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css" rel="stylesheet">
<script src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style type="text/css">
span.error {
	color: #e15d5d;
	position: absolute;
	font-size: 12px;
}

.jumbotron {
	padding-top: 0px !important;
	padding-bottom: 0px !important;
}

.buttonall {
	background-color: #247BA0; /* Green */
	border: none;
	color: white;
	padding: 15px 32px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	margin: 4px 2px;
	cursor: pointer;
	-webkit-transition-duration: 0.4s; /* Safari */
	transition-duration: 0.4s;
}

.buttonall:hover {
	box-shadow: 0 12px 16px 0 rgba(0, 0, 0, 0.24), 0 17px 50px 0
		rgba(0, 0, 0, 0.19);
}

body {
	background-image: url("resources/image/bckgnd-img.jpg");
	-moz-background-size: cover;
	-webkit-background-size: cover;
	background-size: cover;
	background-position: top center !important;
	background-repeat: no-repeat !important;
	background-attachment: fixed;
}

h1 {
	color: #ffffff;
	!
	important
}

.jumbotron {
	background: none
}

.jumbotron .h1, .jumbotron h1 {
	font-size: 45px;
	color: white;
}

.fullBody.jumbotron.text-center {
	margin: 0px 0px 20px -38px;
}

.required {
	color: #e15d5d;
}

.error {
	color: #e15d5d;
	position: absolute;
	font-size: 12px;
}

.labelLogout {
	position: fixed;
	left: 51px;
	top: 5px;
}

img#srcProfile {
	width: 41px;
	height: 27px;
	cursor: pointer;
}

.btn-primary {
	color: #2e6da4;
	background-color: #f7f7f7;
	border-color: #2e6da4;
	width: 117px;
	padding: 9px;
	font-size: 23px;
	font-family: 'FranchiseRegular', 'Arial Narrow', Arial, sans-serif;
	font-weight: bold;
}

.dropdown-menu {
	min-width: 120px;
	background: transparent;
}

.button {
	width: 117px;
	height: 40px;
	color: #2e6da4;
	background-color: #f7f7f7;
	font-size: 13px;
	font-weight: bold;
}

input#logoutButton {
	margin-top: -12px;
}

#expensedetails {
	position: absolute;
	top: 0px;
	width: 100%;
	padding: 18px 6% 60px 6%;
	margin: 0 0 35px 0;
	background: rgb(247, 247, 247);
	border: 1px solid rgba(147, 184, 189, 0.8);
	-webkit-box-shadow: 0pt 2px 5px rgba(105, 108, 109, 0.7), 0px 0px 8px
		5px rgba(208, 223, 226, 0.4) inset;
	-moz-box-shadow: 0pt 2px 5px rgba(105, 108, 109, 0.7), 0px 0px 8px 5px
		rgba(208, 223, 226, 0.4) inset;
	box-shadow: 0pt 2px 5px rgba(105, 108, 109, 0.7), 0px 0px 8px 5px
		rgba(208, 223, 226, 0.4) inset;
	-webkit-box-shadow: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
}

input[type=checkbox] {
	transform: scale(1.5);
}

input#myCheck {
	margin: 0px 0px 18px 12px;
}

#roLable {
	margin-left: 13px !important;
}
</style>

</head>
<body>
    <header>
	 	<div class="fullBody jumbotron text-center">
			<h1 align="center">Group Amount Calculator</h1>
		</div>
	</header>
</body>
</html>