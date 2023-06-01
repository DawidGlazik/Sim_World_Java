import world.World;
public class Main {
	
	private final static int columns = 20;
	private final static int rows = 10;
	
	public static void main(String[] args) {
		World world = new World(columns,rows);
		world.printWorld();		
	}

}
