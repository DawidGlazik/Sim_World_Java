package world;
import plants.Field;

public class Human extends Animal{
	private int lasting, brake;
	
	public Human() {
		this.strength = 5;
		this.initiative = 4;
		this.age = 0;
		this.lasting = 5;
		this.brake = 0;
	}
	
	public Human(World world, Coords xy) {
		this.strength = 5;
		this.initiative = 4;
		this.age = 0;
		this.world = world;
		this.xy = xy;
		this.lasting = 5;
		this.brake = 0;
	}
	
	public Human(World world, Coords xy, int strength, int age, int lasting, int brake) {
		this.strength = strength;
		this.initiative = 4;
		this.age = age;
		this.world = world;
		this.xy = xy;
		this.lasting = lasting;
		this.brake = brake;
	}
	
	private void checkAndKill(int x1, int y1) {
		String comment;
		if (!(this.world.board[this.xy.getX() + x1][this.xy.getY() + y1] instanceof Field)) {
			comment = this.world.board[this.xy.getX() + x1][this.xy.getY() + y1].getName() + this.world.board[this.xy.getX() + x1][this.xy.getY() + y1].getXY() + "killed by (calopalenie) " + this.getName() + this.getXY();
			this.world.log(comment);
			this.world.board[this.xy.getX() + x1][this.xy.getY() + y1] = new Field();
			for (int i = 0; i < this.world.organisms.size(); i++) {
				if (this.world.organisms.get(i).getCoords().getX() == this.xy.getX() + x1 && this.world.organisms.get(i).getCoords().getY() == this.xy.getY() + y1) {
					this.world.organisms.remove(i);
				}
			}
		}
	}
	
	public void action(int direction) {
		if (direction == 0) return;
		else if (direction == 1) {
			if (this.xy.getX() == 0) return;
			else {
				if (this.world.board[this.xy.getX() - 1][this.xy.getY()] instanceof Field) {
					this.world.board[this.xy.getX() - 1][this.xy.getY()] = this.world.board[this.xy.getX()][this.xy.getY()];
					this.world.board[this.xy.getX()][this.xy.getY()] = new Field();
					this.xy.setX(this.xy.getX() - 1);
				}
				else {
					this.world.board[this.xy.getX() - 1][this.xy.getY()].collision(this);
				}
			}
		}
		else if (direction == 2) {
			if (this.xy.getX() == this.world.getHeight() - 1) return;
			else {
				if (this.world.board[this.xy.getX() + 1][this.xy.getY()] instanceof Field) {
					this.world.board[this.xy.getX() + 1][this.xy.getY()] = this.world.board[this.xy.getX()][this.xy.getY()];
					this.world.board[this.xy.getX()][this.xy.getY()] = new Field();
					this.xy.setX(this.xy.getX() + 1);
				}
				else {
					this.world.board[this.xy.getX() + 1][this.xy.getY()].collision(this);
				}
			}
		}
		else if (direction == 3) {
			if (this.xy.getY() == 0) return;
			else {
				if (this.world.board[this.xy.getX()][this.xy.getY() - 1] instanceof Field) {
					this.world.board[this.xy.getX()][this.xy.getY() - 1] = this.world.board[this.xy.getX()][this.xy.getY()];
					this.world.board[this.xy.getX()][this.xy.getY()] = new Field();
					this.xy.setY(this.xy.getY() - 1);
				}
				else {
					this.world.board[this.xy.getX()][this.xy.getY() - 1].collision(this);
				}
			}
		}
		else if (direction == 4) {
			if (this.xy.getY() == this.world.getWidth() - 1) return;
			else {
				if (this.world.board[this.xy.getX()][this.xy.getY() + 1] instanceof Field) {
					this.world.board[this.xy.getX()][this.xy.getY() + 1] = this.world.board[this.xy.getX()][this.xy.getY()];
					this.world.board[this.xy.getX()][this.xy.getY()] = new Field();
					this.xy.setY(this.xy.getY() + 1);
				}
				else {
					this.world.board[this.xy.getX()][this.xy.getY() + 1].collision(this);
				}
			}
		}
	}
	
	public String getName() {
		return "Human";
	}
	
	public int getLasting() {
		return this.lasting;
	}
	
	public int getBrake() {
		return this.brake;
	}
	
	public void setLasting(int lasting) {
		this.lasting = lasting;
	}
	
	public void addBrake(int brake) {
		this.brake += brake;
	}
	
	public void calopalenie() {
		this.lasting--;
		if (this.xy.getX() == 0) {
			if (this.xy.getY() == 0) {
				checkAndKill(0, 1);
				checkAndKill(1, 1);
				checkAndKill(1, 0);
			}
			else if (this.xy.getY() == this.world.getWidth() - 1) {
				checkAndKill(0, -1);
				checkAndKill(1, -1);
				checkAndKill(1, 0);
			}
			else {
				checkAndKill(0, -1);
				checkAndKill(1, -1);
				checkAndKill(1, 0);
				checkAndKill(0, 1);
				checkAndKill(1, 1);
			}
		}
		else if (this.xy.getX() == this.world.getHeight() - 1) {
			if (this.xy.getY() == 0) {
				checkAndKill(0, 1);
				checkAndKill(-1, 1);
				checkAndKill(-1, 0);
			}
			else if (this.xy.getY() == this.world.getWidth() - 1) {
				checkAndKill(0, -1);
				checkAndKill(-1, -1);
				checkAndKill(-1, 0);
			}
			else {
				checkAndKill(0, 1);
				checkAndKill(-1, 1);
				checkAndKill(-1, 0);
				checkAndKill(0, -1);
				checkAndKill(-1, -1);
			}
		}
		else if (this.xy.getY() == 0) {
			checkAndKill(0, 1);
			checkAndKill(-1, 1);
			checkAndKill(-1, 0);
			checkAndKill(1, 1);
			checkAndKill(1, 0);
		}
		else if (this.xy.getY() == this.world.getWidth() - 1) {
			checkAndKill(0, -1);
			checkAndKill(-1, -1);
			checkAndKill(-1, 0);
			checkAndKill(1, -1);
			checkAndKill(1, 0);
		}
		else {
			checkAndKill(0, 1);
			checkAndKill(1, 0);
			checkAndKill(1, 1);
			checkAndKill(-1, 0);
			checkAndKill(-1, 1);
			checkAndKill(-1, -1);
			checkAndKill(0, -1);
			checkAndKill(1, -1);
		}
	}
	
}
