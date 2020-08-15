import java.util.*;

/*
The Question:
There is a broken keyboard in which space gets typed when you type the letter 'e'. Given an input string which is the output from the keyboard. A dictionary of possible words is also provided as an input parameter of the method. Return a list of possible actual input typed by the user.

Example Input: String: "can s r n " Dictionary: ["can", "canes", "serene", "rene", "sam"]
Expected Output: ["can serene", "canes rene"]

Actual Output is:
can serene
canes rene
*/
public class TrieExample {

     public static void main(String []args){

        TrieExample h = new TrieExample();
        h.trieExample();
        
     }
     
     public void trieExample() {
        Trie trie = new Trie();
        trie.add("can");
        // trie.print();
        trie.add("canes");
        // trie.print();
        trie.add("serene");
        // trie.print();
        trie.add("rene");
        trie.add("sam");

        // trie.print();
        // System.out.println(trie.search("can"));
        // System.out.println(trie.search("serene"));
        // System.out.println(trie.search("rene"));
        // System.out.println(trie.search("sachin"));
        // System.out.println(trie.search("sach"));
        
        List<String> result = trie.searchPossibleInputs("can s r n ");
        
         
     }
     
     class TrieNode {
         char c;
         int terminalCount=0;
         TrieNode[] child = null;
         public void print() {
             System.out.println(c+"-"+terminalCount);
             if (child==null) {
                 System.out.println("LEAF NODE");
                 return;
             }
             for (int i=0;i<child.length;i++) {
                 System.out.print(i+"--"+child[i]+"|");
             }
             System.out.println();
         }
         
     }
     class Trie {
         TrieNode root;
         Trie() {
             root = new TrieNode();
             root.c = '*';
         }
         /*
         Example Input: 
         String: "can s r n " 
         Dictionary: ["can", "canes", "serene", "rene", "sam"]
            
            Expected Output: ["can serene", "canes rene"]
         */
         public List<String> searchPossibleInputs(String s) {
            List<String> result = new ArrayList();
            helper(root.child[s.charAt(0) - 'a'], s, result,"", 1);
            for(String ss:result) System.out.println(ss);
            return result;
         }
        public void helper(TrieNode node, String s, List<String> result, String str, int index) {
            if(node==null) return;
            
            if (index == s.length()) {
                str+=Character.toString(node.c);
                result.add(str);
                return;
            }
            str+=Character.toString(node.c);
            char chr = s.charAt(index);
            
            if(chr == ' ' ) {
                if (node.terminalCount > 0 && index < s.length()) {
                    helper(root.child[s.charAt(index+1) - 'a'], s, result, str+Character.toString(' '), index+2);
                }
                for (int i=0;i<node.child.length;i++) {
                    if (node.child[i] != null) {
                        helper(node.child[i], s, result, str, index+1);    
                    }
                }
            } else {
                helper(node.child[chr-'a'], s, result, str, index+1);

            }
         }
         public void add(String s) {
            TrieNode temp;
            temp = root;
            int i=0;
            char[] chars = s.toCharArray();
            int stringLength = chars.length;
            if (temp.child==null) {
                temp.child = new TrieNode[26];
            } else {
                while(i<stringLength) {
                    if (temp.child[chars[i]-'a'] != null) {
                        temp = temp.child[chars[i]-'a'];
                    } else {
                        break;
                    }
                    i++;
                }
            }
            if (temp==null) {
                temp.child = new TrieNode[26];
            }
            TrieNode node;
            while(i<stringLength) {
                node = new TrieNode();
                node.c = chars[i];
                node.child = new TrieNode[26];
                temp.child[chars[i]-'a'] = node;
                temp = node;
                i++;
            }
            temp.terminalCount++;
         }
        public boolean search(String s) {
            TrieNode temp = root;
            for (char chr:s.toCharArray()) {
                // System.out.println(chr+"--"+temp.c+"--"+temp.child[chr-'a']);
                if(temp.child[chr-'a'] == null) return false;
                temp = temp.child[chr-'a'];
            }
            System.out.println(temp.terminalCount);
            if (temp.terminalCount>0) return true;
            else return false;
             
        }
         
        public void print() {
            TrieNode node = root;
            while(node!=null) {
                node.print();
                if(node.child == null) break;
                TrieNode[] children = node.child;
                node = null;
                for(TrieNode i:children) {
                    if(i!=null) {
                        node=i;
                        break;
                    }
                }
            }
        }
     }
}
