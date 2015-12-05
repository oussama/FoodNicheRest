angular.module('fnAppModal')
  .controller('LoginModalCtrl',[
    '$scope','Auth','$state','$uibModalInstance',
    function($scope,Auth,$state,$uibModalInstance ) {


      $scope.submitted = false;
      $scope.user = {
        remember: false
      };

      $scope.submit = function(form) {
        $scope.submitted = true;
        if (form.$valid) {
          Auth.login($scope.user,function(err) {
            if (err) {
              $uibModalInstance.dismiss('cancel');
              console.log(err);
            } else {
              Auth.getCurrentUser().$promise
                .then(function(user) {
                  $uibModalInstance.dismiss('cancel');
                  if (user.accounttype === 0) {
                    $state.go('profile.view',{id: user.userid });
                  } else {
                    $state.go('business.view',{id: user.userid})
                  }
                });
            }
          })
        }
      }
    }
  ]);