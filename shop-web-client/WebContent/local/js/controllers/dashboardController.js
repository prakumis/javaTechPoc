//var dashboardApp = angular.module('dashboardApp', []);

dashboardApp.controller("dashboardController", function($scope, $http, $log, $state) {
	$scope.message = "Angular JS Tutorial New";
	/*$http.get('http://www.google.com')
		.success(function(response) {
			$scope.message =  response.data;
		})
		.error(function(data, status, headers, config) {
			$log.error("status1: "+status);
			$scope.message = status;
		});*/
	$log.info("message11222222222: "+$scope.message);
	
	$scope.products = ["Electronics", "Cloths", "Books", "Groceries", "additional"];
	
	$scope.customData1 = $state.current.data.customData1;
});

dashboardApp.controller("productDetailsController", function($scope, $http, $log, $state) {
	$scope.message = "Angular JS Tutorial in productDetailsController";
	/*$http.get('http://www.google.com')
		.success(function(response) {
			$scope.message =  response.data;
		})
		.error(function(data, status, headers, config) {
			$log.error("status1: "+status);
			$scope.message = status;
		});*/
	$log.info("productDetailsController: "+$scope.message);
	

		$scope.products = [ "Electronics_productDetailsController",
			"Cloths_productDetailsController",
			"Books_productDetailsController",
			"Groceries_productDetailsController" ];
	
	$scope.customData1 = $state.current.data.customData1;
	$scope.customData2 = $state.current.data.customData2;
	$scope.customData3 = $state.current.data.customData3;
	$scope.customData4 = $state.current.data.customData4;
});
