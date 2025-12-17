package com.vinylWebshop.vinylcollectie.dtos.album;

import com.vinylWebshop.vinylcollectie.dtos.artist.ArtistResponseDTO;
import com.vinylWebshop.vinylcollectie.dtos.stock.StockResponseDTO;

import java.util.List;

public class AlbumExtendedResponseDTO extends AlbumResponseDTO {

    private List<StockResponseDTO> stock;
    private List<ArtistResponseDTO> artists;

    public List<StockResponseDTO> getStock() {
        return stock;
    }

    public void setStock(List<StockResponseDTO> stock) {
        this.stock = stock;
    }

    public List<ArtistResponseDTO> getArtists() {
        return artists;
    }

    public void setArtists(List<ArtistResponseDTO> artists) {
        this.artists = artists;
    }
}
