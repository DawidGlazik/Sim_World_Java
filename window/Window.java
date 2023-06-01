package window;
import javax.swing.*;
import javax.swing.border.Border;

import animals.*;
import plants.*;

import java.awt.*;
import java.awt.event.*;

import world.*;

public class Window implements KeyListener, MouseListener{
	private int width,height;
	private Organism board[][];
	private World simulation;
	private JFrame frame = new JFrame();
	private JPanel boardPanel = new JPanel();
	private JPanel menuPanel = new JPanel();
	private JPanel logPanel = new JPanel();
	
	public Window(World world, int height, int width, Organism board[][]) {
		this.simulation = world;
		this.width = height;
		this.height = width;
		this.board = board;
	}
	
	public void print() {
		frame = new JFrame();
		frame.setTitle("Dawid Glazik");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        double Ox = (double) width / height;
        double Oy = 1000 * Ox;
        frame.setSize(1000, (int) Oy);
        board();
        menu();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.addKeyListener(this);
	    frame.setFocusable(true);
	}

	private void refresh() {
		this.board = simulation.board;
	    frame.remove(boardPanel);
	    frame.remove(menuPanel);
	    board();
	    menu();
	    frame.revalidate();
	    frame.repaint();
	}

	private void board() {
		boardPanel = new JPanel();
	    boardPanel.setLayout(new GridLayout(simulation.getHeight(), simulation.getWidth()));
	    boardPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	    boardPanel.addMouseListener(this);
	    for (int i = 0; i < simulation.getHeight(); i++) {
	        for (int j = 0; j < simulation.getWidth(); j++) {
	            JPanel panel = new JPanel();
	            panel.setLayout(new BorderLayout());
	            Color color = new Color(255, 0, 0);
	            JLabel label = new JLabel();
	            if (board[i][j] instanceof Field) {
	                color = new Color(255, 255, 255);
	            } else if (board[i][j] instanceof Human) {
	                color = new Color(120, 120, 120);
	            } else if (board[i][j] instanceof Animal) {
	            	if (board[i][j] instanceof Wolf) label = new JLabel("W");
	            	else if (board[i][j] instanceof Sheep) label = new JLabel("S");
	            	else if (board[i][j] instanceof Fox) label = new JLabel("F");
	            	else if (board[i][j] instanceof Turtle) label = new JLabel("T");
	            	else label = new JLabel("A");
	                color = new Color(246, 246, 68);
	            } else if (board[i][j] instanceof Plant) {
	            	if (board[i][j] instanceof Grass) label = new JLabel("gr");
	            	else if (board[i][j] instanceof Milt) label = new JLabel("m");
	            	else if (board[i][j] instanceof Guarana) label = new JLabel("g");
	            	else if (board[i][j] instanceof DeadlyNightshade) label = new JLabel("d");
	            	else label = new JLabel("h");
	                color = new Color(95, 253, 27);
	            }
	            panel.add(label, BorderLayout.NORTH);
	            panel.setBackground(color);
	            Border border = BorderFactory.createLineBorder(Color.BLACK);
	            panel.setBorder(border);
	            boardPanel.add(panel);
	        }
	    }
	    
	    frame.add(boardPanel, BorderLayout.CENTER);
	}

	private void log() {
		logPanel = new JPanel();
		logPanel.setLayout(new BorderLayout());
		JLabel label = new JLabel();
		label.setText(simulation.console);
		logPanel.add(label, BorderLayout.CENTER);
		frame.add(logPanel, BorderLayout.SOUTH);
	}
	
