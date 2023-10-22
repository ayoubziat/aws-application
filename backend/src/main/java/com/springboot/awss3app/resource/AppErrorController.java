package com.springboot.awss3app.resource;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//@RequestMapping("/error")
public class AppErrorController implements ErrorController {
    private final static String PATH = "/error";

    @RequestMapping("/error")
    String error(HttpServletRequest request) {
        return "<h1>Error occurred</h1>";
    }

//    @RequestMapping(PATH)
//    @ResponseBody
//    public String getErrorPath() {
//        // TODO Auto-generated method stub
//        return "No Mapping Found";
//    }
}
