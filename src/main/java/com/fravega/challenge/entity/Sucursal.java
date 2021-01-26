package com.fravega.challenge.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import nonapi.io.github.classgraph.json.Id;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "direccion",
        "latitud",
        "longitud"
})

@Getter
@Setter
public class Sucursal {
    @Id
    @JsonProperty("id")
    @ApiModelProperty(hidden = true)
    private String id;
    @JsonProperty("direccion")
    String direccion;
    @JsonProperty("latitud")
    String latitud;
    @JsonProperty("longitud")
    String longitud;

    public Sucursal(String direccion, String latitud, String longitud) {
        this.direccion = direccion;
        this.latitud = latitud;
        this.longitud = longitud;
    }
    public Sucursal(){
        super();
    }
}
