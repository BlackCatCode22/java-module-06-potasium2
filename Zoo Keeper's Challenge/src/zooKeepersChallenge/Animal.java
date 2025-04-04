package zooKeepersChallenge;
import java.util.Random;

abstract class Animal {
	// Create a static int that keep track of the number of animals created.
	static int numOfAnimals = 0;
	
	public Animal(String sex, int age, int weight, String animalName, String animalBirthDate, String animalColor, String animalOrigin, String animalArrivalDate) {
		this.sex = sex;
		this.age = age;
		this.weight = weight;
		this.animalName = animalName;
		this.animalBirthDate = animalBirthDate;
		this.animalColor = animalColor;
		this.animalOrigin = animalOrigin;
		this.animalArrivalDate = animalArrivalDate;
		
		numOfAnimals++;
	}
	
	// Variables -- Age is in Years, Weight is in Pounds
	private String sex;
	private int age;
	private int weight;
	private String animalName;
	private String animalBirthDate;
	private String animalColor;
	private String animalOrigin;
	private String animalArrivalDate;
	private String animalID;
	
	// Getters
	public String getSex() {
		return sex;
	}
	
	public int getAge() {
		return age;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public String getAnimalName() {
		return animalName;
	}
	
	public String getAnimalBirthDate() {
		return animalBirthDate;
	}
	
	public String getAnimalColor() {
		return animalColor;
	}
	
	public String getAnimalOrigin() {
		return animalOrigin;
	}
	
	public String getAnimalArrivalDate() {
		return animalArrivalDate;
	}
	
	public String getAnimalID() {
		return animalID;
	}
	
	public void setAnimalID(String animalID) {
		this.animalID = animalID;
	}
	
	public abstract String getAnimalSpecies(); // Abstract method so derived Sub-classes must return a species

	@Override
	public String toString() {
		return animalID + "; " + animalName + "; birth date: " + animalBirthDate + "; " + animalColor + " color; " + sex + "; " + weight + " pounds; from " + animalOrigin + "; arrived: " + animalArrivalDate;
	}
}

// Classes that extend the Animal class
class Hyena extends Animal {
	static int numOfHyenas = 0;
	
	public Hyena(String sex, int age, int weight, String animalName, String animalBirthDate, String animalColor, String animalOrigin, String animalArrivalDate) {
		super(sex, age, weight, animalName, animalBirthDate, animalColor, animalOrigin, animalArrivalDate);
		numOfHyenas++;
		
		setAnimalID(String.format("Hy%02d", numOfHyenas));
	}
	
	public String getAnimalSpecies() {
		return "hyena";
	}
}

class Lion extends Animal {
	static int numOfLions = 0;
	
	public Lion(String sex, int age, int weight, String animalName, String animalBirthDate, String animalColor, String animalOrigin, String animalArrivalDate) {
		super(sex, age, weight, animalName, animalBirthDate, animalColor, animalOrigin, animalArrivalDate);
		numOfLions++;
		
		setAnimalID(String.format("Li%02d", numOfLions));
	}
	
	public String getAnimalSpecies() {
		return "lion";
	}
}

class Tiger extends Animal {
	static int numOfTigers = 0;
	
	public Tiger(String sex, int age, int weight, String animalName, String animalBirthDate, String animalColor, String animalOrigin, String animalArrivalDate) {
		super(sex, age, weight, animalName, animalBirthDate, animalColor, animalOrigin, animalArrivalDate);
		numOfTigers++;
		
		setAnimalID(String.format("Ti%02d", numOfTigers));
	}
	
	public String getAnimalSpecies() {
		return "tiger";
	}
}

class Bear extends Animal {
	static int numOfBears = 0;
	
	public Bear(String sex, int age, int weight, String animalName, String animalBirthDate, String animalColor, String animalOrigin, String animalArrivalDate) {
		super(sex, age, weight, animalName, animalBirthDate, animalColor, animalOrigin, animalArrivalDate);
		numOfBears++;
		
		setAnimalID(String.format("Be%02d", numOfBears));
	}
	
	public String getAnimalSpecies() {
		return "bear";
	}
}