package com.it.kobus.devices.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/device")
public class DevicesResource {

    @GetMapping(path = "/hello")
    public String hello() {
        return "Service UP";
    }
}
