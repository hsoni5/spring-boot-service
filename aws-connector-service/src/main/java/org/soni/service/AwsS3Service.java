package org.soni.service;


import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.File;
import java.io.IOException;

/**
 * This class is used for s3 operation
 * @author VC60363
 */

@Service
@Log4j2
public class AwsS3Service{
    @Autowired
    private S3Client s3Client;
    @Value("${aws.s3.bucket}")
    private String bucketName;

    /**
     * This is used for upload file in aws S3 bucket
     * @param file path
     * @param file
     */
    @Async
    public void uploadFileToS3Bucket(final String filePath, final File file){
        log.info("Uploading file with name= " + file.getName());
        final PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(filePath.concat(file.getName()))
                .build();
        s3Client.putObject(putObjectRequest, file.toPath());
        log.info("Uploaded file with name= " + file.getName());
    }

    /**
     *
     * @param filePath
     * @param fileName
     * @return
     */
    @Async
    public Resource readFileAndReturnResource(final String filePath, final String fileName) {
        log.info("Reading file with name= {} from S3 file location: {} ", fileName, filePath);
        ResponseInputStream<GetObjectResponse> responseInputStream = readFile(filePath, fileName);
        Resource  resource = null;
        try{
            resource = new ByteArrayResource(responseInputStream.readAllBytes());
        }catch(IOException e){
            log.error("File reader exception");
        }
        return resource;
    }

    /**
     *
     * @param filePath
     * @param fileName
     * @return
     */
    @Async
    public ResponseInputStream<GetObjectResponse> readFile(String filePath, String fileName){
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(filePath.concat(fileName))
                .build();
        ResponseInputStream<GetObjectResponse> responseInputStream = s3Client.getObject(getObjectRequest);
        log.info("Read file with name : {}", fileName);
        return responseInputStream;
    }

    /**
     * Method is used delete file
     * @param fileName
     * @param bucketName
     * @return
     */
    @Async
    public String deleteFile(String fileName, String bucketName){
        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .build();
        s3Client.deleteObject(deleteObjectRequest);
        log.info("Delete file with name : {}", fileName);
        return fileName + " removed ...";
    }

}