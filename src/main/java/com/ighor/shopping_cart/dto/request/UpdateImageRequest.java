package com.ighor.shopping_cart.dto.request;

import com.ighor.shopping_cart.entity.Product;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

public class UpdateImageRequest {
    private Long id;
    private String fileName;
    private String fileType;
    private byte[] image;
    private String downloadUrl;
    private Product product;
}
