package com.ingeneo.sura.step.mcpservers.claimRequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class VehicleIncident {

    @JsonProperty("description")
    private String description;

    @JsonProperty("repairShop")
    private String repairShop;

    @JsonProperty("lossParty")
    private String lossParty;

    @JsonProperty("movePermission")
    private Boolean movePermission;

    @JsonProperty("collision")
    private Boolean collision;

    @JsonProperty("driverRelation")
    private String driverRelation;

    @JsonProperty("driver")
    private Driver driver;

    @JsonProperty("vehicle")
    private Vehicle vehicle;

    @JsonProperty("passengers")
    private List<Object> passengers;
}
