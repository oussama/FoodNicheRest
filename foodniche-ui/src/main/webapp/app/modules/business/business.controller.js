angular.module('fnApp')
  .controller('BusinessViewCtrl',[
    '$scope','FileUploader','UPLOAD_URL','$cookieStore',
    function($scope,FileUploader,UPLOAD_URL,$cookieStore) {
      var uploader = $scope.uploader = new FileUploader({
        url: UPLOAD_URL,
        headers: {
          'X-Auth-Token': $cookieStore.get('token')
        }
      });

      uploader.onAfterAddingFile = function() {
        if (uploader.queue.length > 3) {
          uploader.queue.shift();
        }
    
      };
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


