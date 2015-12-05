angular.module('fnApp')
  .controller('RecipeListCtrl',[
    '$scope','recipes',
    function($scope,recipes) {
      $scope.recipes = recipes;
    }
  ])
  .controller('RecipeDetailCtrl',[
    '$scope','recipe',
    function($scope,recipe) {
      $scope.recipe = recipe;
    }
  ])
  .controller('RecipeCreateCtrl',[
    '$scope','Auth','Recipe','FileUploader','growl','$state','$cookieStore','UPLOAD_URL','IMAGE_URL',
    function($scope,Auth,Recipe,FileUploader,growl,$state,$cookieStore,UPLOAD_URL,IMAGE_URL) {
      Auth.getCurrentUserInAsync(function(user) {
        $scope.user = user;
      });

      var resetForm = function() {
        $scope.submitted = false;
        $scope.recipe = {};
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
        onCompleteItem: function(fileItem,file,status) {
          if (status === 200) {
            $scope.recipe.picture = IMAGE_URL + file.fileId;
            $scope.recipe.recipedate = new Date().toISOString();
            $scope.recipe.userid = $scope.user.userid;  
            Recipe.create($scope.recipe)
              .then(function() {
                growl.addSuccessMessage('Recipe created successfully');
                resetForm();
                uploader.clearQueue();
                //$state.go('business.view',{id: $scope.user.userid});
              },function() {
                growl.addErrorMessage("Fail to create recipe");
              })
          } else {
            growl.addErrorMessage("Fail to upload photo")
          }
        }
      });

      $scope.submit = function(form) {
        $scope.submitted = true;
        if (form.$valid) {
          if (uploader.queue.length < 1) {
            growl.addErrorMessage("Please choose at least 1 photo for recipe")
          } else {
            uploader.uploadAll();
          }
        }
      }
    }
  ]);