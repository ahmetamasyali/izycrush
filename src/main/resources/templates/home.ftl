<!DOCTYPE html>
<html lang="en">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="content-type" content="text/html;charset=utf-8" />
<link rel="stylesheet" type="text/css"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"">

<!-- Website CSS style -->
<link rel="stylesheet" type="text/css" href="assets/css/main.css">
<link rel="stylesheet" type="text/css"
	href="assets/css/isteven-multi-select.css">
<link rel="stylesheet" type="text/css"
	href="assets/css/angular-growl.min.css">

<!-- Website Font style -->
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.5.0/css/all.css">

<!-- Google Fonts -->
<link href='https://fonts.googleapis.com/css?family=Passion+One'
	rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Oxygen'
	rel='stylesheet' type='text/css'>

<title>Admin</title>
</head>
<body ng-app="homeApp" ng-controller="homeCtrl">
	<div growl></div>
	<div ui-view></div>

	<script type="text/javascript">
		var isLoggedIn = ${isLoggedIn?c};
		var username = "${username!}";
	</script>
	<script type="text/javascript" src="assets/js/angular.min.js"></script>
	<script type="text/javascript" src="assets/js/ui-router.min.js"></script>
	<script type="text/javascript" src="assets/js/angular-growl.min.js"></script>
	<script type="text/javascript" src="assets/js/isteven-multi-select.js"></script>
	<script type="text/javascript" src="/webjars/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="/webjars/sockjs-client/sockjs.min.js"></script>
	<script type="text/javascript" src="/webjars/stomp-websocket/stomp.min.js"></script>

	<script type="text/javascript"
		src="assets/controller/home-controller.js"></script>
	<script type="text/javascript"
		src="assets/controller/history-controller.js"></script>
</body>
</html>