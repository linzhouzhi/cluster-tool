/**
 * Created by lzz on 2017/7/29.
 */

window.STATIC_URL = "http://localhost:8080/";
window.SCRIPT_FILE = window.STATIC_URL + "jstpl/";
set_server_arg(1460194057, "v0.2", true, 1);


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

$(function () {

    smarty.post( "/node/list", JSON.stringify({}), "node_list","node-list-div",function () {
        console.log(1111);
    });

    $("#node-list").click(function () {
        smarty.post( "/node/list", JSON.stringify({}), "node_list","node-list-div",function () {
            console.log(1111);
        });
    });

    $("#new-node").click(function () {
        smarty.post( "/node/list", JSON.stringify({}), "node_list","node-list-div",function () {
            console.log(1111);
        });
    });

    $("#add-node").click(function () {
        smarty.html( "add_node", {}, "node-list-div",function () {
            add_node_form_init();
            $("#confirm-add-node").click(function(){
                var data = {};
                data["ip"] = $("input[name='ip']").val();
                data["port"] = $("input[name='port']").val();
                data["username"] = $("input[name='username']").val();
                data["password"] = $("input[name='password']").val();
                data["install_path"] = $("input[name='install_path']").val();
                data["server"] = $("input[name='server']").val();

                smarty.open( "terminal", {}, {"title":"Terminal","width":"700px","height":"auto"},function () {
                    var json_str = JSON.stringify(data);
                    console.log( data );
                    set_cookie("add_node_form_data", json_str );
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
});