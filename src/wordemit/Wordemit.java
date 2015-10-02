package wordemit;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author harokias
 */
public class Wordemit {

       WordList wordlist = null;
    /**
     * @param args the command line arguments
     */
    
    
    public Wordemit() {
        wordlist = new WordList();
        try {
            wordlist.load_word_list("/wordemit/count_1w.txt", 1);
        } catch (IOException ex) {
            Logger.getLogger(Wordemit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Wordemit(String wordfile) {
        wordlist = new WordList();
        try {
            wordlist.load_word_list(wordfile, 1);
        } catch (IOException ex) {
            Logger.getLogger(Wordemit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getRandomWord() {
       return wordlist.get_next_word();
    }
    
    public static void main(String[] args) {
        Wordemit emit = new  Wordemit();
        String word = emit.getRandomWord();
        word = emit.getRandomWord();
        
    }
    
    
}
