app.controller('surveyCtrl', ['$scope','$http','growl','$state', function($scope, $http, growl, $state) {
	$scope.isLoggedIn = isLoggedIn;
	$scope.username = username;

	if(!isLoggedIn){
		$state.go('home');
	}
	var loadQuestions = function(){
		$http.get('/loadSurvey/').then(function(response){
			$scope.questions = response.data.data;

			for(var i=0;i<$scope.questions.length ; i++){
				if($scope.questions[i].response != null){
					if($scope.questions[i].response) {
						$scope.questions[i].agree = "true";
					}else {
						$scope.questions[i].agree = "false";
					}
				}
			}
		});
	};

	loadQuestions();

	$scope.sendAnswers = function(){
		$scope.answers = [];
		for(var i=0;i<$scope.questions.length ; i++){
			if($scope.questions[i].agree){
				$scope.answers.push({'questionNo':$scope.questions[i].questionNo, 'response': $scope.questions[i].agree == "true"});
			}else{
				growl.error("Tüm Soruları Cevaplamalısınız!");
				return;
			}
		}
		$http.post('/saveSurvey/',$scope.answers).then(function(response){
			growl.success("BAŞARILI!");
			window.location.href = "/";
		});
	};

}]);

