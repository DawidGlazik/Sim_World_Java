package world;
import java.io.*;
import java.util.ArrayList;

import window.Window;

import java.util.Random;

import javax.swing.*;

import animals.*;
import plants.*;

public class World {
	private int turn;
	private int width;
	private int height;
	public String console;
	public ArrayList<Organism> organisms;
	public Organism board[][];
	
	public World() {
		this.turn = 0;
		this.console = "Events: ";
	}
	
	public World(int width, int height) {
		this.turn = 0;
		this.console = "Events: ";
		this.width = width;
		this.height = height;
		organisms = new ArrayList<>();
		board = new Organism[height][width];
		for (int i = 0; i < height; i++) {
		    for (int j = 0; j < width; j++) {
		        board[i][j] = new Field(this, new Coords(j,i));
		    }
		}
		Random rand = new Random();
		int random1, random2, random3, numberOfOrganisms, k;
		numberOfOrganisms = width * height / 15;
		Human human = new Human(this, new Coords(height / 2, width / 2));
		addOrganism(human);
		for (int i = 0; i < numberOfOrganisms; i++) {
			random1 = rand.nextInt(this.height);
			random2 = rand.nextInt(this.width);
			random3 = rand.nextInt(1000);
			if (board[random1][random2] instanceof Field) {
				if (i < 10) {
					k = i;
				}
				else {
					k = random3 % 10;
				}
				switch (k)
				{
				case 0:
					addOrganism(new Hogweed(this, new Coords( random1, random2 )));
					break;
				case 1:
					addOrganism(new Guarana(this, new Coords( random1, random2 )));
					break;
				case 2:
					addOrganism(new Milt(this, new Coords( random1, random2 )));
					break;
				case 3:
					addOrganism(new Grass(this, new Coords( random1, random2 )));
					break;
				case 4:
					addOrganism(new DeadlyNightshade(this, new Coords( random1, random2 )));
					break;
				case 5:
					addOrganism(new Antelope(this, new Coords( random1, random2 )));
					break;
				case 6:
					addOrganism(new Fox(this, new Coords( random1, random2 )));
					break;
				case 7:
					addOrganism(new Sheep(this, new Coords( random1, random2 )));
					break;
				case 8:
					addOrganism(new Wolf(this, new Coords( random1, random2 )));
					break;
				case 9:
					addOrganism(new Turtle(this, new Coords( random1, random2 )));
					break;
				default:
					break;
				}
			}
		}
	}
	
	public void startCalopalenie() {
		for (int i=0; i<organisms.size(); i++) {
			if (organisms.get(i) instanceof Human) {
				Human tmp = (Human) organisms.get(i);
				if (tmp.getLasting() == 5) {
					String komentarz = "Human starts calopalenie.";
					this.log(komentarz);
					tmp.calopalenie();
					if (tmp.getBrake() != 5) tmp.addBrake(5);
				}
			}
		}
	}
	
	public void handleTurn(int direction) {
		for (int i = 0; i < organisms.size(); i++) {
			if (organisms.get(i) instanceof Human) {
				Human tmp = (Human) organisms.get(i);
				tmp.action(direction);
				if (tmp.getLasting() < 5 && tmp.getLasting() > 0) {
					tmp.calopalenie();
				}
				else if (tmp.getBrake() > 0) {
					tmp.addBrake(-1);
				}
				else {
					tmp.setLasting(5);
				}
			}
			else if (organisms.get(i) instanceof Hogweed) {
				Hogweed tmp = (Hogweed) organisms.get(i);
				tmp.action();
			}
			else organisms.get(i).action();

		}
		for (int i = 0; i < organisms.size(); i++) {
			organisms.get(i).addAge(1);
		}
		this.turn++;
		System.out.println("Turn:"+turn);
		System.out.println(console);
	}
	
	public void printWorld() {
		Window window = new Window(this, height, width, board);
		window.print();
	}
	
	public void addOrganism(Organism _new) {
		this.board[_new.getCoords().getX()][_new.getCoords().getY()] = _new;
		if (organisms.size() == 0) organisms.add(_new);
		else {
			for (int i=0; i<organisms.size(); i++) {
				if (_new.getIniciative() > organisms.get(i).getIniciative()) {
					organisms.add(i, _new);
				    return;
				}
			}
			organisms.add(_new);
		}
	}
	
	public void printOrganisms() {
		for (int i=0;i<organisms.size();i++) {
			System.out.println(organisms.get(i).getName());
		}
	}
	
