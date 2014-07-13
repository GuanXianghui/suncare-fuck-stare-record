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
 * ��¼ʵ�������
 *
 * @author Gxx
 * @module oa
 * @datetime 14-7-13 00:56
 */
public class RecordDao {
    /**
     * �������κŲ�ѯȫ��ս����¼
     *
     * @param fetchId
     * @return
     * @throws Exception
     */
    public static List<Record> queryAllRecordsByFetchId(int fetchId) throws Exception {
        //ս����¼����
        List<Record> list = new ArrayList<Record>();
        //sql���
        String sql = "SELECT id,fetch_id,winner,times,first,win_money,detail,date,time,ip FROM record " +
                "WHERE fetch_id=" + fetchId + " ORDER BY id DESC";
        Connection c = DB.getConn();
        Statement stmt = DB.createStatement(c);
        ResultSet rs = DB.executeQuery(c, stmt, sql);
        try {
            if (rs == null) {
                throw new RuntimeException("���ݿ�������������ԣ�");
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
     * �������κŲ�ѯս����¼
     *
     * @param fetchId
     * @param pageNum
     * @return
     * @throws Exception
     */
    public static List<Record> queryRecordsByFetchId(int fetchId, int pageNum) throws Exception {
        //ս����¼�б�ÿҳ��С
        int pageSize = Integer.parseInt(PropertyUtil.getInstance().getProperty(BaseInterface.RECORD_PAGE_SIZE));
        //ս����¼����
        List<Record> list = new ArrayList<Record>();
        //sql���
        String sql = "SELECT id,fetch_id,winner,times,first,win_money,detail,date,time,ip FROM record " +
                "WHERE fetch_id=" + fetchId + " ORDER BY id DESC LIMIT " + ((pageNum-1) * pageSize) + "," + pageSize;
        Connection c = DB.getConn();
        Statement stmt = DB.createStatement(c);
        ResultSet rs = DB.executeQuery(c, stmt, sql);
        try {
            if (rs == null) {
                throw new RuntimeException("���ݿ�������������ԣ�");
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
     * �������κŲ�ѯս����¼����
     *
     * @param fetchId
     * @return
     * @throws Exception
     */
    public static int countRecordsByFetchId(int fetchId) throws Exception {
        //���
        int count = 0;
        //sql���
        String sql = "SELECT count(1) count_num FROM record WHERE fetch_id=" + fetchId;
        Connection c = DB.getConn();
        Statement stmt = DB.createStatement(c);
        ResultSet rs = DB.executeQuery(c, stmt, sql);
        try {
            if (rs == null) {
                throw new RuntimeException("���ݿ�������������ԣ�");
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
     * ������¼
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
