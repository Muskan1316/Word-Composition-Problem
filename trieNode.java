import java.util.*;

class TrieNode {
    char key;
    TrieNode parent;
    Map<Character, TrieNode> children;
    boolean end;

    public TrieNode(char key) {
        this.key = key;
        this.parent = null;
        this.children = new HashMap<>();
        this.end = false;
    }

    public String getWord() {
        StringBuilder output = new StringBuilder();
        TrieNode node = this;
        while (node != null && node.key != '\0') {
            output.insert(0, node.key);
            node = node.parent;
        }
        return output.toString();
    }
}

class Trie {
    private TrieNode root;

    public Trie() {
        this.root = new TrieNode('\0');
    }

    public void insert(String word) {
        TrieNode node = root;
        for (int index = 0; index < word.length(); index++) {
            char ch = word.charAt(index);
            node.children.putIfAbsent(ch, new TrieNode(ch));
            TrieNode child = node.children.get(ch);
            child.parent = node;
            node = child;
            if (index == word.length() - 1) {
                node.end = true;
            }
        }
    }

    public TrieNode getRoot() {
        return root;
    }

    private void findAllWords(TrieNode node, List<String> arr) {
        if (node.end) {
            arr.add(node.getWord());
        }
        for (TrieNode child : node.children.values()) {
            findAllWords(child, arr);
        }
    }

    public List<String> find(String prefix) {
        TrieNode node = root;
        List<String> output = new ArrayList<>();
        for (int index = 0; index < prefix.length(); index++) {
            char ch = prefix.charAt(index);
            if (node.children.containsKey(ch)) {
                node = node.children.get(ch);
            } else {
                return output;
            }
        }
        findAllWords(node, output);
        return output;
    }
}
