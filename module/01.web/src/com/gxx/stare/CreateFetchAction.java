package com.gxx.stare;

import com.gxx.stare.dao.FetchDao;
import com.gxx.stare.entities.Fetch;
import com.gxx.stare.utils.TokenUtil;

/**
 * ��������Action
 *
 * @author Gxx
 * @module oa
 * @datetime 14-4-18 15:22
 */
public class CreateFetchAction extends BaseAction {
    /**
     * ������� ����˶��ŷָ� ���磺�����,����Ӣ,�����
     */
    String users;
    /**
     * ����� ը������ һ����2����ը�ٶ�Ҳ��2����ûը��1��
     */
    String maxTimes;
    /**
     * ÿ���Ƽ۸� һ����0.5Ԫ����λ��Ԫ
     */
    String eachMoney;
    /**
     * �ֳ����� һ����5��
     */
    String pieces;

    /**
     * ���
     * @return
     */
    public String execute() throws Exception {
        users = users.replaceAll("��", ",");
        logger.info("users:" + users + ",maxTimes:" + maxTimes + ",eachMoney:" + eachMoney + ",pieces:" + pieces);
        Fetch fetch = new Fetch(date, time, users, Integer.parseInt(maxTimes), Float.parseFloat(eachMoney),
                Integer.parseInt(pieces), getIp());
        FetchDao.insertFetch(fetch);
        String resp = "{isSuccess:true,message:'�������γɹ���',hasNewToken:true,token:'" +
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
