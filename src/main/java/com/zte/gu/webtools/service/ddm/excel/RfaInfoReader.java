package com.zte.gu.webtools.service.ddm.excel;

import com.zte.gu.webtools.service.ddm.util.ExcelFieldUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springside.modules.utils.Reflections;

public class RfaInfoReader {

    private static final Logger LOG = LoggerFactory.getLogger(RfaInfoReader.class);
    private static final String[][] RFA_FIELDS = new String[][]{{"boardId", "单板编号"}, {"freqScan", "干扰分析"}};

    private static final Map<String, String> RFA_TITLE_TO_FIELD = new HashMap<String, String>();

    static {
        for (String[] field : RFA_FIELDS) {
            RFA_TITLE_TO_FIELD.put(field[1], field[0]);
        }
    }

    public static List<RfaInfo> readRruRfaInfo(File rruFile) {
        List<RfaInfo> rrus = new ArrayList<RfaInfo>();
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(rruFile);
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheet("射频分析");
            if (sheet == null) {
                sheet = workbook.getSheet("射频分析模块");
            }
            Assert.notNull(sheet, "RU物理特性文档中没有'射频分析'这个sheet。");
            
            Map<String, Integer> indexMap = ExcelFieldUtil.getCellTitleIndex(sheet, RFA_TITLE_TO_FIELD);

            for (int i = 3; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if ((row.getCell(0) == null) || row.getCell(0).toString().equals("")) {
                    break;
                }

                RfaInfo rfaInfo = new RfaInfo();
                for (Map.Entry<String, Integer> entry : indexMap.entrySet()) {
                    String field = entry.getKey();
                    int index = entry.getValue();
                    String value = ObjectUtils.toString(row.getCell(index), "0").trim();
                    Reflections.setFieldValue(rfaInfo, field, value);
                }

                rrus.add(rfaInfo);
            }
            return rrus;
        } catch (Exception e) {
            LOG.warn(e.getMessage(), e);
            throw new RuntimeException(e.getMessage());
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                    LOG.warn(e.getMessage(), e);
                }
            }
        }
    }
}
