package wordemit;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.Math.abs;
import static java.lang.Math.exp;
import static java.lang.Math.floor;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Stores the list of words in the order of their occurrence.
 * @author harokias
 */
public class WordList {
    
    HashMap<Long, String> word_rank_list;
    HashMap<Long, Long> profile;
    long total_words = 0; 
    long modulo_number = 0;
    private long scaling_factor;
    Random rand_generator ;
    boolean do_profile = false;
   

    /**
     * Creates a new Object for the WordList class.
     */
    public WordList() {
        word_rank_list = new HashMap<Long, String>();
        rand_generator = new Random();
        profile = new HashMap<Long, Long>();
    }
    
    /**
     * Given a filename and length of the words,the method loads all the methods
     * into the WordList Object.
     * The file should have one word per line where a word followed by the 
     * frequency of occurrence of the word followed by a space.
     * @param filename name of the file to be read and loaded into the word List.
     * @param word_length length of the words in the file.
     * @throws java.io.IOException
     */
    public void load_word_list(String filename, int word_length) throws IOException {
        BufferedReader br;
        br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(filename)));
        String br_string;
        String[] word_split;
        while((br_string = br.readLine()) != null) {
            word_split = br_string.split("\\s");
            word_rank_list.put(++total_words, word_split[0]);
        }
        br.close();
        // Get the maximum possible area and then increment by one
        // so that the modulo is always within the possible area range
        scaling_factor = 100*total_words;
        modulo_number = (long)Math.ceil(scaling_factor * Math.log(total_words))+1;
    }
    
    /**
     * prints the entire content of the wordlist with their rank.
     */
    public void print_word_list() {
        for(int i=1; i <= total_words; i++) {
            System.out.println(i+" : "+word_rank_list.get(i));
        }
    }
    
    /**
     * prints the total number of words in the word list.
     */
    public void print_word_count() {
        System.out.println("Total Words: "+total_words+" Modulo: "+modulo_number);       
    }
    
        
    public String get_next_word() {
        return word_rank_list.get(get_random_number());
    }
    
    public long get_random_number() {
        long r_num = abs(rand_generator.nextLong());
        double p_num = (r_num%modulo_number)/(double)scaling_factor;
        //System.out.println("p_num "+ p_num);
        double inverse_log = exp(p_num);
        long random = (long)floor(inverse_log);
        if (do_profile)
            add_to_profile(random);
        return random;
    }

    private void add_to_profile(long random) {
       if (profile.containsKey(random))
           profile.put(random, profile.get(random)+1);
       else
           profile.put(random, (long)1);
    }
    
    public void print_profile() {
        if(!do_profile)
            return;
        for(long i=1; i <= total_words; i++) {
            if(profile.containsKey(i))
               System.out.println(i+" : "+profile.get(i));
        }
    }
    
}
