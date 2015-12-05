angular.module('fnApp').config(['$stateProvider',function($stateProvider) {
  $stateProvider
    .state('home',{
      url: '/',
      templateUrl: 'app/modules/home/views/index.html',
      controller: 'HomeCtrl'
    })
    .state('registration',{
      url: '/registration',
      templateUrl: 'app/modules/home/views/registration.html',
      unauthenticated: true,
      controller: 'RegistrationCtrl'
    })
    .state('individualReg',{
      url: '/registration/individual',
      templateUrl: 'app/modules/home/views/individual-reg.html',
    })
    .state('individualReg.step2',{
      url: '/step-2',
      templateUrl: 'app/modules/home/views/individual-reg-step2.html',
      controller: 'IndividualStep2RegCtrl',
      unauthenticated: true
    })
    .state('individualReg.step3',{
      url: '/step-3',
      templateUrl: 'app/modules/home/views/individual-reg-step3.html',
      controller: 'IndividualStep3RegCtrl',
      resolve: {
        groups: [
          'Group',
          function(Group) {
            return Group.getAll();
          }
        ]
      }
    })
    .state('businessReg',{
      url: '/registration/business',
      templateUrl: 'app/modules/home/views/business-reg.html',
      controller: 'BusinessRegCtrl',
      unauthenticated: true,
      resolve: {
        businessTypes: [
          'Business',
          function(Business) {
            return Business.getBusinessTypes();
          }
        ]
      }
    })
    .state('logout',{
      url: '/logout',
      authenticate: true,
      controller: [
        '$scope','$state','Auth',
        function($scope,$state,Auth) {
          Auth.logout();
          $state.go('home');
        }
      ]
    })
}]);