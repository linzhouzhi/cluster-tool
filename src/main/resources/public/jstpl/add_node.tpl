<div class="row">
    <div class="col-xs-4"></div>
      <div class="col-xs-4">
        <div>
        <div class="form-group">
            <label>ip</label>
            <input name="ip" type="text" class="form-control" placeholder="127.0.0.1" data-require="1" data-len="5-20" data-len-msg="长度必须在8-15内" data-format="[0-9]+[\.][0-9]+[\.][0-9]+[\.][0-9]" data-format-msg="只能由 . 和数字组成" >
            <div id="ip_tip"></div>
          </div>
          <div class="form-group">
            <label>port</label>
            <input name="port" type="text" class="form-control" data-require="1" data-type="range" data-min="50" data-max="10000" data-format-msg="端口号必须在10000 以内" placeholder="8080">
            <div id="port_tip"></div>
          </div>
          <div class="form-group" >
              <label>username</label>
              <input name="username" type="text" class="form-control" data-require="1" data-len="3-20" data-len-msg="字符串长度为5-20" data-format="[a-zA-Z0-9]+" data-format-msg="只能由英文字母和数字组成"  placeholder="redis" >
              <div id="username_tip"></div>
          </div>
          <div class="form-group">
              <label>password</label>
              <input name="password" type="text" class="form-control" data-require="1" data-len="5-20" data-len-msg="字符串长度为5-20" data-format-msg="只能由英文字母和数字组成"  placeholder="redis1234">
              <div id="password_tip"></div>
          </div>
          <div class="form-group">
              <label>install-path</label>
              <input name="install_path" type="text" class="form-control" data-require="1" data-len="4-255" data-len-msg="字符串长度为4-255"  data-format-msg="只能由英文字母和数字组成且 ／ 开头" placeholder="/opt/app/install/redis-cluster">
              <div id="install_path_tip"></div>
          </div>
          <div class="form-group">
            <label class="radio-inline">
              <input type="radio" checked="true" name="server" value="redis"> redis
            </label>
            <label class="radio-inline">
              <input type="radio" name="server" value="kafka"> kafka
            </label>
            <label class="radio-inline">
              <input type="radio" name="server" value="zookeeper"> zookeeper
            </label>
          </div>
          <button id="confirm-add-node" class="btn btn-default">submit</button>
        </div>
      </div>
      <div class="col-xs-4"></div>
</div><!-- .row -->
