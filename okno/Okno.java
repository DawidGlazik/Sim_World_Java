package okno;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import rosliny.*;
import swiat.*;

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
	            Color kolor = new Color(255, 0, 0);
	            
	            if (plansza[i][j] instanceof Pole) {
	                kolor = new Color(255, 255, 255);
	            } else if (plansza[i][j] instanceof Czlowiek) {
	                kolor = new Color(120, 120, 120);
	            } else if (plansza[i][j] instanceof Zwierze) {
	                kolor = new Color(0, 255, 0);
	            } else if (plansza[i][j] instanceof Roslina) {
	                kolor = new Color(0, 0, 255);
	            }
	            
	            panel.setBackground(kolor);
	            panel.setPreferredSize(new Dimension(20, 20));
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

	private void dodajOrganizm() {
		
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
        dodajOrganizm();
        System.out.println("Kliknięto kwadrat [" + kolumna + ", " + wiersz + "]");
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
