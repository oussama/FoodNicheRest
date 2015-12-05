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
     url: '/detail/:id',
     templateUrl: 'app/modules/recipe/views/recipe-detail.html',
     controller: 'RecipeDetailCtrl',
     authenticate: true,
     resolve: {
       recipe: [
         'Recipe','$stateParams',
         function(Recipe,$stateParams) {
           return Recipe.getOne($stateParams.id);
         }
       ]
     }
   })
   .state('recipe.create',{
     url: '/create',
     templateUrl: 'app/modules/recipe/views/recipe-create.html',
     controller: 'RecipeCreateCtrl',
     authenticate: true
   })
}]);