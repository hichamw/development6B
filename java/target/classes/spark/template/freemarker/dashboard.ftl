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
                <h1>Welkom bij Giz!</h1>
                <div  ng-controller="myController">
                    <h3>Voertuigen ({{ mydata.resultObject.length }})</h3>
                </div>
                <div ng-controller="kilometerstandController">
                    <h3>Kilometerstand ({{ kilometerstand.resultObject }})</h3>
                </div>
            </div>
        </div>


    </section><! --/wrapper -->
</section><!-- /MAIN CONTENT -->
<#include "bottom.ftl">