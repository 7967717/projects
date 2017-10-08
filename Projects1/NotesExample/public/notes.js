/**
 * Created by roman.rudenko on 25-May-16.
 */
var module = angular.module('myapp', ['ngResource']);

module.factory('Note', function ($resource) {
    return $resource(':username/notes', {username: '@username'});
})
    .controller('NotesController', function ($scope, Note) {
        var url = function () {
            return {username: $scope.username || 'user0'};
        }

        var update = function () {
            $scope.notes = Note.query(url());
        };

        $scope.add = function () {
            var note = new Note();
            note.text = $scope.text;
            note.$save(url(), function () {
                $scope.text = '';
                update();
            });
        };

        update();
    });

