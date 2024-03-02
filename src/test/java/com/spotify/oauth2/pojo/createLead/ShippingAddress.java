
package com.spotify.oauth2.pojo.createLead;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShippingAddress {

    @JsonProperty("city")
    private String city;

    @JsonProperty("lineOne")
    private String lineOne;

    @JsonProperty("lineTwo")
    private String lineTwo;

    @JsonProperty("state")
    private String state;

    @JsonProperty("zipCode")
    private String zipCode;
    public ShippingAddress(){
        this.city= "New York";
        this.lineOne = "131 Varick St";
        this.lineTwo = "11";
        this.state = "NY";
        this.zipCode = "10014";
    }
}
