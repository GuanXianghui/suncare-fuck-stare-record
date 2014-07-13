package com.gxx.stare.entities;

/**
 * 批次实体
 *
 * @author Gxx
 * @module oa
 * @datetime 14-7-13 00:47
 */
public class Record {
    int id;
    int fetchId;
    String winner;
    int times;
    String first;
    float winMoney;
    String detail;
    String date;
    String time;
    String ip;

    /**
     * 新增时使用
     * @param fetchId
     * @param winner
     * @param times
     * @param first
     * @param winMoney
     * @param detail
     * @param date
     * @param time
     * @param ip
     */
    public Record(int fetchId, String winner, int times, String first, float winMoney, String detail, String date,
                  String time, String ip) {
        this.fetchId = fetchId;
        this.winner = winner;
        this.times = times;
        this.first = first;
        this.winMoney = winMoney;
        this.detail = detail;
        this.date = date;
        this.time = time;
        this.ip = ip;
    }

    /**
     * 查询时使用
     * @param id
     * @param fetchId
     * @param winner
     * @param times
     * @param first
     * @param winMoney
     * @param detail
     * @param date
     * @param time
     * @param ip
     */
    public Record(int id, int fetchId, String winner, int times, String first, float winMoney, String detail,
                  String date, String time, String ip) {
        this.id = id;
        this.fetchId = fetchId;
        this.winner = winner;
        this.times = times;
        this.first = first;
        this.winMoney = winMoney;
        this.detail = detail;
        this.date = date;
        this.time = time;
        this.ip = ip;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFetchId() {
        return fetchId;
    }

    public void setFetchId(int fetchId) {
        this.fetchId = fetchId;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public float getWinMoney() {
        return winMoney;
    }

    public void setWinMoney(float winMoney) {
        this.winMoney = winMoney;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
