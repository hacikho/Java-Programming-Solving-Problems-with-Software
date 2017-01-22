
/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.*;
import java.lang.*;

public class Part4 {
    public String testReadUrlString()
    {
        URLResource page = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        String source = page.asString();
        String result = "";
        
        System.out.println("START!!!!!!!!!!!!!!!!!!");
        
        for(int start = 1; start < source.length(); start ++)
        {
            
            int index = source.indexOf("http", start);
            if (index == -1) {
                break;
            }

            int firstQuote = index; // 找到 第一個  "
            int endQuote = source.indexOf("\"", firstQuote);
            
            String sub = source.substring(firstQuote, endQuote);
            sub = sub.toLowerCase();

            int withYoutube = sub.indexOf("youtube.com");
            
            if (withYoutube > -1) {
                System.out.println("sub :::; " + sub);
                System.out.println("\n");
                result = result +" \n " +sub;
            }
            start = endQuote + 1;
        }
        
        System.out.println("END!!!!!!!!!!!!!!!!!!");
        return result;
    }   

    public void testReadUrl(){
        URLResource page = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        //String source = page.asString();
        //System.out.println(source);
        testReadUrlString();
   
    }
}


