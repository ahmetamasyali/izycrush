<!DOCTYPE html>
<html lang="tr">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="content-type" content="text/html;charset=utf-8" />
<link rel="stylesheet" type="text/css"
	href="assets/css/bootstrap.min.css">

<!-- Website CSS style -->
<link rel="stylesheet" type="text/css" href="assets/css/main.css">

<!-- Website Font style -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">

<!-- Google Fonts -->
<link href='https://fonts.googleapis.com/css?family=Passion+One'
	rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Oxygen'
	rel='stylesheet' type='text/css'>

<title>Giriş Yap</title>
</head>
<body ng-app="loginApp" ng-controller="loginCtrl">
	<div class="row main">
		<div class="panel-heading">
			<div class="panel-title text-center">
				<a href="/"><h1 class="title">Izycrush</h1></a>

				<hr />
			</div>
		</div>
		<div class="main-login main-center">
			<div class="form-login">

				<div class="alert alert-success" ng-show="!error && message">
					<strong>{{message}}!</strong>
				</div>
				<div class="alert alert-danger" ng-show="error && message">
					<strong> {{message}}</strong>
				</div>
				<input type="text" id="userName"
					class="form-control input-sm chat-input" placeholder="Kullanıcı Adı"
					ng-model="user.username" /> </br> <input id="userPassword"
					class="form-control input-sm chat-input" type="password"
					placeholder="Şifre" ng-model="user.password" / /> </br>
				<div class="wrapper">
					<span class="group-btn"> <a ng-click="login()"
						style="margin-right: 10px" class="btn btn-success btn-md">Giriş Yap
							<i class="fa fa-sign-in"></i> <a href="register"
							class="btn btn-primary btn-md">Kayıt Ol <i
								class="fa fa-sign-in"></i></a></span>

				</div>
			</div>
		</div>
	</div>


	<script type="text/javascript" src="assets/js/angular.min.js"></script>
	<script type="text/javascript"
		src="assets/controller/login-controller.js"></script>
</body>
</html>