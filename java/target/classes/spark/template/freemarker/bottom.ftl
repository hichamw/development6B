<!-- js placed at the end of the document so the pages load faster -->
<script src="/assets/js/jquery.js"></script>
<script src="/assets/js/bootstrap.min.js"></script>
<script src="/assets/js/jquery-ui-1.9.2.custom.min.js"></script>
<script src="/assets/js/jquery.ui.touch-punch.min.js"></script>
<script class="include" type="text/javascript" src="/assets/js/jquery.dcjqaccordion.2.7.js"></script>
<script src="/assets/js/jquery.scrollTo.min.js"></script>
<script src="/assets/js/jquery.nicescroll.js" type="text/javascript"></script>
<script src="/assets/js/common-scripts.js"></script>

<script>
    //custom select box

    $(function(){
        $('select.styled').customSelect();
    });

    var app = angular.module('myApp', []);
    app.controller('myController', function($scope, $http) {
        $http.get("http://localhost:4567/api/units")
                .then(function(response) {$scope.mydata = response.data;
                    console.log($scope.mydata)
                });

    });
    app.controller('kilometerstandController', function($scope, $http) {
        $http.get("http://localhost:4567/api/kilometerstand")
                .then(function(response) {$scope.kilometerstand = response.data;
                    console.log($scope.kilometst)
                });

    });
</script>
</body>
</html>