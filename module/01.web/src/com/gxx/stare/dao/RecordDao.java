package com.gxx.stare.dao;

import com.gxx.stare.entities.Record;
import com.gxx.stare.interfaces.BaseInterface;
import com.gxx.stare.utils.PropertyUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * 记录实体操作类
 *
 * @author Gxx
 * @module oa
 * @datetime 14-7-13 00:56
 */
public class RecordDao {
    /**
     * 根据批次号查询全部战绩记录
     *
     * @param fetchId
     * @return
     * @throws Exception
     */
    public static List<Record> queryAllRecordsByFetchId(int fetchId) throws Exception {
        //战绩记录集合
        List<Record> list = new ArrayList<Record>();
        //sql语句
        String sql = "SELECT id,fetch_id,winner,times,first,win_money,detail,date,time,ip FROM record " +
                "WHERE fetch_id=" + fetchId + " ORDER BY id DESC";
        Connection c = DB.getConn();
        Statement stmt = DB.createStatement(c);
        ResultSet rs = DB.executeQuery(c, stmt, sql);
        try {
            if (rs == null) {
                throw new RuntimeException("数据库操作出错，请重试！");
            }
            while (rs.next()) {
                int id = rs.getInt("id");
                String winner = rs.getString("winner");
                int times = rs.getInt("times");
                String first = rs.getString("first");
                float winMoney = rs.getFloat("win_money");
                String detail = rs.getString("detail");
                String date = rs.getString("date");
                String time = rs.getString("time");
                String ip = rs.getString("ip");
                Record record = new Record(id, fetchId, winner, times, first, winMoney, detail, date, time, ip);
                list.add(record);
            }
            return list;
        } finally {
            DB.close(rs);
            DB.close(stmt);
            DB.close(c);
        }
    }

    /**
     * 根据批次号查询战绩记录
     *
     * @param fetchId
     * @param pageNum
     * @return
     * @throws Exception
     */
    public static List<Record> queryRecordsByFetchId(int fetchId, int pageNum) throws Exception {
        //战绩记录列表每页大小
        int pageSize = Integer.parseInt(PropertyUtil.getInstance().getProperty(BaseInterface.RECORD_PAGE_SIZE));
        //战绩记录集合
        List<Record> list = new ArrayList<Record>();
        //sql语句
        String sql = "SELECT id,fetch_id,winner,times,first,win_money,detail,date,time,ip FROM record " +
                "WHERE fetch_id=" + fetchId + " ORDER BY id DESC LIMIT " + ((pageNum-1) * pageSize) + "," + pageSize;
        Connection c = DB.getConn();
        Statement stmt = DB.createStatement(c);
        ResultSet rs = DB.executeQuery(c, stmt, sql);
        try {
            if (rs == null) {
                throw new RuntimeException("数据库操作出错，请重试！");
            }
            while (rs.next()) {
                int id = rs.getInt("id");
                String winner = rs.getString("winner");
                int times = rs.getInt("times");
                String first = rs.getString("first");
                float winMoney = rs.getFloat("win_money");
                String detail = rs.getString("detail");
                String date = rs.getString("date");
                String time = rs.getString("time");
                String ip = rs.getString("ip");
                Record record = new Record(id, fetchId, winner, times, first, winMoney, detail, date, time, ip);
                list.add(record);
            }
            return list;
        } finally {
            DB.close(rs);
            DB.close(stmt);
            DB.close(c);
        }
    }

    /**
     * 根据批次号查询战绩记录数量
     *
     * @param fetchId
     * @return
     * @throws Exception
     */
    public static int countRecordsByFetchId(int fetchId) throws Exception {
        //结果
        int count = 0;
        //sql语句
        String sql = "SELECT count(1) count_num FROM record WHERE fetch_id=" + fetchId;
        Connection c = DB.getConn();
        Statement stmt = DB.createStatement(c);
        ResultSet rs = DB.executeQuery(c, stmt, sql);
        try {
            if (rs == null) {
                throw new RuntimeException("数据库操作出错，请重试！");
            }
            while (rs.next()) {
                count = rs.getInt("count_num");
            }
            return count;
        } finally {
            DB.close(rs);
            DB.close(stmt);
            DB.close(c);
        }
    }

    /**
     * 新增记录
     *
     * @param record
     * @throws Exception
     */
    public static void insertRecord(Record record) throws Exception {
        String sql = "insert into record" +
                "(id,fetch_id,winner,times,first,win_money,detail,date,time,ip)" +
                "values" +
                "(null," + record.getFetchId() + ",'" + record.getWinner() + "'," + record.getTimes() + ",'" +
                record.getFirst() + "'," + record.getWinMoney() + ",'" + record.getDetail() + "','" +
                record.getDate() + "','" + record.getTime() + "','" + record.getIp() + "')";
        DB.executeUpdate(sql);
    }
}
