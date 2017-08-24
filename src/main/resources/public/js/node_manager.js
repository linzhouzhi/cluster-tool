/**
 * Created by lzz on 2017/7/29.
 */

window.STATIC_URL = "http://localhost:8080/";
window.SCRIPT_FILE = window.STATIC_URL + "jstpl/";
set_server_arg(1460194057, "v0.2", true, 1);

init_data();

function init_data() {
    window.clusterid = 1;
    window.cluster_ip = "127.0.0.1";
    window.cluster_port = 8006;
}

var ws = null;
var url = window.STATIC_URL + "webSocketServer/sockjs";
var transports = [];

function connect(data) {
    if (!url) {
        alert('Select whether to use W3C WebSocket or SockJS');
        return;
    }

    ws = new SockJS(window.STATIC_URL+"webSocketServer/sockjs");

    ws.onopen = function () {
        echo(data);
        echo_msg('Info: connection opened.');
    };

    ws.onmessage = function (event) {
        echo_msg('Received: ' + event.data);
    };

    ws.onclose = function (event) {
        echo_msg('Info: connection closed.');
        echo_msg(event);
    };
}

function disconnect() {
    if (ws != null) {
        ws.close();
        console.log("close....!!");
        ws = null;
    }
}

function echo(data) {
    if (ws != null) {
        ws.send(data);
    } else {
        alert('connection not established, please connect.');
    }
}

function updateUrl(urlPath) {
    if (urlPath.indexOf('sockjs') != -1) {
        url = urlPath;
        document.getElementById('sockJsTransportSelect').style.visibility = 'visible';
    }
    else {
        if (window.location.protocol == 'http:') {
            url = 'ws://' + window.location.host + urlPath;
        } else {
            url = 'wss://' + window.location.host + urlPath;
        }
        document.getElementById('sockJsTransportSelect').style.visibility = 'hidden';
    }
}

function updateTransport(transport) {
    transports = (transport == 'all') ?  [] : [transport];
}

function echo_msg(message) {
    console.log(message);
    try
    {
        var terminal = document.getElementById('console');
        var p = document.createElement('p');
        p.style.wordWrap = 'break-word';
        p.appendChild(document.createTextNode(message));
        terminal.appendChild(p);
        while (terminal.childNodes.length > 25) {
            terminal.removeChild(console.firstChild);
        }
        terminal.scrollTop = terminal.scrollHeight;
    }
    catch( e )
    {
        console.log( e );
        disconnect();
    }
}

function add_node_form_init() {
    var data = get_cookie("add_node_form_data");
    var form_data = JSON.parse(data);
    if( typeof form_data == "undefine" || form_data == null ){
        return
    }
    $("input[name='ip']").val( form_data.ip );
    $("input[name='port']").val( form_data.port );
    $("input[name='username']").val( form_data.username );
    $("input[name='password']").val( form_data.password );
    $("input[name='install_path']").val( form_data.install_path );
}

function add_node(json_str) {
    $.ajax({
        url:'/node/add',
        type:'POST',
        contentType: 'application/json',
        async:true,
        data:json_str,
        timeout:5000,
        dataType:'json',
        success:function(data){
            console.log(data);
        },
        error: function (data) {
            console.log(data);
        }
    });
}

function move_node(data) {
    $.ajax({
        url:'/node/move_cluster',
        type:'POST',
        contentType: 'application/json',
        async:true,
        data:data,
        timeout:5000,
        dataType:'json',
        success:function(data){
            console.log( data );
            sparrow_win.msg( "添加完成", {icon:6} );
        },
        error: function (data) {
            sparrow_win.msg( "添加失败", {icon:5} );
        }
    });
}


function delete_node(data) {
    $.ajax({
        url:'/node/delete_node',
        type:'POST',
        contentType: 'application/json',
        async:true,
        data:data,
        timeout:5000,
        dataType:'json',
        success:function(data){
            console.log( data );
            sparrow_win.msg( "删除完成", {icon:6} );
        },
        error: function (data) {
            sparrow_win.msg( "fail!!!!", {icon:5} );
        }
    });
}

