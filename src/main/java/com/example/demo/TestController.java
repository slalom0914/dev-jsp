package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
public class TestController {
  @GetMapping("test")
  public String hello(){
    log.info("hello 호출");
    return "test";
  }
}
