'use strict';
angular.module('fnApp')
  .factory('Group', [
    'Restangular',
    function (Restangular) {
      var _rest = Restangular.all('api/groups');
      var _restUserGroup = Restangular.all('api/user/group');
      return {
        create: function(groupObj) {
          return _rest.customPOST(groupObj);
        },
        getAll: function() {
          return _rest.get("");
        },
        getOne: function(id) {
          return _rest.get(id);
        },
        
        getMembers: function(id){
          return _restUserGroup.get(id+'/members');
        }
        //getMember: function(id) {
        //  return _rest.
        //}
      };
    }]);