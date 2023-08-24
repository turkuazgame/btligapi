package com.turkuazgame.btlig.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeneralController {

    @Value("${mobile.game.version}")
    String mobileVersion;

    @Value("${mobile.game.link.android}")
    String androidLink;

    @Value("${mobile.game.link.ios}")
    String iosLink;

    @GetMapping(value="/welcome")
    public String getWelcome() {
        return "Welcome to BtLig Game API";
    }

    @GetMapping(value="/mobile/version")
    public String getMobileVersion() {
        return mobileVersion;
    }

    @GetMapping(value="/mobile/link/android")
    public String getMobileAndroidLink() {
        return androidLink;
    }

    @GetMapping(value="/mobile/link/ios")
    public String getMobileIosLink() {
        return iosLink;
    }
}