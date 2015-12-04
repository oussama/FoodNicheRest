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
              return cb();
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
              currentUser = User.get(function() {
                deferred.resolve(data);
                return cb();
              });
            }).
            error(function (err) {
              deferred.reject(err);
              return cb(err);
            }.bind(this));

          return deferred.promise;
        },
        updateProfile: function(user,callback) {
          var cb = callback || angular.noop;
          return User.update(user,
            function (data) {
              currentUser = User.get(function() {
                return cb(data);
              });
            },
            function (err) {
              return cb(err);
            }.bind(this)).$promise;
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
        getCurrentUserInAsync: function(callback) {
          var cb = callback || angular.noop;
          if (currentUser.hasOwnProperty('$promise')) {
            currentUser.$promise.then(function(user) {
              cb(currentUser);
              return user
            }).catch (function() {
              cb(null);
              return {};
            });
          } else if (currentUser.hasOwnProperty('userid')) {
            cb(currentUser) ;
            return currentUser
          } else {
            cb(null);
            return {}
          }
        },
      }
    }]);
