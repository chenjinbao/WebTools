package com.zte.gu.webtools.excel;

import com.zte.gu.webtools.util.ExcelConfig;
import com.zte.gu.webtools.util.ExcelFieldUtil;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.commons.io.IOUtils;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springside.modules.utils.Reflections;

public class BoardInfoReader {

    private static final Logger logger = LoggerFactory.getLogger(BoardInfoReader.class);

    /**
     * 从物理设备定义文档读取内容
     *
     * @param inputStream
     * @param config
     * @return
     */
    public static Map<String, BoardInfo> readBoardInfo(InputStream inputStream, ExcelConfig config) {
        Map<String, BoardInfo> map = new LinkedHashMap<String, BoardInfo>();
        try {
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheet("Board");
            Assert.notNull(sheet, "物理设备定义文档中没有'Board'这个sheet。");
            Map<String, Integer> indexMap = ExcelFieldUtil.getCellTitleIndex(sheet, config.getBoardFilelds());

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
            IOUtils.closeQuietly(inputStream);
        }
        return map;
    }

    /**
     * 从RRU文档读取内容
     *
     * @param inputStream
     * @param config
     * @return
     */
    public static Map<String, BoardInfo> readRruBoardInfo(InputStream inputStream, ExcelConfig config) {
        Map<String, BoardInfo> map = new LinkedHashMap<String, BoardInfo>();
        try {
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheet("动态管理");
            Assert.notNull(sheet, "RU物理特性文档中没有'动态管理'这个sheet。");
            Map<String, Integer> indexMap = ExcelFieldUtil.getCellTitleIndex(sheet, config.getRuFilelds());

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
            IOUtils.closeQuietly(inputStream);
        }
        return map;
    }

}
