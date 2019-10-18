package com.baobao.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Random;

@RestController
@RequestMapping("/ws")
public class IndexController {

    @GetMapping("/index")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("socket");
        mv.addObject("vendorshopId",6);
        return mv;

    }


}
