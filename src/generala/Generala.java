package generala;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Generala {
	public static void main(String[] args) {
		
		Funciones funcion = new Funciones();
		
		JOptionPane.showMessageDialog(null, "vamos a jugar a la generala",
				"Bienvenido", 0,
				new ImageIcon(
				Generala.class.getResource("imgs/gener.png")
				));
		
		String jugarOtraVez= "no";
				
		do {
		int cantidadJugadores= Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de jugadores"));
		String[] jugador = new String[cantidadJugadores];
		for (int i = 0; i < jugador.length; i++) {
			jugador[i]=JOptionPane.showInputDialog("Ingrese el nombre del jugador " + (i+1)); 
		}
		
		String[] jugadasArray = {"unos", "dos", "tres", "cuatros", "cincos", "seis", "escalera", "full", "poker", "generala"};
		String[][] jugadas= new String[cantidadJugadores][10];         // este es para las opciones
		String[][] jugadasPuntaje= new String[cantidadJugadores][10];    //este es para el texto de los puntos
		int[] puntaje = new int[cantidadJugadores];
		boolean[][] generalas=new boolean[cantidadJugadores][2]; // jugador-> 0 0 <-generala simple / jugador 0 1 generala doble
		for (int i = 0; i < jugadas.length; i++) {
			puntaje[i]=0;
			for (int j = 0; j < jugadas[i].length; j++) {
				jugadas[i][j]=jugadasArray[j];
				jugadasPuntaje[i][j]=jugadasArray[j]+": --\n";
			}
			for (int j = 0; j < generalas[i].length; j++) {
				generalas[i][j]= false;
			}
		}

		int[] dados = new int[5];
		
		
		for (int i = 0; i < 10; i++) {         
				boolean generalaServida = false;
				
			for (int j= 0; j<jugador.length;j++) {
				for (int  k= 0;  k< dados.length; k++) {
					dados[k]=(int)(Math.random()*6+1);
				}
				
				funcion.tirardados(dados, jugador[j]);
				boolean servido = true;
			
				if (dados[0]==dados[1] && dados[1]==dados[2] && dados[2]==dados[3] && dados[3]==dados[4]) {
					JOptionPane.showMessageDialog(null, "Generala servida, GANASTE");
					generalaServida=true;
					puntaje[j]= 100000;
					break;	
				}
			
				String subtexto = "\ningrese las letras correspondientes con un espacio entre ambas (ej: 'b d e') 'no' si no desea hacer cambios";
				String cambio=JOptionPane.showInputDialog(null,
						"¿tirar algun dado devuelta? \n" + "   a) " + dados[0] + "    b) " + dados[1] + "    c) " + dados[2] + "    d) " + dados[3] + "    e) " + dados[4] + subtexto,
						"Primer cambio",
						JOptionPane.DEFAULT_OPTION);
			
				if (!cambio.equalsIgnoreCase("no")) {	
					servido = false;
					funcion.cambiodados(cambio, dados);
					funcion.tirardados(dados, jugador[j]);
					cambio=JOptionPane.showInputDialog(null,
							"¿tirar algun dado devuelta (Ultimo intento)? \n" + "a) " + dados[0] + " b) " + dados[1] + " c) " + dados[2] + " d) " + dados[3] + " e) " + dados[4] + subtexto,
							"Ultimo cambio",
							JOptionPane.DEFAULT_OPTION);
					if (!cambio.equalsIgnoreCase("no")) {
						funcion.cambiodados(cambio, dados);
						funcion.tirardados(dados, jugador[j]);
					}
				}
				String textoDados = "Tus dados:    " + dados[0]+ "    " +dados[1]+ "    " + dados[2] + "    " + dados[3] + "    " + dados[4];
				int puntos = 0;
				funcion.ordenarDados(dados);
				boolean dobleEsteTurno = false;
				if (	dados[0]==dados[4]&&
						!generalas[j][1]&&
						generalas[j][0]) {
					JOptionPane.showMessageDialog(null, "Generala doble +100 puntos\nElija una casilla para tachar (no sumara puntos)");
					generalas[j][1]=true;
					dobleEsteTurno = true;
					puntos = 100;
					
				}
				int opcion = JOptionPane.showOptionDialog(null, textoDados, "Turno de "+ jugador[j], 0, JOptionPane.DEFAULT_OPTION, null, jugadas[j], null);

				while(jugadas[j][opcion].equals("")) {
					opcion = JOptionPane.showOptionDialog(null, textoDados, "Turno de "+ jugador[j], 0, JOptionPane.DEFAULT_OPTION, null, jugadas[j], null);
				}
				
				if(!dobleEsteTurno) {
				switch (opcion) {
				case 0,1,2,3,4,5:
					int cantDados=0;
					for (int k = 0; k < dados.length; k++) {
						if(dados[k]==(opcion+1)) {
							cantDados++;
						}
					}
					puntos = cantDados*(opcion+1);
					break;
				case 6:
					if (dados[0]==(dados[1]+1)&&dados[1]==(dados[2]+1)&&
					dados[2]==(dados[3]+1)&&dados[3]==(dados[4]+1)) {
						puntos=20;
						if (servido) {
							puntos+=5;
						}
					}
					break;
				case 7:
					if ((dados[0]==dados[1]&&dados[2]==dados[4])||(dados[0]==dados[2]&&dados[3]==dados[4])) {
						puntos=30;
						if (servido) {
							puntos+=5;
						}
					}
					break;
				case 8:
					if (dados[0]==dados[3]||dados[1]==dados[4]) {
						puntos=40;
						if (servido) {
							puntos+=5;
						}
					}
					break;
				case 9:
					if (dados[0]==dados[4]) {
						puntos=50;
						generalas[j][0]= true;
					}
					break;
				}	
				}
				if (dobleEsteTurno) {
					jugadas[j][opcion]+= " --tachado-- Generala doble";
				}
				jugadasPuntaje[j][opcion]= jugadas[j][opcion]+ ": " + puntos + " puntos\n"; 
				jugadas[j][opcion]="";
				puntaje[j]+=puntos;
				String textoPuntaje="";
				for (int k = 0; k < jugadasPuntaje[j].length; k++) {
					textoPuntaje +=jugadasPuntaje[j][k];
				}
				JOptionPane.showMessageDialog(null, "Puntos de " + jugador[j] + ":\n" + textoPuntaje + "\nTotal: " + puntaje[j] + " puntos");
				
			}
			if (generalaServida) {
				break;
			}
		}
		
		int maxPuntaje = 0;
		String ganador ="";
		
		for (int i = 0; i < puntaje.length; i++) {
			if (puntaje[i]>maxPuntaje) {
				maxPuntaje=puntaje[i];
				ganador = jugador[i];
			}
		}
		JOptionPane.showMessageDialog(null, "El ganador es "+ ganador + " con " + maxPuntaje + " puntos" ,
				"Fin del juego", 0,
				new ImageIcon(
				Generala.class.getResource("imgs/trofeo.png")
				));	
		
		jugarOtraVez=JOptionPane.showInputDialog("Jugar otra vez? (si/no)");
		} while (jugarOtraVez.equalsIgnoreCase("si"));
		
	}
}
