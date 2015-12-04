angular.module('fnApp')
  .directive('fnLogin',['$uibModal',function($uibModal) {
    return {
      restrict: 'AE',
      link: function($scope,element) {
        element.bind('click',function() {
          $uibModal.open({
            templateUrl: 'app/directives/login/login.modal.html',
            windowClass: 'modal-small login-modal',
            backdrop: 'static',
            controller: [
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
                              $state.go('profile.view',{id: user.userid});
                            } else {
                              $state.go('business.view')
                            }
                          });
                      }
                    })
                  }
                }
              }
            ]
          })
        })
      }
    }
  }
  ]);