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
	height: 40px; 
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
						<h1>Modify Group</h1>
						<p>
							<label for="groupName" class="uname" data-icon="u">Modify Group Name<span class="required">*</span></label>
							<input id="groupName" name="groupName" required="required" type="text" placeholder="Please enter Group Name" /></br>
							<span id="warningOnecreate" class="error" style="display: block;"></span>
						</p></br>
						<p>
							<label for="groupMembers" class="youpasswd" data-icon="p">Modify Group Members<span class="required">*</span></label>
							<textarea type="text" id="groupMembers" name="groupMembers" required="required" placeholder="member1,member2......" title="Please enter group name seperated with comma"></textarea>
							</br> <span id="warningtwocreate" class="error"></span>
						</p>
						<p align="center">
							<button onclick="modifyGroup()" class="buttonall">Modify</button>
							<button onclick="window.location.href='amountDetails.jsp'" class="buttonall">Close</button>
						</p>
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
			<button onclick="window.location.href='ResetPassword.jsp'" class="button">
			<span class="glyphicon glyphicon-lock"></span>  RESET PASSWORD</button>
			<button onclick="window.location.href='viewhistory.jsp'" class="button">
			<span class="fa fa-history"></span> VIEW HISTORY</button>
		</div>
	</div>
</body>

<script type="text/javascript">
	var userID ="";
	var initGroupName="";
	var initGroupMembers="";
	var valid = false;
	$(document).ready(function() {
		userID= getUrlParameter('USER_ID');
		getGroupData(userID);
	});
	
	
	function getGroupData(userID) {
		var dataArray = {};
		dataArray["userEmail"] = '${sessionScope.MAIL}';
		dataArray["userId"] = userID;
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "getGroupDataForModify",
			data : JSON.stringify(dataArray),
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				initGroupName=data.groupName;
				initGroupMembers=data.groupMembers;
				$('#groupName').val(data.groupName);
				$('#groupMembers').val(data.groupMembers);
			},
			error : function(e) {
				alert("ERROR: ", e);
			},
			done : function(e) {
				alert("DONE");
			}
		});
	}
	
	var getUrlParameter = function getUrlParameter(sParam) {
		var sPageURL = window.location.search.substring(1), sURLVariables = sPageURL
				.split('&'), sParameterName, i;

		for (i = 0; i < sURLVariables.length; i++) {
			sParameterName = sURLVariables[i].split('=');

			if (sParameterName[0] === sParam) {
				return sParameterName[1] === undefined ? true
						: decodeURIComponent(sParameterName[1]);
			}
		}
	};
	
	function validate() {
		var groupName = $('#groupName').val();
		var groupMembers = $('#groupMembers').val();
		if (groupName == undefined || groupName == "" || groupName == null) {
			$("#warningOnecreate").text("Please Enter a Group Name");
			return false;
		} else if (groupMembers == undefined || groupMembers == ""
				|| groupMembers == null) {
			$("#warningtwocreate").text("Please Enter a Members Name");
			return false;
		} else {
			return true;
		}
	}
	
	function modifyGroup() {
		if(initGroupName==$('#groupName').val()&&initGroupMembers==$('#groupMembers').val()){
			$('#warningtwocreate').text("Please change any value to modify");
			return false;
		}
		
		valid = validate();
		if (valid) {
			var dataArray = {};
			dataArray["groupName"] = $('#groupName').val();
			dataArray["groupMembers"] = $('#groupMembers').val();
			dataArray["userEmail"] = '${sessionScope.MAIL}';
			dataArray["userId"] = userID;
			$.ajax({
				type : "POST",
				contentType : "application/json",
				url : "modifyGroup",
				data : JSON.stringify(dataArray),
				dataType : 'json',
				timeout : 100000,
				success : function(data) {
					if (data.status == 'S') {
						window.location = "viewgroup.jsp";
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
	var htmltable = "";

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
</script>
</html>
