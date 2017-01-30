
/**
 * Write a description of WhichCountriesExportAssignment here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class WhichCountriesExportAssignment {
    public void listExporters(CSVParser parser, String exportOfInterest){
        for(CSVRecord record: parser){
            String export = record.get("Exports");
            if(export.contains(exportOfInterest)){
                String country = record.get("Country");
                System.out.println(country);
            }
        }   
    }
    
    public void whoExportCoffe(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        listExporters(parser, "coffee");
    }
    
    public String countryInfo(CSVParser parser, String country){
        String result = "NOT FOUND";
       for(CSVRecord record:parser){
           if(record.get("Country").contains(country)){
                result = country + " : " + record.get("Exports") + record.get("Value (dollars)");
            }
        }
    
        return result;
    }
    
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        String a =countryInfo(parser, "Germany");
        System.out.println(a);
    }
}

