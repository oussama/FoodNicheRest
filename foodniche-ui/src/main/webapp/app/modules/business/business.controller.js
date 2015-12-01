angular.module('fnApp')
  .controller('BusinessViewCtrl',[
    '$scope','FileUploader','UPLOAD_URL','$cookieStore',
    function($scope,FileUploader,UPLOAD_URL,$cookieStore) {

    }
  ])

  .controller('BusinessEditCtrl',[
    '$scope',
    function($scope) {
      $scope.submitted = false;

      $scope.submit = function(form) {
        $scope.submitted = true;
      }
    }
  ])

  .controller('BusinessConnectionCtrl',[
    '$scope','$state',
    function($scope,$state) {

    }
  ])

  .controller('BusinessAlbumCtrl',[
    '$scope','$state','albums',
    function($scope,$state,albums) {
      $scope.albums = albums;
    }
  ])


