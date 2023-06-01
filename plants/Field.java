package plants;
import world.*;

public class Field extends Plant{

	public Field() {
		this.strength = 0;
		this.age = 0;
	}
	
	public Field(World world, Coords xy) {
		this.world = world;
		this.xy = xy;
		this.strength = 0;
		this.age = 0;
	}
	
}
