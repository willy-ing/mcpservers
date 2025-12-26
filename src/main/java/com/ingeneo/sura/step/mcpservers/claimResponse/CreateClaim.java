package com.ingeneo.sura.step.mcpservers.claimResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CreateClaim {
    @JsonProperty("result")
    ClaimResult result;

    @JsonProperty("id")
    String id;

    @JsonProperty("jsonrpc")
    String jsonrpc;
}
