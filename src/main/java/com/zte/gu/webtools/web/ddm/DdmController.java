/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zte.gu.webtools.web.ddm;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author 10115701
 */
@Controller
@RequestMapping(value = "/ddm")
public class DdmController {

    @RequestMapping(method = RequestMethod.GET)
    public String show(Model model) {
        return "ddm/ddmScan";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String scan(@RequestParam MultipartFile boardFile, @RequestParam MultipartFile ruFile, Model model) {
        return "ddm/ddmScan";
    }

}
