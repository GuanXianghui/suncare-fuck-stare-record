package com.gxx.stare.dao;

import com.gxx.stare.entities.Fetch;
import com.gxx.stare.interfaces.BaseInterface;
import com.gxx.stare.utils.PropertyUtil;
import org.apache.commons.lang.StringUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * 批次实体操作类
 *
 * @author Gxx
 * @module oa
 * @datetime 14-7-13 00:56
 */
public class FetchDao {
    /**
     * 查询所有用户
     *
     * @return
     * @throws Exception
     */
    public static List<Fetch> queryAllFetches() throws Exception {
        List<Fetch> fetchList = new ArrayList<Fetch>();
        String sql = "SELECT id,date,time,users,max_times,each_money,pieces,ip FROM t_fetch order by id desc";
        Connection c = DB.getConn();
        Statement stmt = DB.createStatement(c);
        ResultSet rs = DB.executeQuery(c, stmt, sql);
        try {
            if (rs == null) {
                throw new RuntimeException("数据库操作出错，请重试！");
            }
            while (rs.next()) {
                int id = rs.getInt("id");
                String date = rs.getString("date");
                String time = rs.getString("time");
                String users = rs.getString("users");
                int maxTimes = rs.getInt("max_times");
                float eachMoney = rs.getFloat("each_money");
                int pieces = rs.getInt("pieces");
                String ip = rs.getString("ip");
                Fetch fetch = new Fetch(id, date, time, users, maxTimes, eachMoney, pieces, ip);
                fetchList.add(fetch);
            }
            return fetchList;
        } finally {
            DB.close(rs);
            DB.close(stmt);
            DB.close(c);
        }
    }

    /**
     * 查询所有用户
     *
     * @return
     * @throws Exception
     */
    public static Fetch getNewestFetch() throws Exception {
        String sql = "SELECT id,date,time,users,max_times,each_money,pieces,ip FROM t_fetch order by id desc limit 1";
        Connection c = DB.getConn();
        Statement stmt = DB.createStatement(c);
        ResultSet rs = DB.executeQuery(c, stmt, sql);
        try {
            if (rs == null) {
                throw new RuntimeException("数据库操作出错，请重试！");
            }
            while (rs.next()) {
                int id = rs.getInt("id");
                String date = rs.getString("date");
                String time = rs.getString("time");
                String users = rs.getString("users");
                int maxTimes = rs.getInt("max_times");
                float eachMoney = rs.getFloat("each_money");
                int pieces = rs.getInt("pieces");
                String ip = rs.getString("ip");
                Fetch fetch = new Fetch(id, date, time, users, maxTimes, eachMoney, pieces, ip);
                return fetch;
            }
            return null;
        } finally {
            DB.close(rs);
            DB.close(stmt);
            DB.close(c);
        }
    }

    /**
     * 新增批次
     *
     * @param fetch
     * @throws Exception
     */
    public static void insertFetch(Fetch fetch) throws Exception {
        String sql = "insert into t_fetch" +
                "(id,date,time,users,max_times,each_money,pieces,ip)" +
                "values" +
                "(null,'" + fetch.getDate() + "','" + fetch.getTime() + "','" + fetch.getUsers() + "'," +
                fetch.getMaxTimes() + "," + fetch.getEachMoney() + "," + fetch.getPieces() + ",'" +
                fetch.getIp() + "')";
        DB.executeUpdate(sql);
    }

    /**
     * 根据id查批次
     * @param id
     * @return
     */
    public static Fetch getFetchById(int id) throws Exception {
        String sql = "SELECT id,date,time,users,max_times,each_money,pieces,ip FROM t_fetch where id=" + id;
        Connection c = DB.getConn();
        Statement stmt = DB.createStatement(c);
        ResultSet rs = DB.executeQuery(c, stmt, sql);
        try {
            if (rs == null) {
                throw new RuntimeException("数据库操作出错，请重试！");
            }
            while (rs.next()) {
                String date = rs.getString("date");
                String time = rs.getString("time");
                String users = rs.getString("users");
                int maxTimes = rs.getInt("max_times");
                float eachMoney = rs.getFloat("each_money");
                int pieces = rs.getInt("pieces");
                String ip = rs.getString("ip");
                Fetch fetch = new Fetch(id, date, time, users, maxTimes, eachMoney, pieces, ip);
                return fetch;
            }
            return null;
        } finally {
            DB.close(rs);
            DB.close(stmt);
            DB.close(c);
        }
    }
}