function content_init() {
    node_list();
}

function node_list() {
    var data = {};
    var cluster_data = JSON.parse( get_cookie("cluster_data") );
    if( cluster_data == null ){
        window.clusterid = $("#select-clsuer").data("clusterid");
        data.clusterid = window.clusterid;
    }else{
        data = cluster_data;
        window.clusterid = data.clusterid;
        $("#select-clsuer").text( $("[data-clusterid=" + window.clusterid + "]").text() );
        $("#select-clsuer").data("clusterid", window.clusterid);
    }
    console.log(data);
    smarty.post( "/node/list", JSON.stringify(data), "node_list","node-list-div",function () {
        console.log(1111);
    });
}

smarty.register_function( "node_status", function(params){
    if( params["status"] == "OK" ){
        return "<span class='glyphicon glyphicon-ok-circle status-success'></span>";
    }
    return "<span class='glyphicon glyphicon-ban-circle status-fail'></span>";
});

$(function () {
    content_init();
    $(".select-cluster").click(function () {
        var data = {};
        data["clusterid"] = $(this).data("clusterid");

        $("#select-clsuer").text( $(this).text() );
        $("#select-clsuer").data("clusterid", data["clusterid"]);

        set_cookie("cluster_data",JSON.stringify(data));
        node_list();
    });

    $(document).on("click", "#new-nodes", function () {
        node_list();
    });

    $(document).on("click", "#node-list", function () {
        node_list();
    });

    $(document).on("click", "#add-node", function () {
        smarty.html( "add_node", {}, "node-list-content-div",function () {

            $('#backage-upload').fileinput({
                uploadUrl: 'http://localhost:8080/node/upload_package',
                allowedFileExtensions: ['jpg', 'png', 'gif']
            });

            $("#other-radio").click(function () {
                $("#package-form").removeClass("hidden");
            });

            add_node_form_init();
            $("#confirm-add-node").click(function(){
                var data = {};
                var cluster_data = JSON.parse( get_cookie("cluster_data") );
                //data["clusterid"] = cluster_data.clusterid;
                data["ip"] = $("input[name='ip']").val();
                data["port"] = $("input[name='port']").val();
                data["username"] = $("input[name='username']").val();
                data["password"] = $("input[name='password']").val();
                data["install_path"] = $("input[name='install_path']").val();
                data["server"] = $("input[name='server']:checked").val();

                smarty.open( "terminal", {}, {"title":"Terminal","width":"700px","height":"auto"},function () {
                    var json_str = JSON.stringify(data);
                    console.log( data );
                    set_cookie("add_node_form_data", json_str );
                    add_node( json_str );
                    connect( json_str );
                } )
            });
        });
    });

    $("#add-cluster").click(function(){
        smarty.open( "add_cluster", {}, {"title":"add cluster","width":"400px","height":"auto"},function () {
            console.log(1113232);
        } )
    });

    $(document).on("click","#move-cluster",function () {
        var data = {};
        data["id"] = $(this).data("id");
        data["ip"] = $(this).data("ip");
        data["port"] = $(this).data("port");
        data["cluster_ip"] = window.cluster_ip;
        data["cluster_port"] = window.cluster_port;
        sparrow_win.confirm( '确认添加集群 '+ window.cluster_ip +':' + window.cluster_port +'!!', function(){
            move_node( JSON.stringify(data) );
        } );
    });
    $(document).on("click","#delete-node",function () {
        var data = {};
        data["id"] = $(this).data("id");
        sparrow_win.confirm( '确认删除!', function(){
            delete_node(JSON.stringify(data));
        } );
    });
    $(document).on("click","#restart-node",function () {

    });
    $(document).on("click","#start-node",function () {

    });
    $(document).on("click","#stop-node",function () {

    });
});