
package com.spotify.oauth2.pojo.LeadResponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonIgnoreProperties(value = {"hasSubscriptions","planId","planName","items","inPersonRate","shippingAddress","keyedInRate"})
public class Order {

    @JsonProperty("uuid")
    private String uuid;

    @JsonProperty("hasSubscriptions")
    @EqualsAndHashCode.Exclude
    private Boolean hasSubscriptions;

    @JsonProperty("planId")
    @EqualsAndHashCode.Exclude
    private String planId;

    @JsonProperty("planName")
    @EqualsAndHashCode.Exclude
    private String planName;

    @JsonProperty("keyedInRate")
    @EqualsAndHashCode.Exclude
    private KeyedInRate keyedInRate;

    @JsonProperty("inPersonRate")
    @EqualsAndHashCode.Exclude
    private InPersonRate inPersonRate;

    @JsonProperty("shippingAddress")
    @EqualsAndHashCode.Exclude
    private ShippingAddress shippingAddress;

    @JsonProperty("items")
    @EqualsAndHashCode.Exclude
    private List<Item> items;

}
