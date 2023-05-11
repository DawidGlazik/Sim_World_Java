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
        frame.setSize(50 * szerokosc + 100, 50 * wysokosc + 100);
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
	    panelPlanszy.setLayout(new GridLayout(wysokosc, szerokosc));
	    panelPlanszy.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	    panelPlanszy.addMouseListener(this);
	    for (int i = 0; i < wysokosc; i++) {
	        for (int j = 0; j < szerokosc; j++) {
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

	
	private void menu() {
		panelMenu = new JPanel();
        panelMenu.setLayout(new BorderLayout());

        JButton button1 = new JButton("Tura");
        JButton button2 = new JButton("Zapisz");
        JButton button3 = new JButton("Wczytaj");
        JButton button4 = new JButton("Calopalenie");

        for (int i=0; i<symulacja.organizmy.size(); i++) {
			if (symulacja.organizmy.get(i) instanceof Czlowiek) {
				Czlowiek tmp = (Czlowiek) symulacja.organizmy.get(i);
				if (tmp.getTrwanie() != 5) {
					button4.setEnabled(false);
				}
				else {
					button4.setEnabled(true);
				}
				break;
			}
			button4.setEnabled(false);
		}
        
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	symulacja.wykonajTure(0);
            	symulacja.konsola = "";
            	odswiez();
            }
        });
        
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	symulacja.zapisz();
            }
        });
        
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	symulacja.wczytaj();
            	odswiez();
            }
        });
        
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	symulacja.rozpocznijCalopalenie();
            	System.out.println(symulacja.konsola);
            	symulacja.konsola = "";
            	odswiez();
            }
        });
        
        JLabel napis = new JLabel("Tura:"+symulacja.getTura());
        panelMenu.add(napis, BorderLayout.WEST);
        
        JPanel pomoc = new JPanel(new FlowLayout());
        pomoc.add(button1);
        pomoc.add(button2);
        pomoc.add(button3);
        pomoc.add(button4);
        panelMenu.add(pomoc, BorderLayout.CENTER);
        
        frame.add(panelMenu, BorderLayout.SOUTH);
	}

	@Override
    public void keyPressed(KeyEvent e) {
		for (int i=0; i<symulacja.organizmy.size(); i++) {
			if (symulacja.organizmy.get(i) instanceof Czlowiek) {
				int keyCode = e.getKeyCode();

		        if (keyCode == KeyEvent.VK_UP) {
		        	symulacja.wykonajTure(1);
		        	symulacja.konsola = "";
		        	odswiez();
		        } else if (keyCode == KeyEvent.VK_DOWN) {
		        	symulacja.wykonajTure(2);
		        	symulacja.konsola = "";
		        	odswiez();
		        } else if (keyCode == KeyEvent.VK_LEFT) {
		        	symulacja.wykonajTure(3);
		        	symulacja.konsola = "";
		        	odswiez();
		        } else if (keyCode == KeyEvent.VK_RIGHT) {
		        	symulacja.wykonajTure(4);
		        	symulacja.konsola = "";
		        	odswiez();
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
                    JButton clickedButton = (JButton) e.getSource();
                    String selectedOption = clickedButton.getText();
                    if (selectedOption.equals("Barszcz Sosnowskiego")) {
                		symulacja.dodajOrganizm(new BarszczSosnowskiego(symulacja, new Polozenie( x, y )));
        			}
        			else if (selectedOption.equals("Guarana")) {
        				symulacja.dodajOrganizm(new Guarana(symulacja, new Polozenie( x, y )));
        			}
        			else if (selectedOption.equals("Mlecz")) {
        				symulacja.dodajOrganizm(new Mlecz(symulacja, new Polozenie( x, y )));
        			}
        			else if (selectedOption.equals("Trawa")) {
        				symulacja.dodajOrganizm(new Trawa(symulacja, new Polozenie( x, y )));
        			}
        			else if (selectedOption.equals("Wilcze Jagody")) {
        				symulacja.dodajOrganizm(new WilczeJagody(symulacja, new Polozenie( x, y )));
        			}
        			else if (selectedOption.equals("Antylopa")) {
        				symulacja.dodajOrganizm(new Antylopa(symulacja, new Polozenie( x, y )));
        			}
        			else if (selectedOption.equals("Lis")) {
        				symulacja.dodajOrganizm(new Lis(symulacja, new Polozenie( x, y )));
        			}
        			else if (selectedOption.equals("Owca")) {
        				symulacja.dodajOrganizm(new Owca(symulacja, new Polozenie( x, y )));
        			}
        			else if (selectedOption.equals("Wilk")) {
        				symulacja.dodajOrganizm(new Wilk(symulacja, new Polozenie( x, y )));
        			}
        			else if (selectedOption.equals("Zolw")) {
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
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
        int y = e.getY();
        int szerokoscKwadratu = panelPlanszy.getWidth() / szerokosc;
        int wysokoscKwadratu = panelPlanszy.getHeight() / wysokosc;
        int kolumna = x / szerokoscKwadratu;
        int wiersz = y / wysokoscKwadratu;
        if (kolumna < szerokosc && wiersz < wysokosc) {
        	dodajOrganizm(wiersz, kolumna);
        	odswiez();
        }
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