	private void menu() {
		menuPanel = new JPanel();
        menuPanel.setLayout(new BorderLayout());

        JButton button1 = new JButton("Turn");
        JButton button2 = new JButton("Save");
        JButton button3 = new JButton("Load");
        JButton button4 = new JButton("Calopalenie");

        for (int i=0; i<simulation.organisms.size(); i++) {
			if (simulation.organisms.get(i) instanceof Human) {
				Human tmp = (Human) simulation.organisms.get(i);
				if (tmp.getLasting() != 5) {
					button4.setEnabled(false);
				}
				else {
					button4.setEnabled(true);
				}
				break;
			}
			button4.setEnabled(false);
		}
        
        log();
        menuPanel.add(logPanel, BorderLayout.SOUTH);
        
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	simulation.handleTurn(0);
            	refresh();
            	simulation.console = "Events: ";
            }
        });
        
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (simulation.save()) simulation.console = "Saved.";
            	else simulation.console = "Error occured.";
            	refresh();
            	simulation.console = "Events: ";
            }
        });
        
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (simulation.load() == 1) simulation.console = "Loaded.";
            	else if (simulation.load() == 2) simulation.console = "Error occured.";
            	else simulation.console = "File doesn't exist.";
            	refresh();
            	simulation.console = "Events: ";
            }
        });
        
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	simulation.startCalopalenie();
            	System.out.println(simulation.console);
            	simulation.console = "Events: ";
            	refresh();
            }
        });
        
        JLabel string = new JLabel("Turn:"+simulation.getTurn());
        menuPanel.add(string, BorderLayout.WEST);
        
        JPanel help = new JPanel(new FlowLayout());
        help.add(button1);
        help.add(button2);
        help.add(button3);
        help.add(button4);
        
        menuPanel.add(help, BorderLayout.CENTER);
        
        frame.add(menuPanel, BorderLayout.SOUTH);
	}

	@Override
    public void keyPressed(KeyEvent e) {
		for (int i=0; i<simulation.organisms.size(); i++) {
			if (simulation.organisms.get(i) instanceof Human) {
				int code = e.getKeyCode();

		        if (code == KeyEvent.VK_UP) {
		        	simulation.handleTurn(1);
		        	refresh();
		        	simulation.console = "Events: ";
		        } else if (code == KeyEvent.VK_DOWN) {
		        	simulation.handleTurn(2);
		        	refresh();
		        	simulation.console = "Events: ";
		        } else if (code == KeyEvent.VK_LEFT) {
		        	simulation.handleTurn(3);
		        	refresh();
		        	simulation.console = "Events: ";
		        } else if (code == KeyEvent.VK_RIGHT) {
		        	simulation.handleTurn(4);
		        	refresh();
		        	simulation.console = "Events: ";
		        }
			}
		}
    }

	private void addOrganism(int x, int y) {
		String[] options = {"Wolf", "Sheep", "Fox", "Turtle", "Antelope", "Grass", "Milt", "Guarana", "Deadly Nightshade", "Hogweed"};
        JDialog dialog = new JDialog((JFrame) null, "Choose one", true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        for (String option : options) {
            JButton button = new JButton(option);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton pushed = (JButton) e.getSource();
                    String chosen = pushed.getText();
                    if (chosen.equals("Hogweed")) {
                		simulation.addOrganism(new Hogweed(simulation, new Coords( x, y )));
        			}
        			else if (chosen.equals("Guarana")) {
        				simulation.addOrganism(new Guarana(simulation, new Coords( x, y )));
        			}
        			else if (chosen.equals("Milt")) {
        				simulation.addOrganism(new Milt(simulation, new Coords( x, y )));
        			}
        			else if (chosen.equals("Grass")) {
        				simulation.addOrganism(new Grass(simulation, new Coords( x, y )));
        			}
        			else if (chosen.equals("Deadly Nightshade")) {
        				simulation.addOrganism(new DeadlyNightshade(simulation, new Coords( x, y )));
        			}
        			else if (chosen.equals("Antelope")) {
        				simulation.addOrganism(new Antelope(simulation, new Coords( x, y )));
        			}
        			else if (chosen.equals("Fox")) {
        				simulation.addOrganism(new Fox(simulation, new Coords( x, y )));
        			}
        			else if (chosen.equals("Sheep")) {
        				simulation.addOrganism(new Sheep(simulation, new Coords( x, y )));
        			}
        			else if (chosen.equals("Wolf")) {
        				simulation.addOrganism(new Wolf(simulation, new Coords( x, y )));
        			}
        			else if (chosen.equals("Turtle")) {
        				simulation.addOrganism(new Turtle(simulation, new Coords( x, y )));
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
        int fieldWidth = boardPanel.getWidth() / height;
        int fieldGeight = boardPanel.getHeight() / width;
        int column = x / fieldWidth;
        int row = y / fieldGeight;
        if (column < height && row < width) {
        	if (board[row][column] instanceof Field) {
        		addOrganism(row, column);
        	}else {
        		this.simulation.console += "Slot occupied.";
        	}        	
        	refresh();
        	simulation.console = "Events: ";
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
