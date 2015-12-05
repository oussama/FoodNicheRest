angular.module('fnApp').config(['$stateProvider',function($stateProvider) {
  $stateProvider
    .state('group',{
      url: '/group',
      template: "<ui-view></ui-view>",
      authenticate: true,
      isBusiness: true
    })
    .state('group.list',{
      url: '/:id',
      templateUrl: 'app/modules/group/views/group-list.html',
      controller: 'GroupListCtrl',
      authenticate: true,
      isBusiness: true,
      resolve: {
        groups: [
          'Group',
          function(Group) {
            return Group.getAll();
          }
        ]
      }
    })
    .state('group.create',{
      url: '/create',
      templateUrl: 'app/modules/group/views/group-create.html',
      controller: 'GroupCreateCtrl',
      authenticate: true,
      isBusiness: true
    })
    .state('group.detail',{
      url: '/detail/:id',
      templateUrl: 'app/modules/group/views/group-detail.html',
      controller: 'GroupDetailCtrl',
      authenticate: true,
      isBusiness: true,
      resolve: {
        group: [
          'Group','$stateParams',
          function(Group,$stateParams) {
            return Group.getOne($stateParams.id);
          }
        ],
        //member: [
        //  'Group','$stateParams',
        //  function(Group,$stateParams) {
        //    return Group.getMember($stateParams.id);
        //  }
        //]
      }
    });
  }
]);
