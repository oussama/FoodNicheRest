angular.module('fnApp').config(['$stateProvider', function($stateProvider) {
  $stateProvider
    .state('dinning',{
      url: '/dinning',
      template: '<ui-view></ui-view>'
    })
    .state('dinning.list',{
      url: '/',
      templateUrl: 'app/modules/dinning/views/dinning-list.html',
      controller: 'DinningListCtrl',
      authenticate: true,
      resolve: {
        types: [
          'Dinning',
          function(Dinning) {
            return Dinning.getTypes();
          }
        ]
      }
    });
    //.state('dinning.detail',{
    //  url: '/detail',
    //  templateUrl: 'app/modules/dining/views/dinning-detail.html',
    //  controller: 'DinningDetailCtrl',
    //  authenticate: true,
    //  resolve: {
    //    types: [
    //      'Dinning',
    //      fu
    //    ]
    //  }
    //})
}]);