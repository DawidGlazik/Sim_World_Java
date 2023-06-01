package animals;
import plants.Field;
import world.*;

public class Fox extends Animal{

	public Fox() {
		this.strength = 3;
		this.initiative = 7;
		this.age = 0;
	}
	
	public Fox(World world, Coords xy) {
		this.world = world;
		this.xy = xy;
		this.strength = 3;
		this.initiative = 7;
		this.age = 0;
	}
	public Fox(World world, Coords xy, int strength, int age) {
		this.world = world;
		this.xy = xy;
		this.strength = strength;
		this.initiative = 7;
		this.age = age;
	}
	
	@Override
	protected void movement(int x1, int y1) {
		if (this.world.board[this.xy.getX()+x1][this.xy.getY()+y1] instanceof Field) {
			this.world.board[this.xy.getX()+x1][this.xy.getY()+y1] = this.world.board[this.xy.getX()][this.xy.getY()];
			this.world.board[this.xy.getX()][this.xy.getY()] = new Field();
			this.xy.setX(this.xy.getX() + x1);
			this.xy.setY(this.xy.getY() + y1);
		}
		else {
			if (this.world.board[this.xy.getX()+x1][this.xy.getY()+y1].getStrength() > this.getStrength()) return;
			else this.world.board[this.xy.getX()+x1][this.xy.getY()+y1].collision(this);
		}
	}
	
	@Override
	protected void birth(int x1, int y1) {
		if (this.world.board[this.xy.getX()+x1][this.xy.getY()+y1] instanceof Field) {
			String komentarz = "New birth: " + this.getName() + "(" + (this.xy.getX()+x1+1) + "," + (this.xy.getY()+y1+1) + ")";
			this.world.log(komentarz);
			this.world.addOrganism(new Fox(this.world, new Coords(this.xy.getX()+x1,this.xy.getY()+y1)));
		}
	}
	
	@Override
	public String getName() {
		return "Fox";
	}
}
