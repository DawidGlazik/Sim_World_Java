package rosliny;
import swiat.*;

public class Pole extends Roslina{

	public Pole() {
		this.sila = 0;
		this.wiek = 0;
	}
	
	public Pole(Swiat swiat, Polozenie xy) {
		this.swiat = swiat;
		this.xy = xy;
		this.sila = 0;
		this.wiek = 0;
	}
	
}
