var app = angular.module('registerApp', ['naif.base64','angular-growl']);

app.controller('registerCtrl',['$scope', '$http','growl', function($scope, $http, growl) {

	$scope.register = function(){
		if(!$scope.profileImage || !$scope.profileImage.base64){
			growl.error("Profil Resmi Zorunludur");
			return;
		}

		if($scope.profileImage.filetype != "image/png"){
			growl.error("Sadece png formatı kabul edilir");
			return;
		}

		$scope.user.profileImage = $scope.profileImage.base64;
		var postObject = JSON.stringify($scope.user);

		$http({
			method: 'POST',
			url: '/register',
			data: postObject,
			headers: {
		        "Content-Type": "application/json"
		    }
		}).then(function (success){
			 if(success.data){
				 if( success.data.status == 'SUCCESS'){
					 $scope.message = 'BAŞARILI!';
					 $scope.error = false;
					 setTimeout(function() {
						 window.location.href = "/login";
						}, 500);
				 }else{
					 $scope.error = true;
					 $scope.message = success.data.message;
				 }
				
				
			 }
		},function (error){
			 $scope.error = true;
			 $scope.message = 'Something Went Wrong :(';
		});
	}

}]);