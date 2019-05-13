<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<jsp:include page="container.jsp" />
</head>
<body>
	<div class="container">
		<section>
			<div id="container_demo">
				<a class="hiddenanchor" id="toregister"></a>
				<a class="hiddenanchor" id="tologin"></a>
				<div id="wrapper">
					<div id="login" class="animate form">
						<h1>Log in</h1>
						<p>
							<label for="username" class="uname" data-icon="u">Your
								email <span class="required">*</span>
							</label> <input id="username" name="username" required="required"
								type="text" placeholder="mymail@mail.com" autocomplete="off" />
						</p>
						<br>
						<p>
							<label for="password" class="youpasswd" data-icon="p">
								Your password <span class="required">*</span>
							</label> <input id="password" name="password" required="required"
								type="password" placeholder="eg. X8df!90EO" autocomplete="off" />
						</p>
						<p>
							<a href="#" class="to_register" id="forgotPassword"
								onclick="window.location.href='Passwordmail.jsp'">Forgot Password</a>
						</p>
						<p class="login button">
							<button id="login1" onclick="LoginSubmit()" class="buttonall">LOGIN</button>
						</p>

						<p class="change_link">
							Not a member yet ? <a href="#toregister" class="to_register">Join
								us</a>
						</p>
					</div>

					<div id="register" class="animate form">
						<h1>Sign up</h1>
						<p>
							<label for="usernamesignup" class="uname" data-icon="u">Your
								User Name <span class="required">*</span>
							</label> <input id="usernamesignup" name="usernamesignup"
								required="required" type="text" placeholder="mysuperusername690"
								autocomplete="off" />
						</p>
						<br>
						<p>
							<label for="emailsignup" class="youmail" data-icon="e">
								Your email <span class="required">*</span>
							</label> <input id="emailsignup" name="emailsignup" required="required"
								type="email" placeholder="mysupermail@mail.com"
								autocomplete="off" />
						</p>
						<br>
						<p>
							<label for="passwordsignup" class="youpasswd" data-icon="p">Your
								password <span class="required">*</span>
							</label> <input id="passwordsignup" name="passwordsignup"
								required="required" type="password" placeholder="eg. X8df!90EO"
								autocomplete="off" />
						</p>
						<br>
						<p>
							<label for="passwordsignup_confirm" class="youpasswd"
								data-icon="p">Please confirm your password <span
								class="required">*</span></label> <input id="passwordsignup_confirm"
								name="passwordsignup_confirm" required="required"
								type="password" placeholder="eg. X8df!90EO" autocomplete="off" />
						</p>
						<br>
						<p class="signin button">
							<button id="create" class="buttonall" onclick="signUpSubmit()">JOIN</button>
						</p>
						<p class="change_link">
							Already a member ? <a href="#tologin" class="to_register"> Go
								and log in </a>
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

	var validlogin = false;
	function validatelogin() {

		$('.error').hide();

		if ($('#username').val() == null || $('#username').val() == ""
				|| $('#username').val() == 'undefined') {

			$("#username").after(
					"<span class='error'>Please Enter a Email ID </span>");
			validlogin = false;
		} else if (!(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test($(
				'#username').val()))) {
			$("#username")
					.after(
							"<span class='error'>Please Enter a valid Email ID </span>");
			validlogin = false;
		} else if ($('#password').val() == null || $('#password').val() == ""
				|| $('#Loginpsw').val() == 'undefined') {

			$("#password").after(
					"<span class='error'>Please Enter a Password </span> ");
			validlogin = false;
		} else {
			validlogin = true;
		}

		return validlogin;

	}

	function LoginSubmit() {

		$('.error').hide();

		validlogin = validatelogin();

		if (validlogin) {
			var dataArrayLogin = {}
			//You need to take data from the fields using Jquery i.e $('#ID').val()
			dataArrayLogin["loginemail"] = $('#username').val();
			dataArrayLogin["loginpassword"] = $('#password').val();
			//similarly set other fields

			$
					.ajax({
						type : "POST",
						contentType : "application/json",
						url : "login",
						data : JSON.stringify(dataArrayLogin),
						dataType : 'json',
						timeout : 100000,
						success : function(data) {
							if (data.loginmailstatus == 'F') {
								$("#username")
										.after(
												"<span class='error'>Email ID not exists </span> ");
							} else if (data.loginmailstatus == 'S') {
								if (data.loginmailandpasswordstatus == 'S') {
									window.location = "amountDetails.jsp";
								} else if (data.loginmailandpasswordstatus == 'F') {
									$("#password")
											.after(
													"<span class='error'>Incorrect Password </span> ");
								}

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

	//........................................................signup

	var valid = false;

	function validate() {

		$('.error').hide();

		//name validtion
		if ($('#usernamesignup').val() == null
				|| $('#usernamesignup').val() == ""
				|| $('#usernamesignup').val() == 'undefined') {

			$("#usernamesignup").after(
					"<span class='error'>Please Enter a Name </span>");
			valid = false;
		} else if ($('#emailsignup').val() == null
				|| $('#emailsignup').val() == ""
				|| $('#emailsignup').val() == 'undefined') {

			$("#emailsignup").after(
					"<span class='error'>Please Enter a Email ID </span>");
			valid = false;
		} else if (!(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test($(
				'#emailsignup').val()))) {
			$("#emailsignup")
					.after(
							"<span class='error'>Please Enter a valid Email ID </span>");
			valid = false;
		} else if ($('#passwordsignup').val() == null
				|| $('#passwordsignup').val() == ""
				|| $('#passwordsignup').val() == 'undefined') {

			$("#passwordsignup").after(
					"<span class='error'>Please Enter a Password </span> ");
			valid = false;
		} else if ($('#passwordsignup_confirm').val() == null
				|| $('#passwordsignup_confirm').val() == ""
				|| $('#Cpsw').val() == 'undefined') {
			$("#passwordsignup_confirm")
					.after(
							"<span class='error'>Please Enter a Confirm Password </span> ");
			valid = false;
		} else if (!($('#passwordsignup').val() == $('#passwordsignup_confirm')
				.val())) {
			$("#passwordsignup_confirm").after(
					"<span class='error'>Passwords do not match</span> <br/>");
			valid = false;
		} else {
			valid = true;
		}

		return valid;
	}
	function signUpSubmit() {

		$('.error').hide();
		//check if password and password2 are equal.If equal continue else return false and show a message for user.
		valid = validate();
		//do the null check for the mandatory fields
		if (valid) {

			var dataArray = {}
			//You need to take data from the fields using Jquery i.e $('#ID').val()
			dataArray["name"] = $('#usernamesignup').val();
			dataArray["password"] = $('#passwordsignup').val();
			dataArray["email"] = $('#emailsignup').val();
			//similarly set other fields

			$
					.ajax({
						type : "POST",
						contentType : "application/json",
						url : "signup",
						data : JSON.stringify(dataArray),
						dataType : 'json',
						timeout : 100000,
						success : function(data) {

							if (data.emailstatus == 'F') {
								alert(" SUCESS!!!");
								window.location = "#tologin";
							} else if (data.emailstatus == 'S') {

								$("#emailsignup")
										.after(
												"<span class='error'>Email already exists,pls login</span><br/><br/>");
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
</script>
</html>
