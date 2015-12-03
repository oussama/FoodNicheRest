angular.module('fnAppModal')
  .controller('CreateProductModalCtrl',[
    '$scope','$uibModalInstance','$cookieStore','FileUploader','growl','Auth','Product','UPLOAD_URL','IMAGE_URL',
    function($scope,$uibModalInstance,$cookieStore,FileUploader,growl,Auth,Product,UPLOAD_URL,IMAGE_URL) {
      Auth.getCurrentUserInAsync(function(user) {
        $scope.user = user;
      });

      var resetForm = function() {
        $scope.product = {};
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
        onCompleteItem: function (fileItem, file, status) {
          if (status === 200) {
            $scope.product.businessid = $scope.user.userid;
            $scope.product.photourl = IMAGE_URL + file.fileId;
            Product.create($scope.product)
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
  ]);