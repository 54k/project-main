var app = angular.module("Application", ["ngRoute", "controllers"]);

app.config(["$routeProvider", function ($routeProvider) {
    $routeProvider.when("/events", {
        templateUrl: "template/event.html",
        controller: "EventController"
    }).when("/events/:eventId", {
        templateUrl: "template/event-detail.html",
        controller: "EventDetailController"
    }).otherwise({
        redirectTo: "/events"
    });
}]);