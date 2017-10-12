package com.sumscope.cdh.realtime.transfer.common;

import com.alibaba.fastjson.JSON;
import com.lmax.disruptor.EventTranslatorOneArg;
import com.sumscope.cdh.realtime.transfer.model.db.SourceBboDBModel;
import com.sumscope.cdh.realtime.transfer.model.db.SourceTradeDBModel;
import com.sumscope.cdh.realtime.transfer.model.db.TargetBboDBModel;
import com.sumscope.cdh.realtime.transfer.model.db.TargetTradeDBModel;
import com.sumscope.cdh.realtime.transfer.model.handler.SourceEventModel;
import com.sumscope.cdh.realtime.transfer.model.handler.TargetEventModel;
import com.sun.tools.javac.comp.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liu.yang on 2017/10/9.
 */
@Component
public class SourceModelConvertToTargetUtil implements EventTranslatorOneArg<TargetEventModel,SourceEventModel>{

    private static final Logger LOGGER = LoggerFactory.getLogger(SourceModelConvertToTargetUtil.class);

    @Autowired
    private CommonUtil commonUtil;

    @Override
    public void translateTo(TargetEventModel targetEventModel, long sequence, SourceEventModel sourceEventModel) {
        switch (sourceEventModel.getBondType()){
            case BBO:
                targetEventModel.setBondType(sourceEventModel.getBondType());
                targetEventModel.setTargetBboDBModelList(convertBbo(sourceEventModel.getSourceBboDBModelList()));
                break;
            case TRADE:
                targetEventModel.setBondType(sourceEventModel.getBondType());
                targetEventModel.setTargetTradeDBModelList(convertTrade(sourceEventModel.getSourceTradeDBModelList()));
                break;
            case SINGLEBBO:
                targetEventModel.setBondType(sourceEventModel.getBondType());
                break;
            default:
                LOGGER.error(String.format("no match Model type. message =>[%s]",sourceEventModel.getMessage()));
                sourceEventModel.setFlag(false);
        }
    }

    private List<TargetBboDBModel> convertBbo(List<SourceBboDBModel> sourceList){

        List<TargetBboDBModel> targetList = new ArrayList<>();

        sourceList.forEach((sourceModel) -> {
            try{
                TargetBboDBModel targetModel = new TargetBboDBModel();

                targetModel.setUuid(commonUtil.getUUID());
                targetModel.setBboMonth(commonUtil.getDBPartition());





                // TODO: 2017/10/10 convert ...

                targetList.add(targetModel);
            }catch (Exception e){
                LOGGER.error(String.format("convert sourceBboModel to targetBboModel fail.exception=>[%s]   message=>[%s]",e.getMessage(), JSON.toJSONString(sourceModel)));
            }
        });
        return targetList;
    }

    public List<TargetTradeDBModel> convertTrade(List<SourceTradeDBModel> sourceList){

        List<TargetTradeDBModel> targetList = new ArrayList<>();

        sourceList.forEach((sourceModel) -> {
            try {
                TargetTradeDBModel targetModel = new TargetTradeDBModel();

                targetModel.setUuid(commonUtil.getUUID());
                targetModel.setTradeMonth(commonUtil.getDBPartition());

                // TODO: 2017/10/10 convert ...

                targetList.add(targetModel);
            } catch (Exception e) {
                LOGGER.error(String.format("convert sourceTradeModel to targetTradeModel fail.exception=>[%s]   message=>[%s]",e.getMessage(), JSON.toJSONString(sourceModel)));
            }
        });
        return targetList;
    }


}
