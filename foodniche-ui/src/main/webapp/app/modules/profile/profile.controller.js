angular.module('fnApp')
  .controller('ProfileViewCtrl',[
    '$scope','FileUploader','$cookieStore','UPLOAD_URL',
    function($scope,FileUploader,$cookieStore,UPLOAD_URL) {
      var uploader = $scope.uploader = new FileUploader({
        url: UPLOAD_URL,
        onAfterAddingFile: function (file) {
          if (uploader.queue.length > 3) {
            uploader.queue.shift();
          }
          file.upload();
        },
        onBeforeUploadItem: function (fileItem) {
          fileItem.headers = {
            fileName: fileItem.name
          };
        }
      });

      var profileUploader = $scope.profileUploader = new FileUploader({
        url: UPLOAD_URL,
        headers: {
          'X-Auth-Token': $cookieStore.get('token')
        }
      });

      profileUploader.onAfterAddingFile = function (file) {
        if (profileUploader.queue.length > 1) {
          profileUploader.queue.shift();
        }
        file.upload();
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