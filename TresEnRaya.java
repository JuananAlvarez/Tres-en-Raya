import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class TresEnRaya {
	public static void main(String[] args) {
		JFrame frame = new JFrame();

		frame.add(new MiLamina());

		// Añadimos un titulo a la ventana
		frame.setTitle("Ventana principal");

		// Indicamos la posicion y el tamaño del frame
		frame.setBounds(300, 300, 450, 300);

		// Indicamos el tipo de cierre del frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Hacemos visible el frame
		frame.setVisible(true);

		frame.setResizable(false);
	}

}

class MiLamina extends JPanel {
	JPanel medio = new JPanel();
	boolean turno = true;
	ImageIcon icon1 = new ImageIcon("jugador1.png");
	ImageIcon icon2 = new ImageIcon("jugador2.png");

	public MiLamina() {
		//Asignamos un grid layout a la lamina del medio
		medio.setLayout(new GridLayout(3, 3));
		//La lamina principal tendra un border layout
		setLayout(new BorderLayout());
		//Añadimos las 3 laminas
		add(new parteArriba(), BorderLayout.NORTH);
		add(medio, BorderLayout.CENTER);
		add(new parteAbajo(), BorderLayout.SOUTH);
	}

	class parteArriba extends JPanel {
		
		JButton[][] botones = new JButton[3][3];

		//Constructor de la parte de arriba donde se hace el action listener en el que se llama el metodo para crear los botones cada vez que le damos al comenzar
		public parteArriba() {

			JLabel texto = new JLabel("Bienvenido al Tres En Raya");
			setLayout(new FlowLayout(FlowLayout.LEFT));
			texto.setOpaque(true);
			texto.setBorder(new EmptyBorder(10, 10, 10, 40));
			texto.setFont(new Font("Arial", Font.BOLD, 20));
			add(texto);
			JButton botonComenzar = new JButton("Comenzar");
			add(botonComenzar);

			botonComenzar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					crearBotones();

				}
			});

		}
		//Metodo para crear los botones recorriendo el for del array bidimensional de los botones

		public void crearBotones() {

			medio.removeAll();
			for (int i = 0; i < 3; i++)
				for (int j = 0; j < 3; j++) {
					botones[i][j] = new JButton();
					medio.add(botones[i][j]);

					// medio.repaint();
					// medio.revalidate();
					medio.updateUI();
					botones[i][j].addActionListener(new claseCasilla());

				}

		}
		//Clase en la que ejecutaremos los eventos para saber si hemos ganado o hemos quedado empate y que al pulsar los botones nos salga una X o una O
		class claseCasilla implements ActionListener {

			@Override
			
			//Action 
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < botones.length; i++) {
					for (int j = 0; j < botones.length; j++) {
						if (e.getSource() == botones[i][j]) {
							turnoJugador(botones[i][j]);

						}

					}
				}
				if (comprobarJuego()) {
					if (turno) {
						JOptionPane.showMessageDialog(medio, "Ha ganado el jugador X");
					} else {
						JOptionPane.showMessageDialog(medio, "Ha ganado el jugador O");
					}
					seguirJugando();

				} else if (juegoEmpatado()) {
					JOptionPane.showMessageDialog(medio, "El juego ha terminado en empate");
					seguirJugando();
				}

			}

		}
		
		//Metodo que al finalizar nos pregunta si queremos seguir jugando
		public void seguirJugando() {

			int opcion = JOptionPane.showOptionDialog(medio, "¿Deseas seguir jugando?", "Seguir Jugando",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[] { "Si", "No" },
					"opcion 1");

			switch (opcion) {
			case 0:
				medio.removeAll();
				medio.updateUI();
				crearBotones();
				break;
			case 1:
				JOptionPane.showMessageDialog(medio, "Gracias por jugar");
				System.exit(0);
				break;
			}
		}
		//Metodo para comprobar si hemos ganado o no y nos coloree el sitio en el que hemos ganado
		public boolean comprobarJuego() {
			for (int i = 0; i < botones.length; i++) {
				if (botones[0][i].isEnabled() == false && botones[1][i].isEnabled() == false
						&& botones[2][i].isEnabled() == false) {
					if (botones[0][i].getIcon().toString().equals(botones[1][i].getIcon().toString())
							&& botones[0][i].getIcon().toString().equals(botones[2][i].getIcon().toString())) {
						botones[0][i].setBackground(Color.orange);
						botones[1][i].setBackground(Color.orange);
						botones[2][i].setBackground(Color.orange);

						return true;
					}
					
					
				} if (botones[i][0].isEnabled() == false && botones[i][1].isEnabled() == false
						&& botones[i][2].isEnabled() == false) {
					if (botones[i][0].getIcon().toString().equals(botones[i][1].getIcon().toString())
							&& botones[i][0].getIcon().toString().equals(botones[i][2].getIcon().toString())) {
						botones[i][0].setBackground(Color.orange);
						botones[i][1].setBackground(Color.orange);
						botones[i][2].setBackground(Color.orange);
						return true;
					}
					
					
				} if (botones[0][0].isEnabled() == false && botones[1][1].isEnabled() == false
						&& botones[2][2].isEnabled() == false) {
					if (botones[0][0].getIcon().toString().equals(botones[1][1].getIcon().toString())
							&& botones[0][0].getIcon().toString().equals(botones[2][2].getIcon().toString())) {
						botones[0][0].setBackground(Color.orange);
						botones[1][1].setBackground(Color.orange);
						botones[2][2].setBackground(Color.orange);
						return true;
					}
					

				} if (botones[0][2].isEnabled() == false && botones[1][1].isEnabled() == false
						&& botones[2][0].isEnabled() == false) {
					if (botones[0][2].getIcon().toString().equals(botones[1][1].getIcon().toString())
							&& botones[0][2].getIcon().toString().equals(botones[2][0].getIcon().toString())) {
						botones[0][2].setBackground(Color.orange);
						botones[1][1].setBackground(Color.orange);
						botones[2][0].setBackground(Color.orange);
						return true;
					}
				}

			}
			return false;
		}
		//Metodo para saber si hemos empatado
		public boolean juegoEmpatado() {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (botones[i][j].isEnabled() == true) {
						
						return false;
					}
				}
			}
			
			return true;

		}
		//Metodo para saber el turno de cada jugador
		public boolean turnoJugador(JButton boton) {

			if (turno) {

				boton.setIcon(icon1);
				boton.setEnabled(false);
				turno = false;
			} else {

				boton.setIcon(icon2);
				boton.setEnabled(false);
				turno = true;
			}

			return turno;
		}

	}
	//Clase para la parte de abajo
	class parteAbajo extends JPanel {
		public parteAbajo() {
			JLabel texto = new JLabel("Autor: Juan Antonio Álvarez Redondo");
			setLayout(new FlowLayout(FlowLayout.LEFT));
			setBackground(Color.BLACK);
			texto.setForeground(Color.WHITE);
			texto.setBorder(new EmptyBorder(10, 0, 10, 0));
			texto.setFont(new Font("Arial", Font.ITALIC, 12));
			add(texto);

		}
	}
}
