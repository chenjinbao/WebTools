package com.zte.gu.webtools.service.ddm.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

public class RfaInfoWriter {

    private static final Logger LOG = LoggerFactory.getLogger(RfaInfoWriter.class);

    public static boolean write(File rruFile, String savePath) {
        List<RfaInfo> rfaInfos = RfaInfoReader.readRruRfaInfo(rruFile);
        List<String> rrus = handleList(rfaInfos);
        try {
            output(updateValue(rrus, "en"), savePath + File.separator + "EDMS_SPM_en.xml");
            output(updateValue(rrus, "zh"), savePath + File.separator + "EDMS_SPM_zh.xml");
        } catch (Exception e) {
            LOG.warn("error", e);
            return false;
        }
        return true;
    }

    private static List<String> handleList(List<RfaInfo> rfaInfos) {
        List<String> rrus = new ArrayList<String>();
        for (RfaInfo rfaInfo : rfaInfos) {
            if (rfaInfo.getFreqScan().startsWith("1")) {
                rrus.add(rfaInfo.getBoardId());
            }
        }
        return rrus;
    }

    private static Document updateValue(List<String> rrus, String language) {
        InputStream in = null;
        Document document = null;
        try {
            String rruStr = StringUtils.join(rrus, '/');

            String src = "com/zte/ommb/guddm/convert/template/EDMS_SPM_en.xml";
            if ("zh".equalsIgnoreCase(language)) {
                src = "com/zte/ommb/guddm/convert/template/EDMS_SPM_zh.xml";
            }
            in = RfaInfoWriter.class.getClassLoader().getResourceAsStream(src);
            SAXReader saxReader = new SAXReader();
            document = saxReader.read(in);
            List<Element> elements = document.selectNodes("//Diagnosis/FuncId[@id='771']");
            if (elements != null && !elements.isEmpty()) {
                elements.get(0).attribute("support_rru").setValue(rruStr);
            }
        } catch (Exception e) {
            LOG.warn("error", e);
        } finally {
            IOUtils.closeQuietly(in);
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
    private static void output(Document document, String path) throws Exception {
        XMLWriter writer = null;
        try {
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("UTF-8");
            writer = new XMLWriter(new OutputStreamWriter(new FileOutputStream(path), "GBK"), format);
            writer.write(document);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

}
