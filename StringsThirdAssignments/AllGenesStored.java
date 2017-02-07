                            
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
 
     public StorageResource getAllGenes(String dna){
        StorageResource geneList = new StorageResource();
        int startIndex = 0;
        int counter = 0;
        while(true){
            String currentGene = findGene(dna, startIndex);
            if(currentGene.isEmpty()){
                break;
            }
<<<<<<< Updated upstream
            if(currentGene.length()>60){
                counter +=1;
            }
            System.out.println(currentGene);
            startIndex = dna1.indexOf(currentGene, startIndex) + currentGene.length();
        }
        System.out.println("Genes that greater tha 60 : " + counter);
        
=======
            geneList.add(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
        return geneList;
>>>>>>> Stashed changes
      
    }
    
    public void testOn(String dna){
        System.out.println("Testing printAllGenes on " + dna);
        
        StorageResource genes = getAllGenes(dna);
        
        System.out.println(genes.size());
        for(String g: genes.data()){
            System.out.println(g);
        }
    }
    
    public void test(){
<<<<<<< Updated upstream
      FileResource fr = new FileResource("GRch38dnapart.fa");
      String dna = fr.asString();
      dna = dna.toUpperCase();

        //System.out.println(dna); 
        printAllGenes(dna.toString());
      
=======
      
       FileResource fr = new FileResource("brca1line.fa");
      String dna = fr.asString();
      dna = dna.toUpperCase();



        //System.out.println(dna); 
        StorageResource list = getAllGenes(dna);
        System.out.println("list siz" + list.size());
       for(String g: list.data()){
            System.out.println(g);
        }
        
>>>>>>> Stashed changes
        //testOn(dna);
        //testOn("ATGCTGATCATAAGAAGATAATAGAGGGCCATGTAA");
        //testOn("ATGCTGATCATAAGAAGATAATAGAGGGCCATGTAA");

       
    }
 
   
}





