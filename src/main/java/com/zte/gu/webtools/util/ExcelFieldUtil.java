package com.zte.gu.webtools.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class ExcelFieldUtil {

    /**
     * 获得物理设备定义文档中标题和列号对应关系
     * 
     * @param sheet
     * @param fieldMap
     * @return
     */
    public static Map<String, Integer> getCellTitleIndex(Sheet sheet, Map<String, String> fieldMap) {
        Map<String, Integer> map = new HashMap<String, Integer>();

        Row firstRow = sheet.getRow(0);
        for (int i = 0; i < firstRow.getLastCellNum(); i++) {
            Cell cell = firstRow.getCell(i);
            String value = ObjectUtils.toString(cell, "").trim();
            if (fieldMap.containsKey(value)) {
                map.put(fieldMap.get(value), i);
            }
        }

        ExcelFieldUtil.verifyTitle(fieldMap, map);
        return map;
    }

    /**
     * 校验标题是否完全
     * 
     * @param titleMap
     * @param indexMap
     */
    public static void verifyTitle(Map<String, String> titleMap, Map<String, Integer> indexMap) {
        for (Map.Entry<String, String> entry : titleMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (!indexMap.containsKey(value)) {
                throw new RuntimeException("没有找到" + key + "列。");
            }
        }
    }

}
