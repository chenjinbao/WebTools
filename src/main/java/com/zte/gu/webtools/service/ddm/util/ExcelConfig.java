/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zte.gu.webtools.service.ddm.util;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

/**
 *
 * @author 10115701
 */
public class ExcelConfig {
    
    private static final Logger LOG = LoggerFactory.getLogger(ExcelConfig.class);
    private static final Map<Integer, ExcelConfig> INSTANCES = new HashMap<Integer, ExcelConfig>();
    
    public static synchronized ExcelConfig getInstance(Integer version) {
        if (!INSTANCES.containsKey(version)) {
            INSTANCES.put(version, new ExcelConfig(version));
        }
        return INSTANCES.get(version);
    }
    
    private final Map<String, String> ruFilelds = new HashMap<String, String>();
    private final Map<String, String> boardFilelds = new HashMap<String, String>();
    private final Map<String, String> actionFields = new LinkedHashMap<String, String>();
    
    private ExcelConfig(Integer version) {
        loadXmlByVersion(version);
    }
    
    private void loadXmlByVersion(Integer version) {
        try {
            File file = ResourceUtils.getFile("classpath:/ddm/version" + version + "/ddmscan.xml");
            SAXReader saxReader = new SAXReader();
            Document doc = saxReader.read(file);
            Element root = doc.getRootElement();
            
            Element ruElement = root.element("RuExcelFileds");
            List<Element> ruColumnElements = ruElement.elements();
            for (Element columnElement : ruColumnElements) {
                getRuFilelds().put(columnElement.attributeValue("name").trim(), columnElement.attributeValue("field").trim());
            }
            
            Element boardElement = root.element("BoardExcelFields");
            List<Element> boardColumnElements = boardElement.elements();
            for (Element columnElement : boardColumnElements) {
                getBoardFilelds().put(columnElement.attributeValue("name").trim(), columnElement.attributeValue("field").trim());
            }
            
            Element actionElement = root.element("ActionFields");
            List<Element> actionFieldElements = actionElement.elements();
            for (Element actionFieldElement : actionFieldElements) {
                getActionFields().put(actionFieldElement.attributeValue("name").trim(), actionFieldElement.attributeValue("field").trim());
            }
        } catch (Exception e) {
            LOG.warn("read xml error,", e);
        }
    }

    /**
     * @return the ruFilelds
     */
    public Map<String, String> getRuFilelds() {
        return ruFilelds;
    }

    /**
     * @return the boardFilelds
     */
    public Map<String, String> getBoardFilelds() {
        return boardFilelds;
    }

    /**
     * @return the actionFields
     */
    public Map<String, String> getActionFields() {
        return actionFields;
    }
    
}
