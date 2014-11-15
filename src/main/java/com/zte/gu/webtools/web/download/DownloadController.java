/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zte.gu.webtools.web.download;

import java.io.FileInputStream;
import java.io.InputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.IOUtils;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Johnny
 */
@Controller
@RequestMapping(value = "/download")
public class DownloadController {

    @RequestMapping(method = RequestMethod.GET)
    public void download(HttpSession session, HttpServletResponse response) {
        String filePath = (String) session.getAttribute("filePath");
        if (filePath != null) {
            response.reset();
            response.setHeader("Content-Disposition", "attachment; filename=\"ddm.zip");
            response.setContentType("application/octet-stream; charset=UTF-8");
            InputStream in = null;
            try {
                in = new FileInputStream(filePath);
                IOUtils.copy(in, response.getOutputStream());
            } catch (Exception e) {
                LoggerFactory.getLogger(DownloadController.class).warn("download error,", e);
            } finally {
                IOUtils.closeQuietly(in);
                session.removeAttribute("filePath");
            }
        }
    }

}
