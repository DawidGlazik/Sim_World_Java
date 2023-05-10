package zwierzeta;
import rosliny.Pole;
import swiat.*;
import java.util.Random;

public class Zolw extends Zwierze{

	public Zolw() {
		this.sila = 2;
		this.inicjatywa = 1;
		this.wiek = 0;
	}
	
	public Zolw(Swiat swiat, Polozenie xy) {
		this.swiat = swiat;
		this.xy = xy;
		this.sila = 2;
		this.inicjatywa = 1;
		this.wiek = 0;
	}
	public Zolw(Swiat swiat, Polozenie xy, int sila, int wiek) {
		this.swiat = swiat;
		this.xy = xy;
		this.sila = sila;
		this.inicjatywa = 1;
		this.wiek = wiek;
	}
	
	@Override
	protected void narodziny(int x1, int y1) {
		if (this.swiat.plansza[this.xy.getX()+x1][this.xy.getY()+y1] instanceof Pole) {
			String komentarz = "Narodziny organizmu: " + this.getNazwa() + "(" + (this.xy.getX()+x1+1) + "," + (this.xy.getY()+y1+1) + ")";
			this.swiat.dziennik(komentarz);
			this.swiat.dodajOrganizm(new Zolw(this.swiat, new Polozenie(this.xy.getX()+x1,this.xy.getY()+y1)));
		}
	}
	
	@Override
	public void akcja() {
		Random rand = new Random();
		int czyRuch = rand.nextInt(4);
		if (czyRuch != 0) return;
		super.akcja();
	}
	
	@Override
	public void kolizja(Organizm org) {
		String komentarz;
		if (org instanceof Zolw) {
			if (this.wiek > 2) rozmnazanie();
			else if(org.getSila() < 5) {
				komentarz = this.getNazwa() + this.getXY() + " zatrzymal " + org.getNazwa() + org.getXY();
				this.swiat.dziennik(komentarz);
			}else if(org.getSila() > this.sila) {
				komentarz = org.getNazwa() + org.getXY() + " pokonuje " + this.getNazwa() + this.getXY();
				this.swiat.dziennik(komentarz);
				for (int i = 0; i < this.swiat.organizmy.size(); i++) {
					if (this.swiat.organizmy.get(i).getPolozenie().getX() == this.xy.getX() && this.swiat.organizmy.get(i).getPolozenie().getY() == this.xy.getY()) {
						this.swiat.organizmy.remove(i);
					}
				}
				swiat.plansza[this.xy.getX()][this.xy.getY()] = org;
				swiat.plansza[org.getPolozenie().getX()][org.getPolozenie().getY()] = new Pole();
				org.setX(this.xy.getX());
				org.setY(this.xy.getY());
			}else {
				komentarz = org.getNazwa() + org.getXY() + " przegrywa z " + this.getNazwa() + this.getXY();
				this.swiat.dziennik(komentarz);
				this.swiat.plansza[org.getPolozenie().getX()][org.getPolozenie().getY()] = new Pole();
				for (int i = 0; i < this.swiat.organizmy.size(); i++) {
					if (this.swiat.organizmy.get(i).getPolozenie().getX() == org.getPolozenie().getX() && this.swiat.organizmy.get(i).getPolozenie().getY() == org.getPolozenie().getY()) {
						this.swiat.organizmy.remove(i);
					}
				}
			}
		}
	}
	
	@Override
	public String getNazwa() {
		return "Zolw";
	}
}
