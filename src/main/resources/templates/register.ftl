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
<link rel="stylesheet" type="text/css" href="assets/css/angular-growl.css">

<!-- Google Fonts -->
<link href='https://fonts.googleapis.com/css?family=Passion+One'
	rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Oxygen'
	rel='stylesheet' type='text/css'>

<title>Kayıt Ol</title>
</head>
<body ng-app="registerApp" ng-controller="registerCtrl">
	<div class="container">
		<div class="row main">
			<div class="panel-heading">
				<div class="panel-title text-center">
					<a href="/"><h1 class="title">Izycrush</h1></a>
					<hr />
				</div>
			</div>
			<div class="main-login main-center">
				<form name="myform" class="form-horizontal" method="post" action="#">
					<div class="alert alert-success" ng-show="!error && message">
						<strong>{{message}}!</strong>
					</div>
					<div class="alert alert-danger" ng-show="error && message">
						<strong> {{message}}</strong>
					</div>
					<div class="form-group">
						<label for="name" class="cols-sm-2 control-label">İsim</label>
						<div class="cols-sm-10">
							<p class="required-text" ng-show="myform.name.$error.required">*Ad Zorunludur</p>
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-user fa"
									aria-hidden="true"></i></span> <input type="text" class="form-control"
									ng-model="user.name" name="name" id="name" ng-required="true"
									placeholder="İsminizi Girin" />
							</div>
						</div>
					</div>

					<div class="form-group">
						<label for="username" class="cols-sm-2 control-label">Kullanıcı Adı</label>
						<div class="cols-sm-10">
							<p class="required-text" ng-show="myform.username.$error.required">*Kullanıcı Adı Zorunludur</p>
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-users fa"
									aria-hidden="true"></i></span> <input type="text" class="form-control"
									ng-model="user.username" name="username" id="username" ng-required="true"
									placeholder="Kullanıcı Adınızı Girin" />
							</div>
						</div>
					</div>

					<div class="form-group">
						<label for="password" class="cols-sm-2 control-label">Şifre</label>
						<div class="cols-sm-10">
							<p class="required-text" ng-show="myform.password.$error.required">*Şifre Zorunludur</p>
							<div class="input-group">
								<span class="input-group-addon"><i
									class="fa fa-lock fa-lg" aria-hidden="true"></i></span> <input
									ng-model="user.password" type="password" class="form-control" ng-required="true"
									name="password" id="password" placeholder="Şifrenizi Girin" />
							</div>
						</div>
					</div>

					<div class="form-group">
						<label for="confirm" class="cols-sm-2 control-label">Şifreyi Tekrar Gir</label>
						<div class="cols-sm-10">
							<p class="required-text" ng-show="myform.confirm.$error.required">*Şifreyi Tekrar Gir Zorunludur</p>
							<div class="input-group">
								<span class="input-group-addon"><i
									class="fa fa-lock fa-lg" aria-hidden="true"></i></span> <input
									ng-model="user.passwordConfirm" type="password" ng-required="true"
									class="form-control" name="confirm" id="confirm"
									placeholder="Şifrenizi Doğrulayın" />
							</div>
						</div>
					</div>

					<div class="form-group">
						<label for="confirm" class="cols-sm-2 control-label">Profil Resmi</label>
						<div class="cols-sm-10">
							<p class="required-text" ng-show="!profileImage">*Profil Resmi Zorunludur</p>
							<div class="input-group">
								<span class="input-group-addon"><i
											class="fa fa-upload fa-lg" aria-hidden="true"></i></span>
								<input ng-show="!profileImage" title="your text" class="custom-file-input" type='file' maxsize="5120" required ng-model='profileImage' base-sixty-four-input>
								<img ng-if="profileImage" src="data:image/png;base64,{{profileImage.base64}}" class="rounded-circle user_img_msg">

							</div>
							<button ng-show="profileImage" style="width: 20%;" type="button" ng-click="profileImage = null"
									class="btn btn-danger btn-sm btn-block">Sil</button>
						</div>
					</div>

					<div class="form-group">
						<label for="description" class="cols-sm-2 control-label">Hakkımda</label>
						<div class="cols-sm-10">
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-users fa"
																   aria-hidden="true"></i></span> <textarea type="text" class="form-control"
																										 ng-model="user.description" name="description" id="description"
																											placeholder="Hakkımdayı Doldurun" ></textarea>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="description" class="cols-sm-2 control-label">Cinsiyet</label>
						<div class="cols-sm-10">
							<p class="required-text" ng-show="myform.gender.$error.required">*Cinsiyet Zorunludur</p>
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-users fa"
																   aria-hidden="true"></i></span>
								<select name="gender" ng-model="user.gender" ng-required="true">
									<option value="MALE">Erkek</option>
									<option value="FEMALE">Kadın</option>
								</select>
							</div>
						</div>
					</div>

					<div class="form-group ">
						<button type="button" ng-disabled="!myform.$valid" ng-click="register()"
							class="btn btn-success btn-lg btn-block login-button">Kayıt Ol</button>
					</div>
					<div class="login-register">
						<a class="btn btn-primary" href="login">Giriş Yap</a>
					</div>
				</form>
			</div>
		</div>
	</div>

	<script type="text/javascript" src="assets/js/angular.min.js"></script>
	<script type="text/javascript" src="assets/js/angular-growl.min.js"></script>
	<script type="text/javascript" src="assets/js/angular-base64-upload.min.js"></script>
	<script type="text/javascript" src="assets/controller/register-controller.js"></script>


</body>
<div growl></div>
</html>