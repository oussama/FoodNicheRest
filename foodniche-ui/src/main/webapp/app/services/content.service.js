'use strict';
angular.module('fnApp')
  .factory('Content', [
    'Restangular',
    function (Restangular) {
      var _rest = Restangular.all('content');
      return {
        create: function(contentObj) {
          return _rest.customPOST(contentObj);
        }
      };
    }]);