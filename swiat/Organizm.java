package swiat;
import rosliny.Pole;

public abstract class Organizm {
	protected int wiek, sila, inicjatywa;
	protected Polozenie xy;
	public Swiat swiat;
	
	public Organizm() {
		this.wiek = 0;
		this.sila = 0;
		this.inicjatywa = 0;
		this.xy = new Polozenie(0,0);
	}
	
	public String getXY() {
		return "(" + (this.xy.getX() + 1) + "," + (this.xy.getY()+1) + ")";
	}
	
	public void setX(int x) {
		this.xy.setX(x);
	}
	
	public void setY(int y) {
		this.xy.setY(y);
	}
	
	public int getWiek() {
		return this.wiek;
	}
	
	public int getSila() {
		return this.sila;
	}
	
	public int getInicjatywa() {
		return this.inicjatywa;
	}
	
	public Polozenie getPolozenie() {
		return this.xy;
	}
	
	public void addSila(int sila) {
		this.sila += sila;
	}
	
	public void addWiek(int wiek) {
		this.wiek += wiek;
	}
	
	public void kolizja(Organizm org) {
		if (org.sila > this.sila) {
			String komentarz = this.getNazwa() + " atakuje silniejszego: " + org.getNazwa() + " i ginie.";
			this.swiat.dziennik(komentarz);
			this.swiat.plansza[this.xy.getX()][this.xy.getY()] = new Pole();
		}
	}
	
	abstract public void akcja();
	
	abstract public void rysowanie();
	
	abstract public String getNazwa();

}
