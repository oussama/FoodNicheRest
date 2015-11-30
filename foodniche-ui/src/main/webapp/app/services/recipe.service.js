'use strict';
angular.module('fnApp')
  .factory('Recipe', [
    'Restangular',
    function (Restangular) {
      var _rest = Restangular.all('recipes');
      return {
        create: function(receipeObj) {
          return _rest.customPOST(receipeObj);
        },
        get: function() {
          return _rest.get("");
        }
      };
    }]);