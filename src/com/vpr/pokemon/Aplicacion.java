package com.vpr.pokemon;

public class Aplicacion {
	public static void main(String[] args) {
		Modelo modelo = new Modelo();
		Vista vista = new Vista();
		Controlador controlador = new Controlador(modelo, vista);
	}
}
