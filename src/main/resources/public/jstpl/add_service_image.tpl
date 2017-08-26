<div class="row">
    <div class="col-xs-4"></div>
      <div class="col-xs-4">
        <div id="add-service-image-form">
          <div class="form-group">
            <label>image</label>
            <input name="image" type="text" class="form-control" placeholder="image" data-require="1">
            <div id="image_tip"></div>
          </div>
          <div class="form-group">
            <label>tag</label>
            <input name="tag" type="text" class="form-control" data-require="1" value="latest">
            <div id="tag_tip"></div>
          </div>
          <div class="form-group" id="package-form">
              <label class="control-label">Select File</label>
              <input id="backage-upload" name="package" type="file" class="file" data-show-preview="false">
          </div>
          <button id="confirm-add-service-image" class="btn btn-default">submit</button>
        </div>
    <div class="col-xs-4"></div>
</div><!-- .row -->