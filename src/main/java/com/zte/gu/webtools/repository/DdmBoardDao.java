/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zte.gu.webtools.repository;

import com.zte.gu.webtools.entity.DdmBoard;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author chen
 */
public interface DdmBoardDao extends PagingAndSortingRepository<DdmBoard, Long> {

    Iterable<DdmBoard> findByVersion(String version);
}
