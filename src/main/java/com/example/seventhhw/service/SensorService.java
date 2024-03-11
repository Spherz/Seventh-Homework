package com.example.seventhhw.service;

import com.example.seventhhw.dto.SensorResponseDTO;
import com.example.seventhhw.entity.Sensor;
import com.example.seventhhw.exception.SensorNotFoundException;
import com.example.seventhhw.repository.SensorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class SensorService {

    private final SensorRepository sensorRepository;

    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Async
    public CompletableFuture<SensorResponseDTO> getSensorByUrl(String url) {
        Sensor sensorData = sensorRepository.findSensorBySensorUrl(url);

        if(sensorData != null) {
            log.info("Sensor Data: " + mapToResponseDTO(sensorData));
            return CompletableFuture.completedFuture(mapToResponseDTO(sensorData));
        } else {
            log.error("Can't find sensor with url " + url);
            throw new SensorNotFoundException("Sensor with url: " + url + " not found");
        }
    }

    @Async
    public CompletableFuture<List<SensorResponseDTO>> getSensorsTemperature(List<String> urls) throws ExecutionException, InterruptedException {
        List<SensorResponseDTO> sensors = new ArrayList<>();
        for (String url : urls) {
            sensors.add(getSensorByUrl(url).get());
        }
        log.info("Sensors List: " + sensors);
        return CompletableFuture.completedFuture(sensors);
    }

    public SensorResponseDTO mapToResponseDTO(Sensor sensor) {
        SensorResponseDTO responseDTO = new SensorResponseDTO();
        responseDTO.setSensorId(sensor.getSensorId());
        responseDTO.setTemperatureC(sensor.getTemperatureC());
        return responseDTO;
    }
}
