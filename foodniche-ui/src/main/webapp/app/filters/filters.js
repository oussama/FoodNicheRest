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
  })
  .filter('fnHours',function() {
    return function(input) {
      var hours = Math.floor(input);
      var minute = Math.round((input - hours) * 60) || '00';
      return hours + ':' +  minute + ' hours'
    }
  });