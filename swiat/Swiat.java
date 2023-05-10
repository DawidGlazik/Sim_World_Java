package swiat;
import java.util.ArrayList;
import rosliny.*;
import zwierzeta.*;
import java.util.Random;
import okno.Okno;

public class Swiat {
	private int tura;
	private int szerokosc;
	private int wysokosc;
	public String konsola;
	public ArrayList<Organizm> organizmy;
	public Organizm plansza[][];
	
	public Swiat() {
		this.tura = 0;
		this.konsola = "";
	}
	
	public Swiat(int szerokosc, int wysokosc) {
		this.tura = 0;
		this.konsola = "";
		this.szerokosc = szerokosc;
		this.wysokosc = wysokosc;
		organizmy = new ArrayList<>();
		plansza = new Organizm[wysokosc][szerokosc];
		for (int i = 0; i < wysokosc; i++) {
		    for (int j = 0; j < szerokosc; j++) {
		        plansza[i][j] = new Pole(this, new Polozenie(j,i));
		    }
		}
		Random rand = new Random();
		int random1, random2, random3, numberOfOrganisms, k;
		numberOfOrganisms = szerokosc * wysokosc / 15;
		Czlowiek czlowiek = new Czlowiek(this, new Polozenie(wysokosc / 2, szerokosc / 2));
		dodajOrganizm(czlowiek);
		for (int i = 0; i < numberOfOrganisms; i++) {
			random1 = rand.nextInt(this.wysokosc);
			random2 = rand.nextInt(this.szerokosc);
			random3 = rand.nextInt(1000);
			if (plansza[random1][random2] instanceof Pole) {
				if (i < 10) {
					k = i;
				}
				else {
					k = random3 % 10;
				}
				switch (k)
				{
				case 0:
					dodajOrganizm(new BarszczSosnowskiego(this, new Polozenie( random1, random2 )));
					break;
				case 1:
					dodajOrganizm(new Guarana(this, new Polozenie( random1, random2 )));
					break;
				case 2:
					dodajOrganizm(new Mlecz(this, new Polozenie( random1, random2 )));
					break;
				case 3:
					dodajOrganizm(new Trawa(this, new Polozenie( random1, random2 )));
					break;
				case 4:
					dodajOrganizm(new WilczeJagody(this, new Polozenie( random1, random2 )));
					break;
				case 5:
					dodajOrganizm(new Antylopa(this, new Polozenie( random1, random2 )));
					break;
				case 6:
					dodajOrganizm(new Lis(this, new Polozenie( random1, random2 )));
					break;
				case 7:
					dodajOrganizm(new Owca(this, new Polozenie( random1, random2 )));
					break;
				case 8:
					dodajOrganizm(new Wilk(this, new Polozenie( random1, random2 )));
					break;
				case 9:
					dodajOrganizm(new Zolw(this, new Polozenie( random1, random2 )));
					break;
				default:
					break;
				}
			}
		}
	}
	
	public void rozpocznijCalopalenie() {
		for (int i=0; i<organizmy.size(); i++) {
			if (organizmy.get(i) instanceof Czlowiek) {
				Czlowiek tmp = (Czlowiek) organizmy.get(i);
				if (tmp.getTrwanie() == 5) {
					String komentarz = "Czlowiek rozpoczyna calopalenie.";
					this.dziennik(komentarz);
					tmp.calopalenie();
					if (tmp.getPrzerwa() != 5) tmp.addPrzerwa(5);
				}
			}
		}
	}
	
	public void wykonajTure(int kierunek) {
		for (int i = 0; i < organizmy.size(); i++) {
			if (organizmy.get(i) instanceof Czlowiek) {
				Czlowiek tmp = (Czlowiek) organizmy.get(i);
				tmp.akcja(kierunek);
				if (tmp.getTrwanie() < 5 && tmp.getTrwanie() > 0) {
					tmp.calopalenie();
				}
				else if (tmp.getPrzerwa() > 0) {
					tmp.addPrzerwa(-1);
				}
				else {
					tmp.setTrwanie(5);
				}
			}
			else if (organizmy.get(i) instanceof BarszczSosnowskiego) {
				BarszczSosnowskiego tmp = (BarszczSosnowskiego) organizmy.get(i);
				tmp.akcja();
			}
			else organizmy.get(i).akcja();

		}
		for (int i = 0; i < organizmy.size(); i++) {
			organizmy.get(i).addWiek(1);
		}
		this.tura++;
		System.out.println("Tura:"+tura);
		System.out.println(konsola);
	}
	
	public void rysujSwiat() {
		Okno okno = new Okno(this, wysokosc, szerokosc, plansza);
		okno.rysuj();
	}
	
	public void dodajOrganizm(Organizm nowy) {
		this.plansza[nowy.getPolozenie().getX()][nowy.getPolozenie().getY()] = nowy;
		if (organizmy.size() == 0) organizmy.add(nowy);
		else {
			for (int i=0; i<organizmy.size(); i++) {
				if (nowy.getInicjatywa() > organizmy.get(i).getInicjatywa()) {
					organizmy.add(i, nowy);
				    return;
				}
			}
			organizmy.add(nowy);
		}
	}
	
	public void wypiszOrganizmy() {
		for (int i=0;i<organizmy.size();i++) {
			System.out.println(organizmy.get(i).getNazwa());
		}
	}
	
	public void dziennik(String konsola) {
		this.konsola += konsola;
		this.konsola += "\n";
	}
	
	public int getSzerokosc() {
		return this.szerokosc;
	}
	
	public int getWysokosc() {
		return this.wysokosc;
	}
	
	public int getTura() {
		return this.tura;
	}
	
	public void zapisz() {
		
	}
	
	public void wczytaj() {
		
	}
	
	public static void main(String[] args) {
		Swiat swiat = new Swiat(20,10);
		swiat.wypiszOrganizmy();
		swiat.rysujSwiat();		
	}

	
}
