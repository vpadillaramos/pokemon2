package com.vpr.pokemon;

public class Pokemon {
	//Atributos
	public enum Tipo{
		Normal, Planta, Hada, Fuego, Agua, Fantasma, Siniestro,
		Psíquico, Acero, Dragón, Tierra, Eléctrico, Veneno,
		Bicho, Volador, Hielo, Lucha, Roca
	}
	private int id;
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
	
	public Pokemon(int id, String nombre, Tipo tipo, int nivel, float peso) {
		this.id = id;
		this.nombre = nombre;
		this.tipo = tipo;
		this.nivel = nivel;
		this.peso = peso;
	}
	
	//Metodos
	public int getId() {
		return id;
	}
	
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
	
	public void setId(int id) {
		this.id = id;
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
	
	public String toString() {
		return nombre;
	}
	
}
