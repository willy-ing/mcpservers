package com.ingeneo.sura.step.mcpservers.claimRequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LossLocation {

    @JsonProperty("country")
    private String country;

    @JsonProperty("addressLine1")
    private String addressLine1;

    @JsonProperty("city")
    private String city;
}
