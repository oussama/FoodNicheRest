angular.module('fnApp')
  .controller('ProfileViewCtrl',[
    '$scope','user','FileUploader','$cookieStore','$state','Auth','Connection','growl','UPLOAD_URL','IMAGE_URL','contents',
    function($scope,user,FileUploader,$cookieStore,$state,Auth,Connection,growl,UPLOAD_URL,IMAGE_URL,contents) {
      $scope.contents = contents;
      $scope.user = user;
      Auth.getCurrentUserInAsync(function(currentUser) {
        $scope.currentUser = currentUser;
        $scope.isCurrentUser = user.userid === currentUser.userid;
        Auth.checkConnection({id: $scope.user.userid}).$promise
          .then(function(res) {
            $scope.isConnected = !_.isEmpty(res);
          });

      });
      var profileUploader = $scope.profileUploader = new FileUploader({
        url: UPLOAD_URL,
        headers: {
          'X-Auth-Token': $cookieStore.get('token')
        },
        onAfterAddingFile: function (file) {
          if (profileUploader.queue.length > 1) {
            profileUploader.queue.shift();
          }
          file.upload();
        },
        onCompleteItem: function(fileItem, file,status) {
          if (status === 200) {
            $scope.user.profilepicture = IMAGE_URL + file.fileId;
            Auth.updateProfile($scope.user).then(function() {
              growl.addSuccessMessage('Profile picture update successfully')
              $scope.isConnected = true;
            },function() {
              growl.addErrorMessage('Cannot update profile picture')
            })
          } else {
            growl.addErrorMessage('Cannot update profile picture')
          }
        }
      });

      $scope.connect = function() {
        var connectObj = {
          connectionsPK: {
            fromUser: {
              userid: $scope.currentUser.userid
            },
            toUser: {
              userid: $scope.user.userid
            },
          },
          status: 0,
          createddate: new Date().toISOString()
        };
        Connection.create(connectObj).then(function() {
          growl.addSuccessMessage('You have been connected with this user');
        },function() {
          growl.addErrorMessage('Fail to connect with this user');
        });
      };

      $scope.removePhoto = function(index) {
        uploader.queue.splice(index,1);
      };
    }
  ])

  .controller('ProfileEditCtrl',[
    '$scope','user','Auth','growl',
    function($scope,user,Auth,growl) {
      $scope.submitted = false;
      $scope.user = user;
      $scope.user.fullName = $scope.user.firstname + " " + $scope.user.lastname;
      $scope.submit = function(form) {
        $scope.submitted = true;
        if (form.$valid) {
          $scope.user.firstname = user.fullName.split(' ').slice(0, -1).join(' ');
          $scope.user.lastname = user.fullName.split(' ').slice(-1).join(' ');
          $scope.user = _.omit($scope.user,'country','vegetarianDiet','fullName');
          Auth.updateProfile($scope.user)
            .then(function() {
              growl.addSuccessMessage("Profile updated successfully");
            },function() {
              growl.addErrorMessage("Cannot update profile");
            })
        }
      }
    }
  ])

  .controller('ProfileConnectionCtrl',[
    '$scope','$state','connections',
    function($scope,$state,connections) {
      $scope.connections = connections
    }
  ])

  .controller('ProfileAlbumCtrl',[
    '$scope','$state','albums',
    function($scope,$state,albums) {
      $scope.albums = albums;
    }
  ]);