
import edu.duke.*;
import java.io.File;

public class FindMultipleGenesStorage {
    
    public int findStopIndex(String dna, int index) {
        
        int stop1 = dna.indexOf("tga", index);
        if ( stop1 == -1 || ( stop1-index ) % 3 != 0 ) {
            stop1 = dna.length();
        }
        
        int stop2 = dna.indexOf("taa", index);
        if ( stop2 == -1 || ( stop2-index ) % 3 != 0 ) {
            stop2 = dna.length();
        }
        
        int stop3 = dna.indexOf("tag", index);
        if ( stop3 == -1 || ( stop3-index ) % 3 != 0 ) {
            stop3 = dna.length();
        }
        
        return Math.min( stop1, Math.min(stop2, stop3) );
    }
    
    
    public StorageResource storeAll(String dna) {
        
        String dnaLow = dna.toLowerCase();
        
        int start = 0;
        
        StorageResource genes = new StorageResource();
        
        while (true) {
            
            int loc = dnaLow.indexOf( "atg", start );
            
            if ( loc == -1 ) {
                break;
            }
            
            int stop = findStopIndex( dnaLow, loc+3 );
            
            if ( stop != dna.length() ) {
                genes.add( dna.substring(loc, stop+3) );
                start = stop + 3;
            } else {
                start = start + 3;
            }
               
        }
        
        return genes;
        
    }
    
    public void testStorageFinder() {
        FileResource dnaFile = new FileResource();
        StorageResource genesFound = storeAll( dnaFile.asString() );
        System.out.println( "Number of genes found: "+genesFound.size() );
        printGenes( genesFound );
    }
    
    public float cgRatio( String dna ) {
        String dnaLow = dna.toLowerCase();
        int cgCount = 0;
        int start = 0;
        
        while (true) {
            int pos = dnaLow.indexOf("c", start);
            if (pos == -1) {
                start = 0;
                break;
            }
            cgCount += 1;
            start = pos + 1;
        }
        
        while (true) {
            int pos = dnaLow.indexOf("g", start);
            if (pos == -1) {
                start = 0;
                break;
            }
            cgCount += 1;
            start = pos + 1;
        }
        
        return ( (float) cgCount ) / dna.length();
        
    }
    
    public void printGenes( StorageResource sr ) {
        
        int sixtyCharQty = 0;
        int highCgRatioQty = 0;
        float cgRatioConst = (float) 0.35;
        
        for ( String s : sr.data() ) {
            
             if ( s.length() > 60 ) {
                 System.out.println( "String longer than 60 characters: "+s );
                 sixtyCharQty++;
             }
             
             
             if ( cgRatio(s) > cgRatioConst ) {
                System.out.println( "String with C-G-ratio higher than 0.35: "+s );
                highCgRatioQty++;
             }
             
        }
        
        System.out.println( "60 characters qty: "+sixtyCharQty );
        System.out.println( "Strings with C-G-ratio higher than 0.35: "+highCgRatioQty );
        
    }

}