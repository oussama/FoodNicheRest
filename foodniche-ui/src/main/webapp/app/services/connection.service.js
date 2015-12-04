'use strict';
angular.module('fnApp')
  .factory('Connection', [
    'Restangular',
    function (Restangular) {
      var _rest = Restangular.all('connections');
      return {
        create: function(connectionObj) {
          return _rest.customPOST(connectionObj);
        }
      };
    }]);