'use strict';
angular.module('fnApp')
  .factory('Dinning', [
    'Restangular',
    function (Restangular) {
      var _rest = Restangular.all('api/dinning');
      return {
        getTypes: function() {
          return _rest.get("types");
        },
        getRestaurants: function(typeid,zipcode) {
          var obj = {
            typeId: typeid
          };
          if (zipcode) {
            obj.zipCode = zipcode;
          }
          return _rest.one('restaurants').customPOST(obj)
        }
      };
    }]);