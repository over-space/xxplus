package com.xxblog.controller;

import com.xxbase.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by admin on 16/05/09.
 */
@Controller
@RequestMapping("/xxblog")
public class BlogController extends BaseController{


    @RequestMapping("/index")
    public ModelAndView index(){
        return new ModelAndView("/xxblog/index");
    }


}
