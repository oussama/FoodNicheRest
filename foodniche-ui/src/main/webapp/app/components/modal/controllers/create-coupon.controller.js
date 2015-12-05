angular.module('fnAppModal')
  .controller('CreateCouponModalCtrl',[
    '$scope','$rootScope','$uibModalInstance','$cookieStore','FileUploader','growl','Auth','Coupon','UPLOAD_URL','IMAGE_URL',
    function($scope,$rootScope,$uibModalInstance,$cookieStore,FileUploader,growl,Auth,Coupon,UPLOAD_URL,IMAGE_URL) {
      Auth.getCurrentUserInAsync(function(user) {
        $scope.user = user;
      });

      Coupon.getCouponType()
        .then(function(res) {
          $scope.couponTypes = res;
          resetForm();
        });

      var resetForm = function() {
        $scope.coupon = {
          coupontypeid: $scope.couponTypes[0].coupontypeid
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
            $scope.coupon.businessid = $scope.user.userid;
            Coupon.create($scope.coupon)
              .then(function(res) {
                growl.addSuccessMessage("Content created successfully");
                uploader.clearQueue();
                resetForm();
                $rootScope.$emit('Product:Created',res);
                $uibModalInstance.dismiss('cancel');
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
          $scope.coupon.businessid = $scope.user.userid;
          Coupon.create($scope.coupon)
            .then(function(res) {
              growl.addSuccessMessage("Coupon created successfully");
              uploader.clearQueue();
              resetForm();
              $rootScope.$emit('Coupon:Created',res);
              $uibModalInstance.dismiss('cancel');
            },function() {
              growl.addErrorMessage("Fail to create coupon")
            })
        }
      }
    }
  ]);