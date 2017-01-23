
/**
 * Write a description of AllCodonsAnd here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AllCodonsAnd {
    
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
    
    public String findGene(String dna){
        int startIndex = dna.indexOf("ATG");
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

    public void testFindStop(){
                    //01234567890123456789012345
        String dna = "xxxyyyzzzTAAxxxyyyzzzTAAxx";
        int dex = findStopCodon(dna, 0 , "TAA");
        if(dex != 9 ) System.out.println("error on 9");
        dex  = findStopCodon(dna, 9 , "TAA");
        if(dex != 21 ) System.out.println("error on 21");
        dex  = findStopCodon(dna, 1 , "TAA");
        if(dex != 26 ) System.out.println("error on 26");
        dex  = findStopCodon(dna, 0 , "TAG");
        if(dex != 26 ) System.out.println("error on 26 TAG");
        System.out.println("test finished");
        
        System.out.println(findGene("AAATGCCCTAACTAGATTAAGAAACC"));
    }
    
    public void testFindGene(){
        String dna = "ATGCCCGGGAAATAACCC";
        String gene  = findGene(dna);
        if(! gene.equals("ATGCCCGGGAAATAA")){
            System.out.println("Error");
        }
        System.out.println("test finished");
        
        dna = "ATGCCCGGGAAATGACCC";
        gene  = findGene(dna);
        if(! gene.equals("ATGCCCGGGAAATGA")){
            System.out.println("Error");
        }
        System.out.println("test finished");
        
        dna = "ATGCCCGGGAAATAGCCC";
        gene  = findGene(dna);
        if(! gene.equals("ATGCCCGGGAAATAG")){
            System.out.println("Error");
        }
        System.out.println("test finished");
    }
    
    
}

