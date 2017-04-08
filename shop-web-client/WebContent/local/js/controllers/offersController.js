//var dashboardApp = angular.module('dashboardApp', []);

dashboardApp.controller("offersController", function($scope, $http, $log) {
	$scope.message = "Angular JS Offers";
	$log.info("message11222222222: "+$scope.message);
	
	$scope.offers = ["Offer 1", "Offer 2", "Offer 3", "Offer 4"];
});
