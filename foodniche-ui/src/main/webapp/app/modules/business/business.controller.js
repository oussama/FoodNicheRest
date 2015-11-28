angular.module('fnApp')
  .controller('BusinessViewCtrl',[
    '$scope','FileUploader','Modal',
    function($scope,FileUploader,Modal) {
      var uploader = $scope.uploader = new FileUploader({
        url: 'upload'
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
    '$scope','$state',
    function($scope,$state) {

    }
  ])

  .controller('BusinessCreateGroupCtrl',[
    '$scope','FileUploader','Group',
    function($scope,FileUploader,Group) {
      $scope.submitted = false;
      $scope.group = {
        approvedmembership: false
      };

      var uploader = $scope.uploader = new FileUploader({
        url: 'upload'
      });

      uploader.onAfterAddingFile = function() {
        if (uploader.queue.length > 3) {
          uploader.queue.shift();
        }
      };

      $scope.submit = function(form) {
        $scope.submitted = true;
        if (form.$valid) {
          Group.create($scope.group)
            .then(function(res) {
              console.log(res);
            },function(err) {
              console.log(err);
            })
        }
      }
    }
  ]);
