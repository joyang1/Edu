/**
 * Created by 挺 on 2014/10/31.
 */
function timecompare(beginTime,endTime){

    var beginTimes=beginTime.substring(0,10).split('-');
    var endTimes=endTime.substring(0,10).split('-');

    beginTime=beginTimes[1]+'-'+beginTimes[2]+'-'+beginTimes[0]+' '+beginTime.substring(10,19);
    endTime=endTimes[1]+'-'+endTimes[2]+'-'+endTimes[0]+' '+endTime.substring(10,19);

    var a =(Date.parse(endTime)-Date.parse(beginTime))/3600/1000;
//    if(a<0){
//        alert("endTime小!");
//    }else if (a>0){
//        alert("endTime大!");
//    }else if (a==0){
//        alert("时间相等!");
//    }else{
//        return 'exception'
//    }

    return a;
}



function CurentTime() {
    var now = new Date();

    var year = now.getFullYear();       //年
    var month = now.getMonth() + 1;     //月
    var day = now.getDate();            //日

    var hh = now.getHours();            //时
    var mm = now.getMinutes();          //分
    var ss = now.getSeconds();          //分

    var clock = year + "-";

    if (month < 10)
        clock += "0";

    clock += month + "-";

    if (day < 10)
        clock += "0";

    clock += day + " ";

    if (hh < 10)
        clock += "0";

    clock += hh + ":";
    if (mm < 10)
        clock += '0';
    clock += mm + ":";

    if (ss < 10)
        clock += '0';
    clock += ss;
    return(clock);
}


function dateCompare(startdate,enddate)
{
    var arr=startdate.split("-");
    var starttime=new Date(arr[0],arr[1],arr[2]);
    var starttimes=starttime.getTime();
    var arrs=enddate.split("-");
    var lktime=new Date(arrs[0],arrs[1],arrs[2]);
    var lktimes=lktime.getTime();

    if(starttimes>=lktimes)
    {
        return false;
    }
    else
        return true;

}