package swiat;
import rosliny.Pole;

public class Czlowiek extends Zwierze{
	private int trwanie, przerwa;
	
	public Czlowiek() {
		this.sila = 5;
		this.inicjatywa = 4;
		this.wiek = 0;
		this.trwanie = 5;
		this.przerwa = 0;
	}
	
	public Czlowiek(Swiat swiat, Polozenie xy) {
		this.sila = 5;
		this.inicjatywa = 4;
		this.wiek = 0;
		this.swiat = swiat;
		this.xy = xy;
		this.trwanie = 5;
		this.przerwa = 0;
	}
	
	public Czlowiek(Swiat swiat, Polozenie xy, int sila, int wiek, int trwanie, int przerwa) {
		this.sila = sila;
		this.inicjatywa = 4;
		this.wiek = wiek;
		this.swiat = swiat;
		this.xy = xy;
		this.trwanie = trwanie;
		this.przerwa = przerwa;
	}
	
	private void sprawdzIZabij(int x1, int y1) {
		String komentarz;
		if (!(this.swiat.plansza[this.xy.getX() + x1][this.xy.getY() + y1] instanceof Pole)) {
			komentarz = this.swiat.plansza[this.xy.getX() + x1][this.xy.getY() + y1].getNazwa() + this.swiat.plansza[this.xy.getX() + x1][this.xy.getY() + y1].getXY() + "zabity(a) przez (calopalenie) " + this.getNazwa() + this.getXY();
			this.swiat.dziennik(komentarz);
			this.swiat.plansza[this.xy.getX() + x1][this.xy.getY() + y1] = new Pole();
			for (int i = 0; i < this.swiat.organizmy.size(); i++) {
				if (this.swiat.organizmy.get(i).getPolozenie().getX() == this.xy.getX() + x1 && this.swiat.organizmy.get(i).getPolozenie().getY() == this.xy.getY() + y1) {
					this.swiat.organizmy.remove(i);
				}
			}
		}
	}
	
	public void akcja(int kierunek) {
		if (kierunek == 0) return;
		else if (kierunek == 1) {
			if (this.xy.getX() == 0) return;
			else {
				if (this.swiat.plansza[this.xy.getX() - 1][this.xy.getY()] instanceof Pole) {
					this.swiat.plansza[this.xy.getX() - 1][this.xy.getY()] = this.swiat.plansza[this.xy.getX()][this.xy.getY()];
					this.swiat.plansza[this.xy.getX()][this.xy.getY()] = new Pole();
					this.xy.setX(this.xy.getX() - 1);
				}
				else {
					this.swiat.plansza[this.xy.getX() - 1][this.xy.getY()].kolizja(this);
				}
			}
		}
		else if (kierunek == 2) {
			if (this.xy.getX() == this.swiat.getWysokosc() - 1) return;
			else {
				if (this.swiat.plansza[this.xy.getX() + 1][this.xy.getY()] instanceof Pole) {
					this.swiat.plansza[this.xy.getX() + 1][this.xy.getY()] = this.swiat.plansza[this.xy.getX()][this.xy.getY()];
					this.swiat.plansza[this.xy.getX()][this.xy.getY()] = new Pole();
					this.xy.setX(this.xy.getX() + 1);
				}
				else {
					this.swiat.plansza[this.xy.getX() + 1][this.xy.getY()].kolizja(this);
				}
			}
		}
		else if (kierunek == 3) {
			if (this.xy.getY() == 0) return;
			else {
				if (this.swiat.plansza[this.xy.getX()][this.xy.getY() - 1] instanceof Pole) {
					this.swiat.plansza[this.xy.getX()][this.xy.getY() - 1] = this.swiat.plansza[this.xy.getX()][this.xy.getY()];
					this.swiat.plansza[this.xy.getX()][this.xy.getY()] = new Pole();
					this.xy.setY(this.xy.getY() - 1);
				}
				else {
					this.swiat.plansza[this.xy.getX()][this.xy.getY() - 1].kolizja(this);
				}
			}
		}
		else if (kierunek == 4) {
			if (this.xy.getY() == this.swiat.getSzerokosc() - 1) return;
			else {
				if (this.swiat.plansza[this.xy.getX()][this.xy.getY() + 1] instanceof Pole) {
					this.swiat.plansza[this.xy.getX()][this.xy.getY() + 1] = this.swiat.plansza[this.xy.getX()][this.xy.getY()];
					this.swiat.plansza[this.xy.getX()][this.xy.getY()] = new Pole();
					this.xy.setY(this.xy.getY() + 1);
				}
				else {
					this.swiat.plansza[this.xy.getX()][this.xy.getY() + 1].kolizja(this);
				}
			}
		}
	}
	
	public String getNazwa() {
		return "Czlowiek";
	}
	
	public int getTrwanie() {
		return this.trwanie;
	}
	
	public int getPrzerwa() {
		return this.przerwa;
	}
	
	public void setTrwanie(int trwanie) {
		this.trwanie = trwanie;
	}
	
	public void addPrzerwa(int przerwa) {
		this.przerwa += przerwa;
	}
	
	public void calopalenie() {
		this.trwanie--;
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
	
}
