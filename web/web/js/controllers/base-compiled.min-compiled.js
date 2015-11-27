angular.module("app", []).controller("test", function ($scope) {
  console.log(1);$.getJSON("api/account/personalInfo", function (resp) {
    $scope.$apply(function () {
      $scope.info = resp.data;$.post("api/account/logout", function () {
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

//# sourceMappingURL=base-compiled.min-compiled.js.map