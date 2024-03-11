package com.example.seventhhw.controller;

import com.example.seventhhw.dto.SensorRequestDTO;
import com.example.seventhhw.dto.SensorResponseDTO;
import com.example.seventhhw.entity.Sensor;
import com.example.seventhhw.service.SensorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/sensors")
public class SensorController {

    private final SensorService sensorService;

    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @GetMapping("/temperature")
    public ResponseEntity<List<SensorResponseDTO>> getTemperature(@RequestParam List<String> urls) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(sensorService.getSensorsTemperature(urls).get());
    }
}
