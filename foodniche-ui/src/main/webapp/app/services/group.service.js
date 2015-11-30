'use strict';
angular.module('fnApp')
  .factory('Group', [
    'Restangular',
    function (Restangular) {
      var _rest = Restangular.all('api/groups');
      return {
        create: function(groupObj) {
          return _rest.customPOST(groupObj);
        },
        getAll: function() {
          return _rest.get("");
        },
        getOne: function(id) {
          return _rest.get(id);
        }
      };
    }]);