package zwierzeta;
import rosliny.Pole;
import swiat.*;

public class Lis extends Zwierze{

	public Lis() {
		this.sila = 3;
		this.inicjatywa = 7;
		this.wiek = 0;
	}
	
	public Lis(Swiat swiat, Polozenie xy) {
		this.swiat = swiat;
		this.xy = xy;
		this.sila = 3;
		this.inicjatywa = 7;
		this.wiek = 0;
	}
	public Lis(Swiat swiat, Polozenie xy, int sila, int wiek) {
		this.swiat = swiat;
		this.xy = xy;
		this.sila = sila;
		this.inicjatywa = 7;
		this.wiek = wiek;
	}
	
	@Override
	protected void ruch(int x1, int y1) {
		if (this.swiat.plansza[this.xy.getX()+x1][this.xy.getY()+y1] instanceof Pole) {
			this.swiat.plansza[this.xy.getX()+x1][this.xy.getY()+y1] = this.swiat.plansza[this.xy.getX()][this.xy.getY()];
			this.swiat.plansza[this.xy.getX()][this.xy.getY()] = new Pole();
			this.xy.setX(this.xy.getX() + x1);
			this.xy.setY(this.xy.getY() + y1);
		}
		else {
			if (this.swiat.plansza[this.xy.getX()+x1][this.xy.getY()+y1].getSila() > this.getSila()) return;
			else this.swiat.plansza[this.xy.getX()+x1][this.xy.getY()+y1].kolizja(this);
		}
	}
	
	@Override
	protected void narodziny(int x1, int y1) {
		if (this.swiat.plansza[this.xy.getX()+x1][this.xy.getY()+y1] instanceof Pole) {
			String komentarz = "Narodziny organizmu: " + this.getNazwa() + "(" + (this.xy.getX()+x1+1) + "," + (this.xy.getY()+y1+1) + ")";
			this.swiat.dziennik(komentarz);
			this.swiat.dodajOrganizm(new Lis(this.swiat, new Polozenie(this.xy.getX()+x1,this.xy.getY()+y1)));
		}
	}
	
	@Override
	public String getNazwa() {
		return "Lis";
	}
}
