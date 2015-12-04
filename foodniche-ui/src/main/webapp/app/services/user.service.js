'use strict';
angular.module('fnApp')
  .factory('User', [
    'Restangular','$resource','API_URL',
    function (Restangular,$resource,API_URL) {
      return $resource(API_URL + 'api/user/:controller/:id',{
        controller: '@_controller'
      },{
        get: {
          method: 'GET',
          params: {
            controller:'profile'
          }
        },
        update: {
          method: 'PUT',
          params: {
            controller: 'profile'
          }
        },
        getMyAlbum: {
          method: 'GET',
          isArray: true,
          params: {
            controller:'my-album',
          }
        },
        getMyConnection: {
          method: 'GET',
          isArray: true,
          params: {
            controller:'my-connection'
          }
        }
      });
      //var _rest = Restangular.all('users');
      //return {
      //  createUser: function(userObj) {
      //    return _rest.customPOST(userObj);
      //  },
      //  getCurrentUser: function() {
      //    return _rest.one('3').get();
      //    //var resource = $resource('/api/user/user.json',{},{
      //    //  get: {
      //    //    method: 'GET'
      //    //  }
      //    //});
      //    //return resource.get();
      //  }
      //};
    }]);