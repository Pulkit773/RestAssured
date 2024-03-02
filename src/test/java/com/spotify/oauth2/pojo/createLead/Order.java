
package com.spotify.oauth2.pojo.createLead;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Order {

    @JsonProperty("planId")
    private String planId;

    @JsonProperty("shippingAddress")
    private ShippingAddress shippingAddress;

    @JsonProperty("items")
    private List<Item> items;
    public Order(){
        this.planId = "4NKNCTE5RGV5P";
        this.shippingAddress = new ShippingAddress();
        this.items = Arrays.asList(new Item());
    }
}
