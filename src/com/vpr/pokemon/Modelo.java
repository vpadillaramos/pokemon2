package com.vpr.pokemon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Modelo {
	//Constantes
	private final String IP = "127.0.0.1"; //192.168.34.5
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
		//añadir esto despues de +DB+ para configurar la zona horaria (casa)
		//localhost:3306/db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC
		conexion = DriverManager.getConnection("jdbc:mysql://"+IP+":3306/"+BD+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", USUARIO, CONTRASENA);
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
		String sentenciaSql = "INSERT INTO pokemon_prueba (nombre,tipo,nivel,peso) VALUES (?,?,?,?)";
		
		sentencia = conexion.prepareStatement(sentenciaSql);
		sentencia.setString(1, pokemon.getNombre());
		sentencia.setString(2, String.valueOf(pokemon.getTipo()));
		sentencia.setInt(3, pokemon.getNivel());
		sentencia.setFloat(4, pokemon.getPeso());
		//sentencia.setString(5, ); imagen
	}
	
	public boolean isNumeric(String cadena) {
		boolean resultado = false;
		if(cadena.matches("\\d*"))	//esto es una expresion regular que comprueba si son numeros
			resultado = true;
		return resultado;
	}
}
