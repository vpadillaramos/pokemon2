package com.vpr.pokemon.util;

import javax.swing.JOptionPane;

public class Util {
	
	/***
	 * Metodo que muestra una ventana de JOptionPane
	 * @param titulo Titulo de la ventana
	 * @param mensaje Mensaje que se mostrara en la ventana
	 */
	public static void mensajeInformacion(String titulo, String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
	}
}
