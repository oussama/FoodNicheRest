'use strict';

// Declare app level module which depends on views, and components
angular.module('fnApp', [
  'ngCookies',
  'ngResource',
  'restangular',
  'ui.bootstrap',
  'ui.router',
  'angular-loading-bar',
  'angularFileUpload',
  'angular-growl'
]);
angular.module('fnApp').constant('API_URL', 'http://localhost:8080/');
angular.module('fnApp').constant('UPLOAD_URL', 'http://localhost:8080/api/files/image');
angular.module('fnApp').constant('IMAGE_URL', 'http://localhost:8080/api/files/image/');

angular.module('fnApp').factory('authInterceptor', function ($rootScope, $q, $cookieStore) {
  return {

    // Add authorization token to headers
    request: function (config) {

      config.headers = config.headers || {};
      if ($cookieStore.get('token')) {
        config.headers['X-Auth-Token'] = $cookieStore.get('token');
      }
      return config;
    },
    // Intercept 401s and redirect you to login
    responseError: function (response) {
      if (response.status === 401) {
        // remove any stale tokens
        $cookieStore.remove('token');
        return $q.reject(response);
      }
      else {
        return $q.reject(response);
      }
    }
  };
});

angular.module('fnApp').config([
  '$urlRouterProvider','$locationProvider','$httpProvider','RestangularProvider','growlProvider','API_URL',
  function ($urlRouterProvider,$locationProvider,$httpProvider,RestangularProvider,growlProvider,API_URL) {
    $urlRouterProvider.when('', '/');
    $urlRouterProvider.otherwise("/");
    // Enable html5 mode
    $locationProvider.html5Mode(true);
    // Set Base Url of Rest Api
    RestangularProvider.setBaseUrl(API_URL);
    $httpProvider.interceptors.push('authInterceptor');
    growlProvider.globalTimeToLive(5000);
  }
]);



angular.module('fnApp').run([
  '$rootScope','$state','$window','Auth','IMAGE_URL',
  function($rootScope,$state,$window,Auth,IMAGE_URL ) {
    $rootScope.$state = $state;
    $rootScope.IMAGE_URL = IMAGE_URL;
    $rootScope.$on('$stateChangeSuccess',function(event) {
      $window.scrollTo(0, 0);
    });
    $rootScope.$on("$stateChangeStart", function (event, toState, toParams,fromState) {
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

      if (toState.isBusiness || toState.isIndividual) {
        Auth.getCurrentUserInAsync(function(user) {
          if (user && user.userid) {
            if (user.accounttype === 0 && toState.isBusiness) {
              $state.go('profile.view');
            } else if (user.accounttype === 1 && toState.isIndividual) {
              $state.go('business.view');
            }
          }
        });
      }
    });
  }
]);

