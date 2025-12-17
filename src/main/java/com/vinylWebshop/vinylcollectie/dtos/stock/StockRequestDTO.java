package com.vinylWebshop.vinylcollectie.dtos.stock;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class StockRequestDTO {

    @Size(max = 50)
    private String condition;

    @NotNull
    @Positive
    private BigDecimal price;

    /**
     * Het albumId wordt gebruikt om stock aan een album te koppelen.
     * De daadwerkelijke koppeling gebeurt in de SERVICE.
     */
    @NotNull
    private Long albumId;

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }
}
