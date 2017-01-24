
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class Part1 {
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
        int startIndex = 0;
        StorageResource geneList  = new StorageResource();
        while(true){
            String currentGene = findGene(dna, startIndex);
            if(currentGene.isEmpty()){
                break;
            }
            geneList.add(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
        return geneList;
    }
 
    public float cgRatio(String dna){
        //dna = dna.toLowerCase();
        int counter = 0;
        System.out.println("length of the dna : " + dna.length());
        for(int i=0; i<dna.length(); i++){
            if(dna.charAt(i) == 'C' || dna.charAt(i) == 'G'){
                counter += 1;
            }
        }
        System.out.println("C or G in dna: " + counter);
        float ratio = (float)counter/dna.length();
        return ratio;
    }
    
    public int countCTG(String dna){
        int counter = 0;
        for(int i = 0 ; i<dna.length(); i+=3){
            if(dna.substring(i, i+3).equals("CTG")){
                counter += 1;
            }
        }
        return counter;
    }
    
    public void testOn(String dna){
        //System.out.println("Testing printAllGenes on " + dna);
        StorageResource genes = getAllGenes(dna);
        for(String g: genes.data()){
            System.out.println(g);
        }
    }
    
    public void processGene(StorageResource sr){
        for(String g: sr.data()){
            if(g.length()>9){
                System.out.println("Greater than 9 char " + g);
            }
        }
    }
    
    public void test(){
        FileResource fr = new FileResource();
        String dna = fr.asString();
        testOn(dna);
        //testOn("");
        //testOn("ATGATCATAAGAAGATAATAGAGGGCCATGTAA");
        //System.out.println(cgRatio("ATGATCATAAGAAGATAATAGAGGGCCATGTAA"));
        //System.out.println(countCTG("ATGCTGATCATAAGAAGATAATAGAGGGCCATGTAA"));
        //System.out.println(getAllGenes("ATGCTGATCATAAGAAGATAATAGAGGGCCATGTAA"));
        
       
        
        //System.out.println(dna);
        
       // StorageResource allGenesList = getAllGenes(dna);
       /*
        for(String g: getAllGenes(dna).data()){
            System.out.println(g);
        }
        System.out.println("done");
        /*StorageResource allGenesList = getAllGenes(dna);
        //List String
        for(String g: allGenesList.data()){
            System.out.println(g);
        }
        */
   
       /*
        for(String g: getAllGenes(dna)){
            System.out.println(g);
        }
        */
    }
}



