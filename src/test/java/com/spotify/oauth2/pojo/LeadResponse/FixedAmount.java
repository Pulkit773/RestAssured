
package com.spotify.oauth2.pojo.LeadResponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FixedAmount {

    @JsonProperty("value")
    private Double value;

    @JsonProperty("currencyCode")
    private String currencyCode;

}
