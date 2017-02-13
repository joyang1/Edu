/**
 * Created by 挺 on 2014/10/11.
 */
function duibi(a,b)
{
    var arr=a.split("-");
    var starttime=new Date(arr[0],arr[1],arr[2]);
    var starttimes=starttime.getTime();
    var arrs=b.split("-");
    var lktime=new Date(arrs[0],arrs[1],arrs[2]);
    var lktimes=lktime.getTime();
    if(starttimes>=lktimes)
    {
        //alert('开始时间大于离开时间，请检查');
        return false;
    }
    else
        return true;
}

function comptime(a,b){
    var beginTime = a;
    var endTime = b;
    var beginTimes=beginTime.substring(0,10).split('-');
    var endTimes=endTime.substring(0,10).split('-');
    beginTime=beginTimes[1]+'-'+beginTimes[2]+'-'+beginTimes[0]+' '+beginTime.substring(10,19);
    endTime=endTimes[1]+'-'+endTimes[2]+'-'+endTimes[0]+' '+endTime.substring(10,19);


    alert(beginTime+"aaa"+endTime);
    alert(Date.parse(endTime));
    alert(Date.parse(beginTime));
    var a =(Date.parse(endTime)-Date.parse(beginTime))/3600/1000;
    if(a<0){
        alert("endTime小!");
    }else if (a>0){
        alert("endTime大!");
    }else if (a==0){
        alert("时间相等!");
    }else{
        return 'exception'
    }

}
