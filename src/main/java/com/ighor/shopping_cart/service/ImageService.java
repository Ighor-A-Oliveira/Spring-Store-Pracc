package com.ighor.shopping_cart.service;

import com.ighor.shopping_cart.dto.objectDTO.ImageDto;
import com.ighor.shopping_cart.entity.Image;
import com.ighor.shopping_cart.entity.Product;
import com.ighor.shopping_cart.exception.AlreadyExistsException;
import com.ighor.shopping_cart.exception.ResourceNotFoundException;
import com.ighor.shopping_cart.repository.ImageRepository;
import com.ighor.shopping_cart.repository.ProductRepository;
import com.ighor.shopping_cart.service.serviceInt.IImageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ImageService implements IImageService {

    private final ImageRepository imageRepository;
    private final ProductRepository productRepository;

    public ImageService(ImageRepository imageRepository,ProductRepository productRepository){
        this.imageRepository = imageRepository;
        this.productRepository = productRepository;
    }


    @Override
    public Image getImageById(Long id) {
        return imageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Image not found"));
    }

    @Override
    public Image getImageByName(String name) {
        return imageRepository.findByName(name).orElseThrow(() -> new ResourceNotFoundException("Image not found"));
    }

    @Override
    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }



    @Override
    public List<ImageDto> saveImages(List<MultipartFile> files, Long productId) {
        //Each image is tied to a product, so here we search for the product
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product Not Found"));
        //Them we create a list of images
        List<ImageDto> savedImageDtos = new ArrayList<>();
        //creating download url
        String baseDownloadUrl = "/api/v1/images/image/download/";
        //We iterate the list and set the properties
        for (MultipartFile file : files){
            try {
                Image image = new Image();
                //Setting properties of each image
                image.setFileName(file.getOriginalFilename());
                image.setFileType(file.getContentType());
                image.setImage(file.getBytes());
                image.setProduct(product);


                //String downloadUrl = buildDownloadUrl+image.getId();
                //image.setDownloadUrl(downloadUrl);
                //Saving to the db
                Image savedImage = imageRepository.save(image);
                //After saving we have an Id for the image so we can create a downloadUrl
                String downloadUrl = baseDownloadUrl+savedImage.getId();
                //Set property
                savedImage.setDownloadUrl(downloadUrl);
                //And update the entity
                imageRepository.save(savedImage);

                //savedImage.setDownloadUrl(baseDownloadUrl+savedImage.getId());

                //Now we can add our entityDto to the list
                ImageDto imageDTO = new ImageDto();
                imageDTO.setId(savedImage.getId());
                imageDTO.setFileName(savedImage.getFileName());
                imageDTO.setDownloadUrl(savedImage.getDownloadUrl());
                savedImageDtos.add(imageDTO);
            } catch (IOException e){
                throw new RuntimeException("Failed to save image", e);
            }
        }
        return savedImageDtos;
    }

    @Override
    public void updateImage(MultipartFile file, Long imageId) {
        //Trying to find image in db
        Image image = imageRepository.findById(imageId)
                //If not in db we throw exception
                .orElseThrow(() -> new ResourceNotFoundException("Image not found"));
        //Then we send the existing image and the temporary image to the updateExistingImage method
        try {
            image.setFileName(file.getOriginalFilename());
            image.setFileType(file.getContentType());
            image.setImage(file.getBytes());
            imageRepository.save(image);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void deleteImageById(Long id) {
        //here we try to search for the image
        imageRepository.findById(id)
                //If the image is present we execute the first line of the block
                .ifPresentOrElse(
                        //delete image
                        imageRepository::delete,
                        //If image is not present we throw exception
                        () -> {throw new ResourceNotFoundException("Image Not Found");}
                );

    }
}
