/**
 * Created by 挺 on 2014/10/21.
 */
function checkLogin(){
    var userright = window.localStorage.getItem("userright");
    document.addEventListener("online", isOnline, false); //网络在线
    document.addEventListener("offline", isOffline, false); //网络不在线
    if(userright == "1"){

    }else{
        $('#gz').text("点此登录");
        $('#gz_button').click(function (){
            window.location.href="lgandreg/login.html";
        });
    }
}

function isOffline() {
    //alert("offline");
    $('#gz').text("点此登录");
    $('#gz_button').click(function (){
        window.location.href="lgandreg/login.html";
    });
    $('#networkInfo').prepend("网络连接错误，请重新连接!");
}
function isOnline() {
    //alert("online");
}