package com.github.hygoni.dormitory.controller;

import com.github.hygoni.dormitory.model.CommonResult;
import com.github.hygoni.dormitory.model.Washer;
import com.github.hygoni.dormitory.service.ResponseService;
import com.github.hygoni.dormitory.service.WasherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class WasherController {
    private final WasherService washerService;
    private final ResponseService responseService;

    @PostMapping("createWasher")
    public CommonResult createWasher(@RequestBody Map<String, String> payload){

        washerService.save(Washer.builder()
                .buildingNumber(Integer.parseInt(payload.get("building_number")))
                .displayName(payload.get("display_name"))
                .lastStarted(0) //밀리초 - > 초 단위로 변환
                .workingTime(Integer.parseInt(payload.get("working_time")))
                .subId(Integer.parseInt(payload.get("sub_id")))
                .build()
                );

        return responseService.getSuccessResult();
    }

    @GetMapping("getWashers")
    public List<Washer> getWashers() {
        return washerService.findAll();
    }

    @PostMapping("startWasher")
    public CommonResult startWasher(@RequestBody Map<String, String> payload) {
        int buildingNumber = Integer.parseInt(payload.get("building_number"));
        int subId = Integer.parseInt(payload.get("sub_id"));
        washerService.start(buildingNumber, subId);
        return responseService.getSuccessResult();
    }
}
