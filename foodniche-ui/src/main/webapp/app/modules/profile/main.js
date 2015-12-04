angular.module('fnApp').config(['$stateProvider',function($stateProvider) {
  $stateProvider
    .state('profile',{
      url: '/profile',
      template: '<ui-view></ui-view>'
    })
    .state('profile.view',{
      url: '/:id',
      templateUrl: 'app/modules/profile/views/profile.html',
      controller: 'ProfileViewCtrl',
      authenticate: true,
      isIndividual: true,
      resolve: {
        user: [
          'User','$stateParams','$state',
          function(User,$stateParams,$state) {
            return User.get({id: $stateParams.id}).$promise
              .then(function(user) {
                if (user.accounttype !== 0) {
                  return $state.go('home')
                } else {
                  return user;
                }
              });
          }
        ]
      }
    })
    .state('profile.edit',{
      url: '/:id/edit',
      templateUrl: 'app/modules/profile/views/profile-edit.html',
      controller: 'ProfileEditCtrl',
      authenticate: true,
      isIndividual: true,
      resolve: {
        user: [
          'Auth',
          function(Auth) {
            return Auth.getCurrentUser();
          }
        ]
      }
    })
    .state('profile.connection',{
      url: '/:id/connections',
      templateUrl: 'app/modules/profile/views/profile-connection.html',
      controller: 'ProfileConnectionCtrl',
      authenticate: true,
      isIndividual: true,
      resolve: {
        connections: [
          'User',
          function(User) {
            return User.getMyConnection();
          }
        ]
      }
    })
    .state('profile.album',{
      url: '/:id/albums',
      templateUrl: 'app/modules/profile/views/profile-album.html',
      controller: 'ProfileAlbumCtrl',
      authenticate: true,
      isIndividual: true,
      resolve: {
        albums: [
          'User',
          function(User) {
            return User.getMyAlbum();
          }
        ]
      }
    })
}]);