package zwierzeta;
import rosliny.Pole;
import swiat.*;
import java.util.Random;

public class Antylopa extends Zwierze{

	public Antylopa() {
		this.sila = 4;
		this.inicjatywa = 4;
		this.wiek = 0;
	}
	
	public Antylopa(Swiat swiat, Polozenie xy) {
		this.swiat = swiat;
		this.xy = xy;
		this.sila = 4;
		this.inicjatywa = 4;
		this.wiek = 0;
	}
	public Antylopa(Swiat swiat, Polozenie xy, int sila, int wiek) {
		this.swiat = swiat;
		this.xy = xy;
		this.sila = sila;
		this.inicjatywa = 4;
		this.wiek = wiek;
	}
	
	@Override
	public void akcja() {
		Random rand = new Random();
		int los;
		if (this.xy.getX() == 0) {
			if (this.xy.getY() == 0) {
				los = rand.nextInt(5);
				if (los == 0) ruch(0, 1);
				else if (los == 1) ruch(1, 1);
				else if (los == 2) ruch(1, 0);
				else if (los == 3) ruch(0, 2);
				else if (los == 4) ruch(2, 0);
			}
			else if (this.xy.getY() == this.swiat.getSzerokosc() - 1) {
				los = rand.nextInt(5);
				if (los == 0) ruch(0, -1);
				else if (los == 1) ruch(1, -1);
				else if (los == 2) ruch(1, 0);
				else if (los == 3) ruch(0, -2);
				else if (los == 4) ruch(2, 0);
			}
			else {
				los = rand.nextInt(8);
				if (los == 0) ruch(0, -1);
				else if (los == 1) ruch(1, -1);
				else if (los == 2) ruch(1, 0);
				else if (los == 3) ruch(0, 1);
				else if (los == 4) ruch(1, 1);
				else if (los == 5 && this.xy.getY() != this.swiat.getSzerokosc() - 2) ruch(0, 2);
				else if (los == 6 && this.xy.getX() != this.swiat.getWysokosc() - 2) ruch(2, 0);
				else if (los == 7 && this.xy.getY() != 1) ruch(0, -2);
			}
		}
		else if (this.xy.getX() == this.swiat.getWysokosc() - 1) {
			if (this.xy.getY() == 0) {
				los = rand.nextInt(5);
				if (los == 0) ruch(0, 1);
				else if (los == 1) ruch(-1, 1);
				else if (los == 2) ruch(-1, 0);
				else if (los == 3) ruch(-2, 0);
				else if (los == 4) ruch(0, 2);
			}
			else if (this.xy.getY() == this.swiat.getSzerokosc() - 1) {
				los = rand.nextInt(5);
				if (los == 0) ruch(0, -1);
				else if (los == 1) ruch(-1, -1);
				else if (los == 2) ruch(-1, 0);
				else if (los == 3) ruch(-2, 0);
				else if (los == 4) ruch(0, -2);
			}
			else {
				los = rand.nextInt(8);
				if (los == 0) ruch(0, 1);
				else if (los == 1) ruch(-1, 1);
				else if (los == 2) ruch(-1, 0);
				else if (los == 3) ruch(0, -1);
				else if (los == 4) ruch(-1, -1);
				else if (los == 5 && this.xy.getY() != this.swiat.getSzerokosc() - 2) ruch(0, 2);
				else if (los == 6 && this.xy.getX() != 1) ruch(-2, 0);
				else if (los == 7 && this.xy.getY() != 1) ruch(0, -2);
			}
		}
		else if (this.xy.getY() == 0) {
			los = rand.nextInt(8);
			if (los == 0) ruch(0, 1);
			else if (los == 1) ruch(-1, 1);
			else if (los == 2) ruch(-1, 0);
			else if (los == 3) ruch(1, 1);
			else if (los == 4) ruch(1, 0);
			else if (los == 5) ruch(0, 2);
			else if (los == 6 && this.xy.getX() != 1) ruch(-2, 0);
			else if (los == 7 && this.xy.getX() != this.swiat.getWysokosc() - 2) ruch(2, 0);
		}
		else if (this.xy.getY() == this.swiat.getSzerokosc() - 1) {
			los = rand.nextInt(8);
			if (los == 0) ruch(0, -1);
			else if (los == 1) ruch(-1, -1);
			else if (los == 2) ruch(-1, 0);
			else if (los == 3) ruch(1, -1);
			else if (los == 4) ruch(1, 0);
			else if (los == 5) ruch(0, -2);
			else if (los == 6 && this.xy.getX() != 1) ruch(-2, 0);
			else if (los == 7 && this.xy.getX() != this.swiat.getWysokosc() - 2) ruch(2, 0);
		}
		else {
			los = rand.nextInt(12);
			if (los == 0) ruch(0, 1);
			else if (los == 1) ruch(1, 0);
			else if (los == 2) ruch(1, 1);
			else if (los == 3) ruch(-1, 0);
			else if (los == 4) ruch(-1, 1);
			else if (los == 5) ruch(-1, -1);
			else if (los == 6) ruch(0, -1);
			else if (los == 7) ruch(1, -1);
			else if (los == 8 && this.xy.getX() != 1) ruch(-2, 0);
			else if (los == 9 && this.xy.getX() != this.swiat.getWysokosc() - 2) ruch(2, 0);
			else if (los == 10 && this.xy.getY() != this.swiat.getSzerokosc() - 2) ruch(0, 2);
			else if (los == 11 && this.xy.getY() != 1) ruch(0, -2);
		}
	}
	
