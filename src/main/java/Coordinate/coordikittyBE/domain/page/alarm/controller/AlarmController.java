package Coordinate.coordikittyBE.domain.page.alarm.controller;

import Coordinate.coordikittyBE.domain.page.alarm.data.repository.AlarmRepository;
import Coordinate.coordikittyBE.domain.page.alarm.entity.AlarmEntity;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("")
public class AlarmController {
    private static final Logger log = LoggerFactory.getLogger(AlarmController.class);
    private final AlarmRepository alarmRepository;
    @PostMapping ("")
    public void create(){
        AlarmEntity alarm = AlarmEntity.builder().alarmId(UUID.randomUUID()).type("follow").actived(true).ttl(200).build();
        alarmRepository.save(alarm);
        log.info("저장");
    }
    @PostMapping("hi")
    public AlarmEntity find(){
        AlarmEntity foundedAlarm = alarmRepository.findById("6fdbd4b8-3c1f-4943-8276-e22b9e0dcc7f").get();
        return foundedAlarm;
    }
}
