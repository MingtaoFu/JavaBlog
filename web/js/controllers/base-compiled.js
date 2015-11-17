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
        templateUrl: 'view/article.html',
        controller: 'article'
    }).when('/me', {
        templateUrl: 'view/me.html',
        controller: 'me'
    }).when('/admin', {
        templateUrl: 'view/editor.html',
        controller: 'admin'
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
}).controller('main', function ($scope, $rootScope) {
    $rootScope.rootdata = {
        login: {
            id: '',
            pwd: ''
        },
        register: {
            id: '',
            name: '',
            pwd: ''
        },
        info: {},
        blogInfo: {}
    };

    $rootScope.status = {
        isValidated: false,
        loginPanel: false

    };

    $scope.loginSubmit = function () {
        $.post('api/account/login', $rootScope.rootdata.login, function (resp) {
            $scope.$apply(function () {
                if (resp.data.status) {
                    document.cookie = "id=" + $rootScope.rootdata.login.id;
                    document.cookie = "token=" + resp.data.token;
                    $rootScope.status.isValidated = true;
                    $scope.status.loginPanel = false;
                } else {}
            });
        });
    };
}).controller('me', function ($scope, $rootScope) {
    $.getJSON('api/account/personalInfo', function (resp) {
        $scope.$apply(function () {
            $rootScope.rootdata.info = resp.data;
        });
    });

    //修改密码
    $scope.modifyPwd = function () {};
}).controller('admin', function ($scope) {
    $scope.publishStatus = '';
    $scope.data = {
        article: {
            title: '',
            content: ''
        }
    };

    //发表文章
    $scope.publish = function () {
        $scope.data.article.content = CKEDITOR.instances.editor.getData();
        $.post('api/article/publish', $scope.data.article, function (resp) {
            $scope.$apply(function () {
                console.log(resp.data.status);
                switch (resp.data.status) {
                    case 0:
                        $scope.publishStatus = "身份认证失败，请重新登录";
                        break;
                    case 1:
                        $scope.publishStatus = "发表成功，跳转中...";
                        break;
                    case 2:
                        $scope.publishStatus = "服务器错误，请稍后再试";
                        break;
                    case 3:
                        $scope.publishStatus = "您没有权限发表文章";
                        break;
                }
            });
        });
    };
}).controller('article', function ($scope, $location) {
    $scope.commentUserList = [];
    $scope.changeToUser = function (cId, rId) {
        console.log(cId + "," + rId);
        $scope.commentUserList[cId] = $scope.comments[cId].resps[rId].fromUser;
    };

    $scope.comments = [];

    //$scope.getComment = function() {
    $.getJSON('api/article/oneArticleContent', $location.search(), function (resp) {
        $scope.$apply(function () {
            var data = resp.data;
            switch (data.status) {
                case 1:
                    //非常不优雅，要改
                    if (data.comments) {
                        if (typeof data.comments.length == "number") {
                            $scope.comments = data.comments;
                        } else {
                            $scope.comments = [data.comments];
                        }
                        //设置commentUserList
                        for (var i in $scope.comments) {
                            $scope.commentUserList.push($scope.comments[i].user);
                        }
                        ///////
                        var resps = data.responses;
                        var comms = $scope.comments;
                        if (resps) {
                            if (typeof resps.length != "number") {
                                resps = [resps];
                            }
                            for (var i = 0; i < resps.length; i++) {
                                for (var j = 0; j < comms.length; j++) {
                                    if (!comms[j].resps) {
                                        comms[j].resps = [];
                                    }
                                    if (comms[j].id == resps[i].commentId) {
                                        comms[j].resps.push(resps[i]);
                                    }
                                }
                            }
                        }
                    }
                    break;
                case 2:
                    break;
            }
        });
    });
    // }
});

//# sourceMappingURL=base-compiled.js.map