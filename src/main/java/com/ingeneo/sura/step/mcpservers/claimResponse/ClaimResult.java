package com.ingeneo.sura.step.mcpservers.claimResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ClaimResult{
    @JsonProperty("claimNumber") String claimNumber;
}
