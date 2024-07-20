package com.foodrecipes.recipepicturedownloader.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import com.foodrecipes.recipepicturedownloader.service.S3Service;

@RestController
@RequestMapping("/recipe-picture-downloader/")
public class S3Controller {
	@Autowired
	private S3Service s3Service;
	@Autowired
	private Environment environment;
	
	@GetMapping("/download/{fileName}")
	public String getImageBase64(@PathVariable("fileName") String imageName) throws IOException {
	    S3Object imageObject = s3Service.getFileFromS3(imageName);
	    byte[] imageBytes = IOUtils.toByteArray(imageObject.getObjectContent());
	    
	    String base64Image = Base64.getEncoder().encodeToString(imageBytes);
	    System.out.println(imageName);
	    return base64Image;
	}
	/*public ResponseEntity<byte[]> getImage(@PathVariable("fileName") String imageName) throws IOException {
        S3Object imageObject = s3Service.getFileFromS3(imageName);
        byte[] imageBytes = IOUtils.toByteArray(imageObject.getObjectContent());
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG); // Adjust content type based on your image type
        headers.setContentLength(imageBytes.length);
        String port = environment.getProperty("local.server.port");
        System.out.println("port: " + port);
        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }*/
	
	
	@PostMapping("/download/images")
    public ResponseEntity<List<String>> getImages(@RequestBody List<String> fileNames) {
        List<String> images = new ArrayList<>();

        try {
            for (String fileName : fileNames) {
                S3Object imageObject = s3Service.getFileFromS3(fileName);
                byte[] imageBytes = IOUtils.toByteArray(imageObject.getObjectContent());

                if (imageBytes != null) {
                    String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                    images.add(base64Image);
                }
            }
        } catch (IOException e) {
            // Log the error and return a 500 Internal Server Error response
            // Replace 'logger' with your logging framework if needed
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON); // Adjust content type based on your image type

        return new ResponseEntity<>(images, headers, HttpStatus.OK);
    }
	
}