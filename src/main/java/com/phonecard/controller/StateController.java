package com.phonecard.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "国家controller", tags = {"国家"})
@RequestMapping(value = "state", method = RequestMethod.POST)
public class StateController {



}
