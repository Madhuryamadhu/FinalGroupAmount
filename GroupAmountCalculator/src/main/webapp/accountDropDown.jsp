<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
<style type="text/css">
.dropdown {
	position: fixed;
	right: 6px;
	top: 5px;
}
</style>

</head>
<body>
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
			<button onclick="window.location.href='viewhistory.jsp'"
				class="button">
				<span class="fa fa-history"></span> VIEW HISTORY
			</button>
			<button onclick="window.location.href='viewgroup.jsp'"
				class="button">
				<span class="fa fa-history"></span> VIEW Group
			</button>
		</div>
	</div>
</body>
<script type="text/javascript">
var htmltable = "";

function profile() {
	$("#ProfileButton").attr('title', "Logged in as ${sessionScope.MAIL}");
}

function logout() {

	var dataArrays = {};

	$
			.ajax({
				type : "POST",
				contentType : "application/json",
				url : "logout",
				data : JSON.stringify(dataArrays),
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