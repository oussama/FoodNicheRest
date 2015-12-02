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
          console.log($rootScope.tempUser);
          $state.go('registration');
        }
      }
    }
  ])


  .controller('IndividualStep2RegCtrl',[
    '$scope','$rootScope','FileUploader','$state',
    function($scope,$rootScope,FileUploader,$state) {
      if (!$rootScope.tempUser) {
        $state.go('home');
      } else {
        $scope.user = $rootScope.tempUser;
        $scope.user.vegetarianDiet = false;
      }
      $scope.submitted = false;

      var uploader = $scope.uploader = new FileUploader({
        url: 'upload'
      });

      uploader.onAfterAddingFile = function () {
        if (uploader.queue.length > 1) {
          uploader.queue.shift();
        }
      };

      $scope.submit = function(form) {
        $scope.submitted = true;
        if (form.$valid) {
          $state.go('individualReg.step3')
        }
      }
    }
  ])

  .controller('IndividualStep3RegCtrl',[
    '$scope','$rootScope','$state','Auth',
    function($scope,$rootScope,$state,Auth) {
      if (!$rootScope.tempUser) {
        $state.go('home');
      } else {
        var user = $rootScope.tempUser;
        user.firstname = user.fullName.split(' ').slice(0, -1).join(' ');
        user.lastname = user.fullName.split(' ').slice(-1).join(' ');
        user.username = user.email;
        user = _.omit(user,'fullName','agree','email','vegetarianDiet','country');
      }

      $scope.createUser = function() {
        Auth.register(user,function(err) {
          if (err) {

          }
          $state.go('home')
        })
      }
    }
  ])

  .controller('BusinessRegCtrl',[
    '$scope','FileUploader',
    function($scope,FileUploader) {
      $scope.submitted = false;
      $scope.user = {
        businessCategory: 'restaurant'
      };

      $scope.submit = function(form) {
        $scope.submitted = true;
      }
    }
  ]);

