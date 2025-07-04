package com.projectmanagement.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SpaController {

    @RequestMapping(value = {"/", "/{path:^(?!api|static).*}/**"})
    public String forward() {
        return "forward:/index.html";
    }
}
