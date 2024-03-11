package com.example.seventhhw.repository;

import com.example.seventhhw.entity.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorRepository extends JpaRepository<Sensor, Long> {
    Sensor findSensorBySensorUrl(String url);
}
