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

app.controller('homeCtrl', ['$scope','$http','$state','growl', function($scope, $http, $state, growl) {

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

app.controller('chatCtrl', ['$scope','$http','growl','$sce','$stateParams','$state', function($scope, $http, growl, $sce, $stateParams, $state) {
	$scope.isLoggedIn = isLoggedIn;
	$scope.username = username;
	$scope.conversationId = $stateParams.conversationId;
	$scope.messages = [];

	$scope.openChat = function(conversationId){
		$state.go('chat', {'conversationId': conversationId});
	};
	$scope.loading = true;
	if(!isLoggedIn){
		$state.go('home');
	} else {
		$http.get('/loadConversation/' + $scope.conversationId).then(function(response){
			$scope.conversation = response.data.data;

			for(var i=0; i < $scope.conversation.messages.length ; i++){
				$scope.messages.push($scope.conversation.messages[i]);
			}
			setTimeout(
				function()
				{
					var scroll=$('#message_card');
					scroll.animate({scrollTop: scroll.prop("scrollHeight")},0);
					$scope.loading = false;
				}, 10);

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

	$scope.onEnterKeyPress = function (event) {
		if (event.charCode == 13)
			$scope.sendMessage();
	};

}]);


app.filter('formatdate', function($filter) {
	return function(timestamp) {
		var currentDate = new Date()
		var toFormat = new Date(timestamp)
		if(toFormat.getDate() == currentDate.getDate() && toFormat.getMonth() == currentDate.getMonth() && toFormat.getFullYear() == currentDate.getFullYear() ) {
			return 'Bugün ' + $filter('date')(toFormat.getTime(), 'H:mm')
		}
		if(toFormat.getDate() == (currentDate.getDate() - 1) && toFormat.getMonth() == currentDate.getMonth() && toFormat.getFullYear() == currentDate.getFullYear()) {
			return 'Dün ' + $filter('date')(toFormat.getTime(), 'H:mm')
		}

		return $filter('date')(toFormat.getTime(), 'EEEE H:mm')
	}
})