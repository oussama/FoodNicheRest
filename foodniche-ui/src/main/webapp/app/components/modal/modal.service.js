'use strict';
angular.module('fnAppModal')
  .factory('Modal', function ($rootScope, $uibModal) {
    function openModal(scope, modalClass) {
      var modalScope = $rootScope.$new();
      scope = scope || {};
      modalClass = modalClass || 'modal-default';

      angular.extend(modalScope, scope);

      return $uibModal.open({
        templateUrl: 'app/components/modal/views/modal.html',
        windowClass: modalClass,
        scope: modalScope,
        backdrop: 'static'
      });
    }

    function openInviteFriendModal(modalClass) {
      modalClass = modalClass || 'modal-small';

      return $uibModal.open({
        templateUrl: 'app/components/modal/views/invite-friend.modal.html',
        windowClass: modalClass,
        backdrop: 'static'
      });
    }

    function openRaveModal(modalClass) {
      modalClass = modalClass || 'modal-small';

      return $uibModal.open({
        templateUrl: 'app/components/modal/views/rave.modal.html',
        windowClass: modalClass,
        backdrop: 'static'
      });
    }

    function openCreateProductModal(modalClass) {
      modalClass = modalClass || 'modal-small';

      return $uibModal.open({
        templateUrl: 'app/components/modal/views/create-product.modal.html',
        windowClass: modalClass,
        backdrop: 'static',
        size: 'lg',
        controller: 'CreateProductModalCtrl'
      });
    }

    function openCreateCouponModal(modalClass) {
      modalClass = modalClass || 'modal-small';

      return $uibModal.open({
        templateUrl: 'app/components/modal/views/create-coupon.modal.html',
        windowClass: modalClass,
        backdrop: 'static',
        size: 'lg',
        controller: 'CreateCouponModalCtrl'
      });
    }

    function openLoginModal(modalClass) {
      modalClass = modalClass || 'modal-small login-modal';

      $uibModal.open({
        templateUrl: 'app/components/modal/views/login.modal.html',
        windowClass: modalClass,
        backdrop: 'static',
        controller: 'LoginModalCtrl'
      })
    }

    // Public API here
    return {
      inviteFriend: function(modalClass) {
        var inviteFriendModal = openInviteFriendModal(modalClass);
      },
      rave: function(modalClass) {
        var raveModal = openRaveModal(modalClass);
      },
      createProduct: function(modalClass) {
        var createProductModal = openCreateProductModal(modalClass);
      },
      loginModal: function(modalClass) {
        var loginModal = openLoginModal(modalClass)
      },
      createCouponModal: function(modalClass) {
        var createCouponModal = openCreateCouponModal(modalClass);
      },
      /* Confirmation modals */
      confirm: {

        /**
         * Create a function to open a delete confirmation modal (ex. ng-click='myModalFn(name, arg1, arg2...)')
         * @param  {Function} del - callback, ran when delete is confirmed
         * @return {Function}     - the function to open the modal (ex. myModalFn)
         */
        delete: function(text,cb) {
          cb = (cb || angular.noop);
          var deteleModal;
          deteleModal = openModal({
            modal: {
              dismissable: false,
              title: 'Confirm Delete',
              html: '<p>' + text + '</p>',
              buttons: [{
                classes: 'btn-danger',
                text: 'Yes',
                click: function(e) {
                  deteleModal.close(e);
                  return cb(true);
                }
              }, {
                classes: 'btn-default',
                text: 'Cancel',
                click: function(e) {
                  deteleModal.dismiss(e);
                  return cb(false);
                }
              }]
            }
          },'modal-danger')
        },
        changeActive : function(text,cb) {
          cb = (cb || angular.noop);
          var modal;
          modal = openModal({
            modal: {
              dismissable: false,
              title: 'Confirm Change',
              html: '<p>'+ text +'</p>',
              buttons: [{
                classes: 'btn-danger',
                text: 'Yes',
                click: function(e) {
                  modal.close(e);
                  return cb(true);
                }
              }, {
                classes: 'btn-default',
                text: 'Cancel',
                click: function(e) {
                  modal.dismiss(e);
                  return cb(false);
                }
              }]
            }
          },'modal-danger')
        },
        makeDefaultPhoto: function(text,cb) {
          cb = (cb || angular.noop);
          var modal;
          modal = openModal({
            modal: {
              dismissable: false,
              title: 'Confirm Change',
              html: '<p>'+ text +'</p>',
              buttons: [{
                classes: 'btn-warning',
                text: 'Yes',
                click: function(e) {
                  modal.close(e);
                  return cb(true);
                }
              }, {
                classes: 'btn-default',
                text: 'Cancel',
                click: function(e) {
                  modal.dismiss(e);
                  return cb(false);
                }
              }]
            }
          },'modal-warning')
        }
      }
    };
  });
