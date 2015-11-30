'use strict';
angular.module('fnApp')
  .factory('ApiUser', [
    'Restangular',
    function (Restangular) {
      var _rest = Restangular.all('api/user');
      return {
        getMyConnection: function() {
          return _rest.get("my-connection");
        },
        getMyAlbum: function() {
          return _rest.get("my-album");
        }
      };
    }]);