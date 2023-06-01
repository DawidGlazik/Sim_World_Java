package world;
import plants.Field;

public abstract class Organism {
	protected int age, strength, initiative;
	protected Coords xy;
	public World world;
	
	public Organism() {
		this.age = 0;
		this.strength = 0;
		this.initiative = 0;
		this.xy = new Coords(0,0);
	}
	
	public String getXY() {
		return "(" + (this.xy.getX() + 1) + "," + (this.xy.getY()+1) + ")";
	}
	
	public void setX(int x) {
		this.xy.setX(x);
	}
	
	public void setY(int y) {
		this.xy.setY(y);
	}
	
	public int getAge() {
		return this.age;
	}
	
	public int getStrength() {
		return this.strength;
	}
	
	public int getIniciative() {
		return this.initiative;
	}
	
	public Coords getCoords() {
		return this.xy;
	}
	
	public void addStrength(int strength) {
		this.strength += strength;
	}
	
	public void addAge(int age) {
		this.age += age;
	}
	
	public void collision(Organism org) {
		if (org.strength > this.strength) {
			String comment = this.getName() + " attacks atronger: " + org.getName() + " and dies.";
			this.world.log(comment);
			this.world.board[this.xy.getX()][this.xy.getY()] = new Field();
		}
	}
	
	abstract public void action();
	
	abstract public void draw();
	
	abstract public String getName();

}
