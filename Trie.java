import java.util.Arrays;
import java.util.TreeMap;

/**
 * LeetCode 208. Implement Trie (Prefix Tree)
 * https://leetcode.com/problems/implement-trie-prefix-tree/
 * 
 * Runtime: 40 ms, faster than 34.67% of Java online submissions.
 * Memory Usage: 47 MB, less than 98.85% of Java online submissions.
 * 
 * Runtime: 29 ms, faster than 95.08% of Java online submissions.
 * Memory Usage: 48.4 MB, less than 73.26% of Java online submissions.
 */
public class Trie {

    // **** class members ****
    public char ch;

    // public TreeMap<Character, Trie> children;
    public Trie[] children;

    public boolean end;

    /** 
     * Initialize your data structure here.
     */
    public Trie() {
        this.ch         = ' ';

        // this.children   = new TreeMap<Character, Trie>();
        this.children   = new Trie[26];

        this.end        = false;
    }

    /** 
     * Initialize your data structure here.
     */
    public Trie(char ch) {
        this.ch         = ch;

        // this.children   = new TreeMap<Character, Trie>();
        this.children   = new Trie[26];

        this.end        = false;
    }
    
    /** 
     * Inserts a word into the trie.
     */
    public void insert(String word) {

        // **** initialization ****
        Trie p = this;

        // **** traverse the word checking and inserting nodes ****
        for (int i = 0; i < word.length(); i++) {

            // **** ****
            char ch = word.charAt(i);

            // **** check we have a child with this character ****

            // if (p.children.containsKey(ch)) {
            //     p = p.children.get(ch);
            // } else {
            //     Trie node = new Trie(ch);
            //     p.children.put(ch, node);
            //     p = node;
            // }

            if (p.children[ch - 'a'] != null)
                p = p.children[ch - 'a'];
            else {
                Trie node = new Trie(ch);
                p.children[ch - 'a'] = node;
                p = node;
            }
        }

        // **** flag as a end of word ****
        p.end = true;
    }
    
    /** 
     * Returns true if the word is in the trie.
     */
    public boolean search(String word) {
        
        // **** initialization ****
        Trie p = this;

        // **** traverse the word and the trie ****
        for (int i = 0; i < word.length(); i++) {

            // **** ****
            char ch = word.charAt(i);

            // **** check if this character is in the trie ****

            // if (p.children.containsKey(ch)) {
            //     p = p.children.get(ch);
            // } else {
            //     return false;
            // }

            if (p.children[ch - 'a'] == null)
                return false;
            
            // **** move to next node ****
            p = p.children[ch - 'a'];
        }

        // **** word in trie ****
        return p.end;
    }
    
    /** 
     * Returns true if there is any word in the trie 
     * that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {

        // **** initialization ****
        Trie p = this;

        // **** traverse the word and the trie ****
        for (int i = 0; i < prefix.length(); i++) {

            // **** ****
            char ch = prefix.charAt(i);

            // **** check if this character is in the trie (or not) ****

            // if (p.children.containsKey(ch)) {
            //     p = p.children.get(ch);
            // } else {
            //     return false;
            // }

            if (p.children[ch - 'a'] == null)
                return false;

            // **** move to the next node ****
            p = p.children[ch - 'a'];

        }

        // **** found prefix ****
        return true;
    }

    /**
     * To string method.
     */
    @Override
    public String toString() {
        // return "ch: " + this.ch + " end: " + this.end + 
        //         " children: " + this.children.toString();

        return "ch: " + this.ch + " end: " + this.end + 
                    " children: " + Arrays.toString(this.children);
    }

    /**
     * Test scaffolding.
     */
    public static void main(String[] args) {
        
        // **** create and initialize the trie ****
        Trie trie = new Trie();

        // **** search for this word ****
        System.out.println("main <<< search(\"apple\"): " + trie.search("apple")); 

        // ???? ????
        System.out.println("main <<< trie: " + trie.toString());

        // **** insert this word ****
        trie.insert("apple");
        System.out.println("main <<< insert(\"apple\")");

        // **** search for this word ****
        System.out.println("main <<< search(\"apple\"): " + trie.search("apple")); 

        // **** search for this word ****
        System.out.println("main <<< search(\"app\"): " + trie.search("app"));

        // **** start with this word ****
        System.out.println("main <<< startWith(\"app\")" + trie.startsWith("app"));

        // **** insert this word ****
        trie.insert("app");
        System.out.println("main <<< insert(\"app\")");

        // **** search for this word ****
        System.out.println("main <<< search(\"app\"): " + trie.search("app"));

        // **** search for this word ****
        System.out.println("main <<< search(\"orange\"): " + trie.search("orange"));   
    }
}