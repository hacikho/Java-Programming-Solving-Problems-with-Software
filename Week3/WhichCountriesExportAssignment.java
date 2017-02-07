
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
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
        for(CSVRecord record: parser){
            String export = record.get("Exports");
            if(export.contains(exportItem1) && export.contains(exportItem2)){
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem){
        int counter = 0;
        for(CSVRecord record: parser){
            String export = record.get("Exports");
            if(export.contains(exportItem)){
                counter +=1;
            }
        }
        return counter;
    }
    
    public void bigExporters(CSVParser parser, String dollarAmount){
        for(CSVRecord record:parser){
            //String country = record.get("Country");
            if(record.get("Value (dollars)").length()>dollarAmount.length()){
                System.out.println(record.get("Country") + " " + record.get("Value (dollars)"));
                
            }
        }
    }
    
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        String a =countryInfo(parser, "Nauru");
        System.out.println(a);
        
        parser = fr.getCSVParser();
        listExportersTwoProducts(parser, "gold", "diamonds");
        
        parser = fr.getCSVParser();
        System.out.println("# of countries export gold : " + numberOfExporters(parser, "gold"));
        
        parser = fr.getCSVParser();
        bigExporters(parser, "$999,999,999,999");
        
    }
}

