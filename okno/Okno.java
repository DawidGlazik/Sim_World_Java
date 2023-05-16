package okno;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

import rosliny.*;
import swiat.*;
import zwierzeta.*;

public class Okno implements KeyListener, MouseListener{
	private int wysokosc,szerokosc;
	private Organizm plansza[][];
	private Swiat symulacja;
	private JFrame frame = new JFrame();
	private JPanel panelPlanszy = new JPanel();
	private JPanel panelMenu = new JPanel();
	private JPanel panelLog = new JPanel();
	
	public Okno(Swiat swiat, int wysokosc, int szerokosc, Organizm plansza[][]) {
		this.symulacja = swiat;
		this.wysokosc = wysokosc;
		this.szerokosc = szerokosc;
		this.plansza = plansza;
	}
	
	public void rysuj() {
		frame = new JFrame();
		frame.setTitle("Dawid Glazik 193069");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        double Ox = (double) wysokosc / szerokosc;
        double Oy = 1000 * Ox;
        frame.setSize(1000, (int) Oy);
        plansza();
        menu();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.addKeyListener(this);
	    frame.setFocusable(true);
	}

	private void odswiez() {
		this.plansza = symulacja.plansza;
	    frame.remove(panelPlanszy);
	    frame.remove(panelMenu);
	    plansza();
	    menu();
	    frame.revalidate();
	    frame.repaint();
	}

	private void plansza() {
		panelPlanszy = new JPanel();
	    panelPlanszy.setLayout(new GridLayout(symulacja.getWysokosc(), symulacja.getSzerokosc()));
	    panelPlanszy.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	    panelPlanszy.addMouseListener(this);
	    for (int i = 0; i < symulacja.getWysokosc(); i++) {
	        for (int j = 0; j < symulacja.getSzerokosc(); j++) {
	            JPanel panel = new JPanel();
	            panel.setLayout(new BorderLayout());
	            Color kolor = new Color(255, 0, 0);
	            JLabel label = new JLabel();
	            if (plansza[i][j] instanceof Pole) {
	                kolor = new Color(255, 255, 255);
	            } else if (plansza[i][j] instanceof Czlowiek) {
	                kolor = new Color(120, 120, 120);
	            } else if (plansza[i][j] instanceof Zwierze) {
	            	if (plansza[i][j] instanceof Wilk) label = new JLabel("W");
	            	else if (plansza[i][j] instanceof Owca) label = new JLabel("O");
	            	else if (plansza[i][j] instanceof Lis) label = new JLabel("L");
	            	else if (plansza[i][j] instanceof Zolw) label = new JLabel("Z");
	            	else label = new JLabel("A");
	                kolor = new Color(246, 246, 68);
	            } else if (plansza[i][j] instanceof Roslina) {
	            	if (plansza[i][j] instanceof Trawa) label = new JLabel("t");
	            	else if (plansza[i][j] instanceof Mlecz) label = new JLabel("m");
	            	else if (plansza[i][j] instanceof Guarana) label = new JLabel("g");
	            	else if (plansza[i][j] instanceof WilczeJagody) label = new JLabel("w");
	            	else label = new JLabel("b");
	                kolor = new Color(95, 253, 27);
	            }
	            panel.add(label, BorderLayout.NORTH);
	            panel.setBackground(kolor);
	            Border ramka = BorderFactory.createLineBorder(Color.BLACK);
	            panel.setBorder(ramka);
	            panelPlanszy.add(panel);
	        }
	    }
	    
	    frame.add(panelPlanszy, BorderLayout.CENTER);
	}

	private void log() {
		panelLog = new JPanel();
		panelLog.setLayout(new BorderLayout());
		JLabel label = new JLabel();
		label.setText(symulacja.konsola);
		panelLog.add(label, BorderLayout.CENTER);
		frame.add(panelLog, BorderLayout.SOUTH);
	}
	
