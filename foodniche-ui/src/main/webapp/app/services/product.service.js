'use strict';
angular.module('fnApp')
  .factory('Product', [
    'Restangular',
    function (Restangular) {
      var _rest = Restangular.all('api/product');
      return {
        create: function(productObj) {
          return _rest.customPOST(productObj);
        },
        getAllByBusiness: function(id) {
          return _rest.get(id);
        }
      };
    }]);