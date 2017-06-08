package proj.checkers.view;

import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import proj.checkers.controller.GameController;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;

public class MainView {
	public static void main(String args[]) {
		Window mainWindow = new Window();
		mainWindow.setVisible(true);
	}
}

class Window extends Frame implements ActionListener, MouseListener {

	public void endNewGame() {
		int dialogResult = JOptionPane.showConfirmDialog(null,
				"Koniec gry. Czy chcesz uruchomiæ now¹ grê?", "Warning",
				JOptionPane.YES_NO_OPTION);
		if (dialogResult == JOptionPane.YES_OPTION) {
			gc.newGame();
		}
	}

	public Window() {
		super("Warcaby");
		setLayout(null);
		setSize(870, 550);
		setLocation(300, 100);
		setFont(new Font("Arial", 0, 16));
		setResizable(false);
		setBackground(Color.WHITE);
		buttonNewGame = new JButton();
		buttonNewGame.setOpaque(false);
		buttonNewGame.setContentAreaFilled(false);
		buttonNewGame.setBorderPainted(false);
		buttonNewGame.setBounds(528, 309, 246, 90);
		buttonNewGame.addActionListener(this);
		add(buttonNewGame);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		addMouseListener(this);

	}

	public void update(Graphics g) {
		paint(g);
	}

	public void paint(Graphics g) {
		drawBoard(g);
	}

	public void drawBoard(Graphics g) {
		Image img = createImage(getSize().width, getSize().height);

		Graphics2D g2 = (Graphics2D) img.getGraphics();
		try {
			BufferedImage logo = ImageIO.read(new File("logo.jpg"));
			g2.drawImage(logo, 0, 0, this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(Color.black);
		g2.fillRect(18, 38 + 110, 322, 322);
		for (int i = 0; i < 8; i++) {

			for (int j = 0; j < 8; j++) {
				if (!gc.checkIfPermitted(i, j))
					g2.setColor(new Color(250, 250, 250)); // jasny
				else
					g2.setColor(new Color(53, 184, 95)); // ciemny

				g2.fillRect(20 + 40 * j, 110 + 40 + 40 * i, 38, 38);

				if ((gc.checkIfNoDraught(i, j))) {
					g2.setColor(Color.black);
					g2.fillOval(21 + 40 * j, 110 + 41 + 40 * i, 36, 36);
					if (gc.checkIfDroughtWhite(i, j)) {
						g2.setColor(new Color(245, 240, 240));
					}

					else {
						g2.setColor(new Color(90, 90, 90));

					}

					g2.fillOval(23 + 40 * j, 110 + 43 + 40 * i, 32, 32);
					if (gc.ifCrownedDraughtsman(i, j)) {
						g2.setColor(Color.RED);
						g2.fillOval(26 + 40 * j, 110 + 46 + 40 * i, 26, 26);
					}
				}
			}
		}

		g.drawImage(img, 100, 10, this);

	}

	JButton buttonNewGame;

	public void actionPerformed(ActionEvent ev) {
		Object cel = ev.getSource();

		if (cel == buttonNewGame) {
			gc.newGame();
			repaint();
		}

	}

	boolean clickedMouse = false;

	public void mouseClicked(MouseEvent ev) {

	}

	GameController gc = new GameController();

	public void mousePressed(MouseEvent ev) {
		clickedMouse = true;
		gc.verifyAndSet(ev.getY(), ev.getX());

	}

	public void mouseReleased(MouseEvent ev) {

		gc.verifyAndCheckMove(ev.getX(), ev.getY());

		if (gc.checkIfEndOfGame()) {
			endNewGame();
		}
		repaint();
	}

	public void mouseEntered(MouseEvent ev) {
	}

	public void mouseExited(MouseEvent ev) {
	}

}
