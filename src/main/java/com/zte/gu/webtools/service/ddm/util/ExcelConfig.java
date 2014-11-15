/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zte.gu.webtools.service.ddm.util;

import com.zte.gu.webtools.entity.DdmAction;
import com.zte.gu.webtools.entity.DdmBoard;
import com.zte.gu.webtools.entity.DdmRru;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author 10115701
 */
public class ExcelConfig {

    public static ExcelConfig createConfig(List<DdmBoard> ddmBoards, List<DdmRru> ddmRrus, List<DdmAction> ddmActions) {
        ExcelConfig config = new ExcelConfig();
        Map<String, String> boardFields = new HashMap<String, String>();
        for (DdmBoard ddmBoard : ddmBoards) {
            boardFields.put(ddmBoard.getColumnName(), ddmBoard.getFieldName());
        }

        Map<String, String> ruFields = new HashMap<String, String>();
        for (DdmRru ddmRru : ddmRrus) {
            ruFields.put(ddmRru.getColumnName(), ddmRru.getFieldName());
        }

        Map<String, String> actionFields = new HashMap<String, String>();
        for (DdmAction ddmAction : ddmActions) {
            actionFields.put(ddmAction.getActionType(), ddmAction.getFieldName());
        }
        
        config.getBoardFilelds().putAll(boardFields);
        config.getRuFilelds().putAll(ruFields);
        config.getActionFields().putAll(actionFields);
        
        return config;
    }

    private final Map<String, String> ruFilelds = new HashMap<String, String>();
    private final Map<String, String> boardFilelds = new HashMap<String, String>();
    private final Map<String, String> actionFields = new LinkedHashMap<String, String>();

    private ExcelConfig() {

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
