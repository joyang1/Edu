var myDb;
function createDb(db_name, db_version, db_displayname, db_size) {
    myDb = window.openDatabase(db_name, db_version, db_displayname, db_size);
}

function onTxError(ex, err) {
    var msgText;
    if (err) {
        msgText = "Tx:" + err.message + "(" + err.code + ")+";
    } else {
        msgText = "Tx:Unknow error";
    }
    console.log(msgText);
    //alert(msgText);
};

function onTxSuccess() {
    console.log("Tx:success");
    //alert("Tx:success");

}

function createTable1(tx) {
    var sqlStr = 'create table if not exists news(titleid int, title text, titledate varchar(15),depid int,depname varchar(50))';
    console.log(sqlStr);
    tx.executeSql(sqlStr, [], onSqlSuccess, onSqlError);
}
function createTable2(tx) {
    var sqlStr = 'create table if not exists newsofall(titleid int, title text,titledate varchar(15),userno varchar(12),depid int,depname varchar(50))';
    console.log(sqlStr);
    tx.executeSql(sqlStr, [], onSqlSuccess, onSqlError);
}


var newsid = new Array();//要删除的newid数组
var delCount; //要删除的数量
function onSqlSuccess(tx, res) {
    if (res) {
        console.log("Insert ID:" + res.insertId);
        console.log("Row affected:" + res.rowsAffected);
        //alert(res.rows.length);
        if (res.rows) {
            var len = res.rows.length;
            //alert(len);
            if (len > 0) {
                for (var i = 0; i < len; i++) {
                    //alert(res.rows.item(i).titleid);
                }
            } else {
                //alert("No records processed.");
            }
        }
    } else {
        //alert("No results returned.");
    }
}

function onSqlError(tx, err) {
    var msgText;
    if (err) {
        msgText = "SQL:" + err.message + "(" + err.code + ")";
    } else {
        msgText = "SQL:Unknow error";
    }
    console.log(msgText);
    alert(msgText);
}

//function updateRecord(tx) {
//    var sqlStr = 'update news set titleid=?, title=? where id=?';
//    console.log(sqlStr);
//    for(var i=0; i<10; i++) {
//            tx.executeSql(sqlStr, [ids[i], titles[i],(i+1)], onSqlSuccess, onSqlError);
//    }
//}


function insertRecord(tx) {
    var insertStr = 'insert into news(titleid, title,titledate,depid,depname) values (?,?,?,?,?)';
    //var delStr = "delete from news where titleid = ?";
    //alert(length);
    if (length == 10) {
        delCount = sizeofnews;
        //alert(delCount);
        for (var i = sizeofnews - 1; i >= 0; i--) {
            tx.executeSql(insertStr, [ids[i], titles[i], titledates[i], depid, depname], onSqlSuccess, onSqlError);
        }
        myDb.transaction(getNewsidofDel, onTxError, onTxSuccess);

    } else if (length + sizeofnews > 10) {
        delCount = length + sizeofnews - 10;
        for (var i = sizeofnews - 1; i >= 0; i--) {
            tx.executeSql(insertStr, [ids[i], titles[i], titledates[i], depid, depname], onSqlSuccess, onSqlError);
        }
        myDb.transaction(getNewsidofDel, onTxError, onTxSuccess);
        //alert(delCount+"#");
//        for(var i=0; i<delCount; i++){
//            alert(newsid[i]+"**");
//            //var delstr = "delete from news where rowid in (select rowid from log order by Time limit "+delcount+")";
//            tx.executeSql(delStr, [newsid[i]], onTxError, onTxSuccess);
//        }
    } else if (length + sizeofnews <= 10) {
        for (var i = sizeofnews - 1; i >= 0; i--) {
            tx.executeSql(insertStr, [ids[i], titles[i], titledates[i], depid, depname], onSqlSuccess, onSqlError);
        }
    }
}

function openView(viewType) {
    var sqlStr = "select * from news where depid = ? order by titleid desc";
    myDb.transaction(
        function (tx) {
            tx.executeSql(sqlStr, [depid], onQuerySuccess, onQueryFailure);
        }, onTxError, onTxSuccess);
}

function onQuerySuccess(tx, results) {
    if (results.rows) {
        console.log("Rows:" + results.rows);
        var len = results.rows.length;
        if (len > 0) {
            var content = "";
            //alert(len);
            for (var i = 0; i < len; i++) {
                var thetitleid = results.rows.item(i).titleid;
                var thetitle = results.rows.item(i).title;
                var thetitledate = results.rows.item(i).titledate;
                var value = depid +";"+ thetitleid;
                content = content + "<li>";
                content = content + "<a href='getOneNews.html?value="+value+"' data-ajax='false'>"
                content = content + "<img src=\"../images/QQ.png\">";
                content = content + "<p style='font-size: 14px'>";
                content = content + thetitleid + ":" + thetitle;
                content = content + "</p>";
                content = content + "<font style='font-size: 12px; color: gray'>" + thetitledate + "</font>"
                content = content + "</a>";
                content = content + "</li>";
            }
            $("#thelist").prepend(content).listview('refresh');
        } else {
            //alert("Now rows.");
        }
    } else {
        alert("No records match selection criteria.");
    }
}


//显示首页新闻
function openIndexView(viewType) {
    //var sqlStr = "select distinct depname from news";
    var sqlStr = "select depid,depname from news group by depname";
    myDb.transaction(
        function (tx) {
            tx.executeSql(sqlStr, [], onQueryIndex, onQueryFailure);
        }, onTxError, onTxSuccess);
}

