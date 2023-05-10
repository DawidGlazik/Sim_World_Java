package swiat;
import java.util.Random;
import rosliny.Pole;

public class Roslina extends Organizm{

	public Roslina() {
		this.sila = 0;
		this.inicjatywa = 0;
		this.xy = new Polozenie(0,0);
	}
	
	public Roslina(Swiat swiat, Polozenie xy) {
		this.sila = 0;
		this.inicjatywa = 0;
		this.swiat = swiat;
		this.xy = xy;
	}
	
	protected void sprawdzIZasiej(int x1, int y1) {
		String komentarz;
		if (this.swiat.plansza[this.xy.getX() + x1][this.xy.getY() + y1] instanceof Pole) {
			komentarz = "Zasiano rosline(" + (this.xy.getX() + x1 + 1) + "," + (this.xy.getY() + y1 + 1) + ")";
			this.swiat.dziennik(komentarz);
			this.swiat.dodajOrganizm(new Roslina(this.swiat, new Polozenie(this.xy.getX() + x1, this.xy.getY() + y1)));
		}
	}
	
	@Override
	public void akcja() {
		Random rand = new Random();
		int los1 = rand.nextInt(15);
		int los2;
		if (los1 != 0) return;
		if (this.xy.getX() == 0) {
			los2 = rand.nextInt(11);
			if (this.xy.getY() == 0) {
				if (los2 == 0) sprawdzIZasiej(0, 1);
				else if (los2 == 1) sprawdzIZasiej(1, 1);
				else if (los2 == 2)sprawdzIZasiej(1, 0);
			}
			else if (this.xy.getY() == this.swiat.getSzerokosc() - 1) {
				if (los2 == 3) sprawdzIZasiej(0, -1);
				else if (los2 == 4) sprawdzIZasiej(1, -1);
				else if (los2 == 5) sprawdzIZasiej(1, 0);
			}
			else {
				if (los2 == 6) sprawdzIZasiej(0, -1);
				else if (los2 == 7) sprawdzIZasiej(1, -1);
				else if (los2 == 8) sprawdzIZasiej(1, 0);
				else if (los2 == 9) sprawdzIZasiej(0, 1);
				else if (los2 == 10) sprawdzIZasiej(1, 1);
			}
		}
		else if (this.xy.getX() == this.swiat.getWysokosc() - 1) {
			los2 = rand.nextInt(11);
			if (this.xy.getY() == 0) {
				if (los2 == 0) sprawdzIZasiej(0, 1);
				else if (los2 == 1) sprawdzIZasiej(-1, 1);
				else if (los2 == 2) sprawdzIZasiej(-1, 0);
			}
			else if (this.xy.getY() == this.swiat.getSzerokosc() - 1) {
				if (los2 == 3) sprawdzIZasiej(0, -1);
				else if (los2 == 4) sprawdzIZasiej(-1, -1);
				else if (los2 == 5) sprawdzIZasiej(-1, 0);
			}
			else {
				if (los2 == 6) sprawdzIZasiej(0, 1);
				else if (los2 == 7) sprawdzIZasiej(-1, 1);
				else if (los2 == 8) sprawdzIZasiej(-1, 0);
				else if (los2 == 9) sprawdzIZasiej(0, -1);
				else if (los2 == 10) sprawdzIZasiej(-1, -1);
			}
		}
		else if (this.xy.getY() == 0) {
			los2 = rand.nextInt(5);
			if (los2 == 0) sprawdzIZasiej(0, 1);
			else if (los2 == 1) sprawdzIZasiej(-1, 1);
			else if (los2 == 2) sprawdzIZasiej(-1, 0);
			else if (los2 == 3) sprawdzIZasiej(1, 1);
			else if (los2 == 4) sprawdzIZasiej(1, 0);
		}
		else if (this.xy.getY() == this.swiat.getSzerokosc() - 1) {
			los2 = rand.nextInt(5);
			if (los2 == 0) sprawdzIZasiej(0, -1);
			else if (los2 == 1) sprawdzIZasiej(-1, -1);
			else if (los2 == 2) sprawdzIZasiej(-1, 0);
			else if (los2 == 3) sprawdzIZasiej(1, -1);
			else if (los2 == 4) sprawdzIZasiej(1, 0);
		}
		else {
			los2 = rand.nextInt(8);
			if (los2 == 0) sprawdzIZasiej(0, 1);
			else if (los2 == 1) sprawdzIZasiej(1, 0);
			else if (los2 == 2) sprawdzIZasiej(1, 1);
			else if (los2 == 3) sprawdzIZasiej(-1, 0);
			else if (los2 == 4) sprawdzIZasiej(-1, 1);
			else if (los2 == 5) sprawdzIZasiej(-1, -1);
			else if (los2 == 6) sprawdzIZasiej(0, -1);
			else if (los2 == 7) sprawdzIZasiej(1, -1);
		}
		
	}
	
	@Override
	public void kolizja(Organizm org) {
		String komentarz = org.getNazwa() + org.getXY() + " zjada " + this.getNazwa() + this.getXY();
		swiat.dziennik(komentarz);
		for (int i=0;i<this.swiat.organizmy.size();i++) {
			if(this.swiat.organizmy.get(i).getPolozenie().getX() == this.xy.getX() && this.swiat.organizmy.get(i).getPolozenie().getY() == this.xy.getY()) {
				this.swiat.organizmy.remove(i);
			}
		}
		swiat.plansza[this.xy.getX()][this.xy.getY()] = org;
		swiat.plansza[org.getPolozenie().getX()][org.getPolozenie().getY()] = new Pole();
		org.setX(this.xy.getX());
		org.setY(this.xy.getY());
	}
	
	@Override
	public void rysowanie() {
		
	}

	@Override
	public String getNazwa() {
		return "Roslina";
	}

}
