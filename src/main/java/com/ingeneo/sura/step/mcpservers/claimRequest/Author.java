package com.ingeneo.sura.step.mcpservers.claimRequest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Author {

    @JsonProperty("documentType")
    private String documentType;

    @JsonProperty("taxID")
    private String taxID;
}
