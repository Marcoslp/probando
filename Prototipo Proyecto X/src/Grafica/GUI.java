package Grafica;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Logica.Nivel;
import PowerUps.Masacrality;
import PowerUps.PowerUp;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;

public class GUI extends JFrame {
	/**
	 * 
	 */
	private JLabel miTiempo;
	private static final long serialVersionUID = 1L;
	private Nivel miNivel;
	private JPanel contentPane;
	private JLabel puntaje;

	/**
	 * Create the frame.
	 */

	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 480);
		contentPane = new JPanel();
		this.setTitle("Bomberman: Digimon Edition");
		
		addKeyListener(new KeyAdapter() {
			
			public void keyPressed(KeyEvent e) {
				 switch(e.getKeyCode()){
					case KeyEvent.VK_LEFT:
						miNivel.obtenerBomberman().establecerDireccion(0);
						break;
			   		case KeyEvent.VK_RIGHT:
						miNivel.obtenerBomberman().establecerDireccion(1);
			   			break;
			   		case KeyEvent.VK_UP:
						miNivel.obtenerBomberman().establecerDireccion(2);
			   			break;
			   		case KeyEvent.VK_DOWN:
						miNivel.obtenerBomberman().establecerDireccion(3);
			   			break;		
			   		case KeyEvent.VK_SPACE:{
			   			miNivel.obtenerBomberman().establecerPuseBomba(true);			   		
			   		}
			   		break;
			   		
			   		case KeyEvent.VK_A:{
			   			miNivel.obtenerBomberman().establecerModoDios(true);
			   			miNivel.obtenerBomberman().DuplicarVelocidad();
			   			
			   		}
			   }
			}
			
		});
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//this.setUndecorated(true);
		contentPane.setLayout(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		miTiempo = new JLabel("Tiempo : 00 : 00 : 00");
		miTiempo.setFont(new java.awt.Font("Tahoma", 1, 11));
		miTiempo.setForeground(new java.awt.Color(2, 2, 2));
		miTiempo.setBounds(0, 417, 172, 25);
		contentPane.add(miTiempo);
		
		puntaje = new JLabel("Puntaje : 0");
		puntaje.setFont(new java.awt.Font("Tahoma", 1, 11));
		puntaje.setForeground(new java.awt.Color(2, 2, 2));
		puntaje.setBounds(181, 417, 191, 25);
		contentPane.add(puntaje);
		miNivel = new Nivel(this);
		setVisible(true);
	}
	
	public void establecerTiempo(String tiempo){
		miTiempo.setText(tiempo);
	}
	
	public void establecerPuntaje(String puntaje){
		this.puntaje.setText(puntaje);	
	}
}