package com.ingeneo.sura.step.mcpservers.claimRequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LobsData {

    @JsonProperty("personalAuto")
    private PersonalAuto personalAuto;
}
