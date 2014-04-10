function EventController($scope, $http) {
    $http.get("http://localhost:3000/events").
        success(function(data) {
            $scope.events = data;
        });
}