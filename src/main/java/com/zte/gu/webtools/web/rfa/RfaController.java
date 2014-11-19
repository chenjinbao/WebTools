/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zte.gu.webtools.web.rfa;

import com.zte.gu.webtools.service.rfa.RfaService;
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
@RequestMapping(value = "/rfa")
public class RfaController {

    private static final List<String> ACCEPT_TYPES = Arrays.asList("application/vnd.ms-excel", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

    @Autowired
    private RfaService rfaService;

    @RequestMapping(method = RequestMethod.GET)
    public String show(Model model) {
        return "rfa/rfaScan";
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView scan(@RequestParam(required = true) MultipartFile ruFile, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("rfa/rfaScan");
        if (ruFile.isEmpty()) {
            modelAndView.getModelMap().addAttribute("error", "请上传RU物理特性文档。");
        }
        if (!ACCEPT_TYPES.contains(ruFile.getContentType())) {
            modelAndView.getModelMap().addAttribute("error", "RU物理特性文档格式不正确。");
        }

        InputStream ruInput = null;
        try {
            ruInput = ruFile.getInputStream();
            File tempZipFile = rfaService.exportXml(ruInput);  //将输出流传递到方法
            if (tempZipFile != null) {
                session.setAttribute("filePath", tempZipFile.getPath());
                session.setAttribute("fileName", "rfa.zip");
                modelAndView.setViewName("redirect:/download");
            }
        } catch (Exception e) {
            LoggerFactory.getLogger(RfaController.class).warn("download error,", e);
            modelAndView.getModelMap().addAttribute("error", "生成文件失败，" + e.getMessage());
        } finally {
            IOUtils.closeQuietly(ruInput);
        }
        return modelAndView;
    }

}
