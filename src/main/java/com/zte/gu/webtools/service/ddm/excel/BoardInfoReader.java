package com.zte.gu.webtools.service.ddm.excel;

import com.zte.gu.webtools.service.ddm.util.ExcelFieldUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springside.modules.utils.Reflections;

public class BoardInfoReader {

    private static final Logger logger = LoggerFactory.getLogger(BoardInfoReader.class);

    private static final String[][] RU_FIELDS = new String[][]{{"boardName", "单板名称"}, {"radioMode", "无线制式"}, {"enableReset", "复位操作"},
    {"enablePowerReset", "掉电复位操作"}, {"enableHardwareReset", "硬复位操作"}, {"enableBlock", "闭塞/解闭塞操作"}, {"enablePA2", "PA使能/去使能（新）"},
    {"enableCarrierPower", "查询载波功率（新）"}, {"rruQueryFile", "RRU文件查询"}, {"rruFileDownload", "RRU文件下载"}, {"rruFileUpload", "RRU文件上传"},
    {"rruFileDelete", "RRU文件删除"}, {"rruAASFileDownload", "下载幅相参数文件"}, {"rruAASOpen", "开启有源天线自愈"}, {"rruAASClose", "关闭有源天线自愈"},
    {"rruQueryAASStatus", "查询有源天线自愈状态"}, {"aisgPowerOpen", "打开AISG口供电"}, {"aisgPowerClose", "关闭AISG口供电"},
    {"subscribeReversePowerTest", "订阅反向功率检测"}, {"unsubscribeReversePowerTest", "退订反向功率检测"}, {"rruLogUpload", "RRU日志上传"}};

    private static final Map<String, String> RU_TITLE_TO_FIELD = new HashMap<String, String>();

    private static final String[][] BOARD_FIELDS = new String[][]{{"boardName", "单板名称"}, {"radioMode", "无线制式"}, {"enableDiskClean", "主控设备"},
    {"enableReset", "复位操作"}, {"enablePowerReset", "下电复位操作"}, {"enableHardwareReset", "硬复位操作"}, {"enableBlock", "阻塞/解阻塞操作"},
    {"enablePA1", "启用/停用操作"}, {"enablePowerOn", "上电"}, {"enablePowerOff", "下电"}};

    private static final Map<String, String> BOARD_TITLE_TO_FIELD = new HashMap<String, String>();

    static {
        for (String[] ruField : RU_FIELDS) {
            RU_TITLE_TO_FIELD.put(ruField[1], ruField[0]);
        }
        for (String[] boardField : BOARD_FIELDS) {
            BOARD_TITLE_TO_FIELD.put(boardField[1], boardField[0]);
        }
    }

    /**
     * 从物理设备定义文档读取内容
     *
     * @param boardFile
     * @return
     */
    public static Map<String, BoardInfo> readBoardInfo(File boardFile) {
        Map<String, BoardInfo> map = new LinkedHashMap<String, BoardInfo>();
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(boardFile);
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheet("Board");

            Map<String, Integer> indexMap = ExcelFieldUtil.getCellTitleIndex(sheet, BOARD_TITLE_TO_FIELD);

            for (int i = 3; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if ((row.getCell(0) == null) || row.getCell(0).toString().equals("")) {
                    break;
                }

                BoardInfo boardInfo = new BoardInfo();
                for (Map.Entry<String, Integer> entry : indexMap.entrySet()) {
                    String field = entry.getKey();
                    int index = entry.getValue();
                    String value = ObjectUtils.toString(row.getCell(index), "").trim();
                    Reflections.setFieldValue(boardInfo, field, value);
                }

                map.put(boardInfo.getBoardName(), boardInfo);
            }
        } catch (Exception e) {
            logger.warn(e.getMessage(), e);
            throw new RuntimeException(e.getMessage());
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                    logger.warn(e.getMessage(), e);
                }
            }
        }
        return map;
    }

    /**
     * 从RRU文档读取内容
     *
     * @param rruFile
     * @return
     */
    public static Map<String, BoardInfo> readRruBoardInfo(File rruFile) {
        Map<String, BoardInfo> map = new LinkedHashMap<String, BoardInfo>();
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(rruFile);
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheet("动态管理");

            Map<String, Integer> indexMap = ExcelFieldUtil.getCellTitleIndex(sheet, RU_TITLE_TO_FIELD);

            for (int i = 3; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if ((row.getCell(0) == null) || row.getCell(0).toString().equals("")) {
                    break;
                }

                BoardInfo boardInfo = new BoardInfo();
                for (Map.Entry<String, Integer> entry : indexMap.entrySet()) {
                    String field = entry.getKey();
                    int index = entry.getValue();
                    String value = ObjectUtils.toString(row.getCell(index), "").trim();
                    Reflections.setFieldValue(boardInfo, field, value);
                }

                map.put(boardInfo.getBoardName(), boardInfo);
            }
        } catch (Exception e) {
            logger.warn(e.getMessage(), e);
            throw new RuntimeException(e.getMessage());
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                    logger.warn(e.getMessage(), e);
                }
            }
        }
        return map;
    }

}
