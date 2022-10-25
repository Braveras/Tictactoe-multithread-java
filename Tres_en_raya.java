import java.util.*;
import java.io.*;
import javax.swing.JOptionPane;

public class Tres_en_raya 
{
	public static void main(String[] args)
	{
			// Asignamos un algoritmo a seguir por cada hilo.
		Integer[] opciones = {1, 2, 3, 4, 5, 6};
        int n1 = (Integer)JOptionPane.showInputDialog(null, "Algoritmo para el PRIMER jugador?\n1 -> Filas (izquierda - derecha)\n2 -> Filas (derecha - izquierda)\n3 -> Columnas (arriba - abajo)\n4 -> Columnas (abajo - arriba)\n5 -> Diagonal (arriba - abajo & izq - dcha)\n6 -> Diagonal (abajo - arriba & dcha - izq)", 
                "Selecciona", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
		int n2 = (Integer)JOptionPane.showInputDialog(null, "Algoritmo para el SEGUNDO jugador?\n1 -> Filas (izquierda - derecha)\n2 -> Filas (derecha - izquierda)\n3 -> Columnas (arriba - abajo)\n4 -> Columnas (abajo - arriba)\n5 -> Diagonal (arriba - abajo & izq - dcha)\n6 -> Diagonal (abajo - arriba & dcha - izq)", 
                "Selecciona", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
		
		Contenedor tablero = new Contenedor(3);
		Jugador jugador1 = new Jugador(tablero,0,"Player 1", n1, 'X'); //init del jugador, añadiendo algoritmo y ficha
		Jugador jugador2 = new Jugador(tablero,1,"Player 2", n2, 'O');
		jugador1.start();
		jugador2.start();
	}
	
}

