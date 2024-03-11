package com.example.seventhhw.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SensorResponseDTO {

    private String sensorId;

    private double temperatureC;
}
