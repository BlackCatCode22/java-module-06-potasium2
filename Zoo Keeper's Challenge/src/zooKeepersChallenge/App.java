package zooKeepersChallenge;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import zooKeepersChallenge.Animal;
import zooKeepersChallenge.Hyena;

public class App {
    public static void main(String[] args) {
        String filePath = "D:\\Homework\\College - 3rd Year\\CIT-63\\java-module-06-potasium2\\Zoo Keeper's Challenge\\arrivingAnimals.txt";
        ArrayList<Animal> animals = new ArrayList<>();
        HashMap<String, Integer> speciesCount = new HashMap<String, Integer>();
        
        processAnimals(filePath, animals, speciesCount);
        genReport(animals, speciesCount);
    }
    
    private static void processAnimals(String filePath, ArrayList<Animal> animals, HashMap<String, Integer> speciesCount) {
        // Open external file
        File file = new File(filePath);

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(", ");
                
                int age = Integer.parseInt(parts[0].trim().split(" ")[0]);
                String sex = parts[0].trim().split(" ")[3];
                
                String birthPart = parts[1].trim();
                String birthSeason = "";
                if (!birthPart.contains("unknown"))
                	birthSeason = birthPart.toLowerCase().replace("born in", "").trim();
                
                String color = parts[2].trim().toLowerCase().replace("color", "").trim();
                int weight = Integer.parseInt(parts[3].trim().split(" ")[0]);
                String origin = parts[4].trim().replace("from", "").trim();
                origin += ", " + parts[5];
                
                
                
                String species = parts[0].trim().split(" ")[4].toLowerCase();
                switch (species) {
                	case "hyena":
                		animals.add(new Hyena(sex, age, weight, genAnimalName(species), genBirthDay(age, birthSeason), color, origin, genAnimalArrival(age)));
                		break;
                	case "lion":
                		animals.add(new Lion(sex, age, weight, genAnimalName(species), genBirthDay(age, birthSeason), color, origin, genAnimalArrival(age)));
                		break;
                	case "tiger":
                		animals.add(new Tiger(sex, age, weight, genAnimalName(species), genBirthDay(age, birthSeason), color, origin, genAnimalArrival(age)));
                		break;
                	case "bear":
                		animals.add(new Bear(sex, age, weight, genAnimalName(species), genBirthDay(age, birthSeason), color, origin, genAnimalArrival(age)));
                		break;
                	default:
                		System.out.println("Animal species is unsupported.");
                		break;
                }
                
                speciesCount.put(species, speciesCount.getOrDefault(species, 0) + 1);
                
                System.out.println("\nNumber of animals is: " + animals.size());
                
                for (Animal animal : animals)
                	System.out.println(animal.toString());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
            e.printStackTrace();
        }
    }
    
    private static void genReport(ArrayList<Animal> animals, HashMap<String, Integer> speciesCount) {
    	String outputFile = "D:\\Homework\\College - 3rd Year\\CIT-63\\java-module-06-potasium2\\Zoo Keeper's Challenge\\newAnimals.txt";
    	try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
    		for (String species : speciesCount.keySet()) {
    			writer.write(species + " Habitat:\n");
    			
    			for (Animal animal : animals) {
    				if (animal.getAnimalSpecies().equals(species))
    					writer.write(animal.toString() + "\n");
    			}
    			writer.write("\n");
    		}
    	} catch (IOException e) {
            e.printStackTrace();
    	}
    }
    
    // Method to generate an Animal Name dependent on the species
    private static String genAnimalName(String species) {
    	String filePath = "D:\\Homework\\College - 3rd Year\\CIT-63\\java-module-06-potasium2\\Zoo Keeper's Challenge\\animalNames.txt";
    	File file = new File(filePath);
    	
    	List<String> names = new ArrayList<>();
    	boolean foundSpecies = false;
    	
    	try (Scanner scanner = new Scanner(file)) {
    		while (scanner.hasNextLine()) {
    			String line = scanner.nextLine();
    			
    			if (line.equals(species + " Names:")) {
    				foundSpecies = true;
    				continue;
    			}
    			if (foundSpecies) {
    				if (line.isEmpty())
    					continue;
    				
    				String[] speciesNames = line.split(", ");
    				for (String name : speciesNames) {
    					names.add(name.trim());
    				}
    			}
    		}
    	} catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
            e.printStackTrace();
    	}
    	
    	Random rand = new Random();
    	return names.get(rand.nextInt(names.size()));
    }
    
    // Method to generate Birthday based on Season and Year
 	public static String genBirthDay(int age, String birthSeason) {
 		Random rand = new Random();
 		
 		int currentYear = 2025;
 		int birthYear = currentYear - age;
 		int birthDay;
 		int birthMonth;
 		
 		// Generate a random Month dependent on the season.
 		switch (birthSeason.toLowerCase()) {
 			case "spring":
 				birthMonth = rand.nextInt(1, 3);
 				break;
 			case "summer":
 				birthMonth = rand.nextInt(4, 6);
 				break;
 			case "fall":
 				birthMonth = rand.nextInt(7, 9);
 				break;
 			case "winter":
 				birthMonth = rand.nextInt(10, 12);
 				break;
 			default:
 				birthMonth = 1;
 				break;
 		}
 		
 		// Generate a random day dependent on the month
 		// Even months up until August have 30 days
 		// Odd months up until September have 31 days
 		if (birthMonth == 2)
 			birthDay = rand.nextInt(1, 28);
 		else if (birthMonth < 8) {
 			if (birthMonth % 2 == 0)
 				birthDay = rand.nextInt(1, 30);
 			else
 				birthDay = rand.nextInt(1, 31);
 		}
 		else if (birthMonth > 7) {
 			if (birthMonth % 2 == 0)
 				birthDay = rand.nextInt(1, 31);
 			else
 				birthDay = rand.nextInt(1, 30);
 		}
 		else
 			birthDay = 1;
 		
 		// This might look like a lot of code to generate a birthday but that's only because it is.
 		String finalBirthDay = String.valueOf(birthYear);
 		if (birthMonth < 10)
 			finalBirthDay += "-0" + String.valueOf(birthMonth);
 		else
 			finalBirthDay += "-" + String.valueOf(birthMonth);
 			
 		if (birthDay < 10)
 			finalBirthDay += "-0" + String.valueOf(birthDay);
 		else
 			finalBirthDay += "-" + String.valueOf(birthDay);
 			
 		return finalBirthDay; // Returns YYYY-MM-DD
 	}
 	
 	// Method to generate arrival dates (effectively the same as genBirthDay() just more random)
 	public static String genAnimalArrival(int age) {
 		Random rand = new Random();
 		
 		int currentYear = 2025;
 		int minimumYear = currentYear - age + 1;
 		
 		int arrivalYear = rand.nextInt(minimumYear, currentYear);
 		int arrivalMonth = rand.nextInt(1, 12);
 		int arrivalDay;
 		
 		if (arrivalMonth == 2)
 			arrivalDay = rand.nextInt(1, 28);
 		else if (arrivalMonth < 8) {
 			if (arrivalMonth % 2 == 0)
 				arrivalDay = rand.nextInt(1, 30);
 			else
 				arrivalDay = rand.nextInt(1, 31);
 		}
 		else if (arrivalMonth > 7) {
 			if (arrivalMonth % 2 == 0)
 				arrivalDay = rand.nextInt(1, 31);
 			else
 				arrivalDay = rand.nextInt(1, 30);
 		}
 		else
 			arrivalDay = 1;
 		
 		String arrivalDate = String.valueOf(arrivalYear);
 		if (arrivalMonth < 10)
 			arrivalDate += "-0" + String.valueOf(arrivalMonth);
 		else
 			arrivalDate += "-" + String.valueOf(arrivalMonth);
 			
 		if (arrivalDay < 10)
 			arrivalDate += "-0" + String.valueOf(arrivalDay);
 		else
 			arrivalDate += "-" + String.valueOf(arrivalDay);
 		
 		return arrivalDate;
 	}
}