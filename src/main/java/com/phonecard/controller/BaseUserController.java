package com.phonecard.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "用户controller" , tags = {"用户"})
@RequestMapping(value = "baseUser" , method = RequestMethod.POST)
public class BaseUserController {



}
