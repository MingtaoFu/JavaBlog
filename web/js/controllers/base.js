/**
 * AngularJS 思路
 * $rootScope 中应该记录登录状态
 *
 * 分不同的 router
 * main / article   login / personalCenter
 */

angular.module("app", ["jQueryRequest", "ngRoute", "ngFileUpload"])
    .config(['$routeProvider', function($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'view/articleList.html',
                controller: 'index'
            })
            .when('/article', {
                templateUrl: 'view/article.html',
                controller: 'article'
            })
            .when('/me', {
                templateUrl: 'view/me.html',
                controller: 'me'
            })
            .when('/admin', {
                templateUrl: 'view/editor.html',
                controller: 'admin'
            })
            .when('/test', {
                templateUrl: 'jQuery-File-Upload-9.11.2/index.html'
            });
    }])
    .filter('trustHtml', function ($sce) {
        return function (input) {
            return $sce.trustAsHtml(input);
        }
    })
    .filter('delTag', function ($sce) {
        return function (input) {
            var reg = new RegExp("<[^<]*>", "gi")
            //return input.replace(reg, '');
            return $sce.trustAsHtml(input.replace(reg, ''));
        }
    })
    .controller('index', function($scope) {
        $scope.articleList = [];

        $.getJSON('api/article/articleList', function(resp) {
            $scope.$apply(function() {
                switch (resp.list.status) {
                    case 1:
                        $scope.articleList = resp.list.articles;
                        break;
                }
            });

        });
    })
    .controller('main', function($scope, $rootScope) {
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
            info: {

            },
            blogInfo: {

            }
        };

        $rootScope.status = {
            isValidated: false,
            loginPanel: false

        };

        $scope.loginSubmit = function() {
            $.post('api/account/login', $rootScope.rootdata.login, function(resp) {
                $scope.$apply(function() {
                    if(resp.data.status) {
                        document.cookie = "id=" + $rootScope.rootdata.login.id;
                        document.cookie = "token=" + resp.data.token;
                        $rootScope.status.isValidated = true;
                        $scope.status.loginPanel = false;
                    } else {

                    }
                });

            });
        };
    })
    .controller('me', ['$scope', '$rootScope', 'Upload', function($scope, $rootScope, Upload) {

        $scope.status = {
            //是否在编辑状态
            editModel: false
        };

        //给文件input增加监听器，一旦选定了，自动上传
        $scope.$watch("file", function() {
            if($scope.file) {
                $scope.upload();
            }
        });

        $scope.upload = function() {
            Upload.upload({
                url: 'api/file/uploadHead',
                file: $scope.file
            }).progress(function (evt) {
                var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
                console.log('progress: ' + progressPercentage + '% ' + evt.config.file.name);
            }).success(function (data, status, headers, config) {
                console.log('file ' + config.file.name + 'uploaded. Response: ' + data);
                $rootScope.rootdata.info.logoUrl = 'img/head_' + $rootScope.rootdata.info.id;
            });
        };

        $scope.pwdData = {
            oldPwd: '',
            newPwd: '',
            rePwd: ''
        };

        $scope.getData = function () {
            $.getJSON('api/account/personalInfo', function(resp) {
                $scope.$apply(function() {
                    $rootScope.rootdata.info = resp.data;
                });
            });
        };


        $scope.getData();

        $scope.$watch("modifyForm.rePwd.$viewValue", function() {
            if($scope.modifyForm.rePwd.$viewValue == $scope.modifyForm.newPwd.$viewValue) {
                $scope.modifyForm.rePwd.$error.same = false;
            } else {
                $scope.modifyForm.rePwd.$error.same = true;
                $scope.modifyForm.$invalid = true;
            }
        });

        $scope.$watch("modifyForm.newPwd.$viewValue", function() {
            if($scope.modifyForm.rePwd.$viewValue == $scope.modifyForm.newPwd.$viewValue) {
                $scope.modifyForm.rePwd.$error.same = false;
            } else {
                $scope.modifyForm.rePwd.$error.same = true;
                $scope.modifyForm.$invalid = true;
            }
        });

        //修改个人信息
        $scope.modifyPersonalInfo = function() {
            $.post('api/account/modifyInfo', $rootScope.rootdata.info, function(resp) {
                $scope.$apply(function() {
                    switch (resp.data.status) {
                        case 1:
                            alert("操作成功");
                            break;
                        case 0:
                            alert("登录状态失效，请重新登录");
                            break;
                        case 2:
                            alert("服务器错误，请稍后再试");
                            break;
                    }
                });
            });
        };

        //修改密码
        $scope.modifyPwd = function() {
            $.post('api/account/modifyPwd', $scope.pwdData, function(resp) {
                $scope.$apply(function() {
                    switch (resp.data.status) {
                        case 1:
                            alert("操作成功");
                            break;
                        case 0:
                            alert("登录状态失效，请重新登录");
                            break;
                        case 3:
                            alert("旧密码错误");
                            break;
                        case 2:
                            alert("服务器错误，请稍后再试");
                            break;
                    }
                });
            });
        }
    }])
    .controller('admin', function($scope) {
        $scope.publishStatus = '';
        $scope.data = {
            article: {
                title: '',
                content: ''
            }
        };

        //发表文章
        $scope.publish = function() {
            $scope.data.article.content = CKEDITOR.instances.editor.getData();
            $.post('api/article/publish', $scope.data.article, function(resp) {
                $scope.$apply(function() {
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

    })
    .controller('article', function($scope, $location, $anchorScroll) {
        $scope.commentUserList = [];

        //回复时变换对象
        $scope.changeToUser = function(cId, rId) {
            console.log(cId+","+rId);
            $scope.commentUserList[cId] = $scope.comments[cId].resps[rId].fromUser;
        };

        $scope.comments = [];

        $scope.newComment = '';
        $scope.newResponse = [];

        //获取评论和回复
        $scope.getData = function() {
            $.getJSON('api/article/oneArticleContent', $location.search(), function(resp) {
                $scope.$apply(function() {
                    var data = resp.data;
                    switch (data.status) {
                        case 1:
                            $scope.article = data.article;
                            //非常不优雅，要改
                            if(data.comments) {
                                if(typeof data.comments.length == "number") {
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
                                if(resps) {
                                    if(typeof resps.length != "number") {
                                        resps = [resps];
                                    }
                                    for (var i = 0;i < resps.length;i++) {
                                        for (var j = 0;j<comms.length;j++) {
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
        };

        $scope.getData();

        //锚点定位
        $scope.goto = function (id) {
            $location.hash(id);
            $anchorScroll();
        };

        //发表评论
        $scope.postComment = function() {
            $.post('api/article/comment/add',
                {articleId: $location.search().articleId, content: $scope.newComment},
                function(resp) {
                    $scope.$apply(
                        function() {
                            switch (resp.data.status) {
                               case 1:
                                   $scope.getData();
                                   break;
                               case 0:
                                   alert("登录状态失效，请重新登录");
                                   break;
                               case 3:
                                   alert("登录状态失效，请重新登录");
                                   break;
                               case 2:
                                   alert("服务器错误，请稍后再试");
                                   break;
                               case 4:
                                   alert("非法操作");
                                   break;
                            }
                        }
                    );
            });
        };

        //发表回复
        $scope.postResponse = function(index) {
            var toUser = $scope.commentUserList[index];
            var commentId = $scope.comments[index].id;
            $.post('api/article/comment/response/add',
                {commentId: commentId, toUser: toUser, content: $scope.newResponse[index]},
                function(resp) {
                    $scope.$apply(function() {
                        switch (resp.data.status) {
                            case 1:
                                $scope.getData();
                                break;
                            case 0:
                                alert("登录状态失效，请重新登录");
                                break;
                            case 3:
                                alert("登录状态失效，请重新登录");
                                break;
                            case 2:
                                alert("服务器错误，请稍后再试");
                                break;
                            case 4:
                                alert("非法操作");
                                break;
                        }
                });
            });
        };
    });
