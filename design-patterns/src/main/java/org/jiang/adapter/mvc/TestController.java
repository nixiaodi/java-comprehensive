package org.jiang.adapter.mvc;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.Controller;

@RestController
public class TestController {
    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
