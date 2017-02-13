/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slv.util;

import com.dao.SqlHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author joyang
 */
public class GetJson {

    private static SqlHelper helper = null;

    public static String getNewsTitleJson(ArrayList al) {
        String json = "";
        json += "itemsSearchGet({\"size\":\"" + al.size() + "\",";
        json += "\"titles\":[";
//        for (int i = 0; i < al.size(); i++) {
        for (int i = al.size() - 1; i >= 0; i--) {
            if (i == al.size() - 1) {
                json += "{\"title\":\"" + al.get(i) + "\"}";
            } else {
                json += "{\"title\":\"" + al.get(i) + "\"},";
            }
        }
        json += "]})";
        return json;
    }

    /*得到教务处新闻最新json数据*/
//    public static String getTitleJson(ResultSet rs) throws SQLException {
//        rs.last();
//        int rows = rs.getRow();
//        if (rows == 0) {
//            return "itemsSearchGet({\"s\":\"n\"})";
//        }
//        String json = "";
//        json += "itemsSearchGet({\"size\":\"" + rows + "\",\"s\":\"y\",";
//        json += "\"titles\":[";
//        rs.beforeFirst();
//        while (rs.next()) {
//            json += "{\"id\":\"" + rs.getInt("article_id") + "\",\"title\":\"" + rs.getString("article_title") + "\",\"a_date\":\"" + rs.getString("article_date") + "\"},";
//        }
//        json = json.substring(0, json.length() - 1);
//        json += "]})";
//        return json;
//    }
    /*得到教务处新闻最新json数据*/
    public static String getTitleJson(ResultSet rs, String dep_id) throws SQLException {
        helper = new SqlHelper();
        String strDepName = "";
        rs.last();
        int rows = rs.getRow();
        if (rows == 0) {
            return "itemsSearchGet({\"s\":\"n\"})";
        }

        ResultSet rsDepName = null;
        String sqlDepName = "SELECT dep_name FROM b_dep WHERE dep_id = " + dep_id + "";
        rsDepName = helper.getRs(sqlDepName);

        try {
            if (rsDepName.next()) {
                strDepName = rsDepName.getString("dep_name");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        String json = "";
        json += "itemsSearchGet({\"size\":\"" + rows + "\",\"s\":\"y\",\"depname\":\"" + strDepName + "\",";
        json += "\"titles\":[";
        rs.beforeFirst();
        while (rs.next()) {
            json += "{\"id\":\"" + rs.getInt("article_id") + "\",\"title\":\"" + rs.getString("article_title") + "\",\"a_date\":\"" + rs.getString("article_date") + "\"},";
        }
        json = json.substring(0, json.length() - 1);
        json += "]})";
        return json;
    }

    /*得到登陆后的用户的新闻首页的json数据*/
    public static String getIndexNewsJson(ResultSet rs1, ResultSet rs2, int dep_id, String str) throws SQLException {
        rs1.last();
        int row = rs1.getRow();
        helper = new SqlHelper();
        ResultSet temprs = null;
        String json = "";
        json += "itemsSearchGet({\"depno\":\"" + row + "\",\"allneeds\":[";
        rs1.beforeFirst();
        int dep = 1;
        while (rs1.next()) {
            int depid = rs1.getInt("dep_id");
            String depname = rs1.getString("dep_name");
            String sql = "select * from o_article where article_dep = '" + depid + "' and article_flag=1 order by article_id desc limit 0,3";
            temprs = helper.getRs(sql);
            temprs.last();
            int temprow = temprs.getRow();
            String str_dep = "titles" + dep;
            if (temprow == 0) {
                json += "{\"depid\":\"" + depid + "\",\"depname\":\"" + depname + "\",\"size\":\"" + temprow + "\"},";
            } else {
                json += "{\"depid\":\"" + depid + "\",\"depname\":\"" + depname + "\",\"size\":\"" + temprow + "\",\"" + str_dep + "\":[";
                temprs.beforeFirst();
                while (temprs.next()) {
                    json += "{\"id\":\"" + temprs.getInt("article_id") + "\",\"title\":\"" + temprs.getString("article_title") + "\",\"a_date\":\"" + temprs.getString("article_date") + "\"},";
                }
                json = json.substring(0, json.length() - 1);
                json += "]},";
            }
            dep++;
        }

        rs2.last();
        int row2 = rs2.getRow();
        rs2.beforeFirst();
        json += "{\"depid\":\"" + dep_id + "\",\"depname\":\"" + str + "\",\"size\":\"" + row2 + "\",";
        json += "\"owntitles\":[";
        while (rs2.next()) {
            json += "{\"id\":\"" + rs2.getInt("article_id") + "\",\"title\":\"" + rs2.getString("article_title") + "\",\"a_date\":\"" + rs2.getString("article_date") + "\"},";
        }
        json = json.substring(0, json.length() - 1);
        json += "]}";
        json += "]})";
        return json;
    }

    /*得到没有登录的用户的新闻首页的json数据*/
    public static String getIndexNewsNotJson(ResultSet rs) throws SQLException {
        rs.last();
        int row = rs.getRow();
        helper = new SqlHelper();
        ResultSet temprs = null;
        String json = "";
        json += "itemsSearchGet({\"depno\":\"" + row + "\",\"allneeds\":[";
        rs.beforeFirst();
        int dep = 1;
        while (rs.next()) {
            int depid = rs.getInt("dep_id");
            String depname = rs.getString("dep_name");
            String sql = "select * from o_article where article_dep = '" + depid + "' and article_flag=1 order by article_id desc limit 0,3";
            temprs = helper.getRs(sql);
            temprs.last();
            int temprow = temprs.getRow();
            String str_dep = "titles" + dep;
            if (temprow == 0) {
                json += "{\"depid\":\"" + depid + "\",\"depname\":\"" + depname + "\",\"size\":\"" + temprow + "\"},";
            } else {
                json += "{\"depid\":\"" + depid + "\",\"depname\":\"" + depname + "\",\"size\":\"" + temprow + "\",\"" + str_dep + "\":[";
                temprs.beforeFirst();
                while (temprs.next()) {
                    json += "{\"id\":\"" + temprs.getInt("article_id") + "\",\"title\":\"" + temprs.getString("article_title") + "\",\"a_date\":\"" + temprs.getString("article_date") + "\"},";
                }
                json = json.substring(0, json.length() - 1);
                json += "]},";
            }
            dep++;
        }
        json = json.substring(0, json.length() - 1);
        json += "]})";
        return json;
    }

     /**
      * @param  rs
      * 得到指定notice_id的通知内容
     * @return 
     * @throws java.sql.SQLException
      */
    public static String getNoticeContentJson(ResultSet rs) throws SQLException {
        rs.last();
        String json = "";
        json += "itemsSearchGet(";
        rs.beforeFirst();
        while (rs.next()) {
            json += "{\"title\":\"" + rs.getString("notice_title") + "\",\"content\":\"" + rs.getString("notice_content") + "\",\"a_date\":\"" + GetTime.getTime(rs.getDate("notice_date")) + "\",\"a_time\":\"" + rs.getTime("notice_time") + "\"},";
        }
        json = json.substring(0, json.length() - 1);
        json += ")";
        return json;
    }

    
    
    /*得到登录后的用户的通知首页的json数据*/
    public static String getIndexInfosJson(ResultSet rs1, ResultSet rs2) throws SQLException {
        rs1.last();
        int row1 = rs1.getRow();
        rs2.last();
        int row2 = rs2.getRow();
        String json = "";
        json += "itemsSearchGet({\"size\":\"" + row1 + "\",";
        json += "\"size1\":\"" + row2 + "\",";
        json += "\"titles\":[";
        rs1.beforeFirst();
        if (row1 > 0) {
            while (rs1.next()) {
                json += "{\"id\":\"" + rs1.getInt("notice_id") + "\",\"title\":\"" + rs1.getString("notice_title") + "\",\"n_date\":\"" + rs1.getString("notice_date") + "\"},";
            }
            json = json.substring(0, json.length() - 1);
            json += "],";
        }

        rs2.beforeFirst();
        if (row2 > 0) {
            json += "\"titles1\":[";
            while (rs2.next()) {
                json += "{\"id\":\"" + rs2.getInt("notice_id") + "\",\"title\":\"" + rs2.getString("notice_title") + "\",\"n_date\":\"" + rs2.getString("notice_date") + "\",\"n_time\":\"" + rs2.getString("notice_time") + "\",\"n_indate\":\"" + rs2.getString("notice_indate") + "\"},";
            }
            json = json.substring(0, json.length() - 1);
            json += "],";
        }
        json = json.substring(0, json.length() - 1);
        json += "})";
        return json;
    }

    /*得到没有登录的用户的通知首页的json数据*/
    public static String getIndexInfoNotJson(ResultSet rs) throws SQLException {
        rs.last();
        int row = rs.getRow();
        String json = "";
        if (row == 0) {
            return "itemsSearchGet({\"s\":\"n\"})";
        }
        json += "itemsSearchGet({\"size\":\"" + row + "\",\"s\":\"y\",\"titles\":[";
        rs.beforeFirst();
        if (row > 0) {
            while (rs.next()) {
                json += "{\"id\":\"" + rs.getInt("notice_id") + "\",\"title\":\"" + rs.getString("notice_title") + "\",\"n_date\":\"" + rs.getString("notice_date") + "\",\"n_time\":\"" + rs.getString("notice_time") + "\",\"n_indate\":\"" + rs.getString("notice_indate") + "\"},";
            }
        }
        json = json.substring(0, json.length() - 1);
        json += "]})";
        return json;
    }

//    /*得到没有登录的用户的通知首页的json数据*/
//    public static String getIndexInfoNot1Json(ResultSet rs) throws SQLException {
//        rs.last();
//        int row = rs.getRow();
//        String json = "";
//        json += "itemsSearchGet({\"size\":\"" + row + "\",\"titles\":[";
//        rs.beforeFirst();
//        if (row > 0) {
//            while (rs.next()) {
//                json += "{\"id\":\"" + rs.getInt("notice_id") + "\",\"title\":\"" + rs.getString("notice_title") + "\",\"n_date\":\"" + rs.getString("notice_date") + "\",\"n_time\":\"" + rs.getString("notice_time") + "\",\"n_indate\":\"" + rs.getString("notice_indate") + "\"},";
//            }
//        }
//        json = json.substring(0, json.length() - 1);
//        json += "]})";
//        return json;
//    }
//    public static String getNewsContentJson(ArrayList al) {
//        String json = "";
//        json += "itemsSearchGet({\"size\":\"" + al.size() + "\",";
//        for (int i = 0; i < al.size() - 5; i++) {
//            if (i == 0) {
//                json += "\"article_id\":\"" + al.get(i) + "\",";
//            } else if (i == 1) {
//                json += "\"article_title\":\"" + al.get(i) + "\",";
//            } else if (i == 2) {
//                json += "\"article_add\":\"" + al.get(i) + "\",";
//            } else if (i == 3) {
//                json += "\"article_dep\":\"" + al.get(i) + "\",";
//            } else if (i == 4) {
//                json += "\"article_content\":\"" + al.get(i) + "\",";
//            } else if (i == 5) {
//                json += "\"article_date\":\"" + al.get(i) + "\",";
//            } else if (i == 6) {
//                json += "\"article_time\":\"" + al.get(i) + "\",";
//            } else if (i == 7) {
//                json += "\"article_click\":\"" + al.get(i) + "\"";
//            }
//        }
//        json += "]})";
//        return json;
//    }
    public static String getNewsContentJson(ResultSet rs) throws SQLException {
        rs.last();
        String json = "";
        json += "itemsSearchGet(";
        rs.beforeFirst();
        while (rs.next()) {
            json += "{\"title\":\"" + rs.getString("article_title") + "\",\"content\":\"" + rs.getString("article_content") + "\",\"a_date\":\"" + GetTime.getTime(rs.getDate("article_date")) + "\",\"a_time\":\"" + rs.getTime("article_time") + "\"},";
        }
        json = json.substring(0, json.length() - 1);
        json += ")";
        return json;
    }

    public static String getNoticeContentJson(ArrayList al) {
        String json = "";
        json += "itemsSearchGet({\"size\":\"" + al.size() + "\",";
        for (int i = 0; i < al.size() - 5; i++) {
            if (i == 0) {
                json += "\"notice_id\":\"" + al.get(i) + "\",";
            } else if (i == 1) {
                json += "\"notice_title\":\"" + al.get(i) + "\",";
            } else if (i == 2) {
                json += "\"notice_add\":\"" + al.get(i) + "\",";
            } else if (i == 3) {
                json += "\"notice_dep\":\"" + al.get(i) + "\",";
            } else if (i == 4) {
                json += "\"notice_content\":\"" + al.get(i) + "\",";
            } else if (i == 5) {
                json += "\"notice_indate\":\"" + al.get(i) + "\",";
            } else if (i == 6) {
                json += "\"notice_date\":\"" + al.get(i) + "\",";
            } else if (i == 7) {
                json += "\"notice_time\":\"" + al.get(i) + "\"";
            }
        }
        json += "]})";
        return json;
    }

    public static String getActTicketJson(ArrayList al) {
        String json = "";
        json += "itemsSearchGet({\"size\":\"" + al.size() + "\",";
        for (int i = 0; i < al.size(); i++) {
            if (i == 0) {
                json += "\"act_ticket_id\":\"" + al.get(i) + "\",";
            } else if (i == 1) {
                json += "\"act_id\":\"" + al.get(i) + "\",";
            } else if (i == 2) {
                json += "\"act_place_id\":\"" + al.get(i) + "\",";
            } else if (i == 3) {
                json += "\"act_seat_id\":\"" + al.get(i) + "\",";
            } else if (i == 4) {
                json += "\"act_ticket_no\":\"" + al.get(i) + "\",";
//            } else if(i == 5) {
//                json += "\"act_ticket_code\":\"" + al.get(i) + "\",";
//            } else if(i == 6) {
//                json += "\"user_id\":\"" + al.get(i) + "\",";
//            } else if(i == 7) {
//                json += "\"act_ticket_flag\":\"" + al.get(i) + "\",";
            } else if (i == 10) {
                json += "\"user_no\":\"" + al.get(i) + "\",";
            } else if (i == 11) {
                json += "\"user_type\":\"" + al.get(i) + "\",";
            } else if (i == 14) {
                json += "\"user_name\":\"" + al.get(i) + "\",";
            } else if (i == 19) {
                json += "\"user_dep\":\"" + al.get(i) + "\",";
            } else if (i == 17) {
                json += "\"user_p_no\":\"" + al.get(i) + "\"";
            }
        }
        json += "]})";
        return json;
    }

    /**
     * 得到最新10条活动标题
     *
     * @param rs 最新10条活动标题的集合级
     */
    public static String getActTitleJson(ResultSet rs) throws SQLException {
        rs.last();
        int rows = rs.getRow();
        if (rows == 0) {
            return "itemsSearchGet({\"s\":\"n\"})";
        }
        String json = "";
        json += "itemsSearchGet({\"size\":\"" + rows + "\",\"s\":\"y\",";
        json += "\"titles\":[";
        rs.beforeFirst();
        while (rs.next()) {
            json += "{\"id\":\"" + rs.getInt("act_id") + "\",\"title\":\"" + rs.getString("act_name") + "\",\"a_date\":\"" + rs.getString("act_begin_date") + "\"},";
        }
        json = json.substring(0, json.length() - 1);
        json += "]})";
        return json;
    }

    /**
     * 得到指定活动id的内容
     *
     * @param rs 得到指定活动id的内容
     * @param rs2
     * @param rs3
     * @param flag1
     * @param flag2
     * @param ticketid
     * @return
     * @throws java.sql.SQLException
     */
    public static String getActContentJson(ResultSet rs, ResultSet rs2, ResultSet rs3, int flag1, int flag2, int ticketid) throws SQLException {
        String json = "";
        json += "itemsSearchGet(";
        rs.beforeFirst();
        while (rs.next()) {
            if ("0".equals(rs.getString("act_ticket_flag"))) {
                flag1 = 0;
                flag2 = 0;
            }
            if (rs2.next() && rs3.next()) {
                json += "{\"title\":\"" + rs.getString("act_name") + "\",\"ab_date\":\"" + GetTime.getTime(rs.getDate("act_begin_date")) + "\",\"ab_time\":\"" + rs.getString("act_begin_time") + "\",\"ae_date\":\"" + GetTime.getTime(rs.getDate("act_end_date")) + "\",\"ae_time\":\"" + rs.getString("act_end_time") + "\",\"ticket_flag\":\"" + rs.getString("act_ticket_flag") + "\",\"content\":\"" + rs.getString("act_content") + "\",\"add\":\"" + rs2.getString("admin_name") + "\",\"place\":\"" + rs3.getString("place_name") + "\",\"flag1\":\"" + flag1 + "\",\"flag2\":\"" + flag2 + "\",\"ticketid\":\"" + ticketid + "\"},";
            }
        }
        json = json.substring(0, json.length() - 1);
        json += ")";
        return json;
    }

    public static String getAllActTitleJson(ResultSet rs) throws SQLException {
        rs.last();
        int rows = rs.getRow();
        if (rows == 0) {
            return "itemsSearchGet({\"s\":\"n\"})";
        }
        SqlHelper helper = new SqlHelper();
        String json = "";
        json += "itemsSearchGet({\"size\":\"" + rows + "\",\"s\":\"y\",";
        json += "\"titles\":[";
        rs.beforeFirst();
        while (rs.next()) {
            String sql2 = "select act_name,act_begin_date from c_act where act_id = " + rs.getString("act_id") + "";
            ResultSet rs2 = helper.getRs(sql2);
            if (rs2.next()) {
                json += "{\"id\":\"" + rs.getInt("act_id") + "\",\"title\":\"" + rs2.getString("act_name") + "\",\"a_date\":\"" + rs2.getDate("act_begin_date") + "\"},";
            }
        }
        json = json.substring(0, json.length() - 1);
        json += "]})";
        return json;
    }

    //根据user_no得到10条活动电子票标题
    public static String getAllActTicketJson(ResultSet rs) throws SQLException {
        rs.last();
        int rows = rs.getRow();
        if (rows == 0) {
            return "itemsSearchGet({\"s\":\"n\"})";
        }
        SqlHelper helper = new SqlHelper();
        String json = "";
        json += "itemsSearchGet({\"size\":\"" + rows + "\",\"s\":\"y\",";
        json += "\"titles\":[";
        rs.beforeFirst();
        while (rs.next()) {
            String sql2 = "select act_name,act_begin_date,act_begin_time from c_act where act_id = " + rs.getString("act_id") + "";
            ResultSet rs2 = helper.getRs(sql2);
            if (rs2.next()) {
                json += "{\"id\":\"" + rs.getInt("act_ticket_id") + "\",\"ticketno\":\"" + rs.getString("act_ticket_no") + "\",\"title\":\"" + rs2.getString("act_name") + "\",\"a_date1\":\"" + rs2.getDate("act_begin_date") + "\",\"a_date\":\"" + GetTime.getTime(rs2.getDate("act_begin_date")) + "\",\"a_time\":\"" + rs2.getTime("act_begin_time") + "\"},";
            }
        }
        json = json.substring(0, json.length() - 1);
        json += "]})";
        return json;
    }

    public static String getActFlagJson(int flag1, int flag2) {
        String json = "";
        json += "itemsSearchGet(";
        json += "{\"flag1\":\"" + flag1 + "\",\"flag2\":\"" + flag2 + "\"}";
        json += ")";
        return json;
    }

    //根据user_no和act_id得到一张具体的电子票
    public static String getActTicketJson(ResultSet rs1, ResultSet rs2, ResultSet rs3, ResultSet rs4, ResultSet rs5) throws SQLException {
        rs1.last();
        String json = "";
        json += "itemsSearchGet(";
        rs1.beforeFirst();
        while (rs1.next() && rs2.next() && rs3.next() && rs4.next() && rs5.next()) {
            json += "{\"ticketno\":\"" + rs1.getString("act_ticket_no") + "\",\"name\":\"" + rs2.getString("user_name") + "\",\"place\":\"" + rs3.getString("place_name") + "\",\"seat\":\"" + rs4.getString("seat_name") + "\",\"dep\":\"" + rs5.getString("dep_name") + "\",\"actname\":\"" + rs5.getString("act_name") + "\",\"a_date1\":\"" + rs5.getDate("act_end_date") + "\",\"a_time1\":\"" + rs5.getTime("act_end_time") + "\",\"a_date\":\"" + GetTime.getTime(rs5.getDate("act_begin_date")) + "\",\"a_time\":\"" + rs5.getTime("act_begin_time") + "\"},";
        }
        json = json.substring(0, json.length() - 1);
        json += ")";
        return json;
    }

     public static String getOrderListJson(ResultSet rs) throws SQLException {
        rs.last();
        int rows = rs.getRow();
        if (rows == 0) {
            return "itemsSearchGet({\"s\":\"n\"})";
        }
        String json = "";
        json += "itemsSearchGet({\"size\":\"" + rows + "\",\"s\":\"y\",";
        json += "\"titles\":[";
        rs.beforeFirst();
        while (rs.next()) {
                json += "{\"id\":\"" + rs.getInt("order_dep_id") + "\",\"name\":\"" + rs.getString("order_dep_name") + "\"},";
        }
        json = json.substring(0, json.length() - 1);
        json += "]})";
        return json;
    }

    /**
     * 
     * @param rs
     * 得到前10条订阅标题
     * @return 
     * @throws java.sql.SQLException
     *
     */
    public static String getOrderTitleJson(ResultSet rs) throws SQLException {
        rs.last();
        int rows = rs.getRow();
        if (rows == 0) {
            return "itemsSearchGet({\"s\":\"n\"})";
        }
        String json = "";
        json += "itemsSearchGet({\"size\":\"" + rows + "\",\"s\":\"y\",";
        json += "\"titles\":[";
        rs.beforeFirst();
        while (rs.next()) {
            json += "{\"id\":\"" + rs.getInt("order_pub_id") + "\",\"title\":\"" + rs.getString("order_pub_title") + "\",\"date\":\"" + rs.getDate("order_pub_date") + "\",\"time\":\"" + rs.getTime("order_pub_time") + "\"},";
        }
        json = json.substring(0, json.length() - 1);
        json += "]})";
        return json;
    }

    /**
     * @param rs
     * 得到order_pub_id的一条记录内容
     * @return 
     * @throws java.sql.SQLException
     *
     */
    public static String getOneOrderJson(ResultSet rs) throws SQLException {
        rs.last();
        String json = "";
        json += "itemsSearchGet({";
        rs.beforeFirst();
        while (rs.next()) {
            json += "\"title\":\"" + rs.getString("order_pub_title") + "\",\"content\":\"" + rs.getString("order_pub_content") + "\",\"name\":\"" + rs.getString("order_dep_name") + "\",\"date\":\"" + rs.getDate("order_pub_date") + "\",\"time\":\"" + rs.getTime("order_pub_time") + "\",";
        }
        json = json.substring(0, json.length() - 1);
        json += "})";
        return json;
    }

    /**
     * 
     * 得到所有已关注的列表
     * @param rs1
     * @param rs2
     * @return 
     * @throws java.sql.SQLException
     */
    public static String getOrderList2Json(ResultSet rs1,ResultSet rs2) throws SQLException {
        rs1.last();
        int rows1 = rs1.getRow();
        rs2.last();
        int rows2 = rs2.getRow();
        int rows = rows1 + rows2;
        if (rows == 0) {
            return "itemsSearchGet({\"s\":\"n\"})";
        }
        String json = "";
        json += "itemsSearchGet({\"size\":\"" + rows + "\",\"s\":\"y\",";
        json += "\"titles\":[";
        rs1.beforeFirst();
        while (rs1.next()) {
            json += "{\"id\":\"" + rs1.getInt("order_dep_id") + "\",\"name\":\"" + rs1.getString("order_dep_name") + "\",\"type\":\""+ 0 +"\"},";
        }
        rs2.beforeFirst();
        while (rs2.next()) {
            json += "{\"id\":\"" + rs2.getInt("order_dep_id") + "\",\"name\":\"" + rs2.getString("order_dep_name") + "\",\"type\":\""+ 1 +"\"},";
        }
        json = json.substring(0, json.length() - 1);
        json += "]})";
        return json;
    }

     /**得到活动互动选择内容
      * 
      * @param rs2
      * @param rs1
      * @return
      * @throws SQLException 
      */
     //得到活动互动选择内容
    public static String getActVoteOptionJson(ResultSet rs1, ResultSet rs2) throws SQLException {
        helper = new SqlHelper();
        rs1.last();
        int rows = rs1.getRow();
        if (rows == 0) {
            return "itemsSearchGet({\"s\":\"n\"})";
        }
        
        int rows2 = 0;
        String user_no = "";
        if (rs2.next()) {
            user_no = rs2.getString("user_no");
        }
        String json = "";
        json += "itemsSearchGet({\"size\":\"" + rows + "\",\"s\":\"y\",";
        json += "\"titles\":[";
        rs1.beforeFirst();
        while (rs1.next()) {
            int flag = 0;
            String sql4 = "select result_id,result_option from c_act_vote_result where vote_id = " + rs1.getInt("vote_id") + " and user_no = " + user_no + "";         
            System.out.println(sql4);
            ResultSet rs4 = helper.getRs(sql4);
            rs4.last();
            rows2 = rs4.getRow();
            if (rows2 != 0) {
                flag = 1; //已投为1
            }
            
            if (flag == 0) {
                json += "{\"id\":\"" + rs1.getInt("vote_id") + "\",\"flag\":\"" + flag + "\",\"title\":\"" + rs1.getString("vote_title") + "\",\"type\":\"" + rs1.getString("vote_select_type") + "\",";
                String sql3 = "select vote_option_id,vote_option from c_act_vote_option where vote_id = " + rs1.getString("vote_id") + "";
                System.out.println(sql3);
                ResultSet rs3 = helper.getRs(sql3);
                int i = 0;
                json += "\"options\":[";
                while (rs3.next()) {
                    i++;
                    json += "{\"optionid\":\"" + rs3.getString("vote_option_id") + "\",\"option\":\"" + rs3.getString("vote_option") + "\"},";
                }
                if (i != 0) {
                    json = json.substring(0, json.length() - 1);
                    json += "],\"opsize\":\"" + i + "\"";
                    json += "},";
                }
            }
             
            if(flag == 1) {
                json += "{\"id\":\""+ rs1.getInt("vote_id") +"\",\"flag\":\"1\",\"title\":\""+ rs1.getString("vote_title") +"\",\"type\":\""+ rs1.getString("vote_select_type") +"\",";
                
                String[] option = rs4.getString("result_option").split(",");
                json += "\"opsize\":\""+option.length+"\",\"options\":[";
                for(int j = 0; j < option.length; j++) {
                    String sql5 = "select vote_option from c_act_vote_option where vote_option_id = "+ option[j] +"";
                   
                    ResultSet rs5 = helper.getRs(sql5);
                    if(rs5.next()) {
                        json += "{\"option\":\""+ rs5.getString("vote_option") +"\"},";
                    }
                }
                json = json.substring(0, json.length() - 1);
                json += "]},";
            }
            
        }
        json = json.substring(0, json.length() - 1);
        json += "]})";
        return json;
    }

    public static String getOwnInfo(ResultSet rs) throws SQLException {
        String json = "";
        json += "itemsSearchGet({";
        rs.beforeFirst();
        while (rs.next()) {
            json += "\"userid\":\"" + rs.getString(1) + "\",\"userbirth\":\"" + GetTime.getTime(rs.getDate(2)) + "\",\"usertype\":\"" + rs.getString(3) + "\",\"name\":\"" + rs.getString(4) + "\",\"userno\":\"" + rs.getString(5) + "\",\"usersex\":\"" + rs.getString(6) + "\",\"userclass\":\"" + rs.getString(7) + "\",";
        }
        json = json.substring(0, json.length() - 1);
        json += "})";
        return json;
    }
}
