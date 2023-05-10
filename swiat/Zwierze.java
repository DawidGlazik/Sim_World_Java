package swiat;
import java.util.Random;
import rosliny.Pole;

public class Zwierze extends Organizm{

	public Zwierze() {
		this.sila = 0;
		this.inicjatywa = 0;
		this.xy = new Polozenie(0,0);
	}
	
	public Zwierze(Swiat swiat, Polozenie xy) {
		this.sila = 0;
		this.inicjatywa = 0;
		this.swiat = swiat;
		this.xy = xy;
	}
	
	protected void ruch(int x1, int y1) {
		if (this.swiat.plansza[this.xy.getX()+x1][this.xy.getY()+y1] instanceof Pole) {
			this.swiat.plansza[this.xy.getX()+x1][this.xy.getY()+y1] = this.swiat.plansza[this.xy.getX()][this.xy.getY()];
			this.swiat.plansza[this.xy.getX()][this.xy.getY()] = new Pole();
			this.xy.setX(this.xy.getX() + x1);
			this.xy.setY(this.xy.getY() + y1);
		}
		else {
			this.swiat.plansza[this.xy.getX()+x1][this.xy.getY()+y1].kolizja(this);
		}
	}
	
	protected void narodziny(int x1, int y1) {
		if (this.swiat.plansza[this.xy.getX()+x1][this.xy.getY()+y1] instanceof Pole) {
			String komentarz = "Narodziny organizmu: " + this.getNazwa() + "(" + (this.xy.getX()+x1+1) + "," + (this.xy.getY()+y1+1) + ")";
			this.swiat.dziennik(komentarz);
			this.swiat.dodajOrganizm(new Zwierze(this.swiat, new Polozenie(this.xy.getX()+x1,this.xy.getY()+y1)));
		}
	}
	
	protected void rozmnazanie() {
		Random rand = new Random();
		int los;
		if (this.xy.getX() == 0) {
			if (this.xy.getY() == 0) {
				los = rand.nextInt(3);
				if (los == 0) ruch(0, 1);
				else if (los == 1) ruch(1, 1);
				else if (los == 2) ruch(1, 0);
			}
			else if (this.xy.getY() == this.swiat.getSzerokosc() - 1) {
				los = rand.nextInt(3);
				if (los == 0) ruch(0, -1);
				else if (los == 1) ruch(1, -1);
				else if (los == 2) ruch(1, 0);
			}
			else {
				los = rand.nextInt(5);
				if (los == 0) ruch(0, -1);
				else if (los == 1) ruch(1, -1);
				else if (los == 2) ruch(1, 0);
				else if (los == 3) ruch(0, 1);
				else if (los == 4) ruch(1, 1);
			}
		}
		else if (this.xy.getX() == this.swiat.getWysokosc() - 1) {
			if (this.xy.getY() == 0) {
				los = rand.nextInt(3);
				if (los == 0) ruch(0, 1);
				else if (los == 1) ruch(-1, 1);
				else if (los == 2) ruch(-1, 0);
			}
			else if (this.xy.getY() == this.swiat.getSzerokosc() - 1) {
				los = rand.nextInt(3);
				if (los == 0) ruch(0, -1);
				else if (los == 1) ruch(-1, -1);
				else if (los == 2) ruch(-1, 0);
			}
			else {
				los = rand.nextInt(5);
				if (los == 0) ruch(0, 1);
				else if (los == 1) ruch(-1, 1);
				else if (los == 2) ruch(-1, 0);
				else if (los == 3) ruch(0, -1);
				else if (los == 4) ruch(-1, -1);
			}
		}
		else if (this.xy.getY() == 0) {
			los = rand.nextInt(5);
			if (los == 0) ruch(0, 1);
			else if (los == 1) ruch(-1, 1);
			else if (los == 2) ruch(-1, 0);
			else if (los == 3) ruch(1, 1);
			else if (los == 4) ruch(1, 0);
		}
		else if (this.xy.getY() == this.swiat.getSzerokosc() - 1) {
			los = rand.nextInt(5);
			if (los == 0) ruch(0, -1);
			else if (los == 1) ruch(-1, -1);
			else if (los == 2) ruch(-1, 0);
			else if (los == 3) ruch(1, -1);
			else if (los == 4) ruch(1, 0);
		}
		else {
			los = rand.nextInt(8);
			if (los == 0) ruch(0, 1);
			else if (los == 1) ruch(1, 0);
			else if (los == 2) ruch(1, 1);
			else if (los == 3) ruch(-1, 0);
			else if (los == 4) ruch(-1, 1);
			else if (los == 5) ruch(-1, -1);
			else if (los == 6) ruch(0, -1);
			else if (los == 7) ruch(1, -1);
		}
	}
	
