package com.cjl.web.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {
    private static final Logger logger = LoggerFactory.getLogger("Console");

    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg", "hello mvc");
        mv.setViewName("index");
        return mv;
    }
}
