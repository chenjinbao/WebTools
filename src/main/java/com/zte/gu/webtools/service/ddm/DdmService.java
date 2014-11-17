/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zte.gu.webtools.service.ddm;

import com.zte.gu.webtools.entity.DdmAction;
import com.zte.gu.webtools.entity.DdmBoard;
import com.zte.gu.webtools.entity.DdmRru;
import com.zte.gu.webtools.entity.DdmVersion;
import com.zte.gu.webtools.repository.DdmActionDao;
import com.zte.gu.webtools.repository.DdmBoardDao;
import com.zte.gu.webtools.repository.DdmRruDao;
import com.zte.gu.webtools.repository.DdmVersionDao;
import com.zte.gu.webtools.excel.ddm.BoardInfoWriter;
import com.zte.gu.webtools.util.DdmExcelConfig;
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
public class DdmService {

    private DdmVersionDao ddmVersionDao;
    private DdmBoardDao ddmBoardDao;
    private DdmRruDao ddmRruDao;
    private DdmActionDao ddmActionDao;

    public List<DdmVersion> getAllVersion() {
        return (List<DdmVersion>) ddmVersionDao.findAll();
    }

    public File exportXml(InputStream boardInputStream, InputStream ruInputStream, String sdrVer) {
        List<DdmBoard> ddmBoards = (List<DdmBoard>) ddmBoardDao.findByVersion(sdrVer);
        List<DdmRru> ddmRrus = (List<DdmRru>) ddmRruDao.findByVersion(sdrVer);
        List<DdmAction> ddmActions = (List<DdmAction>) ddmActionDao.findByVersion(sdrVer);
        DdmExcelConfig config = DdmExcelConfig.createConfig(ddmBoards, ddmRrus, ddmActions);

        File zipFile = BoardInfoWriter.write(boardInputStream, ruInputStream, config);
        return zipFile;
    }

    @Autowired
    public void setDdmVersionDao(DdmVersionDao ddmVersionDao) {
        this.ddmVersionDao = ddmVersionDao;
    }

    @Autowired
    public void setDdmBoardDao(DdmBoardDao ddmBoardDao) {
        this.ddmBoardDao = ddmBoardDao;
    }

    @Autowired
    public void setDdmRruDao(DdmRruDao ddmRruDao) {
        this.ddmRruDao = ddmRruDao;
    }

    @Autowired
    public void setDdmActionDao(DdmActionDao ddmActionDao) {
        this.ddmActionDao = ddmActionDao;
    }

}
