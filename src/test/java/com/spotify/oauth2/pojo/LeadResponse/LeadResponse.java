
package com.spotify.oauth2.pojo.LeadResponse;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsExclude;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"createdTime","modifiedTime"},ignoreUnknown = true)
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
    @EqualsAndHashCode.Exclude
    private String created_time;

    @JsonProperty("modifiedTime")
    @EqualsAndHashCode.Exclude
    private String modified_time;



    public LeadResponse() {

    }

    /*public LeadResponse(String uuid, String email, String first_name, String last_name, String business_name, String phone_number, String reseller_uuid, String salesforce_id, String salesforce_owner_id, String chain_agent_id,
                        String cl_analytics_id, String created_time, String modified_time) {
        this.marketing_info = new MarketingInfo();
        this.order = new Order();
        this.uuid = uuid;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.business_name = business_name;
        this.phone_number = phone_number;
        this.reseller_uuid = reseller_uuid;
        this.salesforce_id = salesforce_id;
        this.salesforce_owner_id = salesforce_owner_id;
        this.chain_agent_id = chain_agent_id;
        this.cl_analytics_id = cl_analytics_id;
        this.created_time = created_time;
        this.modified_time = modified_time;

    }*/

}