	@Override
	public void kolizja(Organizm org) {
		Random rand = new Random();
		int los = rand.nextInt(2);
		if (org instanceof Antylopa) {
			if (this.getWiek() > 2) rozmnazanie();
		}else if(los == 0) {
			String komentarz = this.getNazwa() + this.getXY() + " ucieka.";
			this.swiat.dziennik(komentarz);
			if (this.xy.getY() != swiat.getSzerokosc() - 1 && this.swiat.plansza[this.xy.getX()][this.xy.getY() + 1] instanceof Pole) {
				swiat.plansza[this.xy.getX()][this.xy.getY() + 1] = swiat.plansza[this.xy.getX()][this.xy.getY()];
				swiat.plansza[this.xy.getX()][this.xy.getY()] = new Pole();
				this.xy.setY(this.xy.getY()+1);
			}
			else if (this.xy.getX() != swiat.getWysokosc() - 1 && this.swiat.plansza[this.xy.getX() + 1][this.xy.getY()] instanceof Pole) {
				swiat.plansza[this.xy.getX() + 1][this.xy.getY()] = swiat.plansza[this.xy.getX()][this.xy.getY()];
				swiat.plansza[this.xy.getX()][this.xy.getY()] = new Pole();
				this.xy.setX(this.xy.getX()+1);
			}
			else if (this.xy.getY() != 0 && this.swiat.plansza[this.xy.getX()][this.xy.getY() - 1] instanceof Pole) {
				swiat.plansza[this.xy.getX()][this.xy.getY() - 1] = swiat.plansza[this.xy.getX()][this.xy.getY()];
				swiat.plansza[this.xy.getX()][this.xy.getY()] = new Pole();
				this.xy.setY(this.xy.getY()-1);
			}
			else if (this.xy.getX() != 0 && this.swiat.plansza[this.xy.getX() - 1][this.xy.getY()] instanceof Pole) {
				swiat.plansza[this.xy.getX() - 1][this.xy.getY()] = swiat.plansza[this.xy.getX()][this.xy.getY()];
				swiat.plansza[this.xy.getX()][this.xy.getY()] = new Pole();
				this.xy.setX(this.xy.getX()-1);
			}
		}else if(org.getSila() >= this.sila) {
			String komentarz = org.getNazwa() + org.getXY() + " pokonuje " + this.getNazwa() + this.getXY();
			this.swiat.dziennik(komentarz);
			for (int i=0;i<this.swiat.organizmy.size();i++) {
				if (this.swiat.organizmy.get(i).getPolozenie().getX() == this.xy.getX() && this.swiat.organizmy.get(i).getPolozenie().getY() == this.xy.getY()) {
					this.swiat.organizmy.remove(i);
				}
			}
			swiat.plansza[this.xy.getX()][this.xy.getY()] = org;
			swiat.plansza[org.getPolozenie().getX()][org.getPolozenie().getY()] = new Pole();
			org.setX(this.xy.getX());
			org.setY(this.xy.getY());
		}else {
			String komentarz = org.getNazwa() + org.getXY() + " przegrywa z: " + this.getNazwa() + this.getXY();
			this.swiat.dziennik(komentarz);
			swiat.plansza[org.getPolozenie().getX()][org.getPolozenie().getY()] = new Pole();
			for (int i=0;i<this.swiat.organizmy.size();i++) {
				if (this.swiat.organizmy.get(i).getPolozenie().getX() == org.getPolozenie().getX() && this.swiat.organizmy.get(i).getPolozenie().getY() == org.getPolozenie().getY()) {
					this.swiat.organizmy.remove(i);
				}
			}
		}
	}
	
	@Override
	protected void narodziny(int x1, int y1) {
		if (this.swiat.plansza[this.xy.getX()+x1][this.xy.getY()+y1] instanceof Pole) {
			String komentarz = "Narodziny organizmu: " + this.getNazwa() + "(" + (this.xy.getX()+x1+1) + "," + (this.xy.getY()+y1+1) + ")";
			this.swiat.dziennik(komentarz);
			this.swiat.dodajOrganizm(new Antylopa(this.swiat, new Polozenie(this.xy.getX()+x1,this.xy.getY()+y1)));
		}
	}
	
	@Override
	public String getNazwa() {
		return "Antylopa";
	}
}
