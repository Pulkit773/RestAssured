
package com.spotify.oauth2.pojo.createLead;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MarketingInfo {

    @JsonProperty("utm_campaign")
    private String utmCampaign;

    public MarketingInfo(){
        this.utmCampaign = "Clover_Commerce_WhereToBuy";
    }
}
