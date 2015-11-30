angular.module('fnApp').config(['$stateProvider',function($stateProvider) {
  $stateProvider
    .state('profile',{
      url: '/profile',
      template: '<ui-view></ui-view>'
    })
    .state('profile.view',{
      url: '/',
      templateUrl: 'app/modules/profile/views/profile.html',
      controller: 'ProfileViewCtrl',
      authenticate: true
    })
    .state('profile.edit',{
      url: '/edit',
      templateUrl: 'app/modules/profile/views/profile-edit.html',
      controller: 'ProfileEditCtrl',
      authenticate: true
    })
    .state('profile.connection',{
      url: '/my-connection',
      templateUrl: 'app/modules/profile/views/profile-connection.html',
      controller: 'ProfileConnectionCtrl',
      authenticate: true
    })
    .state('profile.album',{
      url: '/my-album',
      templateUrl: 'app/modules/profile/views/profile-album.html',
      controller: 'ProfileAlbumCtrl',
      authenticate: true
    })
}]);