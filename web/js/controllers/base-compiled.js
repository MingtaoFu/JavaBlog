/**
 * AngularJS 思路
 * $rootScope 中应该记录登录状态
 *
 * 分不同的 router
 * main / article   login / personalCenter
 */

angular.module("app", ["jQueryRequest", "ngRoute"]).config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/', {
        templateUrl: 'view/articleList.html'
    }).when('/article', {
        templateUrl: 'view/article.html'
    }). //controller: '',
    when('/me', {
        templateUrl: 'view/me.html'
    });
}]).controller("test", function ($scope, rqService) {
    console.log(rqService);
    /*
     $.post("api/account/login", {id: "da", pwd: "dada"}, function(resp) {
     $scope.$apply(function() {
      var data = resp.data;
     if(data.status) {
     document.cookie = "id=da";
     document.cookie = "token="+data.token;
      }
      $scope.data = resp;
        });
     });
     */
    $.getJSON("api/account/personalInfo", function (resp) {
        $scope.$apply(function () {
            $scope.info = resp.data;
            $.post("api/account/logout", function () {
                $scope.$apply(function () {
                    $.getJSON("api/account/personalInfo", function (resp) {
                        $scope.$apply(function () {

                            $scope.info2 = resp.data;
                        });
                    });
                });
            });
        });
    });
});

//# sourceMappingURL=base-compiled.js.map