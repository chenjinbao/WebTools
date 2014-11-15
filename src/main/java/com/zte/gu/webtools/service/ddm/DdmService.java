/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zte.gu.webtools.service.ddm;

import com.zte.gu.webtools.entity.DdmVersion;
import com.zte.gu.webtools.repository.DdmVersionDao;
import com.zte.gu.webtools.service.ddm.excel.BoardInfoWriter;
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
    
    public List<DdmVersion> getAllVersion() {
        return (List<DdmVersion>) ddmVersionDao.findAll();
    }

    public File exportXml(InputStream boardInputStream, InputStream ruInputStream, String sdrVer) {
        File zipFile = BoardInfoWriter.write(boardInputStream, ruInputStream, sdrVer);
        return zipFile;
    }

    @Autowired
    public void setDdmVersionDao(DdmVersionDao ddmVersionDao) {
        this.ddmVersionDao = ddmVersionDao;
    }

}
