<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<meta charset="UTF-8" />
<title>Amount Calculator</title>
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
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/FileSaver.js/1.3.3/FileSaver.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/0.4.1/html2canvas.min.js"></script>
<style type="text/css">
.jumbotron {
    padding-top: 0px !important;
    padding-bottom: 0px !important;
}

span.error {
	color: #e15d5d;
	position: absolute;
	font-size: 12px;
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

.dropdown {
	position: fixed;
	right: 51px;
	top: 5px;
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

.yellowMessage {
	color: black;
	background-color: yellow;
	font-family: Courier New;
	font-size: xx-large;
}

.redMessage {
	color: black;
	background-color: red;
	font-family: Courier New;
	font-size: xx-large;
}

.greenMessage {
	color: black;
	background-color: green;
	font-family: Courier New;
	font-size: xx-large;
}

#expenseAmount {
	position: absolute;
	top: 0px;
	width: 243%;
	padding: 18px 6% 60px 6%;
	margin: 0 0 35px -356px;
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

a#screenshot {
	/* bottom: 47%; */
	font-size: 18px;
	position: relative;
	padding: 0px 0px 6px 1080px;
	font-weight: bold;
	color: #247BA0;
}

div#totAmountDetails {
	font-size: 18px;
}

.dropdown {
	position: fixed;
	right: 6px;
	top: 5px;
}
</style>
</head>
<body>
	<header>
		<div class="fullBody jumbotron text-center">
			<h1 align="center">
				Group Amount Calculator
			</h1>
		</div>
	</header>

	<div class="container">
		<!-- Codrops top bar -->

		<section>
			<div id="container_demo">
				<!-- hidden anchor to stop jump http://www.css3create.com/Astuce-Empecher-le-scroll-avec-l-utilisation-de-target#wrap4  -->
				<div id="wrapper">
					<div id="expenseAmount" class="animate form">
						<h1>Amount Refund Details</h1>
						<p>
						<div id="details" align="center">
							<div id="deatilsMessage"></div>
						</div>
						</p>

						<p align="center">
							<button id="detailsbutton" class="buttonall"
								onclick="window.location.href='amountInTable.jsp'">Details</button>
							<button id="reload2" class="buttonall"
								onclick="window.location.href='amountDetails.jsp'">Enter
								Details Again</button>
								<button id="close" class="buttonall"
								onclick="window.location.href='frontpage.jsp'" style="display:none;"><span class="fa fa-close"></span> Close</button>
						
						</p>
						</br> <a href="#" id="screenshot" onclick="generate();"><span
							class="glyphicon glyphicon-camera" style="font-size: 43px;"></span></a>

					</div>

				</div>
			</div>
		</section>
	</div>
	<div class="dropdown">
		<button type="button" class="btn btn-primary dropdown-toggle"
			data-toggle="dropdown">Account</button>
		<div class="dropdown-menu">

			<button class="button" id="logoutButton" onclick="logout()">
				<span class="glyphicon glyphicon-off"></span> LOGOUT
			</button>
			<button class="button" id="ProfileButton" onmouseover="profile()">
				<span class="glyphicon glyphicon-user"></span> PROFILE
			</button>
			<button onclick="window.location.href='ResetPassword.jsp'"
				class="button">
				<span class="glyphicon glyphicon-lock"></span> RESET PASSWORD
			</button>
			<button onclick="window.location.href='viewhistory.jsp'" class="button">
			<span class="fa fa-history"></span> VIEW HISTORY</button>
		</div>
	</div>

</body>

<script type="text/javascript">
	$(document).ready(
			function() {

				if ('${sessionScope.MAIL}' == ""
						|| '${sessionScope.MAIL}' == 'undefined'
						|| '${sessionScope.MAIL}' == null) {
					$('.dropdown').hide();
					$('#screenshot').hide();
					$('#close').show();
				}
				loadMessages();
			});

	function loadMessages() {
		var dataArrayLogin = {}
		dataArrayLogin["userId"] = '${sessionScope.USER_ID}';
		dataArrayLogin["userMail"] = '${sessionScope.MAIL}';
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "getMessages",
			data : JSON.stringify(dataArrayLogin),
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				$('#deatilsMessage').append(data.messageHtml);
			},
			error : function(e) {
				alert("ERROR: ", e);
			},
			done : function(e) {
				alert("DONE");
			}
		});

		//screenshotofAmmountsummary();
	}

	function profile() {
		$("#ProfileButton").attr('title', "Logged in as ${sessionScope.MAIL}");
	}

	function logout() {

		var dataArrayscreenshot = {};

		$
				.ajax({
					type : "POST",
					contentType : "application/json",
					url : "logout",
					data : JSON.stringify(dataArrayscreenshot),
					dataType : 'json',
					timeout : 100000,
					success : function(data) {

						window.location = "${pageContext.request.contextPath}/#tologin";
					},
					error : function(e) {
						alert("ERROR: ", e);
					},
					done : function(e) {
						alert("DONE");
					}
				});
	}

	(function(exports) {
		function urlsToAbsolute(nodeList) {
			if (!nodeList.length) {
				return [];
			}
			var attrName = 'href';
			if (nodeList[0].__proto__ === HTMLImageElement.prototype
					|| nodeList[0].__proto__ === HTMLScriptElement.prototype) {
				attrName = 'src';
			}
			nodeList = [].map.call(nodeList, function(el, i) {
				var attr = el.getAttribute(attrName);
				if (!attr) {
					return;
				}
				var absURL = /^(https?|data):/i.test(attr);
				if (absURL) {
					return el;
				} else {
					return el;
				}
			});
			return nodeList;
		}

		function screenshotPage() {
			var wrapper = document.getElementById('expenseAmount');
			html2canvas(
					wrapper,
					{
						background : '#fff',
						onrendered : function(canvas) {
							canvas
									.toBlob(function(blob) {
										saveAs(blob,
												'SummaryScreenshot-${sessionScope.USER_ID}.png');
										$('.buttonall').show();
										$('#screenshot').show();
									});
						}
					});
		}

		function addOnPageLoad_() {
			window.addEventListener('DOMContentLoaded', function(e) {
				var scrollX = document.documentElement.dataset.scrollX || 0;
				var scrollY = document.documentElement.dataset.scrollY || 0;
				window.scrollTo(scrollX, scrollY);
			});
		}

		function generate() {
			screenshotPage();
			$('.buttonall').hide();
			$('#screenshot').hide();
		}
		exports.screenshotPage = screenshotPage;
		exports.generate = generate;
	})(window);
</script>


</html>