	private void menu() {
		panelMenu = new JPanel();
        panelMenu.setLayout(new BorderLayout());

        JButton przycisk1 = new JButton("Tura");
        JButton przycisk2 = new JButton("Zapisz");
        JButton przycisk3 = new JButton("Wczytaj");
        JButton przycisk4 = new JButton("Calopalenie");

        for (int i=0; i<symulacja.organizmy.size(); i++) {
			if (symulacja.organizmy.get(i) instanceof Czlowiek) {
				Czlowiek tmp = (Czlowiek) symulacja.organizmy.get(i);
				if (tmp.getTrwanie() != 5) {
					przycisk4.setEnabled(false);
				}
				else {
					przycisk4.setEnabled(true);
				}
				break;
			}
			przycisk4.setEnabled(false);
		}
        
        log();
        panelMenu.add(panelLog, BorderLayout.SOUTH);
        
        przycisk1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	symulacja.wykonajTure(0);
            	odswiez();
            	symulacja.konsola = "Zdarzenia: ";
            }
        });
        
        przycisk2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (symulacja.zapisz()) symulacja.konsola = "Pomyslnie zapisano stan symulacji.";
            	else symulacja.konsola = "Wystąpił błąd podczas zapisu do pliku.";
            	odswiez();
            	symulacja.konsola = "Zdarzenia: ";
            }
        });
        
        przycisk3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (symulacja.wczytaj() == 1) symulacja.konsola = "Pomyslnie wczytano stan symulacji.";
            	else if (symulacja.wczytaj() == 2) symulacja.konsola = "Wystąpił błąd podczas wczytywania pliku.";
            	else symulacja.konsola = "Plik nie istnieje. Wczytywanie zakończone niepowodzeniem.";
            	odswiez();
            	symulacja.konsola = "Zdarzenia: ";
            }
        });
        
        przycisk4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	symulacja.rozpocznijCalopalenie();
            	System.out.println(symulacja.konsola);
            	symulacja.konsola = "Zdarzenia: ";
            	odswiez();
            }
        });
        
        JLabel napis = new JLabel("Tura:"+symulacja.getTura());
        panelMenu.add(napis, BorderLayout.WEST);
        
        JPanel pomoc = new JPanel(new FlowLayout());
        pomoc.add(przycisk1);
        pomoc.add(przycisk2);
        pomoc.add(przycisk3);
        pomoc.add(przycisk4);
        
        panelMenu.add(pomoc, BorderLayout.CENTER);
        
        frame.add(panelMenu, BorderLayout.SOUTH);
	}

	@Override
    public void keyPressed(KeyEvent e) {
		for (int i=0; i<symulacja.organizmy.size(); i++) {
			if (symulacja.organizmy.get(i) instanceof Czlowiek) {
				int kod = e.getKeyCode();

		        if (kod == KeyEvent.VK_UP) {
		        	symulacja.wykonajTure(1);
		        	odswiez();
		        	symulacja.konsola = "Zdarzenia: ";
		        } else if (kod == KeyEvent.VK_DOWN) {
		        	symulacja.wykonajTure(2);
		        	odswiez();
		        	symulacja.konsola = "Zdarzenia: ";
		        } else if (kod == KeyEvent.VK_LEFT) {
		        	symulacja.wykonajTure(3);
		        	odswiez();
		        	symulacja.konsola = "Zdarzenia: ";
		        } else if (kod == KeyEvent.VK_RIGHT) {
		        	symulacja.wykonajTure(4);
		        	odswiez();
		        	symulacja.konsola = "Zdarzenia: ";
		        }
			}
		}
    }

	private void dodajOrganizm(int x, int y) {
		String[] opcje = {"Wilk", "Owca", "Lis", "Zolw", "Antylopa", "Trawa", "Mlecz", "Guarana", "Wilcze Jagody", "Barszcz Sosnowskiego"};
        JDialog dialog = new JDialog((JFrame) null, "Wybierz opcję", true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        for (String opcja : opcje) {
            JButton button = new JButton(opcja);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton klikniety = (JButton) e.getSource();
                    String wybrano = klikniety.getText();
                    if (wybrano.equals("Barszcz Sosnowskiego")) {
                		symulacja.dodajOrganizm(new BarszczSosnowskiego(symulacja, new Polozenie( x, y )));
        			}
        			else if (wybrano.equals("Guarana")) {
        				symulacja.dodajOrganizm(new Guarana(symulacja, new Polozenie( x, y )));
        			}
        			else if (wybrano.equals("Mlecz")) {
        				symulacja.dodajOrganizm(new Mlecz(symulacja, new Polozenie( x, y )));
        			}
        			else if (wybrano.equals("Trawa")) {
        				symulacja.dodajOrganizm(new Trawa(symulacja, new Polozenie( x, y )));
        			}
        			else if (wybrano.equals("Wilcze Jagody")) {
        				symulacja.dodajOrganizm(new WilczeJagody(symulacja, new Polozenie( x, y )));
        			}
        			else if (wybrano.equals("Antylopa")) {
        				symulacja.dodajOrganizm(new Antylopa(symulacja, new Polozenie( x, y )));
        			}
        			else if (wybrano.equals("Lis")) {
        				symulacja.dodajOrganizm(new Lis(symulacja, new Polozenie( x, y )));
        			}
        			else if (wybrano.equals("Owca")) {
        				symulacja.dodajOrganizm(new Owca(symulacja, new Polozenie( x, y )));
        			}
        			else if (wybrano.equals("Wilk")) {
        				symulacja.dodajOrganizm(new Wilk(symulacja, new Polozenie( x, y )));
        			}
        			else if (wybrano.equals("Zolw")) {
        				symulacja.dodajOrganizm(new Zolw(symulacja, new Polozenie( x, y )));
        			}
                    dialog.dispose();
                }
            });
            panel.add(button);
        }

        dialog.add(panel);
        dialog.setSize(400, 150);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    
	}
	
    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
        int y = e.getY();
        int szerokoscKwadratu = panelPlanszy.getWidth() / szerokosc;
        int wysokoscKwadratu = panelPlanszy.getHeight() / wysokosc;
        int kolumna = x / szerokoscKwadratu;
        int wiersz = y / wysokoscKwadratu;
        if (kolumna < szerokosc && wiersz < wysokosc) {
        	if (plansza[wiersz][kolumna] instanceof Pole) {
        		dodajOrganizm(wiersz, kolumna);
        	}else {
        		this.symulacja.konsola += "Miejsce zajete";
        	}        	
        	odswiez();
        	symulacja.konsola = "Zdarzenia: ";
        }
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
}
