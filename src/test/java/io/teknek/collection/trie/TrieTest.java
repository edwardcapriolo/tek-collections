package io.teknek.collection.trie;

import io.teknek.collections.trie.Trie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TrieTest {
    @Test
    void simple(){
        Trie t = new Trie();
        t.insert("he");
        Assertions.assertTrue(t.find("he"));
    }
}
