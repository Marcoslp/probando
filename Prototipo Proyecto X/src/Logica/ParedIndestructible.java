package Logica;

/**
 * Clase que modela la logica de una pared indestructible
 * @author Hern�n Pocchiola, Marcos Leguizam�n, Jos� Ochoa
 */

import Personajes.Bomberman;
import Personajes.Enemigo;


public class ParedIndestructible extends Pared{
	
	//Atributos
	
	//Constructor
	
	public ParedIndestructible (Celda c) {
		super(c);
	}
	
	//Operaciones
	
	/**
	 * M�todo que intenta destruir una pared indestructible
	 */
	
	public void destruirPared () {
		miCelda.obtenerGraficos().establecerimagenActual(1);
	}
		
	public void recibirBomberman (Bomberman b, int dir) {
	}

	public void recibirEnemigo (Enemigo e, int dir) {
		//DEBERIA CAMBIAR LA IMAGEN DEL ENEMIGO
	}

	
	public void establecerImagen() {
		
		miCelda.obtenerGraficos().establecerimagenActual(1); 
		//ELIGE LA IMAGEN
		//miCelda.obtenerGraficos().obtenerImagenActual().setBounds(miCelda.obtenerPosicion().obtenerX()*32, miCelda.obtenerPosicion().obtenerY()*32, miCelda.obtenerGraficos().obtenerAncho(), miCelda.obtenerGraficos().obtenerAlto());
		
	}
}