package rosliny;
import swiat.*;

public class BarszczSosnowskiego extends Roslina{

	public BarszczSosnowskiego() {
		this.sila = 10;
		this.wiek = 0;
	}
	
	public BarszczSosnowskiego(Swiat swiat, Polozenie xy) {
		this.swiat = swiat;
		this.xy = xy;
		this.sila = 10;
		this.wiek = 0;
	}
	public BarszczSosnowskiego(Swiat swiat, Polozenie xy, int wiek) {
		this.swiat = swiat;
		this.xy = xy;
		this.sila = 10;
		this.wiek = wiek;
	}
	
	protected void sprawdzIZasiej(int x1, int y1) {
		String komentarz;
		if (this.swiat.plansza[this.xy.getX()+x1][this.xy.getY()+y1] instanceof Pole) {
			komentarz = "Zasiano " + this.getNazwa() + (this.xy.getX() + x1 + 1) + "," + (this.xy.getY()+y1+1) + ")";
			this.swiat.dziennik(komentarz);
			this.swiat.dodajOrganizm(new BarszczSosnowskiego(this.swiat, new Polozenie(this.xy.getX()+x1, this.xy.getY()+y1)));
		}
	}
	
	private void sprawdzIZabij(int x1, int y1) {
		String komentarz;
		if (this.swiat.plansza[this.xy.getX()+x1][this.xy.getY()+y1] instanceof Zwierze) {
			komentarz = this.swiat.plansza[this.xy.getX()+x1][this.xy.getY()+y1].getNazwa() + this.swiat.plansza[this.xy.getX()+x1][this.xy.getY()+y1].getXY() + " zabity(a) przez " + this.getNazwa() + this.getXY();
			this.swiat.dziennik(komentarz);
			this.swiat.plansza[this.xy.getX()+x1][this.xy.getY()+y1] = new Pole();
			for (int i = 0; i < this.swiat.organizmy.size(); i++) {
				if (this.swiat.organizmy.get(i).getPolozenie().getX() == this.xy.getX() + x1 && this.swiat.organizmy.get(i).getPolozenie().getY() == this.xy.getY() + y1) {
					this.swiat.organizmy.remove(i);
				}
			}
		}
	}
	
	private void zabijOkolice() {
		if (this.xy.getX() == 0) {
			if (this.xy.getY() == 0) {
				sprawdzIZabij(0, 1);
				sprawdzIZabij(1, 1);
				sprawdzIZabij(1, 0);
			}
			else if (this.xy.getY() == this.swiat.getSzerokosc() - 1) {
				sprawdzIZabij(0, -1);
				sprawdzIZabij(1, -1);
				sprawdzIZabij(1, 0);
			}
			else {
				sprawdzIZabij(0, -1);
				sprawdzIZabij(1, -1);
				sprawdzIZabij(1, 0);
				sprawdzIZabij(0, 1);
				sprawdzIZabij(1, 1);
			}
		}
		else if (this.xy.getX() == this.swiat.getWysokosc() - 1) {
			if (this.xy.getY() == 0) {
				sprawdzIZabij(0, 1);
				sprawdzIZabij(-1, 1);
				sprawdzIZabij(-1, 0);
			}
			else if (this.xy.getY() == this.swiat.getSzerokosc() - 1) {
				sprawdzIZabij(0, -1);
				sprawdzIZabij(-1, -1);
				sprawdzIZabij(-1, 0);
			}
			else {
				sprawdzIZabij(0, 1);
				sprawdzIZabij(-1, 1);
				sprawdzIZabij(-1, 0);
				sprawdzIZabij(0, -1);
				sprawdzIZabij(-1, -1);
			}
		}
		else if (this.xy.getY() == 0) {
			sprawdzIZabij(0, 1);
			sprawdzIZabij(-1, 1);
			sprawdzIZabij(-1, 0);
			sprawdzIZabij(1, 1);
			sprawdzIZabij(1, 0);
		}
		else if (this.xy.getY() == this.swiat.getSzerokosc() - 1) {
			sprawdzIZabij(0, -1);
			sprawdzIZabij(-1, -1);
			sprawdzIZabij(-1, 0);
			sprawdzIZabij(1, -1);
			sprawdzIZabij(1, 0);
		}
		else {
			sprawdzIZabij(0, 1);
			sprawdzIZabij(1, 0);
			sprawdzIZabij(1, 1);
			sprawdzIZabij(-1, 0);
			sprawdzIZabij(-1, 1);
			sprawdzIZabij(-1, -1);
			sprawdzIZabij(0, -1);
			sprawdzIZabij(1, -1);
		}
	}
	
	@Override
	public void akcja() {
		zabijOkolice();
		super.akcja();
	}
	
	@Override
	public void kolizja(Organizm org) {
		String komentarz = org.getNazwa() + org.getXY() + " zjada " + this.getNazwa() + this.getXY() + " i umiera.";
		this.swiat.dziennik(komentarz);
		swiat.plansza[this.xy.getX()][this.xy.getY()] = new Pole();
		swiat.plansza[org.getPolozenie().getX()][org.getPolozenie().getY()] = new Pole();
		for (int i = 0; i < this.swiat.organizmy.size(); i++) {
			if (this.swiat.organizmy.get(i).getPolozenie().getX() == this.xy.getX() && this.swiat.organizmy.get(i).getPolozenie().getY() == this.xy.getY()) {
				this.swiat.organizmy.remove(i);
			}
			if (this.swiat.organizmy.get(i).getPolozenie().getX() == org.getPolozenie().getX() && this.swiat.organizmy.get(i).getPolozenie().getY() == org.getPolozenie().getY()) {
				this.swiat.organizmy.remove(i);
			}
		}
	}
	
	@Override
	public String getNazwa() {
		return "Barszcz_Sosnowskiego";
	}
}
