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
		});

		$urlRouterProvider.otherwise('/');
	}]);

app.controller('homeCtrl', ['$scope','$http','$state', function($scope, $http, $state) {

	$scope.isLoggedIn = isLoggedIn;
	$scope.username = username;

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

}]);

app.controller('chatCtrl', ['$scope','$http','growl','$sce','$stateParams','$state', function($scope, $http, growl, $sce, $stateParams, $state) {
	$scope.isLoggedIn = isLoggedIn;
	$scope.username = username;
	$scope.conversationId = $stateParams.conversationId;
	$scope.messages = [];

	if(!isLoggedIn){
		$state.go('home');
	} else {
		$http.get('/loadConversation/' + $scope.conversationId).then(function(response){
			$scope.conversation = response.data.data;

			for(var i=0; i < $scope.conversation.messages.length ; i++){
				$scope.messages.push($scope.conversation.messages[i]);
			}
		});

		$http.get('/loadConversations/').then(function(response){
			$scope.allConversations = response.data.data;
		});

	}

	$scope.logout = function(){
		window.location.href = "/logout";
	};

	var stompClient = null;

	var socket = new SockJS('/messaging-socket');
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function (frame) {
		stompClient.subscribe('/topic/messaging/send/'+ $scope.conversationId, function (response) {
			var message = JSON.parse(response.body);
			$scope.messages.push(message);
			$scope.$apply();
		});
	});

	$scope.renderHTML = function(html_code)
	{
		var decoded = angular.element('<textarea />').html(html_code).text();
		return $sce.trustAsHtml(decoded);
	};

	$scope.sendMessage = function(){
		stompClient.send("/app/newMessage/" + $scope.conversationId, {}, $scope.messageModel);
		$scope.messageModel = '';
	};

}]);


