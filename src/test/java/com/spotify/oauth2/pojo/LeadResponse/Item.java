
package com.spotify.oauth2.pojo.LeadResponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Item {

    @JsonProperty("uuid")
    private String uuid;
    @JsonProperty("fdmpId")
    private String fdmpId;
    @JsonProperty("displayName")
    private String displayName;
    @JsonProperty("purchaseType")
    private String purchaseType;
    @JsonProperty("quantity")
    private Integer quantity;
    @JsonProperty("price")
    private Price price;
    @JsonProperty("digitalCatalogId")
    private String digitalCatalogId;


}
