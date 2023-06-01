package plants;
import world.*;

public class DeadlyNightshade extends Plant{

	public DeadlyNightshade() {
		this.strength = 99;
		this.age = 0;
	}
	
	public DeadlyNightshade(World world, Coords xy) {
		this.world = world;
		this.xy = xy;
		this.strength = 99;
		this.age = 0;
	}
	public DeadlyNightshade(World world, Coords xy, int age) {
		this.world = world;
		this.xy = xy;
		this.strength = 99;
		this.age = age;
	}
	
	protected void checkAndSeed(int x1, int y1) {
		String comment;
		if (this.world.board[this.xy.getX()+x1][this.xy.getY()+y1] instanceof Field) {
			comment = "Seed: " + this.getName() + "(" + (this.xy.getX() + x1 + 1) + "," + (this.xy.getY()+y1+1) + ")";
			this.world.log(comment);
			this.world.addOrganism(new DeadlyNightshade(this.world, new Coords(this.xy.getX()+x1, this.xy.getY()+y1)));
		}
	}
	
	@Override
	public void collision(Organism org) {
		String comment = org.getName() + org.getXY() + " eats " + this.getName() + this.getXY() + " and dies.";
		this.world.log(comment);
		world.board[this.xy.getX()][this.xy.getY()] = new Field();
		world.board[org.getCoords().getX()][org.getCoords().getY()] = new Field();
		for (int i = 0; i < this.world.organisms.size(); i++) {
			if (this.world.organisms.get(i).getCoords().getX() == this.xy.getX() && this.world.organisms.get(i).getCoords().getY() == this.xy.getY()) {
				this.world.organisms.remove(i);
			}
			if (this.world.organisms.get(i).getCoords().getX() == org.getCoords().getX() && this.world.organisms.get(i).getCoords().getY() == org.getCoords().getY()) {
				this.world.organisms.remove(i);
			}
		}
	}
	
	@Override
	public String getName() {
		return "Deadly_Nightshade";
	}
}
