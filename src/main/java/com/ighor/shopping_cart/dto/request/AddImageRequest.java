package com.ighor.shopping_cart.dto.request;

import com.ighor.shopping_cart.entity.Product;
import lombok.Data;

@Data
public class AddImageRequest {
    private Long id;
    private String fileName;
    private String fileType;
    private byte[] image;
    private String downloadUrl;
    private Long productId;
}
