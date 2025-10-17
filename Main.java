package filestorage;

public class Main {
    public static void main(String[] args) {
        FileManager manager = new FileManager();

        // Upload files
        manager.uploadFile("files/doc1.pdf", "doc1.pdf", "finance report Q1", "alice");
        manager.uploadFile("files/doc2.pdf", "doc2.pdf", "project plan marketing", "bob");

        // Search
        manager.searchFiles("finance");
        manager.searchFiles("marketing");
    }
}
