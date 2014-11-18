/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zte.gu.webtools.repository;

import com.zte.gu.webtools.entity.RfaFreqscanTemplate;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author Johnny
 */
public interface RfaFreqscanTemplateDao extends PagingAndSortingRepository<RfaFreqscanTemplate, Long> {

    RfaFreqscanTemplate findByVersion(String version);
}
