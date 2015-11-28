'use strict';

// Declare app level module which depends on views, and components
angular.module('fnApp', [
  'ngCookies',
  'ngResource',
  'restangular',
  'ui.bootstrap',
  'ui.router',
  'angular-loading-bar',
  'angularFileUpload'
]);
angular.module('fnApp').constant('API_URL', 'http://localhost:8080/FoodNicheRest/');

angular.module('fnApp').config([
  '$urlRouterProvider','$locationProvider','RestangularProvider','API_URL',
  function ($urlRouterProvider,$locationProvider,RestangularProvider,API_URL) {
    $urlRouterProvider.when('', '/');
    $urlRouterProvider.otherwise("/");
    // Enable html5 mode
    $locationProvider.html5Mode(true);
    // Set Base Url of Rest Api
    RestangularProvider.setBaseUrl(API_URL);
  }
]);



angular.module('fnApp').run([
  '$rootScope','$state','$window','Auth',
  function($rootScope,$state,$window,Auth ) {
    $rootScope.$state = $state;

    $rootScope.$on("$stateChangeSuccess", function (event, toState, fromState) {
      $window.scrollTo(0, 0);

      if (toState.authenticate) {
        Auth.getCurrentUserInAsync(function(user) {

          if (!user || !user.userid) {
            $state.go('home');
          }
        });
      }

      if (toState.unauthenticated) {
        Auth.getCurrentUserInAsync(function(user) {
          if (user && user.userid) {
            if (user.accounttype === 0) {
              $state.go('profile.view');
            } else {
              $state.go('business.view');
            }
          }
        });
      }
    });
  }
]);

