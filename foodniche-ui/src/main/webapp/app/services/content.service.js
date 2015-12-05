'use strict';
angular.module('fnApp')
  .factory('Content', [
    'Restangular',
    function (Restangular) {
      var _contentRest = Restangular.all('content');
      var _contentTypeRest = Restangular.all('contenttypes');
      return {
        getContentType: function() {
          return _contentTypeRest.get("");
        },
        create: function(contentObj) {
          return _contentRest.customPOST(contentObj);
        },
        getAll: function() {
          return _contentRest.get("");
        }
      };
    }]);