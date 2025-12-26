package com.ingeneo.sura.step.mcpservers.claimRequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Vehicle {

    @JsonProperty("licensePlate")
    private String licensePlate;

    @JsonProperty("make")
    private String make;

    @JsonProperty("model")
    private String model;

    @JsonProperty("engineNumber")
    private String engineNumber;

    @JsonProperty("year")
    private Integer year;

    @JsonProperty("color")
    private String color;

    @JsonProperty("vehicleType")
    private String vehicleType;

    @JsonProperty("fasecoldaCode")
    private String fasecoldaCode;

    @JsonProperty("vin")
    private String vin;
}
