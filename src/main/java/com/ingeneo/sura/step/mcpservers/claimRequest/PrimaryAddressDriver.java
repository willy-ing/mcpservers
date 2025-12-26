package com.ingeneo.sura.step.mcpservers.claimRequest;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PrimaryAddressDriver {

    @JsonProperty("addressLine1")
    private String addressLine1;

    @JsonProperty("addressType")
    private String addressType;

    @JsonProperty("city")
    private String city;

}
