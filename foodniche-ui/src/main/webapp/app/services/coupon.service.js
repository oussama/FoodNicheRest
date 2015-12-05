'use strict';
angular.module('fnApp')
  .factory('Coupon', [
    'Restangular',
    function (Restangular) {
      var _rest = Restangular.all('coupons');
      var _couponTypeRest = Restangular.all('coupontypes');
      return {
        create: function(couponObj) {
          return _rest.customPOST(couponObj);
        },
        getAll: function() {
          return _rest.get("");
        },
        getCouponType: function() {
          return _couponTypeRest.get("");
        }
      };
    }]);