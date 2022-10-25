import java.util.*;
import java.io.*;

public class Jugador extends Thread
{
	private Contenedor tablero;
	private int turno;
	private char ficha;
	private String algoritmo;
	
	public Jugador (Contenedor _teclado, int turno, String _nombre, int alg, char ficha) //Se asignan valores
	{
		super(_nombre);
		tablero = _teclado;
		this.turno = turno;
		this.ficha = ficha;
		
		switch(alg)
		{
			case 1:
				algoritmo = "filas";
				break;
			case 2:
				algoritmo = "filas_alt";
				break;
			case 3:
				algoritmo = "columnas";
				break;
			case 4:
				algoritmo = "columnas_alt";
				break;
			case 5:
				algoritmo = "diagonal";
				break;
			case 6:
				algoritmo = "diagonal_alt";
				break;
			default:
				System.out.println("Error al seleccionar algoritmo! "+alg); //En caso de que pase algo inesperado en la ventana, forzar salida.
				System.exit(0);
				break;
		}
	}
	public void run()
	{
		for (int i = 0; i<5; i++) //Cada hilo mueve un máximo de 5 veces, o hasta que haya ganador
		{
			tablero.esperoTurno(turno);
			
			if (tablero.isTerminado()) //Comprueba si el otro hilo ha terminado
				break;
			
			System.out.println (this.getName()+": Me toca mover...");
			System.out.print (this.getName()+": Pongo "+ficha+" en ");
			
			tablero.mover(ficha, algoritmo); //Coloca ficha con el algoritmo seleccionado
			tablero.mostrar();
			
			if (tablero.comprobar(ficha)) //Comprueba si el hilo ha ganado tras colocar ficha
			{
				System.out.println("Linea!! "+this.getName()+" es el ganador!");
				tablero.terminar(); //Si ha ganado, termina de jugar
				break;
			}
			try {
				sleep (200);
			} catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			
			tablero.cambioTurno();
		}
		tablero.cambioTurno();
	}
}
