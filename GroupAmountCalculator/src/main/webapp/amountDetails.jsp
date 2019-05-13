<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<jsp:include page="container.jsp" />
<jsp:include page="accountDropDown.jsp"></jsp:include>
<style type="text/css">
select#groupName, select#groupMemeber {
	width: 250px;
    margin-top: 4px;
    padding: 10px 5px 10px 32px;
    border: 1px solid rgb(178, 178, 178);
    -webkit-appearance: textfield;
    -webkit-box-sizing: content-box;
    -moz-box-sizing: content-box;
    box-sizing: content-box;
    -webkit-border-radius: 3px;
    -moz-border-radius: 3px;
    border-radius: 3px;
    -webkit-box-shadow: 0px 1px 4px 0px rgba(168, 168, 168, 0.6) inset;
    -moz-box-shadow: 0px 1px 4px 0px rgba(168, 168, 168, 0.6) inset;
    box-shadow: 0px 1px 4px 0px rgba(168, 168, 168, 0.6) inset;
    -webkit-transition: all 0.2s linear;
    -moz-transition: all 0.2s linear;
    -o-transition: all 0.2s linear;
    transition: all 0.2s linear;
}





	
}

span#warningOneGroup, span#warningOneName {
	position: relative;
}

div#groupNameDiv, div#groupMembersDiv {
	margin: 0px 0px 30px 0px;
}


</style>
</head>
<body>
	<div class="container">
		<section>
			<div id="container_demo">
				<div id="wrapper">
					<div id="expensedetails" class="animate form">
						<h1>Expense Details</h1>
						<p id="checkbox">
							<label for="exists" class="exists"> Do you want to select from existing group??</label>
							<input type="checkbox" id="myCheck" required="required">
						</p>
						<div id="groupNameDiv">
							<div class="row">
								<label for="groupName" class="uname" id="roLable">Groups<span
									class="required">*</span></label> <br>
								<div class="col-lg-8">
									<select id="groupName" class="groupName"
										title="Please Select the Group from DropDown"></select><br> <span
										id="warningOneGroup" class="error"></span>
								</div>
								<div class="col-lg-4">
									<button onclick="createNewGroup()" title="Create New Group"
										class="btn">
										<span class="fa fa-plus-square" style="font-size: 20px;"></span>
									</button>
								</div>
							</div>
						</div>
						<div id="groupMembersDiv">
							<label for="groupMemeber" class="uname">Name<span
								class="required">*</span></label><br> <select id="groupMemeber"
								class="groupMemeber"
								title="Please Select the Members from Database" style="width : 92%;"></select><br> <span
								id="warningOneName" class="error"></span>
						</div>


						<p id="namediv">
							<label for="username" class="uname"> Name <span
								class="required">*</span></label> <input id="name" name="username"
								required="required" type="text" placeholder="myname"
								autocomplete="off" /><br> <span id="warningOne" class="error"></span><br>
						</p>
						<p>
							<label for="amount" class="amount"> Amount <span
								class="required">*</span></label> <input id="amount" name="amount"
								required="required" type="text" placeholder="Amount"
								autocomplete="off" /><br> <span id="warningTwo" class="error"></span><br>
						</p>
						<p>
							<label for="reason" class="reason"> Reason </label> <input
								id="reason" name="reason" type="text" placeholder="Reason"
								autocomplete="off" />
						</p>
						<br>
						<p align="center">
							<button onclick="submit()" class="buttonall">Submit</button>
							<button onclick="haveMoreDetails()" class="buttonall">Have
								More</button>
							<button id="close" class="buttonall"
								onclick="window.location.href='frontpage.jsp'"
								style="display: none;">
								<span class="fa fa-close"></span> Close
							</button>

						</p>
					</div>
				</div>
			</div>
		</section>
	</div>
</body>