var localDepname; //本地的depname
var localdepid;
var localDepname1; //本地的depname
var localdepid1;
function onQueryIndex(tx, results) {
    if (results.rows) {
        var len = results.rows.length;
        if (len > 0) {
            for (var i = 0; i < len; i++) {
                localDepname = results.rows.item(i).depname;
                localdepid = results.rows.item(i).depid;
                myDb.transaction(getDepInfo, onTxError, onTxSuccess);
                alert(localDepname+"*");
            }
        } else {
            //alert("Now rows.");
        }
    } else {
        alert("No records match selection criteria.");
    }
}
//得到本地所有depname
function getDepInfo(viewType) {
    var sqlStr = "select * from news where depname = ? order by titleid desc";
    myDb.transaction(
        function (tx) {
            tx.executeSql(sqlStr, [localDepname], onQueryDep, onQueryFailure);
        }, onTxError, onTxSuccess);
}


function onQueryDep(tx, results) {
    if (results.rows) {
        var len = results.rows.length;
        if (len > 0) {
            if (len > 3) {
                len = 3;
            }
            var content ="";
            content += "<li data-role=\"list-divider\" >" + localDepname + "<a class=\"gengduo\" href=\"news/gonggong.html\" onclick=\"openWindowWithValue("+localdepid+")\" data-ajax=\"false\">更多</a></li>"
            for (var i = 0; i < len; i++) {
                var thetitleid = results.rows.item(i).titleid;
                var thetitle = results.rows.item(i).title;
                var thetitledate = results.rows.item(i).titledate;
                content = content + "<li>";
                //content = content + "<a href='http://59.46.168.82:9090/EduWeb/GetOneNewsServlet?id=" + thetitleid + "'>";
                content = content + "<a onclick='openWindowWithValue("+thetitleid+")'>";
                content = content + "<p style='font-size: 14px'>";
                content = content + thetitleid + ":" + thetitle;
                content = content + "</p>";
                content = content + "<font style='font-size: 12px; color: gray'>" + thetitledate + "</font>"
                content = content + "</a>";
                content = content + "</li>";
            }
            $("#thelist").prepend(content).listview('refresh');
        }

    }
    else {
        alert("No records match selection criteria.");
    }
}

function onQueryFailure() {
    alert("error");
}


//查本地记录条数
function getLengthofNews(viewType) {
    var sqlStr = "select * from news where depid=?";
    myDb.transaction(
        function (tx) {
            tx.executeSql(sqlStr, [depid], onQuerySuccessGet, onQueryFailure);
        }, onTxError, onTxSuccess);
}
var length = 0;
function onQuerySuccessGet(tx, results) {
    if (results.rows) {
        console.log("Rows:" + results.rows);
        length = results.rows.length;
    } else {
        //alert("No records match selection criteria.");
    }
}


//查本地记录的最新的1条数据的id
function getLastNews(viewType) {
    var sqlStr = "select titleid from news where depid=? order by titleid desc";
    myDb.transaction(
        function (tx) {
            tx.executeSql(sqlStr, [depid], onQueryLastNews, onQueryFailure);
        }, onTxError, onTxSuccess);
    //alert(depid);
}

function onQueryLastNews(tx, results) {
    //alert(results);
    if (results.rows) {
        console.log("Rows:" + results.rows);
        var length1 = results.rows.length;
        //alert(length1);
        if (length1 > 0) {
            lastid = results.rows.item(0).titleid;
            //alert(lastid);
        }
    } else {
        //alert("No records match selection criteria.");
    }
}

//查本地记录的最旧的1条数据的id
function getFirstNews(viewType) {
    var sqlStr = "select titleid from news where depid=? order by titleid asc";
    myDb.transaction(
        function (tx) {
            tx.executeSql(sqlStr, [depid], onQueryFirstNews, onQueryFailure);
        }, onTxError, onTxSuccess);
}

function onQueryFirstNews(tx, results) {
    if (results.rows) {
        console.log("Rows:" + results.rows);
        var length2 = results.rows.length;
        //alert(length2);
        if (length2 > 0) {
            firstid = results.rows.item(0).titleid;
            window.localStorage.setItem("firstId", firstid);
            //alert(firstid);
        }
    } else {
        //alert("No records match selection criteria.");
    }
}


//查本地记录的需要删除数据的id
function getNewsidofDel(viewType) {
    var sqlStr = "select titleid from news where depid=?  order by titleid asc";
    myDb.transaction(
        function (tx) {
            tx.executeSql(sqlStr, [depid], onQueryNewsofDel, onQueryFailure);
        }, onTxError, onTxSuccess);
}

function onQueryNewsofDel(tx, results) {
    if (results.rows) {
        console.log("Rows:" + results.rows);
        var lengthofDel = results.rows.length;
        //alert(lengthofDel + "*");
        var delStr = "delete from news where titleid = ?";
        if (lengthofDel > 0) {
            for (var i = 0; i < delCount; i++) {
                newsid[i] = results.rows.item(i).titleid;
                tx.executeSql(delStr, [newsid[i]], onTxError, onTxSuccess);
            }
        }
    } else {
        //alert("No records match selection criteria.");
    }
}

function openWindowWithValue(depid){
    window.open("news/gonggong.html?value="+escape(depid));
}

//function openWindowWithValueElse(id){
//    alert("aa");
//    window.open("getOneNews.html?value="+escape(id));
//}

