app.controller('surveyCtrl', ['$scope','$http','growl', function($scope, $http, growl) {
	$scope.isLoggedIn = isLoggedIn;
	$scope.username = username;

	$http.get('/loadQuestions/').then(function(response){
		$scope.questions = response.data.data;
	});

	

}]);

