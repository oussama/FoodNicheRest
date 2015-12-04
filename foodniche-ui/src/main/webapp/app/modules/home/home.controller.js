angular.module('fnApp')
  .controller('HomeCtrl',[
    '$scope','$rootScope','$state','Auth',
    function($scope,$rootScope,$state,Auth) {
      $scope.isLoggedIn = Auth.isLoggedIn;
      $scope.submitted = false;
      $scope.user = {};

      $scope.submit = function(form) {
        $scope.submitted = true;
        if (form.$valid) {
          $rootScope.tempUser = $scope.user;
          $state.go('registration');
        }
      }
    }
  ])


  .controller('IndividualStep2RegCtrl',[
    '$scope','$rootScope','FileUploader','Auth','$state','growl','IMAGE_URL',
    function($scope,$rootScope,FileUploader,Auth,$state,growl,IMAGE_URL) {
      if (!$rootScope.tempUser) {
        $state.go('home');
      } else {
        $scope.user = $rootScope.tempUser;
        $scope.user.vegetarianDiet = false;

      }
      $scope.submitted = false;

      var uploader = $scope.uploader = new FileUploader({
        url: 'upload',
        onAfterAddingFile: function () {
          if (uploader.queue.length > 1) {
            uploader.queue.shift();
          }
        },
        onCompleteItem: function(fileItem, file, status) {
          if (status === 200) {
            $scope.user.profilepicture = IMAGE_URL + file.fileId;
            createUser($scope.user);
          }
        }
      });


      $scope.submit = function(form) {
        $scope.submitted = true;
        if (form.$valid) {
          if (uploader.queue.length > 0) {
            //uploader.uploadAll();
            createUser($scope.user);
          } else {
            createUser($scope.user);
          }
        }
      };

      var createUser = function(user) {
        user.firstname = user.fullName.split(' ').slice(0, -1).join(' ');
        user.lastname = user.fullName.split(' ').slice(-1).join(' ');
        user.username = user.email;
        user = _.omit($scope.user,'fullName','agree','email','vegetarianDiet','country');
        Auth.register(user,function(err) {
          if (err) {
            growl.addErrorMessage(err);
          }
          $state.go('individualReg.step3')
        })
      }
    }
  ])

  .controller('IndividualStep3RegCtrl',[
    '$scope','$rootScope','$state','Auth',
    function($scope,$rootScope,$state,Auth) {
      if (!$rootScope.tempUser) {
        $state.go('home');
      }

      $scope.skip = function() {
        $rootScope.tempUser = {};
        $state.go('home');
      }
    }
  ])

  .controller('BusinessRegCtrl',[
    '$scope','businessTypes','FileUploader',
    function($scope,businessTypes,FileUploader) {
      $scope.submitted = false;
      $scope.businessTypes = businessTypes;
      $scope.user = {
        businessCategory: businessTypes[0].businesstypeid
      };

      $scope.submit = function(form) {
        $scope.submitted = true;
      }
    }
  ]);

