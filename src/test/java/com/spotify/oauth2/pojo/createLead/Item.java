
package com.spotify.oauth2.pojo.createLead;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Item {

    @JsonProperty("digitalCatalogId")
    private String digitalCatalogId;

    @JsonProperty("fdmpId")
    private String fdmpId;

    @JsonProperty("quantity")
    private Integer quantity;

    @JsonProperty("purchaseType")
    private String purchaseType;
    public Item(){
        this.digitalCatalogId = "FLEX_GEN_3";
        this.fdmpId = "777-1000108721";
        this.quantity = 1 ;
        this.purchaseType ="P";
    }
}
