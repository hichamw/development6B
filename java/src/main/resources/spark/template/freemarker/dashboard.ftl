<#include "head.ftl">
<!--main content start-->
<section id="main-content">
    <section class="wrapper site-min-height">
        <div class="breadcrumbs">
            <h3>
                Dashboard
            </h3>
        </div>
        <div class="row mt">
            <div class="col-lg-12">

                <div  ng-controller="myController">
                    <h3>Voertuigen ({{ mydata.resultObject.length }})</h3>
                    <ul ng-repeat="v in mydata.resultObject track by $index">
                        <li>UnitID: {{ v }}</li>
                    </ul>


                </div>
            </div>
        </div>


    </section><! --/wrapper -->
</section><!-- /MAIN CONTENT -->
<#include "bottom.ftl">