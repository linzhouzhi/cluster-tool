<div class="container-fluid container-title">
    <div class="container-title-content">
        <div class="pull-left">
            <h1>docker tool</h1>
            <p>docker manager tool center</p>
        </div>
        <div class="pull-right" style="margin-top:55px">
            <button type="button" id="new-nodes" class="btn btn-success" style="margin-left:10px"><span class="glyphicon glyphicon-menu-hamburger"></span> new-nodes</button>
            <button type="button" id="node-list" class="btn btn-success" style="margin-left:10px"><span class="glyphicon glyphicon-menu-hamburger"></span> node-list</button>
            <button type="button" id="add-image" class="btn btn-success" style="margin-left:10px"><span class="glyphicon glyphicon-plus"></span> add-node</button>
        </div>
    </div>
</div>
<div class="container-fluid" style="margin-bottom:70px">
    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div id="image-list-content-div">
                    {{include file="image_list_content.tpl"}}
                </div>
            </div>
        </div>
    </div><!-- .row -->
</div><!-- .container-fluid -->