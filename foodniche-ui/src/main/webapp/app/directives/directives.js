angular.module('fnApp')
  .directive('fnResponsiveSlider',[function() {
    return {
      restrict: 'AE',
      link: function($scope,element,attrs) {
        $(element).responsiveSlides({
          //auto: false,
          auto: true,
          pager: true,
          nav: true,
          speed: 1997,
          maxwidth: 3000,
          namespace: "transparent-btns"
        });
      }
    }
  }])
  .directive('fnOwlCarousel',[function() {
    return {
      restrict: 'AE',
      link: function($scope,element,attrs) {
        var options = {
          margin: 10,
          nav: true,
          loop: true,
          responsive: {
            0: {
              items: 1
            },
            400: {
              items: 2
            },
            600: {
              items: 4
            },
            950: {
              items: 5
            },
            1200: {
              items: 6
            }
          }
        };
        if (attrs.autoplay !== undefined) {
          options.autoplay = true;
        }
        if (attrs.autoplayTimeout !== undefined) {
          options.autoplayTimeout = parseInt(attrs.autoplayTimeout);
        }
        $(element).owlCarousel(options);
      }
    }
  }])
  .directive('fnRaveModal',['Modal',function(Modal) {
    return {
      restrict: 'AE',
      link: function($scope,element) {
        element.bind('click',function() {
          Modal.rave();
        })
      }
    }
  }])
  .directive('fnInviteFriendModal',['Modal',function(Modal) {
    return {
      restrict: 'AE',
      link: function($scope,element) {
        element.bind('click',function() {
          Modal.inviteFriend();
        })
      }
    }
  }])
  .directive('fnGoHome',['$state',function($state) {
    return {
      restrict: 'AE',
      scope: {
        routeName: '@fnGoHome'
      },
      link: function($scope,element) {
        element.bind('click',function() {
          $state.go($scope.routeName);
        })
      }
    }
  }])
  .directive('ngThumb', ['$window', function ($window) {
    var helper = {
      support: !!($window.FileReader && $window.CanvasRenderingContext2D),
      isFile: function (item) {
        return angular.isObject(item) && item instanceof $window.File;
      },
      isImage: function (file) {
        var type = '|' + file.type.slice(file.type.lastIndexOf('/') + 1) + '|';
        return '|jpg|png|jpeg|bmp|gif|'.indexOf(type) !== -1;
      }
    };

    return {
      restrict: 'A',
      replace: true,
      link: function (scope, element, attributes) {
        if (!helper.support) {
          return;
        }

        var file = scope.$eval(attributes.ngThumb);
        if (!helper.isFile(file)) {
          return;
        }
        if (!helper.isImage(file)) {
          return;
        }

        var reader = new FileReader();

        reader.onload = function (event) {
          attributes.$set('src',event.target.result);
        };
        reader.readAsDataURL(file);
      }
    };
  }]);