<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6 lt8"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7 lt8"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8 lt8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<head>
<meta charset="UTF-8" />
<!-- <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">  -->
<title>Login and Registration Form with HTML5 and CSS3</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description"
	content="Login and Registration Form with HTML5 and CSS3" />
<meta name="keywords"
	content="html5, css3, form, switch, animation, :target, pseudo-class" />
<meta name="author" content="Codrops" />
<link rel="shortcut icon" href="../favicon.ico">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/demo.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/style3.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/animate-custom.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
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

#creategroup {
	position: absolute;
	top: 0px;
	width: 88%;
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

textarea#groupMembers {
	margin: 0px 0px 0px 0px;
	padding: 12px 176px 3px 33px;
	height: 43px;
}

.required {
	color: #e15d5d;
}

.error {
	color: #e15d5d;
	position: absolute;
	font-size: 12px;
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
	height: 31px;
	color: #2e6da4;
	background-color: #f7f7f7;
	font-size: 13px;
	font-weight: bold;
}

input#logoutButton {
	margin-top: -12px;
}

.dropdown {
	position: fixed;
	right: 6px;
	top: 5px;
}
.error {
	color: #e15d5d;
	position: absolute;
	font-size: 12px;
}
</style>
</head>
<body>
	<header>
		<div class="fullBody jumbotron text-center">
			<h1 align="center">
				<u> Group Amount Calculator</u>
			</h1>
		</div>
	</header>
	<div class="container">
		<!-- Codrops top bar -->
		<section>
			<div id="container_demo">
				<!-- hidden anchor to stop jump http://www.css3create.com/Astuce-Empecher-le-scroll-avec-l-utilisation-de-target#wrap4  -->
				<a class="hiddenanchor" id="toregister"></a> <a class="hiddenanchor"
					id="tologin"></a>
				<div id="wrapper">
					<div id="creategroup" class="animate form">
						<h1>MAIL</h1>
						<p>
							<label for="username" class="uname" data-icon="u">Enter
								email to reset password </label> <input id="forgotPassword"
								name="username" required="required" type="text"
								placeholder="mymail@mail.com" autocomplete="off" />
								<span id="warningOne" class="error"></span>
								
						</p>
						</br>
						<p align="center">
							<button id="create" class="buttonall" onclick="forgotpass()">ENTER</button>
							<button id="close" class="buttonall"
								onclick="window.location.href='${pageContext.request.contextPath}/#tologin'">CLOSE</button>
						</p>

					</div>
				</div>
			</div>
		</section>
	</div>

</body>

<script type="text/javascript">
	$(document).ready(function() {
	});
	var valid = false;

	function forgotpass() {
		$('.error').empty();
		valid = validate();

		if (valid) {
			var dataArrayLogin = {}
			dataArrayLogin["loginemail"] = $('#forgotPassword').val();
			$
					.ajax({
						type : "POST",
						contentType : "application/json",
						url : "forgotpass",
						data : JSON.stringify(dataArrayLogin),
						dataType : 'json',
						timeout : 100000,
						success : function(data) {
							if (data.loginmailstatus == 'S') {
								alert("Mail have been sent to registered mail id, Please check");
								window.location = "${pageContext.request.contextPath}/#tologin";
							} else if (data.loginmailstatus == 'N') {
								alert("Account Does not exist for the given email!!");
							}else{
								alert("Something went wrong, Please try again!!");
							}
						},
						error : function(e) {
							alert("ERROR: ", e);
						},
						done : function(e) {
							alert("DONE");
						}
					});
		}

	}

	var valid = false;

	function validate() {

		$('.error').hide();
		if ($('#forgotPassword').val() == null
				|| $('#forgotPassword').val() == ""
				|| $('#forgotPassword').val() == 'undefined') {

			$("#forgotPassword").after(
					"<span class='error'>Please Enter a Mail ID </span> ");
			valid = false;
		} else {
			valid = true;
		}

		return valid;
	}
</script>
</html>
