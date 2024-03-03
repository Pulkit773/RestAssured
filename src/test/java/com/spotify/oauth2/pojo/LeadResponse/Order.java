
package com.spotify.oauth2.pojo.LeadResponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Order {

    @JsonProperty("uuid")
    private String order_uuid;

    @JsonProperty("hasSubscriptions")
    private Boolean hasSubscriptions;

    @JsonProperty("planId")
    private String planId;

    @JsonProperty("planName")
    private String planName;

    @JsonProperty("keyedInRate")
    private KeyedInRate keyedInRate;

    @JsonProperty("inPersonRate")
    private InPersonRate inPersonRate;

    @JsonProperty("shippingAddress")
    private ShippingAddress shippingAddress;

    @JsonProperty("items")
    private List<Item> items;


}
