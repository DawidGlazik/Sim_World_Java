package rosliny;
import swiat.*;

public class Guarana extends Roslina{

	public Guarana() {
		this.sila = 0;
		this.wiek = 0;
	}
	
	public Guarana(Swiat swiat, Polozenie xy) {
		this.swiat = swiat;
		this.xy = xy;
		this.sila = 0;
		this.wiek = 0;
	}
	public Guarana(Swiat swiat, Polozenie xy, int wiek) {
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
			this.swiat.dodajOrganizm(new Guarana(this.swiat, new Polozenie(this.xy.getX()+x1, this.xy.getY()+y1)));
		}
	}
	
	@Override
	public void kolizja(Organizm org) {
		String komentarz = org.getNazwa() + org.getXY() + " - sila rosnie o 3 po zjedzeniu " + this.getNazwa() + this.getXY();
		org.addSila(3);
		swiat.dziennik(komentarz);
		for (int i = 0; i < this.swiat.organizmy.size(); i++) {
			if (this.swiat.organizmy.get(i).getPolozenie().getX() == this.xy.getX() && this.swiat.organizmy.get(i).getPolozenie().getY() == this.xy.getY()) {
				this.swiat.organizmy.remove(i);
			}
		}
		swiat.plansza[this.xy.getX()][this.xy.getY()] = org;
		swiat.plansza[org.getPolozenie().getX()][org.getPolozenie().getY()] = new Pole();
		org.setX(this.xy.getX());
		org.setY(this.xy.getY());
	}
	
	@Override
	public String getNazwa() {
		return "Guarana";
	}
}

