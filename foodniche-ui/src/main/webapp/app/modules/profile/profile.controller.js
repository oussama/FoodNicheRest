angular.module('fnApp')
  .controller('ProfileViewCtrl',[
    '$scope','FileUploader','Modal',
    function($scope,FileUploader,Modal) {
      var uploader = $scope.uploader = new FileUploader({
        url: 'upload'
      });

      uploader.onAfterAddingFile = function () {
        if (uploader.queue.length > 3) {
          uploader.queue.shift();
        }
      };

      var profileUploader = $scope.profileUploader = new FileUploader({
        url: 'upload'
      });

      profileUploader.onAfterAddingFile = function () {
        if (profileUploader.queue.length > 1) {
          profileUploader.queue.shift();
        }
      };

      $scope.removePhoto = function(index) {
        uploader.queue.splice(index,1);
      };
    }
  ])

  .controller('ProfileEditCtrl',[
    '$scope',
    function($scope) {
      $scope.submitted = false;

      $scope.submit = function(form) {
        $scope.submitted = true;
      }
    }
  ])

  .controller('ProfileConnectionCtrl',[
    '$scope','$state',
    function($scope,$state) {

    }
  ])

  .controller('ProfileAlbumCtrl',[
    '$scope','$state',
    function($scope,$state) {

    }
  ]);