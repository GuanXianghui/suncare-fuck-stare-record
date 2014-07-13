package com.gxx.stare;

import com.gxx.stare.dao.FetchDao;
import com.gxx.stare.dao.RecordDao;
import com.gxx.stare.entities.Fetch;
import com.gxx.stare.entities.Record;
import com.gxx.stare.utils.TokenUtil;

/**
 * ������¼Action
 *
 * @author Gxx
 * @module oa
 * @datetime 14-4-18 15:22
 */
public class CreateRecordAction extends BaseAction {
    /**
     * Ӯ������
     */
    String winner;
    /**
     * ը������
     */
    String times;
    /**
     * �ȳ�����
     */
    String first;
    /**
     * Ӯ�Ľ��
     */
    String winMoney;
    /**
     * �������ϸ
     */
    String detail;

    /**
     * ���
     * @return
     */
    public String execute() throws Exception {
        logger.info("winner:" + winner + ",times:" + times + ",first:" + first + ",winMoney:" + winMoney +
                ",detail:" + detail);
        Fetch fetch = FetchDao.getNewestFetch();
        Record record = new Record(fetch.getId(), winner, Integer.parseInt(times), first, Float.parseFloat(winMoney), detail, date,
                time, getIp());
        RecordDao.insertRecord(record);
        String resp = "{isSuccess:true,message:'������¼�ɹ���',hasNewToken:true,token:'" +
                TokenUtil.createToken(request) + "'}";
        write(resp);
        return null;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getWinMoney() {
        return winMoney;
    }

    public void setWinMoney(String winMoney) {
        this.winMoney = winMoney;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
