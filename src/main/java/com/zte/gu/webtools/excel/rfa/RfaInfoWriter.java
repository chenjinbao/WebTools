package com.zte.gu.webtools.excel.rfa;

import com.google.common.io.Files;
import com.zte.gu.webtools.util.RfaExcelConfig;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springside.modules.utils.Collections3;
import org.springside.modules.utils.Reflections;

public class RfaInfoWriter {

    private static final Logger LOG = LoggerFactory.getLogger(RfaInfoWriter.class);
    private static final String[] RADIOS = new String[]{"UMTS{UMTS}", "GSM{GSM}"};

    public static File write(InputStream boardInputStream, RfaExcelConfig excelConfig) {
        List<RfaInfo> rfaInfos = RfaInfoReader.readRruRfaInfo(boardInputStream, excelConfig);
        List<String> rrus = getFreqScanRrus(rfaInfos);
        try {
            List<File> files = new ArrayList<File>();
            files.add(output(updateXml(rrus, excelConfig.getEnFile()), "en"));
            files.add(output(updateXml(rrus, excelConfig.getZhFile()), "zh"));
            for (String radio : RADIOS) {
                files.add(writeProp(rfaInfos, excelConfig, radio));
            }
            return zipFiles(files);
        } catch (Exception e) {
            LOG.warn("error", e);
            throw new RuntimeException(e.getMessage());
        }
    }

    private static List<String> getFreqScanRrus(List<RfaInfo> rfaInfos) {
        List<String> rrus = new ArrayList<String>();
        for (RfaInfo rfaInfo : rfaInfos) {
            if (rfaInfo.getFreqScan().startsWith("1")) {
                rrus.add(rfaInfo.getBoardId());
            }
        }
        return rrus;
    }

    private static Document updateXml(List<String> rrus, String xml) {
        StringReader reader = null;
        Document document = null;
        try {
            String rruStr = StringUtils.join(rrus, '/');
            reader = new StringReader(xml);
            SAXReader saxReader = new SAXReader();
            document = saxReader.read(reader);
            List<Element> elements = document.selectNodes("//Diagnosis/FuncId[@id='771']");
            if (elements != null && !elements.isEmpty()) {
                elements.get(0).attribute("support_rru").setValue(rruStr);
            }
        } catch (Exception e) {
            LOG.warn("error", e);
            throw new RuntimeException("生成XML失败," + e.getMessage());
        } finally {
            IOUtils.closeQuietly(reader);
        }
        return document;
    }

    /**
     * 输出文件
     *
     * @param document
     * @param path
     * @throws Exception
     */
    private static File output(Document document, String language) throws Exception {
        XMLWriter writer = null;
        try {
            File tempDir = Files.createTempDir();
            File file = new File(tempDir.getPath() + File.separator + "EDMS_SPM_" + language + ".xml");
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("UTF-8");
            writer = new XMLWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"), format);
            writer.write(document);
            return file;
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    private static File zipFiles(List<File> files) {
        ZipOutputStream zipOutputStream = null;
        InputStream in = null;
        try {
            File tempFile = File.createTempFile("rfa_", ".zip", Files.createTempDir());
            zipOutputStream = new ZipOutputStream(tempFile);
            for (File file : files) {
                try {
                    in = new FileInputStream(file);
                    ZipEntry entry = new ZipEntry(file.getName());
                    zipOutputStream.putNextEntry(entry);
                    IOUtils.copy(in, zipOutputStream);
                } finally {
                    IOUtils.closeQuietly(in);
                }
            }
            return tempFile;
        } catch (Exception e) {
            LOG.warn("压缩文件出错，", e);
            throw new RuntimeException(e.getMessage());
        } finally {
            IOUtils.closeQuietly(zipOutputStream);
        }
    }

    private static File writeProp(List<RfaInfo> rfaInfos, RfaExcelConfig excelConfig, String radio) {
        Properties prop = new Properties();
        Map<String, String> actionFields = excelConfig.getActionFields();
        for (Map.Entry<String, String> entry : actionFields.entrySet()) {
            String action = entry.getKey();
            String fieldName = entry.getValue();
            List<RfaInfo> tempList = new ArrayList<RfaInfo>();
            for (RfaInfo info : rfaInfos) {
                if (!info.getRadioMode().contains(radio)) {
                    continue;
                }
                String supportValue = (String) Reflections.invokeGetter(info, fieldName);
                if (supportValue != null && !supportValue.trim().isEmpty() && supportValue.trim().contains("1")) {
                    tempList.add(info);
                }
            }
            String rrus = Collections3.extractToString(tempList, "boardId", ",");
            if (StringUtils.isNotBlank(rrus) && !rrus.endsWith(",")) {
                rrus += ",";
            }
            prop.put(action, rrus);
        }

        File tempDir = Files.createTempDir();
        File tempFile = new File(tempDir.getPath() + File.separator + radio + "-rfa-supportrru.properties");
        OutputStream outStream = null;
        try {
            outStream = new FileOutputStream(tempFile);
            prop.store(outStream, null);
        } catch (Exception e) {
            LOG.warn("生成properties文件出错，", e);
            throw new RuntimeException(e.getMessage());
        } finally {
            IOUtils.closeQuietly(outStream);
        }
        return tempFile;
    }

}
