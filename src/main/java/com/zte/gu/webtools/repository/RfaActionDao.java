/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zte.gu.webtools.repository;

import com.zte.gu.webtools.entity.RfaAction;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author chen
 */
public interface RfaActionDao extends PagingAndSortingRepository<RfaAction, Long> {

    Iterable<RfaAction> findByVersion(String version);
}
