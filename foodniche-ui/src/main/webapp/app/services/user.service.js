'use strict';
angular.module('fnApp')
  .factory('User', [
    'Restangular','$resource','API_URL',
    function (Restangular,$resource,API_URL) {
      return $resource(API_URL + 'users/:id',{
        id: '@_id'
      },{
        get: {
          method: 'GET',
          params: {
            id:'3'
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