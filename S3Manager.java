package filestorage;

import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.File;

public class S3Manager {
    private static final String BUCKET_NAME = "kamal-s3-bucket";

    public static void uploadFile(String filePath, String s3Key) {
        try {
            S3Client s3 = S3Client.builder()
                    .region(Region.US_EAST_1)
                    .credentialsProvider(ProfileCredentialsProvider.create())
                    .build();

            File file = new File(filePath);
            PutObjectRequest request = PutObjectRequest.builder()
                    .bucket(BUCKET_NAME)
                    .key(s3Key)
                    .build();

            s3.putObject(request, file.toPath());
            System.out.println("Uploaded file to S3: " + s3Key);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
