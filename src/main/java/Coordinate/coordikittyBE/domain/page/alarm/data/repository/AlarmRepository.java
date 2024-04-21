package Coordinate.coordikittyBE.domain.page.alarm.data.repository;

import Coordinate.coordikittyBE.domain.page.alarm.entity.AlarmEntity;
import org.springframework.data.repository.CrudRepository;


public interface AlarmRepository extends CrudRepository<AlarmEntity, String> {
}