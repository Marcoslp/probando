package PowerUps;

import Personajes.Bomberman;


public class Fatality extends PowerUp{
	
	//Atributos
	
	//Constructor
	
	public Fatality (int x, int y) {
		
		super(x,y);
		
	}
	
	//Operaciones
	
	public void empower (Bomberman b) {
		b.obtenerBomba().duplicarAlcance();
	}

}
