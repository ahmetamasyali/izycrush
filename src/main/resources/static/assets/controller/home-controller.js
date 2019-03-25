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
			url: '/chat',
			templateUrl: "assets/views/chat.html",
			controller: "chatCtrl"
		});

		$urlRouterProvider.otherwise('/');
	}]);

app.controller('homeCtrl', ['$scope','$http','growl','$sce', function($scope) {

	$scope.isLoggedIn = isLoggedIn;
	$scope.username = username;

	$scope.logout = function(){
		window.location.href = "/logout";
	}
}]);

app.controller('chatCtrl', ['$scope','$http','growl','$sce', function($scope, $http, growl, $sce) {



	$scope.isLoggedIn = isLoggedIn;
	$scope.username = username;

	$scope.logout = function(){
		window.location.href = "/logout";
	}

	var stompClient = null;

	var socket = new SockJS('/gs-guide-websocket');
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function (frame) {
		stompClient.subscribe('/topic/greetings/1', function (greeting) {
			var message = JSON.parse(greeting.body);
			$scope.messages.push(message);
			$scope.$apply();
		});
	});

	function disconnect() {
		if (stompClient !== null) {
			stompClient.disconnect();
		}
	}


	$scope.messages = [];

	$scope.renderHTML = function(html_code)
	{
		var decoded = angular.element('<textarea />').html(html_code).text();
		return $sce.trustAsHtml(decoded);
	};

	$scope.sendMessage = function(){
		stompClient.send("/app/hello/1", {}, $scope.messageModel);
		$scope.messageModel = '';
	};


}]);


