angular.module('fnApp').config(['$stateProvider',function($stateProvider) {
  $stateProvider
    .state('product',{
      url: '/product',
      template: '<ui-view></ui-view>'
    })
    .state('product.detail',{
      url: '/detail',
      templateUrl: 'app/modules/product/views/product-detail.html',
      controller: 'ProductDetailCtrl'
    })
}]);