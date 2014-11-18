package com.zte.gu.webtools.excel.rfa;

import com.zte.gu.webtools.util.ExcelFieldUtil;
import com.zte.gu.webtools.util.RfaExcelConfig;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
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

public class RfaInfoReader {

    private static final Logger LOG = LoggerFactory.getLogger(RfaInfoReader.class);

    public static List<RfaInfo> readRruRfaInfo(InputStream inputStream, RfaExcelConfig excelConfig) {
        List<RfaInfo> rrus = new ArrayList<RfaInfo>();
        try {
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheet("射频分析");
            if (sheet == null) {
                sheet = workbook.getSheet("射频分析模块");
            }
            Assert.notNull(sheet, "RU物理特性文档中没有'射频分析'这个sheet。");
            
            Map<String, Integer> indexMap = ExcelFieldUtil.getCellTitleIndex(sheet, excelConfig.getRuFilelds());

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
                    Reflections.invokeSetter(rfaInfo, field, value);
                }

                rrus.add(rfaInfo);
            }
            return rrus;
        } catch (Exception e) {
            LOG.warn(e.getMessage(), e);
            throw new RuntimeException(e.getMessage());
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
    }
}
