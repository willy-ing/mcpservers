package com.ingeneo.sura.step.mcpservers.claimRequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LossEstimate {

    @JsonProperty("amount")
    private Long amount;

    @JsonProperty("currency")
    private String currency;
}
