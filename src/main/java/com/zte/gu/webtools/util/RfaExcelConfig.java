/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zte.gu.webtools.util;

import com.zte.gu.webtools.entity.RfaAction;
import com.zte.gu.webtools.entity.RfaRru;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 10115701
 */
public class RfaExcelConfig {

    public static RfaExcelConfig createConfig(List<RfaRru> rfaRrus, List<RfaAction> rfaActions) {
        RfaExcelConfig config = new RfaExcelConfig();

        Map<String, String> ruFields = new HashMap<String, String>();
        for (RfaRru rfaRru : rfaRrus) {
            ruFields.put(rfaRru.getColumnName(), rfaRru.getFieldName());
        }

        Map<String, String> actionFields = new HashMap<String, String>();
        for (RfaAction rfaAction : rfaActions) {
            actionFields.put(rfaAction.getActionType(), rfaAction.getFieldName());
        }

        config.getRuFilelds().putAll(ruFields);
        config.getActionFields().putAll(actionFields);

        return config;
    }

    private final Map<String, String> ruFilelds = new HashMap<String, String>();
    private final Map<String, String> actionFields = new LinkedHashMap<String, String>();

    private RfaExcelConfig() {

    }

    /**
     * @return the ruFilelds
     */
    public Map<String, String> getRuFilelds() {
        return ruFilelds;
    }

    /**
     * @return the actionFields
     */
    public Map<String, String> getActionFields() {
        return actionFields;
    }

}
