/**
 * Created by mingtao on 15-11-3.
 */

angular.module("app", [])
.controller("test", function($scope) {
        console.log(1);
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
        $.getJSON("api/account/personalInfo", function(resp) {
                        $scope.$apply(function() {
                            $scope.info = resp.data;


                            $.post("api/account/logout", function() {
                                $scope.$apply(function() {
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
