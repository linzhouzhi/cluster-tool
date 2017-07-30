/**
 * Created by lzz on 2017/7/28.
 */
var stompClient = null;

function connect1() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        stompClient.subscribe('/topic/greetings', function (data) {
            showGreeting(data.body);
        });
    });
}

function send_msg(command) {
    stompClient.send("/app/com/lzz/terminal", {}, command.trim() );
    // 清空 input 内容
    $("#terminal-input").val("");
}

function showGreeting(message) {
    $("#terminal-content").append("<p>" + message + "</p>");
    // 重新设置滚动高度
    var h = $("#terminal-content").height() - $("#terminal").height();
    $("#terminal").scrollTop(h + 100);
}

function init_terminal_input_preix_val() {
    var data_preix = get_cookie("terminal_input_preix_val");
    if( typeof data_preix == "undefine" || data_preix == null ){
        data_preix = "";
    }
    return data_preix;
}

$(function () {
    connect1();
    //init input preix
    $("#terminal-input-preix").val( init_terminal_input_preix_val() );

    $( "#send" ).click(function() {
        var content = $("#terminal-input").val().trim();
        if( content == "undefine" || content == "" ){
            $("#terminal-content").append("<p> 无效指令 !!!!</p>");
            return;
        }
        var cmd = "";
        // 如果 .. 开头就忽略前缀
        if( content.startsWith("..", 0) ){
            cmd = content.substr(2).trim();
        }else{
            var data_preix = init_terminal_input_preix_val();
            cmd = data_preix + " " + content;
        }
        var message = ">> " + content;
        $("#terminal-content").append("<p>" + message + "</p>");
        send_msg( cmd );
    });
    $("#footer").click(function(){
        $("#terminal-input").focus();
    });

    $("#terminal-input-preix").click(function (event) {
        event.stopPropagation();//阻止事件冒泡即可
        $(this).focus();
    });

    $("#terminal-input-preix").blur(function(){
        var terminal_input_preix_val = $(this).val();
        set_cookie("terminal_input_preix_val", terminal_input_preix_val );
    });
    // /Users/lzz/soft_install/redis/redis-cluster/redis01


    var $input = $("#terminal-input");
    $input.typeahead({
        source: ["info","cluster nodes","cluster info","--scan --pattern '*'","config get maxmemory"],
        autoSelect: true
    });
});