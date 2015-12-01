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
        getAll: function() {
          return _rest.get("");
        },
        getOne: function(id) {
          return _rest.get(id);
        }
      };
    }]);