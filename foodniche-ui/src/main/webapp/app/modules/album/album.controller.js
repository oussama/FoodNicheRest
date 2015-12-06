angular.module('fnApp')
  .controller('AlbumListCtrl',[
    '$scope','albums',
    function($scope,albums) {
      $scope.albums = albums;
    }
  ])
  .controller('AlbumDetailCtrl',[
    '$scope','album','contents',
    function($scope,album,contents) {
      $scope.album = album;
      $scope.contents = contents;
    }
  ]);