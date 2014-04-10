var controllers = angular.module("controllers", []);

controllers.controller("EventController", ["$scope", "$http", function ($scope, $http) {
    $http.get("/events").
        success(function (data) {
            $scope.events = data;
        });
}]);

controllers.controller("EventDetailController", ["$scope", "$http", "$routeParams", function ($scope, $http, $routeParams) {
    $http.get("/events/" + $routeParams["eventId"]).success(function (data) {
        $scope.event = data;
    })
}]);
