angular.module('fnApp').config(['$stateProvider',function($stateProvider) {
  $stateProvider
    .state('business',{
      url: '/business',
      template: '<ui-view></ui-view>'
    })
    .state('business.view',{
      url: '/:id',
      templateUrl: 'app/modules/business/views/business.html',
      controller: 'BusinessViewCtrl',
      authenticate: true,
      isBusiness: true,
      resolve: {
        contents: [
          'Content',
          function(Content) {
            return Content.getAll();
          }
        ],
        products: [
          'Product','$stateParams',
          function(Product,$stateParams) {
            return Product.getAllByBusiness($stateParams.id)
          }
        ],
        coupons: [
          'Coupon',
          function(Coupon) {
            return Coupon.getAll()
          }
        ]
      }
    })
    .state('business.edit',{
      url: '/:id/edit',
      templateUrl: 'app/modules/business/views/business-edit.html',
      controller: 'BusinessEditCtrl',
      authenticate: true,
      isBusiness: true
    })
    .state('business.connection',{
      url: '/:id/my-connection',
      templateUrl: 'app/modules/business/views/business-connection.html',
      controller: 'BusinessConnectionCtrl',
      authenticate: true,
      isBusiness: true,
      resolve: {
        connections: [
          'User',
          function(User) {
            return User.getMyConnection();
          }
        ]
      }
    })
    .state('business.album',{
      url: '/:id/my-album',
      templateUrl: 'app/modules/business/views/business-album.html',
      controller: 'BusinessAlbumCtrl',
      authenticate: true,
      isBusiness: true,
      resolve: {
        albums: [
          'User',
          function(User) {
            return User.getMyAlbum();
          }
        ]
      }
    })
}]);