'use strict';
angular.module('fnAppModal')
  .directive('fnRaveModal',['Modal',function(Modal) {
    return {
      restrict: 'AE',
      link: function($scope,element) {
        element.bind('click',function() {
          Modal.rave();
        })
      }
    }
  }])
  .directive('fnInviteFriendModal',['Modal',function(Modal) {
    return {
      restrict: 'AE',
      link: function($scope,element) {
        element.bind('click',function() {
          Modal.inviteFriend();
        })
      }
    }
  }])
  .directive('fnCreateProductModal',['Modal',function(Modal) {
    return {
      restrict: 'AE',
      link: function($scope,element) {
        element.bind('click',function(){
          Modal.createProduct()
        })
      }
    }
  }])
  .directive('fnLoginModal',['Modal', function(Modal) {
    return {
      restrict: 'AE',
      link: function($scope,element) {
        element.bind('click',function(){
          Modal.loginModal()
        })
      }
    }
  }]);