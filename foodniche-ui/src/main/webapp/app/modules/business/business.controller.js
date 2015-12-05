angular.module('fnApp')
  .controller('BusinessViewCtrl',[
    '$scope','$rootScope','FileUploader','Auth','Product','Coupon','UPLOAD_URL','$cookieStore',
    function($scope,$rootScope,FileUploader,Auth,Product,Coupon,UPLOAD_URL,$cookieStore) {
      Auth.getCurrentUserInAsync(function(user) {
        $scope.user = user;
        Product.getAllByBusiness(user.userid)
          .then(function(res) {
            $scope.products = res;
          });
        Coupon.getAll()
          .then(function(res) {
            $scope.coupons = res;
          })
      });

      $rootScope.$on('Product:Created',function(event,product) {
        $scope.products.push(product);
      });

      $rootScope.$on('Coupon:Created',function(event,coupon) {
        $scope.coupons.push(coupon);
      });
    }
  ])

  .controller('BusinessEditCtrl',[
    '$scope',
    function($scope) {
      $scope.submitted = false;

      $scope.submit = function(form) {
        $scope.submitted = true;
      }
    }
  ])

  .controller('BusinessConnectionCtrl',[
    '$scope','$state','connections',
    function($scope,$state,connections) {
      $scope.connections = connections;
    }
  ])

  .controller('BusinessAlbumCtrl',[
    '$scope','$state','albums',
    function($scope,$state,albums) {
      $scope.albums = albums;
    }
  ])


