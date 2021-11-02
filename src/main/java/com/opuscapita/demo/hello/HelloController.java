package com.opuscapita.demo.hello;

import com.opuscapita.demo.config.AppSettings;
import com.opuscapita.demo.error.ErrorResponseDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private AppSettings appSettings;

    @Autowired
    public HelloController(AppSettings appSettings) {
        this.appSettings = appSettings;
    }

    @GetMapping("/hello")
    @ApiOperation(value = "Greeter.", notes = "Greets using given name.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Greeting returned."),
        @ApiResponse(code = 500, message = "When 'error' name is given.", response = ErrorResponseDto.class)
    })
    public String hello(@RequestParam(defaultValue = "stranger") String name) {
        if ("error".equals(name)) {
            throw new IllegalArgumentException("Bad!");
        }
        return appSettings.getHelloPrefix() + name;
    }
}
