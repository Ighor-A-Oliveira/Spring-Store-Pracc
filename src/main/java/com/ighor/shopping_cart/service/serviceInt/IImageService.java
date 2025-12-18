package com.ighor.shopping_cart.service.serviceInt;

import com.ighor.shopping_cart.dto.objectDTO.ImageDto;
import com.ighor.shopping_cart.entity.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageService {

    Image getImageById(Long id);
    Image getImageByName(String name);
    List<Image> getAllImages();
    //Image addImage(Image image);
    List<ImageDto> saveImages(List<MultipartFile>  files, Long productId);
    void updateImage(MultipartFile file, Long imageId);
    void deleteImageById(Long id);
}
