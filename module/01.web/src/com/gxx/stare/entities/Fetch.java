package com.gxx.stare.entities;

/**
 * 批次实体
 *
 * @author Gxx
 * @module oa
 * @datetime 14-7-13 00:47
 */
public class Fetch {
    int id;
    String date;
    String time;
    String users;
    int maxTimes;
    float eachMoney;
    int pieces;
    String ip;

    /**
     * 新增时使用
     * @param date
     * @param time
     * @param users
     * @param maxTimes
     * @param eachMoney
     * @param pieces
     * @param ip
     */
    public Fetch(String date, String time, String users, int maxTimes, float eachMoney, int pieces, String ip) {
        this.date = date;
        this.time = time;
        this.users = users;
        this.maxTimes = maxTimes;
        this.eachMoney = eachMoney;
        this.pieces = pieces;
        this.ip = ip;
    }

    /**
     * 查询时使用
     * @param id
     * @param date
     * @param time
     * @param users
     * @param maxTimes
     * @param eachMoney
     * @param pieces
     * @param ip
     */
    public Fetch(int id, String date, String time, String users, int maxTimes, float eachMoney, int pieces, String ip) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.users = users;
        this.maxTimes = maxTimes;
        this.eachMoney = eachMoney;
        this.pieces = pieces;
        this.ip = ip;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }

    public int getMaxTimes() {
        return maxTimes;
    }

    public void setMaxTimes(int maxTimes) {
        this.maxTimes = maxTimes;
    }

    public float getEachMoney() {
        return eachMoney;
    }

    public void setEachMoney(float eachMoney) {
        this.eachMoney = eachMoney;
    }

    public int getPieces() {
        return pieces;
    }

    public void setPieces(int pieces) {
        this.pieces = pieces;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
