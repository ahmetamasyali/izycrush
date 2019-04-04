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

