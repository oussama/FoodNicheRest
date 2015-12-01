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
        '$scope','$cookieStore','FileUploader','growl','Auth','Content','UPLOAD_URL',
        function($scope,$cookieStore,FileUploader,growl,Auth,Content,UPLOAD_URL) {
          Auth.getCurrentUserInAsync(function(user) {
            $scope.user = user;
          });

          var resetForm = function() {
            $scope.content = {};
            $scope.submitted = false;
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
              if (status === 200) {
                $scope.content.contentid = 3;
                $scope.content.contenttypeid = 1;
                $scope.content.userid = $scope.user.userid;
                $scope.content.filename = response.serverFileName;
                $scope.content.contentdate = new Date().toISOString();
                if ($scope.type === 'group') {
                  $scope.content.groupid = $scope.id;
                }
                Content.create($scope.content)
                  .then(function() {
                    growl.addSuccessMessage("Content created successfully");
                    uploader.clearQueue();
                    resetForm();
                  },function(err) {
                    growl.addErrorMessage(err)
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