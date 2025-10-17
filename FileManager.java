package filestorage;

import java.sql.*;

public class FileManager {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/file_storage";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "yourpassword";

    private SearchIndex index = new SearchIndex();

    public void uploadFile(String filePath, String filename, String keywords, String uploader) {
        String s3Key = uploader + "/" + filename;

        // Upload to S3
        S3Manager.uploadFile(filePath, s3Key);

        // Save metadata to SQL
        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO files (filename, keywords, uploader, s3_key) VALUES (?, ?, ?, ?)"
            );
            stmt.setString(1, filename);
            stmt.setString(2, keywords);
            stmt.setString(3, uploader);
            stmt.setString(4, s3Key);
            stmt.executeUpdate();
            conn.close();

            // Add to search index
            FileMetadata file = new FileMetadata(filename, keywords, uploader, s3Key);
            index.addFile(file);

            System.out.println("File uploaded and indexed: " + filename);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchFiles(String keyword) {
        System.out.println("Search results for: " + keyword);
        for (FileMetadata file : index.search(keyword)) {
            System.out.println(file.getFilename() + " | Uploaded by: " + file.getUploader());
        }
    }
}
