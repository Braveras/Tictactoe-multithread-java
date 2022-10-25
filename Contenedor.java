import java.util.*;
import java.io.*;

public class Contenedor
{
	private int dato;
	private char tablero[][];
	private int filas,columnas;
	private int turno_actual;
	private boolean terminado;
	
	public Contenedor (int _rango) 
	{
		int f,c;
		tablero = new char[_rango][_rango];
		filas=columnas=_rango;
		for (f=0;f<filas;f++)
			for (c=0;c<columnas;c++)
				tablero[f][c]=' ';
		turno_actual=0;
	}
	
	public synchronized void esperoTurno(int _turno)
	{
		if (turno_actual!=_turno)
		{
			try 
			{
				wait();
			}
			catch (InterruptedException e) { }
		}
	}
	
	public synchronized void cambioTurno()
	{
		turno_actual=turno_actual-1;
		notifyAll();
	}
	
	public synchronized void mover(char ficha, String algoritmo)
	{
		boolean encontrado = false;
		if (algoritmo == "filas")
		{
			bucle:
			for (int i = 0; i < 3; i++) { // Comprobar posiciones vacías por orden de filas
				for (int j = 0; j < 3; j++) {
					if (tablero[i][j] == ' ') {
						System.out.println("la posicion "+i+","+j);
						tablero[i][j] = ficha;
						encontrado = true;
						break bucle;
					}
				}
			}
			if (!encontrado) //Si no ha encontrado posiciones vacías, y no ha declarado ya un ganador, es empate.
			{
				System.out.println("No quedan posiciones en el tablero!\nEsto es un empate.");
				System.exit(0);
			}
		} 
		else if (algoritmo == "columnas")
		{
			bucle:
			for (int i = 0; i < 3; i++) { // Comprobar posiciones vacías por orden de columnas
				for (int j = 0; j < 3; j++) {
					if (tablero[j][i] == ' ') {
						System.out.println("la posicion "+j+","+i);
						tablero[j][i] = ficha;
						encontrado = true;
						break bucle;
					}
				}
			}
			if (!encontrado)
			{
				System.out.println("No quedan posiciones en el tablero!\nEsto es un empate.");
				System.exit(0);
			}
		}
		else if (algoritmo == "diagonal")
		{
			bucle:
			for (int dia = 0; dia < 5; dia++) { // Comprobar posiciones vacías por orden diagonal (de derecha a izquierda, y de arriba abajo, empezando por 0,0)
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						if (i + j == dia && tablero[i][j] == ' ') {
							System.out.println("la posicion "+i+","+j);
							tablero[i][j] = ficha;
							encontrado = true;
							break bucle;
						}
					}
				}
			}
			if (!encontrado)
			{
				System.out.println("No quedan posiciones en el tablero!\nEsto es un empate.");
				System.exit(0);
			}
		}
		else if (algoritmo == "filas_alt")
		{
			bucle:
			for (int i = 2; i > -1; i--) { // Comprobar posiciones vacías por orden de filas invertido
				for (int j = 2; j > -1; j--) {
					if (tablero[i][j] == ' ') {
						System.out.println("la posicion "+i+","+j);
						tablero[i][j] = ficha;
						encontrado = true;
						break bucle;
					}
				}
			}
			if (!encontrado) //Si no ha encontrado posiciones vacías, y no ha declarado ya un ganador, es empate.
			{
				System.out.println("No quedan posiciones en el tablero!\nEsto es un empate.");
				System.exit(0);
			}
		}
		else if (algoritmo == "columnas_alt")
		{
			bucle:
			for (int i = 2; i > -1; i--) { // Comprobar posiciones vacías por orden de columnas invertido
				for (int j = 2; j > -1; j--) {
					if (tablero[j][i] == ' ') {
						System.out.println("la posicion "+j+","+i);
						tablero[j][i] = ficha;
						encontrado = true;
						break bucle;
					}
				}
			}
			if (!encontrado)
			{
				System.out.println("No quedan posiciones en el tablero!\nEsto es un empate.");
				System.exit(0);
			}
		}
		else if (algoritmo == "diagonal_alt")
		{
			bucle:
			for (int dia = 4; dia > -1; dia--) { // Comprobar posiciones vacías por orden diagonal invertido
				for (int i = 2; i > -1; i--) {
					for (int j = 2; j > -1; j--) {
						if (i + j == dia && tablero[i][j] == ' ') {
							System.out.println("la posicion "+i+","+j);
							tablero[i][j] = ficha;
							encontrado = true;
							break bucle;
						}
					}
				}
			}
			if (!encontrado)
			{
				System.out.println("No quedan posiciones en el tablero!\nEsto es un empate.");
				System.exit(0);
			}
		}
    }
	
	public synchronized void mostrar() {
		System.out.println(Arrays.deepToString(tablero).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
	}
	
	public synchronized boolean comprobar(char ficha){ 
        for(int i=0; i<=2;i++){
            if(tablero[0][i]==ficha&&tablero[1][i]==ficha&&tablero[2][i]==ficha)
                return true;
            if(tablero[i][0]==ficha&&tablero[i][1]==ficha&&tablero[i][2]==ficha)
                return true;
        }
        if (tablero[0][0] == ficha && tablero[1][1] == ficha && tablero[2][2] == ficha)
            return true;
        return tablero[0][2] == ficha && tablero[1][1] == ficha && tablero[2][0] == ficha;
    }
	
	public synchronized boolean isTerminado() {
		return terminado;
	}
	
	public synchronized void terminar() {
		terminado = true;
	}
}