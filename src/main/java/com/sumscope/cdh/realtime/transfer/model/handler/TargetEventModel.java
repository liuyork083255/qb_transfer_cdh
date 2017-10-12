package com.sumscope.cdh.realtime.transfer.model.handler;

import com.sumscope.cdh.realtime.transfer.model.db.TargetBboDBModel;
import com.sumscope.cdh.realtime.transfer.model.db.TargetTradeDBModel;

import java.util.List;

/**
 * Created by liu.yang on 2017/9/28.
 */
public class TargetEventModel extends AbstractModel{
    private List<TargetBboDBModel> targetBboDBModelList;
    private List<TargetTradeDBModel> targetTradeDBModelList;

    public List<TargetBboDBModel> getTargetBboDBModelList() {
        return targetBboDBModelList;
    }

    public void setTargetBboDBModelList(List<TargetBboDBModel> targetBboDBModelList) {
        this.targetBboDBModelList = targetBboDBModelList;
    }

    public List<TargetTradeDBModel> getTargetTradeDBModelList() {
        return targetTradeDBModelList;
    }

    public void setTargetTradeDBModelList(List<TargetTradeDBModel> targetTradeDBModelList) {
        this.targetTradeDBModelList = targetTradeDBModelList;
    }
}
