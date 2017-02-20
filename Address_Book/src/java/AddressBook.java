import java.io.*;
import java.util.*;
import java.io.IOException;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nathasha
 */

//Logic for your address book
public class AddressBook {
    
private static Map<String, List<String>> map;
String fileName;


    public AddressBook(String fileName) throws IOException{
        this.fileName = fileName;
        this.initAddressBook(fileName);
    
    }
    //read from file and create Address Book
    public void initAddressBook(String fileName) throws IOException{
         map = new HashMap<>();
        try{
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        
        
        List<String> arraylist = new ArrayList<>();
        String[] temp = new String[2];
		String[] array;

		String line;

		while((line = br.readLine()) != null){

			temp = line.split(",",2);   //split into two Strings
			array = temp[1].split(",");  //Split second string,
			arraylist = Arrays.asList(array);  //put array into arraylist
			map.put(temp[0],arraylist);
		}

		br.close();
                
                
        }catch (java.io.FileNotFoundException e) {
            System.out.println("The file not found");

	} 
    }
    
    //search details of the requested contact in the address book
    public List<String> search(String name ){
	List<String> list = new ArrayList<>();
        try{
            list = map.get(name);
        }catch(NullPointerException e){
                //.println("There is no person called '"+name+"' in Phone Book!");
        }
        
        return list;
    }
    
    public Map<String, List<String>> addEntry(String name, String details) throws IOException{
                        
                        synchronized(this){
                        List<String> arraylist = new ArrayList<>();
    
                        String[] array = details.split(",");   
		
			arraylist = Arrays.asList(array);  
			map.put(name,arraylist);
                        
                        this.writeFile();
                        }
                        
                        
                        return map;
    
    }
    
    public void writeFile() throws IOException{
    BufferedWriter out = new BufferedWriter(new FileWriter(fileName, false));

			for(String keys : map.keySet()){

				out.write(keys+",");
				out.flush();

				for(String contacts : map.get(keys)){
					out.write(contacts+",");
					out.flush();
				}

				out.write("\n");
				out.flush();

			}

			out.close();
    
    }
}


