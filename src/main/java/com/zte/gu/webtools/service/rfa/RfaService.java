/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zte.gu.webtools.service.rfa;

import com.zte.gu.webtools.entity.RfaAction;
import com.zte.gu.webtools.entity.RfaRru;
import com.zte.gu.webtools.excel.rfa.RfaInfoWriter;
import com.zte.gu.webtools.repository.RfaActionDao;
import com.zte.gu.webtools.repository.RfaRruDao;
import com.zte.gu.webtools.util.RfaExcelConfig;
import java.io.File;
import java.io.InputStream;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author 10115701
 */
// Spring Bean的标识.
@Component
// 类中所有public函数都纳入事务管理的标识.
@Transactional
public class RfaService {

    private RfaActionDao rfaActionDao;
    private RfaRruDao rfaRruDao;

    public File exportXml(InputStream ruInputStream) {
        List<RfaRru> rfaRrus = (List<RfaRru>) rfaRruDao.findAll();
        List<RfaAction> rfaActions = (List<RfaAction>) rfaActionDao.findAll();
        RfaExcelConfig config = RfaExcelConfig.createConfig(rfaRrus, rfaActions);
        File zipFile = RfaInfoWriter.write(ruInputStream, config);
        return zipFile;
    }

    @Autowired
    public void setRfaActionDao(RfaActionDao rfaActionDao) {
        this.rfaActionDao = rfaActionDao;
    }

    @Autowired
    public void setRfaRruDao(RfaRruDao rfaRruDao) {
        this.rfaRruDao = rfaRruDao;
    }

}
