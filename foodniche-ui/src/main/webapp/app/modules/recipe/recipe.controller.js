angular.module('fnApp')
  .controller('RecipeListCtrl',[
    '$scope','recipes',
    function($scope,recipes) {
      $scope.recipes = recipes;
    }
  ])
  .controller('RecipeDetailCtrl',[
    '$scope',
    function($scope) {

    }
  ]);