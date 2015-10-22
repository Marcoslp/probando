package L�gica;

import Grafica.GUI;
import Personajes.*;

import java.util.Random;

import PowerUps.Fatality;
import PowerUps.PowerUp;
import PowerUps.SpeedUp;
import Threads.ContadorThread;


public class Nivel {
	
	private static final int largo=31;
	private static final int ancho=13;
	
	//Atributos
	
	protected int marcadorTiempo;
	protected int marcadorPuntos;
	protected Bomberman miBomberman;
	protected Enemigo[] misEnemigos;
	protected Celda [][] misCeldas;
	protected PowerUp [] misPowerUps;
	
	//Constructor
	
	public Nivel (GUI miGui) {
		miBomberman = new Bomberman(1,1,this);
		miGui.add(miBomberman.obtenerGrafico().obtenerImagenActual());
		miGui.getContentPane().setComponentZOrder(miBomberman.obtenerGrafico().obtenerImagenActual(), 0);//PARA PONER EL LABEL DEL BOMBERMAN POR ENCIMA DEL PISO
		//misPowerUps = p;
		marcadorPuntos=0;
		
		//Ver como modelar el marcador de tiempo
		
		
		//Creo el arreglo de celdas vac�o
		misCeldas= new Celda[largo][ancho];
		for(int i=0; i<largo; i++){
			for(int j=0; j<ancho; j++){
				misCeldas[i][j]= new Celda(i,j,this);
				miGui.add(misCeldas[i][j].obtenerGraficos().obtenerImagenActual());
				miGui.getContentPane().setComponentZOrder(misCeldas[i][j].obtenerGraficos().obtenerImagenActual(), 1);
				
			}
		}
		
		//Inicializo los bordes con paredes indestructibles
		
		for(int i=0; i<largo; i++){ //hasta 31
			
			misCeldas[i][0].establecerPared(new ParedIndestructible(misCeldas[i][0]));
			
			misCeldas[i][12].establecerPared(new ParedIndestructible(misCeldas[i][12]));
		}
		
		for(int j=0; j<ancho; j++){
			
			misCeldas[0][j].establecerPared(new ParedIndestructible(misCeldas[0][j]));
			
			misCeldas[30][j].establecerPared(new ParedIndestructible(misCeldas[30][j]));
		}
		
		
		//Seteo al Bomberman en la posici�n inicial
		misCeldas[1][1].setBomberman(miBomberman);
		
		//Creo las paredes indestructibles dentro de la grilla
		int cantParedes=0;
		int largoAux=2;
		int anchoAux=2;
			while(largoAux<largo-1){
				while(anchoAux<ancho-1){
				misCeldas[largoAux][anchoAux].establecerPared(new ParedIndestructible(misCeldas[largoAux][anchoAux]));
				anchoAux+=2;
				cantParedes++;
				}
				largoAux+=2;
				anchoAux=2;
			}
			
			Random rnd= new Random();
			boolean termine=false;
			Pared paredAux;
			int aux=0;
			// INICIALIZACION PAREDES DESTRUCTIBLES
			while(!termine){
				int Px= rnd.nextInt(30);
				int Py= rnd.nextInt(12);
				if(misCeldas[Px][Py].obtenerPared() == null && (Px!= 1 && Py!=1) && (Px!= 1 && Py!=2) && (Px!= 1 && Py!=5)){ //EVITA QUE SE PONGA ARRIBA DEL BOMBERMAN Y LOS POWERUPS, PROVISORIO. SE HARIA UN PRIVADO QUE CONTROLE MAS POSICIONES PROHIBIDAS
					paredAux= new ParedDestructible(misCeldas[Px][Py]);
					misCeldas[Px][Py].establecerPared(paredAux);
					aux++;
				}
				if(aux == (cantParedes/2))
					termine=true;
			}
			//SE INICILIZAN ENEMIGOS
			//ROGULOS
			misEnemigos = new Enemigo [6];
			termine = false;
			int i = 0;
			while (i < 3){
				int Ex= rnd.nextInt(30);
				int Ey= rnd.nextInt(12);
				if(misCeldas[Ex][Ey].obtenerPared() == null){
					misEnemigos[i] = new Rogulo (Ex,Ey,this);
					misCeldas[Ex][Ey].a�adirEnemigo(misEnemigos[i]);
					miGui.add(misEnemigos[i].obtenerGrafico().obtenerImagenActual());
					miGui.getContentPane().setComponentZOrder(misEnemigos[i].obtenerGrafico().obtenerImagenActual(), 0);
					misEnemigos[i].start();
					i++;					
					}
				}
				
			//ALTAIR
			while (i<5) {
				int Fx= rnd.nextInt(30);
				int Fy= rnd.nextInt(12);
				if(misCeldas[Fx][Fy].obtenerPared() == null){
					misEnemigos[i] = new Altair (Fx,Fy,this);
					misCeldas[Fx][Fy].a�adirEnemigo(misEnemigos[i]);
					miGui.add(misEnemigos[i].obtenerGrafico().obtenerImagenActual());
					miGui.getContentPane().setComponentZOrder(misEnemigos[i].obtenerGrafico().obtenerImagenActual(), 0);//PARA PONER EL LABEL DE LOS ENEMIGOS POR ENCIMA DEL PISO
					misEnemigos[i].start();
					i++;					
					}
			}
			/*
			// SIRIUS
			misEnemigos[5] = new Sirius (29,11);
			}*/
			
			//SE INICIALIZAN LOS POWERUPS
			//POR SER UN PROTOTIPO DE PRUEBA SOLAMENTE CREO 2 Y LOS AGREGO A POSICIONES ARBITRARIAS
			//SINO SERIA NECESARIO PODER DESTRUIR LAS PAREDES DESTRUCTIBLES
			//INICIALIZO EL SPEED UP PROVISORIO
			this.misPowerUps = new PowerUp[11]; 
			
			this.misPowerUps[0] = new SpeedUp(1,2,this.misCeldas[1][2]);
		    misCeldas[1][2].establecerPowerUp(misPowerUps[0]);
			miGui.add(this.misPowerUps[0].obtenerImagen());
		    miGui.getContentPane().setComponentZOrder(misPowerUps[0].obtenerImagen(), 0); //PARA PONER EL LABEL DEL POWERUP POR ENCIMA DEL PISO
		    
		    //INICIALIZO EL FATALITY
		    
		   	this.misPowerUps[1] = new Fatality(1,5,this.misCeldas[1][5]);
		    misCeldas[1][5].establecerPowerUp(misPowerUps[1]);
			miGui.add(this.misPowerUps[1].obtenerImagen());
		    miGui.getContentPane().setComponentZOrder(misPowerUps[1].obtenerImagen(), 0);//PARA PONER EL LABEL DEL POWERUP POR ENCIMA DEL PISO
			
	}
	
