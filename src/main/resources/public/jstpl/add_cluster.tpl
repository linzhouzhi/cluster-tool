<div class="row" style="margin: 10px">
<div class="col-sm-12" style="padding-left:40px;padding-right:40px">
    <div class="form-group" >
        <label>cluster-name</label>
        <input name="cluster_name" type="text" class="form-control" data-require="1" data-len="3-20" data-len-msg="字符串长度为5-20" data-format="[a-zA-Z0-9]+" data-format-msg="只能由英文字母和数字组成"  placeholder="redis" >
        <div id="cluster_name_tip"></div>
    </div>
    <div class="form-group">
      <label>ip</label>
      <input name="cluster_ip" type="text" class="form-control" placeholder="127.0.0.1" data-require="1" data-len="5-20" data-len-msg="长度必须在8-15内" data-format="[0-9]+[\.][0-9]+[\.][0-9]+[\.][0-9]" data-format-msg="只能由 . 和数字组成" >
      <div id="cluster_ip_tip"></div>
    </div>
    <div class="form-group">
      <label>port</label>
      <input name="cluster_port" type="text" class="form-control" data-require="1" data-type="range" data-min="50" data-max="10000" data-format-msg="端口号必须在10000 以内" placeholder="8080">
      <div id="cluster_port_tip"></div>
    </div>
    <button id="confirm-add-cluster" class="btn btn-default">submit</button>
</div>
</div><!-- .row -->
