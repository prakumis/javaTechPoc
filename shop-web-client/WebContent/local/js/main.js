var dashboardApp = angular.module('dashboardApp', ['ui.router']);

dashboardApp.config(function($stateProvider, $urlRouterProvider) {
	$urlRouterProvider.otherwise("home");
	$stateProvider
		.state("home", {
			url: "/home",
			templateUrl: "templates/home.html",
			controller: "dashboardController",
			data: {
				customData1: "custome data 1",
				customData2: "custome data 2"
			}
		})
		.state("products", {
			url: "/products",
			templateUrl: "templates/products.html",
			controller: "dashboardController",
			data: {
				customData1: "Product's custome data 1",
				customData2: "Product's custome data 2"
			},
			abstract: true
		})
		.state("viewproduct", {
			url: "/viewproduct",
			templateUrl: "templates/viewProduct.html",
			controller: "dashboardController",
			data: {
				customData1: "Product's custome data 1",
				customData2: "Product's custome data 2"
			}
		})
		.state("viewkart", {
			url: "/viewkart",
			templateUrl: "templates/viewKart.html",
			controller: "dashboardController",
			data: {
				customData1: "Product's custome data 1",
				customData2: "Product's custome data 2"
			}
		})
		.state("products.myProduct", {
			//url: "/products/:id",
			url: "/asdfg",
			templateUrl: "templates/productDetails.html",
			controller: "productDetailsController",
			data: {
				customData3: "Product Details's custome data 1",
				customData4: "Product Details's custome data 2"
			}
		})
		.state("offers", {
			url: "/offers",
			templateUrl: "templates/offers.html",
			controller: "offersController"
		})
})