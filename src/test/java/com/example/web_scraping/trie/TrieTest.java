/**
 * Unit tests for the Trie data structure.
 */
package com.example.web_scraping.trie;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TrieTest {

    /**
     * Tests the insert and startsWith methods to check prefix matching.
     */
    @Test
    void testInsertAndStartsWith() {
        Trie trie = new Trie();
        trie.insert("technology");
        trie.insert("innovation");

        assertTrue(trie.startsWith("tech"), "Prefix 'tech' should exist in the Trie");
        assertTrue(trie.startsWith("inno"), "Prefix 'inno' should exist in the Trie");
        assertFalse(trie.startsWith("bio"), "Prefix 'bio' should not exist in the Trie");
    }

    /**
     * Tests the behavior of an empty Trie to ensure it does not contain any prefixes.
     */
    @Test
    void testEmptyTrie() {
        Trie trie = new Trie();
        assertFalse(trie.startsWith("any"), "Empty Trie should not contain any prefix");
    }

    /**
     * Tests inserting an empty string to verify it is handled correctly.
     */
    @Test
    void testInsertEmptyString() {
        Trie trie = new Trie();
        trie.insert("");

        assertTrue(trie.startsWith(""), "Empty string should be a valid prefix");
    }

    /**
     * Tests inserting and searching for an exact word in the Trie.
     */
    @Test
    void testInsertAndSearchExactWord() {
        Trie trie = new Trie();
        trie.insert("technology");

        assertTrue(trie.startsWith("technology"), "Exact word 'technology' should exist in the Trie");
    }
}
