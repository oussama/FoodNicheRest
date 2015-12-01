angular.module('fnApp')
  .directive('fnHeader',[function() {
    return {
      restrict: 'AE',
      templateUrl: 'app/directives/header/header.html',
      controller: [
        '$scope','Auth',
        function($scope,Auth) {
          Auth.getCurrentUserInAsync(function(user) {
            $scope.user = user;
          });
          $scope.isLoggedIn = Auth.isLoggedIn;
        }
      ]
    }
  }
]);