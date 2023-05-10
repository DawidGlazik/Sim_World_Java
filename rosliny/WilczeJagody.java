package rosliny;
import swiat.*;

public class WilczeJagody extends Roslina{

	public WilczeJagody() {
		this.sila = 99;
		this.wiek = 0;
	}
	
	public WilczeJagody(Swiat swiat, Polozenie xy) {
		this.swiat = swiat;
		this.xy = xy;
		this.sila = 99;
		this.wiek = 0;
	}
	public WilczeJagody(Swiat swiat, Polozenie xy, int wiek) {
		this.swiat = swiat;
		this.xy = xy;
		this.sila = 99;
		this.wiek = wiek;
	}
	
	protected void sprawdzIZasiej(int x1, int y1) {
		String komentarz;
		if (this.swiat.plansza[this.xy.getX()+x1][this.xy.getY()+y1] instanceof Pole) {
			komentarz = "Zasiano " + this.getNazwa() + (this.xy.getX() + x1 + 1) + "," + (this.xy.getY()+y1+1) + ")";
			this.swiat.dziennik(komentarz);
			this.swiat.dodajOrganizm(new WilczeJagody(this.swiat, new Polozenie(this.xy.getX()+x1, this.xy.getY()+y1)));
		}
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
		return "Wilcze_Jagody";
	}
}
