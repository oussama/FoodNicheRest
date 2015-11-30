angular.module('fnApp')
  .directive('fnFooter',[function() {
    return {
      restrict: 'AE',
      templateUrl: 'app/directives/footer/footer.html',
      controller: [
        '$scope',
        function($scope) {

        }
      ]
    }
  }
  ]);