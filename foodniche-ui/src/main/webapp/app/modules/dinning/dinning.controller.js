angular.module('fnApp')
  .controller('DinningListCtrl',[
    '$scope','types','Dinning','growl',
    function($scope,types,Dinning,growl) {
      $scope.types = types;
      var getData = function(typeid, zipcode) {
        Dinning.getRestaurants(typeid,zipcode)
          .then(function(data) {
            $scope.restaurents = data;
          },function(err) {
            growl.addErrorMessage(err);
          })
      };
      if (types.length >= 1) {
        $scope.selectedType = types[0];
        getData($scope.selectedType.businesstypeid);
      }

      $scope.restaurents = [];





      $scope.search = function() {
        getData($scope.selectedType.businesstypeid,$scope.zipcode);
      }
    }
  ]);