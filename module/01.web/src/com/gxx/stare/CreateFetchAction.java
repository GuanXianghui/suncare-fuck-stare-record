package com.gxx.stare;

import com.gxx.stare.dao.FetchDao;
import com.gxx.stare.entities.Fetch;
import com.gxx.stare.utils.TokenUtil;

/**
 * 新增批次Action
 *
 * @author Gxx
 * @module oa
 * @datetime 14-4-18 15:22
 */
public class CreateFetchAction extends BaseAction {
    /**
     * 玩家姓名 多个人逗号分隔 比如：关向辉,林琼英,严明皓
     */
    String users;
    /**
     * 最大倍数 炸弹翻倍 一般是2倍，炸再多也是2倍，没炸是1倍
     */
    String maxTimes;
    /**
     * 每张牌价格 一般是0.5元，单位是元
     */
    String eachMoney;
    /**
     * 手持牌数 一般是5张
     */
    String pieces;

    /**
     * 入口
     * @return
     */
    public String execute() throws Exception {
        users = users.replaceAll("，", ",");
        logger.info("users:" + users + ",maxTimes:" + maxTimes + ",eachMoney:" + eachMoney + ",pieces:" + pieces);
        Fetch fetch = new Fetch(date, time, users, Integer.parseInt(maxTimes), Float.parseFloat(eachMoney),
                Integer.parseInt(pieces), getIp());
        FetchDao.insertFetch(fetch);
        String resp = "{isSuccess:true,message:'新增批次成功！',hasNewToken:true,token:'" +
                TokenUtil.createToken(request) + "'}";
        write(resp);
        return null;
    }

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }

    public String getMaxTimes() {
        return maxTimes;
    }

    public void setMaxTimes(String maxTimes) {
        this.maxTimes = maxTimes;
    }

    public String getEachMoney() {
        return eachMoney;
    }

    public void setEachMoney(String eachMoney) {
        this.eachMoney = eachMoney;
    }

    public String getPieces() {
        return pieces;
    }

    public void setPieces(String pieces) {
        this.pieces = pieces;
    }
}
