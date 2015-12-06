angular.module('fnApp').config(['$stateProvider',function($stateProvider) {
  $stateProvider
    .state('album',{
      url: '/album',
      template: "<ui-view></ui-view>",
      authenticate: true,
      isBusiness: true
    })
    .state('album.list',{
      url: '/:id',
      templateUrl: 'app/modules/album/views/album-list.html',
      controller: 'AlbumListCtrl',
      authenticate: true,
      isBusiness: true,
      resolve: {
        albums: [
          'Album',
          function(Album) {
            return Album.getAll();
          }
        ]
      }
    })
    
    .state('album.detail',{
      url: '/detail/:id',
      templateUrl: 'app/modules/album/views/album-detail.html',
      controller: 'AlbumDetailCtrl',
      authenticate: true,
      isBusiness: true,
      resolve: {
        album: [
          'album','$stateParams',
          function(album,$stateParams) {
            return album.getOne($stateParams.id);
          }
        ],
        contents: [
          'Content','$stateParams',
          function(Content,$stateParams) {
            return Content.getAlbumContent($stateParams.id);
          }
        ]
      }
    });
  }
]);
