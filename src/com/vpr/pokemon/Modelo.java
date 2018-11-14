package com.vpr.pokemon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Modelo {
	//Constantes
	private final String IP = "192.168.34.5";
	private final String BD = "vpadilla";
	private final String USUARIO = "vpadilla";
	private final String CONTRASENA = "42ha3h";
	
	//Variables
	private static Connection conexion;
	private PreparedStatement sentencia = null;
	private ResultSet resultado = null;
	
	public Modelo() {
		
	}
	
	//Metodos
	public void conectarDb() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		conexion = DriverManager.getConnection("jdbc:mysql://"+IP+":3306/"+BD, USUARIO, CONTRASENA);
	}
	
	public void desconectarDb() throws SQLException {
		conexion.close();
		conexion = null;
	}

	public boolean iniciarSesion(String usuario, String contrasena) throws SQLException{
		String sentenciaSql = "SELECT id FROM usuarios WHERE usuario = ? AND contrasena = SHA1(?)";
		
		sentencia = conexion.prepareStatement(sentenciaSql);
		sentencia.setString(1, usuario);
		sentencia.setString(2, contrasena);
		resultado = sentencia.executeQuery();
		
		boolean encontrado = resultado.next(); //esto es para poder cerrar la sentencia
		sentencia.close();
		return encontrado;
	}
	
	//Metodos de la ventana de pokemons
	public void guardarPokemon(Pokemon pokemon) throws SQLException {
		String sentenciaSql = "INSERT INTO pokemon (nombre,tipo,nivel,peso,imagen) VALUES (?,?,?,?,?)";
		
		sentencia = conexion.prepareStatement(sentenciaSql);
		sentencia.setString(1, pokemon.getNombre());
		sentencia.setString(2, String.valueOf(pokemon.getTipo()));
		sentencia.setInt(3, pokemon.getNivel());
		sentencia.setFloat(4, pokemon.getPeso());
		//sentencia.setString(5, );
	}
}
