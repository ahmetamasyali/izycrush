

app.controller('historyCtrl', ['$scope','$http','growl','$filter', function($scope,$http,growl,$filter) {
	$scope.currentPage = 0;
	$scope.pageSize = 10;
	$scope.data = [];
	$scope.q = '';

	$scope.getData = function () {
		
		return $filter('filter')($scope.data, $scope.q)
		
	}

	$scope.numberOfPages=function(){
		return Math.ceil($scope.getData().length/$scope.pageSize);                
	}
	
	$http.get("/payment/list").then(function(success){
		
		$scope.data = success.data.data;
	});
	
	$scope.selectedOrder = null;
	$scope.selectItem= function(item){
		$scope.selectedPayment = item;
		if($scope.selectedPayment.cart.discount != null){
			if($scope.selectedPayment.cart.discount.discountRule == 'PRICE_LIMIT'){
				$scope.discountMessage = 'Siparişlerin toplamı '+$scope.selectedPayment.cart.discount.priceCondition.amount+' TL';
				$scope.discountMessage += ' den fazla ise ';
			}else{
				$scope.discountMessage = 'Toplam '+$scope.selectedPayment.cart.discount.numberCondition+' veya daha fazla Siparişte';
			}
			if($scope.selectedPayment.cart.discount.discountType == 'RATIO'){
				$scope.discountMessage += '%'+$scope.selectedPayment.cart.discount.discountRatio + ' indirim';
			}else{
				$scope.discountMessage += ' 1 ürün bedava';
			}
		}else{
			$scope.discountMessage = '';
		}
		
	}
	
}]);


app.filter('startFrom', function() {
	return function(input, start) {
		start = +start; //parse to int
		return input.slice(start);
	}
});
