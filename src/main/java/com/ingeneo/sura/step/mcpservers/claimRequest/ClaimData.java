package com.ingeneo.sura.step.mcpservers.claimRequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ClaimData {

    @JsonProperty("lossDate")
    private String lossDate;

    @JsonProperty("notificationDate")
    private String notificationDate;

    @JsonProperty("lossType")
    private String lossType;

    @JsonProperty("lossCause")
    private String lossCause;

    @JsonProperty("lobs")
    private LobsData lobs;

    @JsonProperty("mainContact")
    private MainContact mainContact;

    @JsonProperty("lossLocation")
    private LossLocation lossLocation;

    @JsonProperty("description")
    private String description;

    @JsonProperty("macaNumber")
    private String macaNumber;

    @JsonProperty("faultRating")
    private String faultRating;

    @JsonProperty("lossEstimate")
    private LossEstimate lossEstimate;

    @JsonProperty("authorUser")
    private String authorUser;

    @JsonProperty("isSuspect")
    private Boolean isSuspect;

    @JsonProperty("suspectDesc")
    private String suspectDesc;

    @JsonProperty("originCause")
    private String originCause;

    @JsonProperty("segment")
    private String segment;

    @JsonProperty("author")
    private Author author;

    @JsonProperty("authorityTransit")
    private String authorityTransit;
}
