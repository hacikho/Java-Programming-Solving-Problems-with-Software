
/**
 * Write a description of FindingGeneSimpleAndTest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class FindingGeneSimpleAndTest {
    public String findGeneSimple(String dna){
        //Start codon is "ATG"
        //Stop codon is "TAA"
        String result = "";
        int startIndex = dna.indexOf("ATG");
        if(startIndex == -1){//no ATG
            result = "";
        }
        int stopIndex = dna.indexOf("TAA", startIndex + 3);
        if(stopIndex == -1){//no TAA
            result = "";
        }
        if((stopIndex-startIndex)%3==0){
            result  = dna.substring(startIndex, stopIndex+3);
        }
        return result;
    }

    public void testFindGeneSimple(){
        String dna = "AAATGCCCTAACTAGATTAAGAAACC";
        System.out.println("Dna strand is " + dna );
        String gene = findGeneSimple(dna);
        System.out.println("Gene is " + gene);
        
        dna = "AATGCTAGGGTAATATGGT";
        System.out.println("Dna strand is " + dna );
        gene = findGeneSimple(dna);
        System.out.println("Gene is " + gene);

        dna = "ATCCTATGCTTCGGCTGCTCTAATATGGT";
        System.out.println("Dna strand is " + dna );
        gene = findGeneSimple(dna);
        System.out.println("Gene is " + gene);
    }
}
