
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class Part2 {

    public String findGeneSimple(String dna, String startCodon, String stopCodon){
        //Start codon is "ATG"
        //Stop codon is "TAA"
        String result = "";
        
            int startIndex = dna.indexOf(startCodon);
            if(startIndex == -1){//no ATG
                result = "";
            }
            int stopIndex = dna.indexOf(stopCodon, startIndex + 3);
            if(stopIndex == -1){//no TAA
                result = "";
            }
            if((stopIndex-startIndex)%3==0){
                result  = dna.substring(startIndex, stopIndex+3);
            }
            return result;
            
    }

    public void testFindGeneSimple(){
        String startCodon = "ATG";
        String stopCodon = "TAA";
        
        String dna = "AATGCGTAATATGGT";
        System.out.println("Dna strand is " + dna );
        String gene = findGeneSimple(dna, startCodon, stopCodon);
        System.out.println("Gene is " + gene);
        
        dna = "AATGCTAGGGTAATATGGT";
        System.out.println("Dna strand is " + dna );
        gene = findGeneSimple(dna, startCodon, stopCodon);
        System.out.println("Gene is " + gene);

        dna = "ATCCTATGCTTCGGCTGCTCTAATATGGT";
        System.out.println("Dna strand is " + dna );
        gene = findGeneSimple(dna, startCodon, stopCodon);
        System.out.println("Gene is " + gene);
    }
}

