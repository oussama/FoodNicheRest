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

  .controller('RegistrationCtrl',[
    '$rootScope','$scope','$state','growl','Auth',
    function($rootScope,$scope,$state,growl,Auth) {
      if (!$rootScope.tempUser) {
        $state.go('home');
      }

      $scope.chooseAccountType = function(type) {
        Auth.register({
          username: $rootScope.tempUser.email,
          password: $rootScope.tempUser.password,
          business: type
        },function(err) {
          if (err) {
            growl.addErrorMessage(err);
          } else {
            if (type) {
              $state.go('businessReg')
            } else {
              $state.go('individualReg.step2')
            }
          }

        });
      }
    }
  ])


  .controller('IndividualStep2RegCtrl',[
    '$scope','$rootScope','FileUploader','Auth','$cookieStore','$state','growl','UPLOAD_URL','IMAGE_URL',
    function($scope,$rootScope,FileUploader,Auth,$cookieStore,$state,growl,UPLOAD_URL,IMAGE_URL) {
      if (!$rootScope.tempUser) {
        $state.go('home');
      } else {
        Auth.login({
          username: $rootScope.tempUser.email,
          password: $rootScope.tempUser.password,
          remember: false
        }).then(function(res) {
          $scope.token = res.data.token;
          $scope.uploader.headers = {
            'X-Auth-Token': $scope.token
          };
          console.log($scope.uploader);
          Auth.getCurrentUserInAsync(function(user) {
            $scope.user = user;
            $scope.user.fullName = $rootScope.tempUser.fullName;
            $scope.user.password = $rootScope.tempUser.password;
            $scope.user.vegetarianDiet = false;
          });
        });
        //$scope.user = angular.copy($rootScope.tempUser);


      }
      $scope.submitted = false;

      var uploader = $scope.uploader = new FileUploader({
        url: UPLOAD_URL,
        headers: {
          'X-Auth-Token': $scope.token
        },
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
            uploader.uploadAll();
          } else {
            createUser($scope.user);
          }
        }
      };

      var createUser = function(user) {
        user.firstname = user.fullName.split(' ').slice(0, -1).join(' ');
        user.lastname = user.fullName.split(' ').slice(-1).join(' ');
        user = _.omit($scope.user,'fullName','agree','email','vegetarianDiet','country');
        Auth.updateProfile(user,function(err) {
          if (err) {
            growl.addErrorMessage(err);
          } else {
            $state.go('individualReg.step3')
          }
        })
      }
    }
  ])

  .controller('IndividualStep3RegCtrl',[
    '$scope','$rootScope','groups','$state','Auth',
    function($scope,$rootScope,groups,$state,Auth) {
      if (!$rootScope.tempUser) {
        //$state.go('home');
      } else {
        //Auth.login({
        //  username: $rootScope.tempUser.email,
        //  password: $rootScope.tempUser.password,
        //  remember: false
        //}).then(function(res) {
        //  $scope.token = res.data.token;
        //  Auth.getCurrentUserInAsync(function(user) {
        //    $scope.user = user;
        //  });
        //});
      }

      $scope.groups = groups



      $scope.skip = function() {
        $rootScope.tempUser = {};
        $state.go('home');
      }
    }
  ])

  .controller('BusinessRegCtrl',[
    '$scope','$rootScope','businessTypes','growl','$state','Business','Auth',
    function($scope,$rootScope,businessTypes,growl,$state,Business,Auth) {
      if (!$rootScope.tempUser) {
        $state.go('home');
      } else {
        Auth.login({
          username: $rootScope.tempUser.email,
          password: $rootScope.tempUser.password,
          remember: false
        }).then(function(res) {
          Auth.getCurrentUserInAsync(function(user) {
            $scope.user = user;
            $scope.businessTypes = businessTypes;
            $scope.business = {
              businessType: {
                businesstypeid: businessTypes[0].businesstypeid
              },
              user: {
                userid: $scope.user.userid
              }
            };
          });
        });
      }
      $scope.submitted = false;


      $scope.submit = function(form) {
        $scope.submitted = true;
        Business.create($scope.business)
          .then(function(res) {
            growl.addSuccessMessage('Account created successfully')
          },function() {
            growl.addErrorMessage('Fail to create business')
          })
      }
    }
  ]);

