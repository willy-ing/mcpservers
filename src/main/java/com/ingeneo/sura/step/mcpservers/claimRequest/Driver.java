package com.ingeneo.sura.step.mcpservers.claimRequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Driver {

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("middleName")
    private String middleName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("secondLastName")
    private String secondLastName;

    @JsonProperty("workNumber")
    private String workNumber;

    @JsonProperty("cellNumber")
    private String cellNumber;

    @JsonProperty("emailAddress1")
    private String emailAddress1;

    @JsonProperty("policyRole")
    private String policyRole;

    @JsonProperty("documentType")
    private String documentType;

    @JsonProperty("taxID")
    private String taxID;

    @JsonProperty("primaryAddressDriver")
    private PrimaryAddressDriver primaryAddressDriver;
}
