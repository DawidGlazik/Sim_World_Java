package animals;
import plants.Field;
import world.*;

public class Wolf extends Animal{

	public Wolf() {
		this.strength = 9;
		this.initiative = 5;
		this.age = 0;
	}
	
	public Wolf(World world, Coords xy) {
		this.world = world;
		this.xy = xy;
		this.strength = 9;
		this.initiative = 5;
		this.age = 0;
	}
	public Wolf(World world, Coords xy, int strength, int age) {
		this.world = world;
		this.xy = xy;
		this.strength = strength;
		this.initiative = 5;
		this.age = age;
	}
	
	@Override
	protected void birth(int x1, int y1) {
		if (this.world.board[this.xy.getX()+x1][this.xy.getY()+y1] instanceof Field) {
			String comment = "New birth: " + this.getName() + "(" + (this.xy.getX()+x1+1) + "," + (this.xy.getY()+y1+1) + ")";
			this.world.log(comment);
			this.world.addOrganism(new Wolf(this.world, new Coords(this.xy.getX()+x1,this.xy.getY()+y1)));
		}
	}
	
	@Override
	public String getName() {
		return "Wolf";
	}
}
