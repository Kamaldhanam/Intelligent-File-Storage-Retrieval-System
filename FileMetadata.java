package filestorage;

public class FileMetadata {
    private int id;
    private String filename;
    private String keywords;
    private String uploader;
    private String s3Key;

    public FileMetadata(String filename, String keywords, String uploader, String s3Key) {
        this.filename = filename;
        this.keywords = keywords;
        this.uploader = uploader;
        this.s3Key = s3Key;
    }

    public String getFilename() { return filename; }
    public String getKeywords() { return keywords; }
    public String getUploader() { return uploader; }
    public String getS3Key() { return s3Key; }
}
