
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
    private String utm_campaign;

    public MarketingInfo(){
        this.utm_campaign = "Clover_Commerce_WhereToBuy";
    }
}
