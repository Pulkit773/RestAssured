
package com.spotify.oauth2.pojo.LeadResponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LeadResponse {

    @JsonProperty("uuid")
    private String uuid;

    @JsonProperty("email")
    private String email;

    @JsonProperty("firstName")
    private String first_name;

    @JsonProperty("lastName")
    private String last_name;

    @JsonProperty("businessName")
    private String business_name;

    @JsonProperty("phoneNumber")
    private String phone_number;

    @JsonProperty("marketingInfo")
    private MarketingInfo marketing_info;

    @JsonProperty("resellerUuid")
    private String reseller_uuid;

    @JsonProperty("order")
    private Order order;

    @JsonProperty("salesforceId")
    private String salesforce_id;

    @JsonProperty("salesforceOwnerId")
    private String salesforce_owner_id;

    @JsonProperty("chainAgentId")
    private String chain_agent_id;

    @JsonProperty("clAnalyticsId")
    private String cl_analytics_id;

    @JsonProperty("activityType")
    private String activityType;

    @JsonProperty("createdTime")
    private String created_time;

    @JsonProperty("modifiedTime")
    private String modified_time;

}
