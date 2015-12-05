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
  })
  .filter('fnFullName',function() {
    return function(user) {
      if (user) {
        return user.firstname + " " + user.lastname;
      }
    }
  });