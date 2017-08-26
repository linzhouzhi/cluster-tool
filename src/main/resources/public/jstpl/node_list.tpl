<div class="container-fluid container-title">
    <div class="container-title-content">
        <div class="pull-left">
            <h1>cluster tool</h1>
            <p>cluster tool manager tool center</p>
        </div>
        <div class="pull-right" style="margin-top:55px">
            <button type="button" id="add-service-image" class="btn btn-success" style="margin-left:10px"><span class="glyphicon glyphicon-menu-hamburger"></span> add-image</button>
            <button type="button" id="node-list" class="btn btn-success" style="margin-left:10px"><span class="glyphicon glyphicon-menu-hamburger"></span> node-list</button>
            <button type="button" id="add-node" class="btn btn-success" style="margin-left:10px"><span class="glyphicon glyphicon-plus"></span> add-node</button>
        </div>
    </div>
</div>
<div class="container-fluid" style="margin-bottom:70px">
    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div id="node-list-content-div">
                    {{include file="node_list_content.tpl"}}
                </div>
            </div>
        </div>
    </div><!-- .row -->
</div><!-- .container-fluid -->