/**
 * Created by 挺 on 2014/10/7.
 */
Date.prototype.format = function (format) {
    var o = {
        "M+": this.getMonth() + 1, //month
        "d+": this.getDate(), //day
        "h+": this.getHours(), //hour
        "m+": this.getMinutes(), //minute
        "s+": this.getSeconds(), //second
        "q+": Math.floor((this.getMonth() + 3) / 3), //quarter
        "S": this.getMilliseconds() //millisecond
    }

    if (/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }

    for (var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return format;
}

function dateformat(x, y) {
    var z = {M: x.getMonth() + 1, d: x.getDate(), h: x.getHours(), m: x.getMinutes(), s: x.getSeconds()};
    y = y.replace(/(M+|d+|h+|m+|s+)/g, function (v) {
        return ((v.length > 1 ? "0" : "") + eval('z.' + v.slice(-1))).slice(-2)
    });
    return y.replace(/(y+)/g, function (v) {
        return x.getFullYear().toString().slice(-v.length)
    });
}

function formatDate(date, format) {
    if (arguments.length < 2 && !date.getTime) {
        format = date;
        date = new Date();
    }
    typeof format != 'string' && (format = 'YYYY年MM月DD日 hh时mm分ss秒');
    var week = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', '日', '一', '二', '三', '四', '五', '六'];
    return format.replace(/YYYY|YY|MM|DD|hh|mm|ss|星期|周|www|week/g, function (a) {
        switch (a) {
            case "YYYY":
                return date.getFullYear();
            case "YY":
                return (date.getFullYear() + "").slice(2);
            case "MM":
                return date.getMonth() + 1;
            case "DD":
                return date.getDate();
            case "hh":
                return date.getHours();
            case "mm":
                return date.getMinutes();
            case "ss":
                return date.getSeconds();
            case "星期":
                return "星期" + week[date.getDay() + 7];
            case "周":
                return "周" + week[date.getDay() + 7];
            case "week":
                return week[date.getDay()];
            case "www":
                return week[date.getDay()].slice(0, 3);
        }
    });
}

function StringToDate(DateStr) {
    var converted = Date.parse(DateStr);
    var myDate = new Date(converted);
    if (isNaN(myDate)) {
        var delimCahar = DateStr.indexOf('/') != -1 ? '/' : '-';
        var arys = DateStr.split('-');
        myDate = new Date(arys[0], --arys[1], arys[2]);
    }
    return myDate;
}