	//Operaciones
	
	public void explosion (Posicion e, Bomba bomb) {
		ContadorThread contador = new ContadorThread (3,miBomberman);
		contador.start();	
	}
	
	public void incrementarPuntuacion (int x) {
		marcadorPuntos += x;
	}
	
	public void bombermanPisoPowerUp () {
		
	}
	
	public Bomberman obtenerBomberman () {
		return miBomberman;
	}
	
	public void moverBomberman (int dir){
		int x = miBomberman.obtenerPosicion().obtenerX();
		int y = miBomberman.obtenerPosicion().obtenerY();
		switch(dir){
			case 0:{
				misCeldas[x-1][y].recibirBomberman(miBomberman);
			}
			break;
			case 1:{
				misCeldas[x+1][y].recibirBomberman(miBomberman);
			}
			break;
			case 2:{
				misCeldas[x][y-1].recibirBomberman(miBomberman);
			}
			break;
			case 3:{
				misCeldas[x][y+1].recibirBomberman(miBomberman);
			}
			break;
		}
	}
		
	
	public void moverEnemigo (Enemigo e, int dir) {
		int x = e.obtenerPosicion().obtenerX();
		int y = e.obtenerPosicion().obtenerY();
		switch(dir){
			case 0:{
				misCeldas[x-1][y].recibirEnemigo(e);
			}
			break;
			case 1:{
				misCeldas[x+1][y].recibirEnemigo(e);
			}
			break;
			case 2:{
				misCeldas[x][y-1].recibirEnemigo(e);
			}
			break;
			case 3:{
				misCeldas[x][y+1].recibirEnemigo(e);
			}
			break;
		}
	}

	public void matarBomberman() {
		miBomberman.morir();	
	}

	public void matarEnemigo(Enemigo e) {
		
	}
	
	public Celda obtenerCelda(int i, int j){
		return misCeldas[i][j];
	}
	
	public int obtenerMarcadorTiempo(){
		return marcadorTiempo;		
	}

	public void eliminarPowerUp(PowerUp miPowerUp) {
		boolean cortar = false;
		for(int i = 0; i < this.misPowerUps.length && !cortar ; i++){
			cortar = miPowerUp == this.misPowerUps[i];
			if(cortar){
				this.misPowerUps[i] = null; //BUSCO EL POWERUP EN EL ARREGLO Y LO QUITO				
			}
		}		
	}
}
