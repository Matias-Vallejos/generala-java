package generala;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Funciones {
	public void tirardados (int[] dados, String x) {
		String [] tirarlos = {"TIRAR LOS DADOS"};
		JOptionPane.showOptionDialog(null, null,
				"Turno de "+ x, 0, 0,
				new ImageIcon(
				Funciones.class.getResource("imgs/taza.png")
				),
				tirarlos, null);
		JOptionPane.showMessageDialog(null, null,
				"Turno de "+ x, 0,
				new ImageIcon(
				Funciones.class.getResource("imgs/tirar.png")
				));
		JOptionPane.showMessageDialog(null,
				"Tus dados:    " + dados[0]+ "    " +dados[1]+ "    " + dados[2] + "    " + dados[3] + "    " + dados[4],
				"Turno de " + x, JOptionPane.DEFAULT_OPTION);
		}
	public int[] cambiodados(String cambio, int[] dados) {
			cambio = cambio.trim();
			String[] cambios=cambio.split(" ");
			
			for (int i = 0; i < cambios.length; i++) {
				if (cambios[i].equalsIgnoreCase("a")) {
					
					dados[0]=(int)(Math.random()*6+1);
				} else if (cambios[i].equalsIgnoreCase("b")) {
					
					dados[1]=(int)(Math.random()*6+1);
				} else if (cambios[i].equalsIgnoreCase("c")) {
					
					dados[2]=(int)(Math.random()*6+1);
				} else if (cambios[i].equalsIgnoreCase("d")) {
					
					dados[3]=(int)(Math.random()*6+1);
				} else if (cambios[i].equalsIgnoreCase("e")) {
					
					dados[4]=(int)(Math.random()*6+1);
				}
			}
		return dados;
	}
	public int[] ordenarDados(int[] dados) {
		boolean cambio = false;
		do {
			cambio = false;
		for (int i = 0; i < 4; i++) {
			if (dados[i]<dados[i+1]) {
				int a = dados[i+1];
				dados [i+1] = dados[i];
				dados [i] = a;
				cambio = true;
			}
		}} while(cambio);
		return dados;
		}
}
