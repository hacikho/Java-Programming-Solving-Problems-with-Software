
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
        
            if(record.get("Country") != country){
                String result = record.get(country) + " : " + record.get("Exports") + " : " 
                + record.get("Value(dollars)");
                return  result;
            }else{
                return   "NOT FOUND";
            }
        
        return "";
    }
    
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println(countryInfo(parser, "Germany"));
    }
}

