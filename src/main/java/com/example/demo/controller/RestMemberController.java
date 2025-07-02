package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/member/*")
public class RestMemberController {
  //-> http://localhost:8000/member/jsonMemberList
  @GetMapping("jsonMemberList")
  public String jsonMemberList(){
    log.info("jsonMemberList");
    List<Map<String,Object>> list = new ArrayList<>();
    Map<String,Object> map = new HashMap<>();
    map.put("id","kiwi");
    map.put("passwd","123");
    map.put("name","키위");
    list.add(map);
    map = new HashMap<>();
    map.put("id","tomato");
    map.put("passwd","123");
    map.put("name","토마토");
    list.add(map);
    Gson g = new Gson();
    String temp = g.toJson(list);
    return temp;
  }
}
