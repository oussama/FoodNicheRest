angular.module('fnApp')
  .controller('GroupCreateCtrl',[
    '$scope','$cookieStore','FileUploader','Group','UPLOAD_URL',
    function($scope,$cookieStore,FileUploader,Group,UPLOAD_URL) {
      var resetForm = function() {
        $scope.submitted = false;
        $scope.group = {
          approvedmembership: false
        };
      };

      var createGroup = function() {
        Group.create($scope.group)
          .then(function() {
            $scope.success = {
              status: true,
              message: "Create group successfully"
            };
            $scope.error = {};
            resetForm();
          },function(err) {
            $scope.error = {
              status: true,
              message: err
            };
            $scope.success = {};
          })
      };

      resetForm();

      var uploader = $scope.uploader = new FileUploader({
        url: UPLOAD_URL,
        headers: {
          'X-Auth-Token': $cookieStore.get('token')
        },
        onAfterAddingFile: function() {
          if (uploader.queue.length > 1) {
            uploader.queue.shift();
          }
        },
        onCompleteItem: function (fileItem, response, status) {
          createGroup();
        }
      });

      $scope.submit = function(form) {
        $scope.submitted = true;
        if (form.$valid) {
          if (uploader.queue.length > 1) {
            uploader.uploadAll();
          } else {
            createGroup();
          }

        }
      }
    }
  ])
  .controller('GroupListCtrl',[
    '$scope','groups',
    function($scope,groups) {
      $scope.groups = groups;
    }
  ])
  .controller('GroupDetailCtrl',[
    '$scope','group',
    function($scope,group) {
      $scope.group = group;
    }
  ]);