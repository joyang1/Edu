function getCookie(c_name) {
    //alert(window.document.cookie.length);
    if (window.document.cookie.length > 0) {
        var c_start = window.document.cookie.indexOf(c_name + "=");
        //alert(c_start);
        if (c_start != -1) {
            c_start = c_start + c_name.length + 1;
            var c_end = window.document.cookie.indexOf(";", c_start);
            if (c_end == -1)
                c_end = window.document.cookie.length;
            return unescape(window.document.cookie.substring(c_start, c_end));
        }
    }
    return "";
}

function setCookie(c_name, value, expiredays) {
    var exdate = new Date();
    exdate.setDate(exdate.getDate() + expiredays);
    window.document.cookie = c_name + "=" + escape(value)
        + ((expiredays == null) ? "" : ";expires=" + exdate.toGMTString());
    //alert(window.document.cookie);
}
