
package com.spotify.oauth2.pojo.LeadResponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShippingAddress {

    @JsonProperty("lineOne")
    private String lineOne;
    @JsonProperty("lineTwo")
    private String lineTwo;
    @JsonProperty("city")
    private String city;
    @JsonProperty("state")
    private String state;
    @JsonProperty("zipCode")
    private String zipCode;

}
