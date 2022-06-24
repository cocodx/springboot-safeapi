package com.lg.safeapi.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.lg.safeapi.entity.UploadData;
import com.lg.safeapi.utils.GsonUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 正能量导师
 * @date 2022/6/24-13:34
 * @Description
 * UploadDataListener 不能被spring管理，每次读取excel都要new，里面用到spring，可以构造方法传进去
 */
@Slf4j
public class UploadDataListener implements ReadListener<UploadData> {
    private static int BATCH_COUNT=1000;
    private List<UploadData> cacheDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    @Override
    public void invoke(UploadData uploadData, AnalysisContext analysisContext) {
        log.info("解析一条数据:{}", GsonUtil.toJson(uploadData));
        cacheDataList.add(uploadData);
        if (cacheDataList.size()>=BATCH_COUNT){
            saveData();
            //存储完成，清理list
            cacheDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }

    private void saveData() {
        System.out.println("存储数据！");
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
