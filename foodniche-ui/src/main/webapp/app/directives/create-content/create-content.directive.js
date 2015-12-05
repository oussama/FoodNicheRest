angular.module('fnApp')
  .directive('fnCreateContent',[function() {
    return {
      restrict: 'AE',
      replace: true,
      templateUrl: 'app/directives/create-content/create-content.html',
      scope: {
        type: '@fnCreateContent',
        id: '=fnCreateContentId'
      },
      controller: [
        '$scope','$cookieStore','FileUploader','growl','Auth','Content','UPLOAD_URL','IMAGE_URL',
        function($scope,$cookieStore,FileUploader,growl,Auth,Content,UPLOAD_URL,IMAGE_URL) {
          Auth.getCurrentUserInAsync(function(user) {
            $scope.user = user;
          });

          Content.getContentType()
            .then(function(res) {
              $scope.contentTypes = res;
              resetForm();
            });

          var resetForm = function() {
            $scope.content = {
              contenttypeid: $scope.contentTypes[0].contenttypeid
            };
            $scope.submitted = false;
          };



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
            onCompleteItem: function (fileItem, file, status) {
              if (status === 200) {
                $scope.content.userid = $scope.user.userid;
                $scope.content.filename = IMAGE_URL + file.fileId;
                $scope.content.contentdate = new Date().toISOString();
                if ($scope.type === 'group') {
                  $scope.content.groupid = $scope.id;
                }
                if ($scope.type === 'business') {
                  $scope.content.businessid = $scope.id;
                }
                Content.create($scope.content)
                  .then(function() {
                    growl.addSuccessMessage("Content created successfully");
                    uploader.clearQueue();
                    resetForm();
                  },function(err) {
                    growl.addErrorMessage("Fail to create content")
                  })
              }
            }
          });

          $scope.removePhoto = function(index) {
            uploader.queue.splice(index,1);
          };

          $scope.submit = function(form) {
            $scope.submitted = true;
            if (form.$valid) {
              if (uploader.queue.length <= 0) {
                growl.addErrorMessage("Please select at least 1 photo to upload")
              } else {
                uploader.uploadAll();
              }
            }
          }
        }
      ]
    }
  }]);