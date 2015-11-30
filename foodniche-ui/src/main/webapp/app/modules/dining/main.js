angular.module('fnApp').config(['$stateProvider', function($stateProvider) {
  $stateProvider
    .state('dining',{
      url: '/dining',
      template: '<ui-view></ui-view>'
    })
    .state('dining.list',{
      url: '/',
      templateUrl: 'app/modules/dining/views/dining-list.html',
      controller: 'DiningListCtrl',
      authenticate: true
    })
    .state('dining.detail',{
      url: '/detail',
      templateUrl: 'app/modules/dining/views/dining-detail.html',
      controller: 'DiningDetailCtrl',
      authenticate: true
    })
}]);