<script type="text/javascript">
	var nameAmountMap = new Object();

	var x;
	var html = "";
	var count = 1;
	var html2 = "";
	var totalAmount = 0;
	var finalAmountHtml = "";
	var perHeadAmount = 0;
	var finalDetailList = "";
	var name = "";
	var amount = "";
	var reason = "";
	var status = 0;

	var nameList = [];
	var amountList = [];
	var reasonList = [];
	var isExistingGroup = false;

	$(document).ready(
			function() {

				$('#groupNameDiv').hide();
				$('#groupMembersDiv').hide();
				$('.error').empty();

				if ('${sessionScope.MAIL}' == ""
						|| '${sessionScope.MAIL}' == 'undefined'
						|| '${sessionScope.MAIL}' == null) {
					$('.dropdown').hide();
					$('#checkbox').hide();
					$('#groupNameDiv').hide();
					$('#groupMembersDiv').hide();
					$('#close').show();
				}

			});

	$('input[type="checkbox"]').click(function() {
		if ($(this).prop("checked") == true) {
			isExistingGroup = true;
			loadGroupNames();
			$('#namediv').hide();
			$('#groupNameDiv').show();
			$('#groupMembersDiv').show();
			$('#groupMemeber').attr("disabled", true);
		} else if ($(this).prop("checked") == false) {
			nameList = [];
			amountList = [];
			reasonList = [];
		
			isExistingGroup = false;
			$('#namediv').show();
			$('#groupNameDiv').hide();
			$('#groupMembersDiv').hide();
		}
	});

	$("#groupName").change(function() {
		loadGroupMembers($('#groupName').val());
	});

	

	function createNewGroup() {
		window.location = "${pageContext.request.contextPath}/createNewGroup.jsp";
	}

	function loadGroupNames() {
		$('.error').empty();
		var dataArray = {}
		dataArray["userEmail"] = '${sessionScope.MAIL}';
		dataArray["userId"] = '${sessionScope.USER_ID}';
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "loadGroupNames",
			data : JSON.stringify(dataArray),
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				if (data.status == 'S') {
					$('#groupName').append(data.dropDownOptions);
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

	function loadGroupMembers(groupId) {
		$('.error').empty();
		var dataArray = {}
		dataArray["userEmail"] = '${sessionScope.MAIL}';
		dataArray["userId"] = '${sessionScope.USER_ID}';
		dataArray["groupId"] = groupId;
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "loadGroupMembers",
			data : JSON.stringify(dataArray),
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				if (data.status == 'S') {
					$('#groupMemeber').empty();
					$('#groupMemeber').attr("disabled", false);
					$('#groupMemeber').append(data.dropDownOptions);
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
	
	function haveMoreDetails() {
		$('.error').empty();

		var name = "";
		if (isExistingGroup) {
			name = $('#groupMemeber').val();
			var group = $('#groupName').val();

			if (group == undefined || group == "" || group == null) {
				$("#warningOneGroup").text("Please Enter a Group Name");
				return false;
			} else if (name == undefined || name == "" || name == null) {
				$("#warningOneName").text("Please select a name");
				return false;
			}
		} else {
			name = $('#name').val();
		}
		var amount = $('#amount').val();
		var reason = $('#reason').val();
		var group = $('#groupName').val();

		if (validate(name, amount)) {
			addToList(name, amount, reason);
			$('#name').val('');
			$('#amount').val('');
			$('#reason').val('');
		} else {
			return false;
		}

	}

	function validate(name, amount) {
		$('#warningOne').val('');
		$('#warningTwo').val('');
		$('.error').empty();

		if (name == undefined || name == "") {
			$("#warningOne").text("Please select a name");
			return false;
		} else if (amount == undefined || amount == "" || amount == null) {
			$("#warningTwo").text("Amount cannot be blank");
			return false;
		} else if (!(/^\d+$/.test(amount))) {
			$("#warningTwo").text("Amount should be a number");
			return false;
		}
		return true;
	}

	function addToList(name, amount, reason) {
		nameList.push(name);
		amountList.push(amount);
		if (reason == undefined || reason == "" || reason == null)
			reason = "-";
		reasonList.push(reason);
	}

	function submit() {
		$('.error').empty();
		var name = "";

		if (isExistingGroup) {
			name = $('#groupMemeber').val();
			var group = $('#groupName').val();

			if (group == undefined || group == "" || group == null) {
				$("#warningOneGroup").text("Please Enter a Group Name");
				return false;
			} else if (name == undefined || name == "" || name == null) {
				$("#warningOneName").text("Please select a name");
				return false;
			}
		} else {
			name = $('#name').val();
		}
		var amount = $('#amount').val();
		var reason = $('#reason').val();

		if (validate(name, amount)) {
			addToList(name, amount, reason);
		} else {
			return false;
		}

		var groupname = $('#groupName').val();

		var dataArray = {}
		dataArray["userMail"] = '${sessionScope.MAIL}';
		dataArray["name"] = nameList;
		dataArray["amount"] = amountList;
		dataArray["reason"] = reasonList;
		dataArray["groupname"] = $("#groupName option[value=" + groupname + "]")
				.text();
		groupName
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "amountSubmit",
			data : JSON.stringify(dataArray),
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				if (data.status == 'S') {
					window.location = "amountInSentence.jsp";
				} else if (data.loginmailstatus == 'F') {
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

</script>


</html>

