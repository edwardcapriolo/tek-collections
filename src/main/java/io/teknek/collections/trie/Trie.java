package io.teknek.collections.trie;

import java.util.HashMap;
import java.util.Map;

class TrieNode{
    final Map<Character, TrieNode> children = new HashMap<>();
    boolean endOfWord;

}
public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            current = current.children.computeIfAbsent(word.charAt(i), c -> new TrieNode());
        }
        current.endOfWord = true;
    }

    public boolean find(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = current.children.get(ch);
            System.out.println("found char"+ ch);
            if (node == null) {
                return false;
            }
            current = node;
        }
        return current.endOfWord;
    }



    public boolean containsNode(String word){
        TrieNode current = root;

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = current.children.get(ch);
            if (node == null) {
                return false;
            }
            current = node;
        }
        return current.endOfWord;
    }
}
