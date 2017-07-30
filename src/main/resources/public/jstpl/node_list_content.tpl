<table class="table table-striped  m-b-none">
	<thead>
		<tr>
		    <th>status</td>
			<th>ID</th>
			<th>host</th>
			<th>username</th>
			<th>password</th>
			<th>server</th>
			<th>install_path</th>
			<th style="width:290px">option</th>
		</tr>
	</thead>
	<tbody>
		{{foreach from=$result item=rs}}
		<tr id="admin_row_{{$rs.ID}}">
		    <td>{{node_status status=$rs.STATUS}}</td>
		    <td>{{$rs.ID}}</td>
		    <td>{{$rs.IP}}:{{$rs.PORT}}</td>
		    <td>{{$rs.USERNAME}}</td>
		    <td>{{$rs.PASSWORD}}</td>
		    <td>{{$rs.SERVER}}</td>
		    <td>{{$rs.INSTALL_PATH}}</td>
		    <td style="text-align:right">
		        <button type="button" id="delete-node" data-id="{{$rs.ID}}" class="btn btn-default btn-sm">delete</button>
		        <button type="button" id="restart-node" class="btn btn-default btn-sm">restart</button>
		        <button type="button" id="start-node" class="btn btn-info btn-sm">start</button>
		        <button type="button" id="stop-node" class="btn btn-warning btn-sm">stop</button>
		        <button type="button" id="move-cluster" data-id="{{$rs.ID}}" data-ip="{{$rs.IP}}" data-port="{{$rs.PORT}}" class="btn btn-success btn-sm">move</button>
		    </td>
		</tr>
		{{/foreach}}
	</tbody>
</table>