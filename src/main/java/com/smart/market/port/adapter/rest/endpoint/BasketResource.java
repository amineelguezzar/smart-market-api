package com.smart.market.port.adapter.rest.endpoint;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Slf4j
@RequiredArgsConstructor
@RestController
public class BasketResource {
    @RequestMapping("/")
    public String run() {
        return "Hello World";
    }
}