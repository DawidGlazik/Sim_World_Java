package zwierzeta;
import rosliny.Pole;
import swiat.*;

public class Owca extends Zwierze{

	public Owca() {
		this.sila = 4;
		this.inicjatywa = 4;
		this.wiek = 0;
	}
	
	public Owca(Swiat swiat, Polozenie xy) {
		this.swiat = swiat;
		this.xy = xy;
		this.sila = 4;
		this.inicjatywa = 4;
		this.wiek = 0;
	}
	public Owca(Swiat swiat, Polozenie xy, int sila, int wiek) {
		this.swiat = swiat;
		this.xy = xy;
		this.sila = sila;
		this.inicjatywa = 4;
		this.wiek = wiek;
	}
	
	@Override
	protected void narodziny(int x1, int y1) {
		if (this.swiat.plansza[this.xy.getX()+x1][this.xy.getY()+y1] instanceof Pole) {
			String komentarz = "Narodziny organizmu: " + this.getNazwa() + "(" + (this.xy.getX()+x1+1) + "," + (this.xy.getY()+y1+1) + ")";
			this.swiat.dziennik(komentarz);
			this.swiat.dodajOrganizm(new Owca(this.swiat, new Polozenie(this.xy.getX()+x1,this.xy.getY()+y1)));
		}
	}
	
	@Override
	public String getNazwa() {
		return "Owca";
	}
	
}
