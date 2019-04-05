app.controller('surveyCtrl', ['$scope','$http','growl', function($scope, $http, growl) {
	$scope.isLoggedIn = isLoggedIn;
	$scope.username = username;


	var loadQuestions = function(){
		$http.get('/loadSurvey/').then(function(response){
			$scope.questions = response.data.data;

			for(var i=0;i<$scope.questions.length ; i++){
				if($scope.questions[i].response){
					$scope.questions[i].agree = $scope.questions[i].response;
					$scope.questions[i].disagree = !$scope.questions[i].response;
				}
			}
		});
	};

	loadQuestions();

	$scope.sendAnswers = function(){
		$scope.answers = [];
		for(var i=0;i<$scope.questions.length ; i++){
			if($scope.questions[i].agree == true || $scope.questions[i].disagree == true){
				$scope.answers.push({'questionNo':$scope.questions[i].questionNo, 'response': $scope.questions[i].agree == true});
			}else{
				growl.error("Tüm Soruları Cevaplamalısınız!");
				return;
			}
		}
		$http.post('/saveSurvey/',$scope.answers).then(function(response){
			growl.success("BAŞARILI!");
			$state.go('home');
		});
	};

}]);

