angular.module('fnApp')
  .controller('DinningListCtrl',[
    '$scope','types','Dinning','growl',
    function($scope,types,Dinning,growl) {
      $scope.types = types;
      $scope.selectedType = types[0];

      $scope.restaurents = [];

      var getData = function(typeid, zipcode) {
        Dinning.getRestaurants(typeid,zipcode)
          .then(function(data) {
            $scope.restaurents = data;
          },function(err) {
            growl.addErrorMessage(err);
          })
      };

      getData($scope.selectedType.businesstypeid);

      $scope.search = function() {

      }
    }
  ]);