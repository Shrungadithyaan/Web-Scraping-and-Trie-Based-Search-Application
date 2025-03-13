package com.example.web_scraping.trie;

import java.util.HashMap;
import java.util.Map;

/**
 * Trie data structure implementation for efficient prefix-based search.
 */
public class Trie {
    private final TrieNode root = new TrieNode(); // Root node of the Trie

    /**
     * Inserts a word into the Trie.
     * @param word The word to be inserted.
     */
    public void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            node.children.putIfAbsent(c, new TrieNode()); // Create node if not exists
            node = node.children.get(c); // Move to the next node
        }
        node.isEndOfWord = true; // Mark end of the word
    }

    /**
     * Checks if there is any word in the Trie that starts with the given prefix.
     * @param prefix The prefix to check.
     * @return True if any word in the Trie starts with the prefix, false otherwise.
     */
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            if (!node.children.containsKey(c)) {
                return false; // Prefix not found
            }
            node = node.children.get(c);
        }
        return true; // Prefix exists in the Trie
    }

    /**
     * Inner class representing a node in the Trie.
     */
    private static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>(); // Child nodes of the current node
        boolean isEndOfWord = false; // Flag to indicate end of a valid word
    }
}
