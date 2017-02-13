
/**
 * Write a description of ParsingWeatherDataAssignment here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class ParsingWeatherDataAssignment {
    
    //Step 1
    public CSVRecord coldestHourInFile(CSVParser parser){
        //start with largestSoFar as nothing
        CSVRecord smallestSoFar = null;
        
        for(CSVRecord currentRow: parser){
           smallestSoFar = getSmallestOfTwo(currentRow, smallestSoFar);
        }
        return smallestSoFar;
    }
    
    public void testColdestHourInFile(){
        FileResource fr = new FileResource("nc_weather/2015/weather-2015-01-01.csv");
        CSVRecord largest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature was " + largest.get("TemperatureF") + " at "
                                                + largest.get("TimeEST"));
    }
    //end of step 1
     public CSVRecord getSmallestOfTwo(CSVRecord currentRow, CSVRecord smallestSoFar){
        if(smallestSoFar == null){
            smallestSoFar = currentRow;
        }else{
            Double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            Double smallestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
            if(currentTemp < smallestTemp && currentTemp != -9999){
                smallestSoFar = currentRow;
            }
        }
        return smallestSoFar;
    }
    
    //step 2
    public  String fileWithColdestTemperature(){
        CSVRecord coldestRecord = null;
        DirectoryResource dr = new DirectoryResource();
        String filename = "";

        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord record = coldestHourInFile(fr.getCSVParser());
            double recordTemp = Double.parseDouble(record.get("TemperatureF"));
            if (recordTemp == -9999) {
                continue;
            }

            if (coldestRecord == null) {
                coldestRecord = record;
                filename = f.getAbsolutePath();
            } else {
                double coldestTemp = Double.parseDouble(coldestRecord.get("TemperatureF"));
                if (recordTemp < coldestTemp) {
                    coldestRecord = record;
                    filename = f.getAbsolutePath();
                }
            }
        }
        return filename; 
    }
    
    public  void testFileWithColdestTemperature() {
		String filename = fileWithColdestTemperature();
		FileResource fr = new FileResource(filename);
		CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
		System.out.println("Coldest day was in file "+ filename);
		System.out.println("Coldest temperature on that day was " + smallest.get("TemperatureF"));
		System.out.println("All the Temperature on the coldest day were:");
		
		for (CSVRecord currentRow : fr.getCSVParser()) {
            // use method to compare two records
            System.out.println(currentRow.get("DateUTC") + ": " + currentRow.get("TemperatureF") );;
        }
	}
    
	
	//Step 3 
	public CSVRecord lowestHumidityInFile(CSVParser parser){
	  CSVRecord lowestHumiditySoFar = null;
	  for(CSVRecord currentRow: parser){
	      if(lowestHumiditySoFar == null){
	          lowestHumiditySoFar = currentRow;
	      }else{
	          if(currentRow.get("Humidity")!= "N/A"){
	              int currentHumidity = Integer.parseInt(currentRow.get("Humidity"));
	              int lowestHumidity = Integer.parseInt(lowestHumiditySoFar.get("Humidity"));
	          
	              if(currentHumidity < lowestHumidity){
	                  lowestHumiditySoFar= currentRow;
	               }   
	          }
	  }
     }
	  return lowestHumiditySoFar;
	}  
   
	
   public void testLowestHumidityInFile(){
       FileResource fr = new FileResource();
       CSVParser parser = fr.getCSVParser();
       CSVRecord csv = lowestHumidityInFile(parser);
       System.out.println("Lowest Humidity  was " + csv.get("Humidity") + " at "
                                                + csv.get("DateUTC"));
   }
   
   
   public CSVRecord lowestHumidityInManyFiles(){
       CSVRecord lowestHumiditySoFar = null;
       DirectoryResource dr = new DirectoryResource();
       
       //iterate over files
       for(File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            //use method to get the largest in file
            //CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
            for(CSVRecord currentRow: fr.getCSVParser()){
                if(lowestHumiditySoFar == null){
                    lowestHumiditySoFar = currentRow;
	            }else{
	                if(currentRow.get("Humidity").equals("N/A")){
	                   
	                }else{
	                    int currentHumidity = Integer.parseInt(currentRow.get("Humidity"));
	                    int lowestHumidity = Integer.parseInt(lowestHumiditySoFar.get("Humidity"));
	          
	                    if(currentHumidity < lowestHumidity){
	                        lowestHumiditySoFar= currentRow;
	                    }    
	                }
	            }
	        }
       }
       return lowestHumiditySoFar;
   }
   
   public void testLowestHumidityInManyFiles(){
       CSVRecord lowest = lowestHumidityInManyFiles();
       System.out.println("Lowest Humidity  was " + lowest.get("Humidity") + " at "
                                                + lowest.get("DateUTC"));
   }
   
   public double averageTemperatureInFile(CSVParser parser){
       double sum = 0.0;
       int counter = 0;
       for(CSVRecord currentRow: parser){
           sum += Double.parseDouble(currentRow.get("TemperatureF"));
           counter += 1;
       }
        return sum/counter;
   }
   
   public void testAverageTemperatureInFile(){
       FileResource fr = new FileResource();
       CSVParser parser = fr.getCSVParser();
       double average = averageTemperatureInFile(parser);
       System.out.println("Average temperature in file " + average);
   }
   
   public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int humidity){
       double sum = 0.0;
       int counter = 0;
       for(CSVRecord currentRow: parser){
           if(Integer.parseInt(currentRow.get("Humidity")) >= humidity){
               sum += Double.parseDouble(currentRow.get("TemperatureF"));
               counter += 1;
           }
       }
        return sum/counter;
   }
   
   public void testAverageTemperatureWithHighHumidityInFile(){
       FileResource fr = new FileResource();
       CSVParser parser = fr.getCSVParser();
       double average = averageTemperatureWithHighHumidityInFile(parser, 80);
       System.out.println("Average temperature in file with Humidity " + average);
   }
    /*
    public CSVRecord hottestInManyDays(){
        CSVRecord largestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        
        //iterate over files
        for(File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            //use method to get the largest in file
            CSVRecord currentRow = hottestHourInFile(fr.getCSVParser());
            largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
        }
        return largestSoFar;
    }
    */
   
    /*
    public void testHottestInManyDays(){
        CSVRecord largest = hottestInManyDays();
        System.out.println("hottest temperature was " + largest.get("TemperatureF") + " at "
                                                       + largest.get("DateUTC"));
    }
    */
}

