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

	$http.get("/loadAllUsers").then(function(success){
		$scope.allUsers = [];
		for(var i=0; i < success.data.length ; i++){
			$scope.allUsers.add(success.data[i]);
		}
	});
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
		stompClient.subscribe('/messaging/send/1', function (greeting) {
			var message = JSON.parse(greeting.body);
			$scope.messages.push(message);
			$scope.$apply();
		});
	});

	$scope.messages = [];

	$scope.renderHTML = function(html_code)
	{
		var decoded = angular.element('<textarea />').html(html_code).text();
		return $sce.trustAsHtml(decoded);
	};

	$scope.sendMessage = function(){
		stompClient.send("/app/newMessage/1", {}, $scope.messageModel);
		$scope.messageModel = '';
	};

}]);


