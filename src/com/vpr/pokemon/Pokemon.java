package com.vpr.pokemon;

public class Pokemon {
	//Atributos
	public enum Tipo{
		Normal, Planta, Hada, Fuego, Agua, Fantasma, Siniestro,
		Ps�quico, Acero, Drag�n, Tierra, El�ctrico, Veneno,
		Bicho, Volador, Hielo, Lucha, Roca
	}
	private String nombre;
	private Tipo tipo;
	private int nivel;
	private float peso;
	//private imagen;
	
	//Constructores
	public Pokemon() {
		
	}
	
	public Pokemon(String nombre, Tipo tipo, int nivel, float peso) {
		this.nombre = nombre;
		this.tipo = tipo;
		this.nivel = nivel;
		this.peso = peso;
	}
	
	//Metodos
	
	public String getNombre() {
		return nombre;
	}
	
	public Tipo getTipo() {
		return tipo;
	}
	
	public int getNivel() {
		return nivel;
	}
	
	public float getPeso() {
		return peso;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	
	public void setPeso(float peso) {
		this.peso = peso;
	}
	
	
}
