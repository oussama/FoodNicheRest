angular.module('fnApp').config(['$stateProvider', function($stateProvider) {
 $stateProvider
   .state('recipe',{
     url: '/recipe',
     template: '<ui-view></ui-view>'
   })
   .state('recipe.list',{
     url: '/',
     templateUrl: 'app/modules/recipe/views/recipe-list.html',
     controller: 'RecipeListCtrl',
     authenticate: true,
     resolve: {
       recipes: [
         'Recipe',
         function(Recipe) {
           return Recipe.getAll();
         }
       ]
     }
   })
   .state('recipe.detail',{
     url: '/detail',
     templateUrl: 'app/modules/recipe/views/recipe-detail.html',
     controller: 'RecipeDetailCtrl',
     authenticate: true
   })
}]);