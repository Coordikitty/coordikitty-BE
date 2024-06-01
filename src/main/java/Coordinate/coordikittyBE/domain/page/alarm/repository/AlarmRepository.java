package Coordinate.coordikittyBE.domain.page.alarm.repository;

import Coordinate.coordikittyBE.domain.page.alarm.entity.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlarmRepository extends JpaRepository<Alarm, String> {
}