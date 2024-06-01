package Coordinate.coordikittyBE.domain.page.alarm.controller;

import Coordinate.coordikittyBE.domain.page.alarm.repository.AlarmRepository;
import Coordinate.coordikittyBE.domain.page.alarm.entity.Alarm;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
        Alarm alarm = Alarm.builder()
                .id(UUID.randomUUID())
                .type("follow")
                .actived(true)
                .ttl(1000)
                .build();
        alarmRepository.save(alarm);
        log.info("저장");
    }
    @PostMapping("hi")
    public Alarm find(@RequestBody String id){
        return alarmRepository.findById(id).isPresent() ? alarmRepository.findById(id).get() : null;
    }
}