	@Override
	public void akcja() {
		int los;
		Random rand = new Random();
		if (this.xy.getX() == 0) {
			if (this.xy.getY() == 0) {
				los = rand.nextInt(3);
				if (los == 0) ruch(0, 1);
				else if (los == 1) ruch(1, 1);
				else if (los == 2) ruch(1, 0);
			}
			else if (this.xy.getY() == this.swiat.getSzerokosc() - 1) {
				los = rand.nextInt(3);
				if (los == 0) ruch(0, -1);
				else if (los == 1) ruch(1, -1);
				else if (los == 2) ruch(1, 0);
			}
			else {
				los = rand.nextInt(5);
				if (los == 0) ruch(0, -1);
				else if (los == 1) ruch(1, -1);
				else if (los == 2) ruch(1, 0);
				else if (los == 3) ruch(0, 1);
				else if (los == 4) ruch(1, 1);
			}
		}
		else if (this.xy.getX() == this.swiat.getWysokosc() - 1) {
			if (this.xy.getY() == 0) {
				los = rand.nextInt(3);
				if (los == 0) ruch(0, 1);
				else if (los == 1) ruch(-1, 1);
				else if (los == 2) ruch(-1, 0);
			}
			else if (this.xy.getY() == this.swiat.getSzerokosc() - 1) {
				los = rand.nextInt(3);
				if (los == 0) ruch(0, -1);
				else if (los == 1) ruch(-1, -1);
				else if (los == 2) ruch(-1, 0);
			}
			else {
				los = rand.nextInt(5);
				if (los == 0) ruch(0, 1);
				else if (los == 1) ruch(-1, 1);
				else if (los == 2) ruch(-1, 0);
				else if (los == 3) ruch(0, -1);
				else if (los == 4) ruch(-1, -1);
			}
		}
		else if (this.xy.getY() == 0) {
			los = rand.nextInt(5);
			if (los == 0) ruch(0, 1);
			else if (los == 1) ruch(-1, 1);
			else if (los == 2) ruch(-1, 0);
			else if (los == 3) ruch(1, 1);
			else if (los == 4) ruch(1, 0);
		}
		else if (this.xy.getY() == this.swiat.getSzerokosc() - 1) {
			los = rand.nextInt(5);
			if (los == 0) ruch(0, -1);
			else if (los == 1) ruch(-1, -1);
			else if (los == 2) ruch(-1, 0);
			else if (los == 3) ruch(1, -1);
			else if (los == 4) ruch(1, 0);
		}
		else {
			los = rand.nextInt(8);
			if (los == 0) ruch(0, 1);
			else if (los == 1) ruch(1, 0);
			else if (los == 2) ruch(1, 1);
			else if (los == 3) ruch(-1, 0);
			else if (los == 4) ruch(-1, 1);
			else if (los == 5) ruch(-1, -1);
			else if (los == 6) ruch(0, -1);
			else if (los == 7) ruch(1, -1);
		}
	}

	@Override
	public void kolizja(Organizm org) {
		if (org.getNazwa() == this.getNazwa()) {
			if (this.getWiek() > 2) rozmnazanie();
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
				if (this.swiat.organizmy.get(i).getPolozenie().getX() == org.xy.getX() && this.swiat.organizmy.get(i).getPolozenie().getY() == org.xy.getY()) {
					this.swiat.organizmy.remove(i);
				}
			}
		}
	}
	
	@Override
	public void rysowanie() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getNazwa() {
		return "Zwierze";
	}

}
