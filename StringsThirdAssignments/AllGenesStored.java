
/**
 * Write a description of AllGenesStored here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;
import java.io.*;
public class AllGenesStored {
    public int findStopCodon(String dnaStr, int startIndex, String stopCodon){
        int currIndex = dnaStr.indexOf(stopCodon, startIndex + 3);
        while(currIndex!=-1){
            int diff = currIndex - startIndex;
            if(diff % 3 == 0){
                return currIndex;
            }else{
                currIndex = dnaStr.indexOf(stopCodon, currIndex + 1 );
            }
        }
        return -1;
    }
    
    public String findGene(String dna, int where){
        int startIndex = dna.indexOf("ATG", where);
        if(startIndex == -1){
            return "";
        }
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        int minIndex = 0;
        if(taaIndex == -1 || (tgaIndex != -1 && tgaIndex < taaIndex)){
            minIndex = tgaIndex;
        }else{
            minIndex = taaIndex;
        }
        
        if(minIndex == -1 || (tagIndex != -1 && tagIndex < minIndex)){
            minIndex = tagIndex;
        }
        
        if(minIndex == -1){
            return "";
        }
        
        return dna.substring(startIndex, minIndex+3);
    }
 
     public void printAllGenes(String dna1){
       
        int startIndex = 0;
        
        while(true){
            String currentGene = findGene(dna1, startIndex);
            if(currentGene.isEmpty()){
                break;
            }
          
            System.out.println(currentGene);
            startIndex = dna1.indexOf(currentGene, startIndex) + currentGene.length();
        }
        
      
    }
    
    public void testOn(String dna){
        //System.out.println("Testing printAllGenes on " + dna);
         printAllGenes(dna);
         //
        //for(String g: genes.data()){
         //   System.out.println(g);
        //}
    }
    
    public void test(){
      FileResource fr = new FileResource("brca1line.fa");
      String dna = fr.asString();

        //System.out.println(dna); 
        printAllGenes(dna.toString());
        //testOn(dna);
        //printAllGenes("ATGCTGATCATAAGAAGATAATAGAGGGCCATGTAA");
        //testOn("ATGCTGATCATAAGAAGATAATAGAGGGCCATGTAA");

       
    }
 
    
}





