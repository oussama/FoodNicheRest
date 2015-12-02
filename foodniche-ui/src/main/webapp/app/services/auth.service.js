'use strict';

angular.module('fnApp')
  .factory('Auth', [
    '$location', '$rootScope', '$http','$q', 'User', '$cookieStore','API_URL','$timeout',
    function ($location, $rootScope, $http,$q, User, $cookieStore,API_URL,$timeout) {
      var currentUser = {};
      if ($cookieStore.get('token')) {
        currentUser = User.get();
      }
      //var _rest = Restangular.all('auth');
      return {
        register: function (user,callback) {
          var cb = callback || angular.noop;
          $http.post(API_URL + 'auth/register',user)
            .success(function() {
              return cb(user);
            })
            .error(function(err) {
              this.logout();
              return cb(err);
            }.bind(this));
        },
        login: function(user,callback) {
          var cb = callback || angular.noop;
          var deferred = $q.defer();

          $http.post(API_URL + 'auth/login',user).
            success(function (data) {
              $cookieStore.put('token', data.data.token);
              currentUser = User.get();
              deferred.resolve(data);
              return cb();
            }).
            error(function (err) {
              deferred.reject(err);
              return cb(err);
            }.bind(this));

          return deferred.promise;
        },
        logout: function() {
          $cookieStore.remove('token');
          currentUser = {};
        },
        isLoggedIn: function () {
          return currentUser.hasOwnProperty('userid');
        },
        isLoggedInAsync: function (cb) {
          if (currentUser.hasOwnProperty('$promise')) {
            currentUser.$promise.then(function () {
              cb(true);
            }).catch(function () {
              cb(false);
            });
          } else if (currentUser.hasOwnProperty('role')) {
            cb(true);
          } else {
            cb(false);
          }
        },
        getCurrentUser: function () {
          return currentUser;
        },
        getCurrentUserInAsync: function(cb) {
          if (currentUser.hasOwnProperty('$promise')) {
            currentUser.$promise.then(function() {
              cb(currentUser);
            }). catch (function() {
              cb(null);
            });
          } else if (currentUser.hasOwnProperty('userid')) {
            cb(currentUser) ;
          } else {
            cb(null);
          }
        },
      }
    }]);