	public void log(String console) {
		this.console += console;
		this.console += "; ";
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public int getTurn() {
		return this.turn;
	}
	
	public boolean save() {		
		String file = JOptionPane.showInputDialog(null, "Insert file's name:");
        if (file != null && !file.isEmpty()) {
            return saveToFile(file);
        }
        return false;
	}
	
	private boolean saveToFile(String file) {
		String content = String.valueOf(this.turn) + " " + String.valueOf(this.width) + " " + String.valueOf(this.height) + "\n";
		for (int i=0;i<organisms.size();i++) {
			content += organisms.get(i).getName() + " " + organisms.get(i).getCoords().getX() + " " + organisms.get(i).getCoords().getY() + " " + organisms.get(i).getStrength() + " " + organisms.get(i).getAge() + " ";
			if (organisms.get(i) instanceof Human) {
				Human tmp = (Human) organisms.get(i);
				content += tmp.getLasting() + " " + tmp.getBrake();
			}
			content += "\n";
		}
	    try (FileWriter writer = new FileWriter(file)) {
	        writer.write(content);
	        System.out.println("Simulation saved.");
	        return true;
	    } catch (IOException e) {
	        System.out.println("Error occured: " + e.getMessage());
	        return false;
	    }
	}
	
	public int load() {
		String fileName = JOptionPane.showInputDialog(null, "Insert file's name:");
		File file = new File(fileName);
        if (file.exists() && fileName != null && !fileName.isEmpty()) {
            if (loadFromFile(fileName)) {
            	return 1;
            }else {
            	return 2;
            }
        } else {
        	System.out.println("File doesn't exist.");
        	return 3;
        }
	}
	
	private boolean loadFromFile(String file) {
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String name;
			int x, y, age, strength, brake, lasting;
			board = null;
			organisms.clear();
			
            String line = reader.readLine();
            String[] elements = line.split(" ");
            this.turn = Integer.parseInt(elements[0]);
            this.width = Integer.parseInt(elements[1]);
            this.height = Integer.parseInt(elements[2]);
            board = new Organism[height][width];
            organisms = new ArrayList<>();
            
            for (int i = 0; i < height; i++) {
    		    for (int j = 0; j < width; j++) {
    		        board[i][j] = new Field(this, new Coords(j,i));
    		    }
    		}
            
            while ((line = reader.readLine()) != null) {
            	elements = line.split(" ");
            	name = elements[0];
            	x = Integer.parseInt(elements[1]);
            	y = Integer.parseInt(elements[2]);
            	strength = Integer.parseInt(elements[3]);
            	age = Integer.parseInt(elements[4]);
            	if (name.equals("Barszcz_Sosnowskiego")) {
            		addOrganism(new Hogweed(this, new Coords( x, y ), age));
    			}
    			else if (name.equals("Guarana")) {
    				addOrganism(new Guarana(this, new Coords( x, y ), age));
    			}
    			else if (name.equals("Mlecz")) {
    				addOrganism(new Milt(this, new Coords( x, y ), age));
    			}
    			else if (name.equals("Trawa")) {
    				addOrganism(new Grass(this, new Coords( x, y ), age));
    			}
    			else if (name.equals("Wilcze_Jagody")) {
    				addOrganism(new DeadlyNightshade(this, new Coords( x, y ), age));
    			}
    			else if (name.equals("Antylopa")) {
    				addOrganism(new Antelope(this, new Coords( x, y ), strength, age));
    			}
    			else if (name.equals("Lis")) {
    				addOrganism(new Fox(this, new Coords( x, y ), strength, age));
    			}
    			else if (name.equals("Owca")) {
    				addOrganism(new Sheep(this, new Coords( x, y ), strength, age));
    			}
    			else if (name.equals("Wilk")) {
    				addOrganism(new Wolf(this, new Coords( x, y ), strength, age));
    			}
    			else if (name.equals("Zolw")) {
    				addOrganism(new Turtle(this, new Coords( x, y ), strength, age));
    			}
    			else if (name.equals("Czlowiek")) {
    				lasting = Integer.parseInt(elements[5]);
    				brake = Integer.parseInt(elements[6]);
    				addOrganism(new Human(this, new Coords( x, y ), strength, age, lasting, brake));
    			}
            }
        } catch (IOException e) {
            System.err.println("Błąd podczas wczytywania pliku: " + e.getMessage());
            return false;
        }
		return true;
	}
	
}
