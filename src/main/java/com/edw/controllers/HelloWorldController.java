package com.edw.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 *     com.edw.controllers.HelloWorldController
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 03 Feb 2022 09:20
 */
@RestController
public class HelloWorldController {
    @GetMapping("/")
    public Map index() {
        return new HashMap() {{
            put("hello", "world");
        }};
    }
}
