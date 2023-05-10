package rosliny;
import swiat.*;

public class Trawa extends Roslina{

	public Trawa() {
		this.sila = 0;
		this.wiek = 0;
	}
	
	public Trawa(Swiat swiat, Polozenie xy) {
		this.swiat = swiat;
		this.xy = xy;
		this.sila = 0;
		this.wiek = 0;
	}
	public Trawa(Swiat swiat, Polozenie xy, int wiek) {
		this.swiat = swiat;
		this.xy = xy;
		this.sila = 0;
		this.wiek = wiek;
	}
	
	protected void sprawdzIZasiej(int x1, int y1) {
		String komentarz;
		if (this.swiat.plansza[this.xy.getX()+x1][this.xy.getY()+y1] instanceof Pole) {
			komentarz = "Zasiano " + this.getNazwa() + (this.xy.getX() + x1 + 1) + "," + (this.xy.getY()+y1+1) + ")";
			this.swiat.dziennik(komentarz);
			this.swiat.dodajOrganizm(new Trawa(this.swiat, new Polozenie(this.xy.getX()+x1, this.xy.getY()+y1)));
		}
	}
	
	@Override
	public String getNazwa() {
		return "Trawa";
	}
}
