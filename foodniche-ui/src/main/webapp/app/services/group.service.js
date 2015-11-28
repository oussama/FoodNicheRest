'use strict';
angular.module('fnApp')
  .factory('Group', [
    'Restangular',
    function (Restangular) {
      var _rest = Restangular.all('groups');
      return {
        create: function(groupObj) {
          return _rest.customPOST(groupObj);
        },
      };
    }]);