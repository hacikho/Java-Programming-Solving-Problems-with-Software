
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.regex.Pattern;
import java.util.regex.Matcher;
public class Part3 {
    public boolean twoOccurrences(String a, String b){
        
        Pattern p = Pattern.compile(a);
        Matcher m = p.matcher(b);
        int count = 0;
        while (m.find()){
            count +=1;
        }
        if(count>=2){
            return true;
        }else{
            return false;
        }
        
    }
    
    public void lastPart(String a, String b){
        int startIndex = b.indexOf(a);
        int len = a.length();
        if(startIndex == -1){
            System.out.println("The part of the string after " + a + " in " + b + " is " + b);
        }else{
            System.out.println("The part of the string after " + a + " in " + b + " is " + b.substring(len+startIndex));
        }
    }
    
    public void test(){
        System.out.println(twoOccurrences("by", "A story by Abby Long"));
        lastPart("an", "banana");
        lastPart("cok", "bu cocuk cok konusuyor");
        lastPart("zoo", "forest");
    }

}
