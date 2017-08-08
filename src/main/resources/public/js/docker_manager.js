/**
 * Created by lzz on 2017/7/29.
 */

window.STATIC_URL = "http://localhost:8080/";
window.SCRIPT_FILE = window.STATIC_URL + "jstpl/";
set_server_arg(1460194057, "v0.2", true, 1);

images_list();
function images_list() {
    var data = {};
    console.log(data);
    smarty.post( "/docker/image_list", JSON.stringify(data), "image_list","image-list-div",function () {
        console.log(1111);
    });
}

$(document).on("click", "#image-list", function () {
    smarty.html( "image_list_nav", {}, "image-list-content-div",function () {

    });
});

$(document).on("click",".list-group-item", function () {
    smarty.html( "service_list", {}, "image-add-content-div",function () {

    });
});

$(document).on("click", "#add-image", function () {
    smarty.html( "add_image", {}, "image-add-content-div",function () {
        add_image_form_init();
        $("#confirm-add-image").click(function(){
            var data = {};
            data["ip"] = $("input[name='ip']").val();
            data["port"] = $("input[name='port']").val();
            data["username"] = $("input[name='username']").val();
            data["password"] = $("input[name='password']").val();
            data["install_path"] = $("input[name='install_path']").val();
            data["server"] = $("input[name='server']:checked").val();

            console.log( data );
        });
    });
});

function add_image_form_init() {
    var data = get_cookie("add_image_form_data");
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