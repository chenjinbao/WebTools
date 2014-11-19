/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zte.gu.webtools.web.ddm;

import com.zte.gu.webtools.entity.DdmVersion;
import com.zte.gu.webtools.service.ddm.DdmService;
import java.io.File;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.IOUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author 10115701
 */
@Controller
@RequestMapping(value = "/ddm")
public class DdmController {

    private static final List<String> ACCEPT_TYPES = Arrays.asList("application/vnd.ms-excel", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

    @Autowired
    private DdmService ddmService;

    @RequestMapping(method = RequestMethod.GET)
    public String show(Model model) {
        List<DdmVersion> versions = ddmService.getAllVersion();
        model.addAttribute("versions", versions);
        return "ddm/ddmScan";
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView scan(@RequestParam(required = true) MultipartFile boardFile, @RequestParam(required = true) MultipartFile ruFile, @RequestParam(required = true) String sdrVer, HttpSession session) {
        List<DdmVersion> versions = ddmService.getAllVersion();
        ModelAndView modelAndView = new ModelAndView("ddm/ddmScan");
        modelAndView.getModelMap().addAttribute("versions", versions);
        if (boardFile.isEmpty()) {
            modelAndView.getModelMap().addAttribute("error", "请上传物理设备定义文档。");
        }
        if (!ACCEPT_TYPES.contains(boardFile.getContentType())) {
            modelAndView.getModelMap().addAttribute("error", "物理设备定义文档格式不正确。");
        }
        if (ruFile.isEmpty()) {
            modelAndView.getModelMap().addAttribute("error", "请上传RU物理特性文档。");
        }
        if (!ACCEPT_TYPES.contains(ruFile.getContentType())) {
            modelAndView.getModelMap().addAttribute("error", "RU物理特性文档格式不正确。");
        }

        InputStream boardInput = null;
        InputStream ruInput = null;
        try {
            boardInput = boardFile.getInputStream();
            ruInput = ruFile.getInputStream();
            File tempZipFile = ddmService.exportXml(boardInput, ruInput, sdrVer);  //将输出流传递到方法
            if (tempZipFile != null) {
                session.setAttribute("filePath", tempZipFile.getPath());
                session.setAttribute("fileName", "ddm.zip");
                modelAndView.setViewName("redirect:/download");
            }
        } catch (Exception e) {
            LoggerFactory.getLogger(DdmController.class).warn("download error,", e);
            modelAndView.getModelMap().addAttribute("error", "生成文件失败，" + e.getMessage());
        } finally {
            IOUtils.closeQuietly(boardInput);
            IOUtils.closeQuietly(ruInput);
        }
        return modelAndView;
    }

}
