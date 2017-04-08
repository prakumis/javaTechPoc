var dashboardApp = angular.module('dashboardApp', ['ngRoute']);

dashboardApp.config(function($routeProvider, $locationProvider) {
	$routeProvider
		.when("/home", {
			templateUrl: "templates/home.html",
			controller: "dashboardController"
		})
		.when("/products", {
			templateUrl: "templates/products.html",
			controller: "dashboardController"
		})
		.when("/offers", {
			templateUrl: "templates/offers.html",
			controller: "offersController"
		})
		.otherwise({
            redirectTo: '/home'
        })
	/*$locationProvider.html5Mode({
		enabled: true,
		requireBase: false
	});*/
})