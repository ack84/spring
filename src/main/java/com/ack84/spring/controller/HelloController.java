package com.ack84.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    //template
    @GetMapping("hello")
    public String hello (Model model){
        model.addAttribute("data","hello");
        return "hello";
    }
    //mvc template localhost:8080/hello-mvc?name=spring
    @GetMapping("hello-mvc")
    public String helloMvc (@RequestParam("name")String name, Model model){

        model.addAttribute("name",name);
        return "hello-template";

    }
    //api 호출(string반환) localhost:8080/hello-string?name=spring
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString (@RequestParam("name") String name){

        return "hello "+ name;
    }
    //api 호출(객체반환, json으로 반환) localhost:8080/hello-api?name=spring
    @GetMapping("hell-api")
    @ResponseBody
    public Hello helloapi (@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello{
        String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
