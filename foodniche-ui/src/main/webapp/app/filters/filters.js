'use strict';
angular.module('fnApp')
  .filter('fnLike',function() {
    return function(input) {
      if (!isNaN(input)) {
        if (input > 1) {
          return input + " likes"
        }
        return input + " like"
      }
    }
  });