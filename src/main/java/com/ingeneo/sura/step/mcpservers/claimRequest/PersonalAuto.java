package com.ingeneo.sura.step.mcpservers.claimRequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class PersonalAuto {

    @JsonProperty("vehicleIncidents")
    private List<VehicleIncident> vehicleIncidents;

    @JsonProperty("fixedPropertyIncident")
    private List<Object> fixedPropertyIncident;


}
