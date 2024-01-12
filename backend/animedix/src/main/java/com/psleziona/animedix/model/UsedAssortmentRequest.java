package com.psleziona.animedix.model;


import lombok.Data;
import org.springframework.lang.Nullable;

@Data
public class UsedAssortmentRequest {
    private Integer idSurgery;
    private Integer idAssortment;
    @Nullable
    private Integer quantity;
    @Nullable
    private Double volume;
}
