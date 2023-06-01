package plants;
import world.*;

public class Grass extends Plant{

	public Grass() {
		this.strength = 0;
		this.age = 0;
	}
	
	public Grass(World world, Coords xy) {
		this.world = world;
		this.xy = xy;
		this.strength = 0;
		this.age = 0;
	}
	public Grass(World world, Coords xy, int age) {
		this.world = world;
		this.xy = xy;
		this.strength = 0;
		this.age = age;
	}
	
	protected void checkAndSeed(int x1, int y1) {
		String comment;
		if (this.world.board[this.xy.getX()+x1][this.xy.getY()+y1] instanceof Field) {
			comment = "Seed: " + this.getName() + "(" + (this.xy.getX() + x1 + 1) + "," + (this.xy.getY()+y1+1) + ")";
			this.world.log(comment);
			this.world.addOrganism(new Grass(this.world, new Coords(this.xy.getX()+x1, this.xy.getY()+y1)));
		}
	}
	
	@Override
	public String getName() {
		return "Grass";
	}
}
