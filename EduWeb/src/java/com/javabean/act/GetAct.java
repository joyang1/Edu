
package com.javabean.act;

import com.dao.DB;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *news表的相关操作
 * 
 * @author slv
 */
public class GetAct {

    private Connection c = null;
    private PreparedStatement ps = null;
    private Statement st = null;
    private ResultSet rs = null;
    private DB cd = null;

    //获取新闻的条数
    public int getAllActCount() {
        int allnewscount = 0;
        cd = new DB();
        c = cd.getConn();
        String sql = "select * from c_act";
        try {
            st = c.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                allnewscount++;
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            this.close();
        }
        return allnewscount;
    }

    //获取指定id的新闻的题目
    public String getName(int id) {
        String title = "";
        cd = new DB();
        c = cd.getConn();
        String sql = "select * from c_act";
        try {
            st = c.createStatement();
            rs = st.executeQuery(sql);
            rs.beforeFirst();
            while (rs.next()) {
                if (rs.getInt("act_id") == id) {
                    title = rs.getString("act_name");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            this.close();
        }

        return title;
    }

    //获取所有新闻的id
    public int[] getId() {
        int[] id = new int[this.getAllActCount()];
        cd = new DB();
        c = cd.getConn();
        String sql = "select * from c_act";
        try {
            st = c.createStatement();
            rs = st.executeQuery(sql);
            int i = 0;
            while (rs.next()) {
                id[i] = rs.getInt("act_id");
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            this.close();
        }

        return id;

    }

    //获取指定id的新闻的内容
    public String getContent(int id) {
        String content = "";
        cd = new DB();
        c = cd.getConn();
        String sql = "select * from c_act";
        try {
            st = c.createStatement();
            rs = st.executeQuery(sql);
            rs.beforeFirst();
            while (rs.next()) {
                if (rs.getInt("act_id") == id) {
                    content = rs.getString("act_content");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            this.close();
        }

        return content;
    }
    //获取指定id的新闻的时间
    public String getTime(int id) {
        String time = "";
        cd = new DB();
        c = cd.getConn();
        String sql = "select * from c_act";
        try {
            st = c.createStatement();
            rs = st.executeQuery(sql);
            rs.beforeFirst();
            while (rs.next()) {
                if (rs.getInt("act_id") == id) {
                    time = rs.getString("act_date");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            this.close();
        }

        return time;
    }

    //管理员添加新闻时调用的方法
    public boolean addact(String org_id, String type, String dep, String name, String level, String begindate, String enddate, String begintime, String endtime, String content, String actflag, String add, String D_Picture, String D_OriginalFileName, String D_SaveFileName, String D_SavePathFileName, String ticketflag, String ticketnum, String ticketend, String place) {
        boolean flag = false;
        cd = new DB();
        c = cd.getConn();
        String sql = "insert into c_act values(" + null + ",'" + org_id + "','" + type + "','" + dep + "','" + name + "','" + level + "','" + begindate + "','" + enddate + "','" + begintime + "','" + endtime + "','" + content + "','" + actflag + "','" + add + "','" + D_Picture + "','" + D_OriginalFileName + "','" + D_SaveFileName + "','" + D_SavePathFileName + "','" + ticketflag + "','" + ticketnum + "','" + ticketend + "','" + place + "')";
        try {
            ps = c.prepareStatement(sql);
            int a = ps.executeUpdate();
            if (a != 0) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            this.close();
        }
        return flag;
    }
    
     public boolean addActTicket(String act_id, String act_seat_id, String act_ticket_no, String act_ticket_code, String user_id, String place_id) {
        boolean flag = false;
        cd = new DB();
        c = cd.getConn();
        String sql = "insert into c_act_ticket values(" + null + ",'" + act_id + "','" + act_seat_id + "','" + act_ticket_no + "','" + act_ticket_code + "','" + user_id + "',1,'" + place_id + "')";
        out.println(sql);
        try {
            ps = c.prepareStatement(sql);
            int a = ps.executeUpdate();
            if (a != 0) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            this.close();
        }
        return flag;
    }
    

    //关闭数据库链接的方法
    private void close() {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace(System.err);
            }
        }
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace(System.err);
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace(System.err);
            }
        }
        if (c != null) {
            try {
                c.close();
            } catch (SQLException e) {
                e.printStackTrace(System.err);
            }
        }
    }
}
