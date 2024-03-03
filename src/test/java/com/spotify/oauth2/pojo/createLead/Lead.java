package com.spotify.oauth2.pojo.createLead;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import static com.spotify.oauth2.utils.FakerUtils.*;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Lead {

    @JsonProperty("email")
    private String email;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @JsonProperty("businessName")
    private String businessName;

    @JsonProperty("marketingInfo")
    private MarketingInfo marketingInfo;

    @JsonProperty("activityType")
    private String activityType;

    @JsonProperty("resellerUuid")
    private String resellerUuid;

    @JsonProperty("salesforceOwnerId")
    private String salesforceOwnerId;

    @JsonProperty("chainAgentId")
    private String chainAgentId;

    @JsonProperty("order")
    private Order order;

    public Lead(){
        this.email = "pulkit.agrawal+4353746031@clover.com";//generateEmail();
        this.firstName = "FN"+generateName();
        this.lastName = "LN"+generateName();
        this.phoneNumber = "4084120645";
        this.businessName = "MMIS Test 1709025193425";
        this.marketingInfo = new MarketingInfo();
        this.activityType = "upsertGrowthLead_c";
        this.resellerUuid = "47HMNNZ9BPF8E";
        this.salesforceOwnerId = "00G7j000001EdZdEAK";
        this.chainAgentId = "526975216882";
        this.order = new Order();
    }
}
