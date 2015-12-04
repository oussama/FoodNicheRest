'use strict';
angular.module('fnApp')
  .factory('Business', [
    'Restangular',
    function (Restangular) {
      var _businessRest = Restangular.all('business');
      var _businesstypeRest = Restangular.all('businesstypes');
      return {
        getBusinessTypes: function() {
          return _businesstypeRest.get("");
        }
      };
    }]);