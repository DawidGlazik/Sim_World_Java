package rosliny;
import swiat.*;

public class Mlecz extends Roslina{

	public Mlecz() {
		this.sila = 0;
		this.wiek = 0;
	}
	
	public Mlecz(Swiat swiat, Polozenie xy) {
		this.swiat = swiat;
		this.xy = xy;
		this.sila = 0;
		this.wiek = 0;
	}
	
	public Mlecz(Swiat swiat, Polozenie xy, int wiek) {
		this.swiat = swiat;
		this.xy = xy;
		this.sila = 0;
		this.wiek = wiek;
	}
	
	protected void sprawdzIZasiej(int x1, int y1) {
		String komentarz;
		if (this.swiat.plansza[this.xy.getX()+x1][this.xy.getY()+y1] instanceof Pole) {
			komentarz = "Zasiano " + this.getNazwa() + "(" + (this.xy.getX() + x1 + 1) + "," + (this.xy.getY()+y1+1) + ")";
			this.swiat.dziennik(komentarz);
			this.swiat.dodajOrganizm(new Mlecz(this.swiat, new Polozenie(this.xy.getX()+x1, this.xy.getY()+y1)));
		}
	}
	
	@Override
	public void akcja() {
		for (int i=0;i<3;i++) {
			super.akcja();
		}
	}
	
	@Override
	public String getNazwa() {
		return "Mlecz";
	}
}
