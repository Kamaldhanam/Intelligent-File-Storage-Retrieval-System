package filestorage;

import java.util.*;

public class SearchIndex {

    private static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        List<FileMetadata> files = new ArrayList<>();
    }

    private TrieNode root = new TrieNode();

    public void addFile(FileMetadata file) {
        String[] keywords = file.getKeywords().split("\\s+");
        for (String keyword : keywords) {
            TrieNode node = root;
            for (char c : keyword.toLowerCase().toCharArray()) {
                node.children.putIfAbsent(c, new TrieNode());
                node = node.children.get(c);
            }
            node.files.add(file);
        }
    }

    public List<FileMetadata> search(String keyword) {
        TrieNode node = root;
        for (char c : keyword.toLowerCase().toCharArray()) {
            node = node.children.get(c);
            if (node == null) return new ArrayList<>();
        }
        return node.files;
    }
}
