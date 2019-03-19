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
		}).state('cart', {
			url: '/cart',
			templateUrl: "assets/views/cart.html",
			controller: "homeCtrl"
		}).state('history', {
			url: '/history',
			templateUrl: "assets/views/history.html",
			controller: "historyCtrl"
		});

		$urlRouterProvider.otherwise('/');
	}]);



app.controller('homeCtrl', ['$scope','$http','growl', function($scope,$http,growl) {
	
	$scope.paymentMethods = ['CASH','CREDIT_CART','SODEXO','TICKET'];
	$scope.paymentMethod = 'CASH';
	
	$scope.isLoggedIn = isLoggedIn;
	$scope.username = username;

	$scope.logout = function(){
		window.location.href = "/logout";
	}

	

	$scope.getCartListSuccess = function(success){
		$scope.cart = success.data.data;
		if($scope.cart.discount != null){
			if($scope.cart.discount.discountRule == 'PRICE_LIMIT'){
				$scope.discountMessage = 'Siparişlerin toplamı '+$scope.cart.discount.priceCondition.amount+' TL';
				$scope.discountMessage += ' den fazla ise ';
			}else{
				$scope.discountMessage = 'Toplam '+$scope.cart.discount.numberCondition+' veya daha fazla Siparişte';
			}
			if($scope.cart.discount.discountType == 'RATIO'){
				$scope.discountMessage += '%'+$scope.cart.discount.discountRatio + ' indirim';
			}else{
				$scope.discountMessage += ' 1 ürün bedava';
			}
		}else{
			$scope.discountMessage = '';
		}
		$scope.additionsInput = [];
		for(var i=0;i<$scope.cart.orders.length;i++){
			$scope.additionsInput[i] = $scope.additions;
		}

	}
	$scope.removeFromCart = function(order){
		$http.post("/cart/remove",order).then($scope.getCartListSuccess);
	}

	$scope.updateCart = function(cart){
		for(var i=0;i<cart.orders.length;i++){
			if(cart.orders.quantity == null || cart.orders.quantity<1){
				cart.orders.quantity = 1;
			}
		}
		
		$http.post("/cart/update",cart).then($scope.getCartListSuccess);
	}

	if($scope.isLoggedIn){
		$http.get("cart/get").then($scope.getCartListSuccess);
	}
	

	$http.get("additions").then(function(success){
		if( success.data.status == 'SUCCESS'){
			$scope.error = false;
			$scope.additions = success.data.data;
			
		}else{
			$scope.error = true;
			$scope.message = success.data.message;
		}
	});
	
	

	$http.get("drinks").then(function(success){
		if( success.data.status == 'SUCCESS'){
			$scope.drinks = success.data.data;
			$scope.additionsInDrinksArray = [];
			for(var i=0;i<$scope.drinks.length;i++){
				$scope.additionsInDrinksArray[i] = [];
			}
		}else{
			$scope.error = true;
			$scope.message = success.data.message;
		}
	});
	$scope.addToCart = function(drink,drinkIndex){
		if(!$scope.isLoggedIn){
			window.location.href = "/login";
		}
		var order = {'drink':drink,'quantity':1,additions:[]};
		if(order.quantity == null || order.quantity<1){
			order.quantity = 1;
		}
		for(var i=0;i<$scope.additions.length;i++){
			if($scope.additionsInDrinksArray[drinkIndex][i]){
				order.additions.push($scope.additions[i]);
				$scope.additionsInDrinksArray[drinkIndex][i] = false;
			}
		}
		$http.post("/cart/add",order).then(function(success){
			growl.success("Sepete eklendi");
			$scope.cart = success.data.data;
		});

	}
	
	$scope.calculate = function(order){
		if(order == null){
			return 0;
		}
		var totalPrice = order.drink.price.amount;
		for(var i=0;i<order.additions.length;i++){
			totalPrice+=order.additions[i].price.amount;
		}
		return totalPrice;
	}
	
	$scope.checkOut = function(cart){
		if($scope.paymentMethod != null){
			$scope.makingPayment = true;
			$http.post("/payment/makePayment?paymentMethod="+$scope.paymentMethod,cart).then(function(){
				growl.success("Sipariş Yola Çıktı");
				$scope.cart = success.data.data;
			});
		}
	}


}]);

