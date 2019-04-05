var app = angular.module('homeApp', ['ui.router','angular-growl']);

app.config([
	'$stateProvider',
	'$urlRouterProvider',
	function($stateProvider, $urlRouterProvider) {

		$stateProvider
		.state('home', {
			url: '/',
			templateUrl: "assets/views/main.html",
			controller: "homeCtrl"
		}).state('chat', {
			url: '/chat/:conversationId',
			templateUrl: "assets/views/chat.html",
			controller: "chatCtrl"
		}).state('survey', {
			url: '/survey',
			templateUrl: "assets/views/survey.html",
			controller: "surveyCtrl"
		});

		$urlRouterProvider.otherwise('/');
	}]);

app.controller('homeCtrl', ['$scope','$http','$state','growl', function($scope, $http, $state, growl) {

	$scope.isLoggedIn = isLoggedIn;
	$scope.username = username;
	$scope.surveyFilled = surveyFilled;


	if(isLoggedIn && !surveyFilled){
		$state.go('survey');
	}

	$scope.logout = function(){
		window.location.href = "/logout";
	}

	if(isLoggedIn){
		$http.get("/loadAllUsers").then(function(response){
			$scope.allUsers = [];
			for(var i=0; i < response.data.data.length ; i++){
				if(response.data.data[i].username !== $scope.username ){
					$scope.allUsers.push(response.data.data[i]);
				}

			}
		});
	}

	$scope.sendMessage = function(username){
		$http.post('/createNewConversation/' + username,{}).then(function(response){
			$state.go('chat', {'conversationId': response.data.data});
		});
	};

	$scope.goToMessages = function(){
		$http.get('/loadConversations/').then(function(response){
			if(response.data.data && response.data.data[0]){
				$state.go('chat', {'conversationId': response.data.data[0].id});
			}
			else
			{
				growl.error("Şu anda bir konuşmaya dahil değilsiniz!");
			}
		});
	};

}]);

app.filter('formatdate', function($filter) {
	return function(timestamp) {
		var currentDate = new Date();
		var toFormat = new Date(timestamp);
		if(toFormat.getDate() == currentDate.getDate() && toFormat.getMonth() == currentDate.getMonth() && toFormat.getFullYear() == currentDate.getFullYear() ) {
			return 'Bugün ' + $filter('date')(toFormat.getTime(), 'H:mm')
		}
		if(toFormat.getDate() == (currentDate.getDate() - 1) && toFormat.getMonth() == currentDate.getMonth() && toFormat.getFullYear() == currentDate.getFullYear()) {
			return 'Dün ' + $filter('date')(toFormat.getTime(), 'H:mm')
		}

		return $filter('date')(toFormat.getTime(), 'EEEE H:mm')
	}
});