package com.vpr.pokemon;

import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.vpr.pokemon.Pokemon.Tipo;


public class Modelo {
	//Constantes
	private final String IP = "192.168.34.5"; //192.168.34.5 casa:127.0.0.1
	private final String BD = "vpadilla";
	private final String USUARIO = "vpadilla";
	private final String CONTRASENA = "42ha3h";
	
	//Variables
	private static Connection conexion;
	private PreparedStatement sentencia = null;
	private ResultSet resultado = null;
	private ArrayList<Pokemon> listPokemon;
	
	public Modelo() {
		
	}
	
	//Metodos
	public void conectarDb() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		//añadir esto despues de +DB+ para configurar la zona horaria (casa)
		//localhost:3306/db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC
		conexion = DriverManager.getConnection("jdbc:mysql://"+IP+":3306/"+BD, USUARIO, CONTRASENA);
	}
	
	public void desconectarDb() throws SQLException {
		conexion.close();
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
		
		sentencia = conexion.prepareStatement(sentenciaSql, PreparedStatement.RETURN_GENERATED_KEYS);
		sentencia.setString(1, pokemon.getNombre());
		sentencia.setString(2, pokemon.getTipo().name());
		sentencia.setInt(3, pokemon.getNivel());
		sentencia.setFloat(4, pokemon.getPeso());
		//sentencia.setString(5, ); imagen
		
		sentencia.executeUpdate();
		
		//Guardo el id del pokemon
		ResultSet idGenerado = sentencia.getGeneratedKeys();
		idGenerado.next();
		int idPokemon = idGenerado.getInt(1);
		pokemon.setId(idPokemon);
	}
	
	public void modificarPokemon(Pokemon pokemon) throws SQLException {
		String sentenciaSql = "UPDATE pokemon_prueba SET nombre = ?, tipo = ?, nivel = ?, peso= ? " + "WHERE id = ?";
		sentencia = conexion.prepareStatement(sentenciaSql);
		sentencia.setString(1, pokemon.getNombre());
		sentencia.setString(2, pokemon.getTipo().name());
		sentencia.setInt(3, pokemon.getNivel());
		sentencia.setFloat(4, pokemon.getPeso());
		sentencia.setInt(5, pokemon.getId());
		
		sentencia.executeUpdate();
	}
	
	public void eliminarPokemon(Pokemon pokemon) throws SQLException {
		String sentenciaSql = "DELETE FROM pokemon_prueba WHERE id = ?";
		
		sentencia = conexion.prepareStatement(sentenciaSql);
		sentencia.setInt(1, pokemon.getId());
		sentencia.executeUpdate();
	}
	
	public ArrayList<Pokemon> getPokemones() throws SQLException {
		String sentenciaSql = "SELECT id,nombre,tipo,nivel,peso FROM pokemon_prueba";
		
		sentencia = conexion.prepareStatement(sentenciaSql);
		resultado = sentencia.executeQuery();
		
		listPokemon = new ArrayList();
		while(resultado.next()) {
			Pokemon p = new Pokemon(resultado.getInt(1),resultado.getString(2),Tipo.valueOf(resultado.getString(3)),resultado.getInt(4),resultado.getFloat(5));
			listPokemon.add(p);
		}
		
		return listPokemon;
	}
	/***
	 * Copmprueba si una cadena es un entero
	 * @param cadena
	 * @return
	 */
	public boolean isInt(String cadena) {
		boolean resultado = false;
		if(cadena.matches("\\d*"))	//esto es una expresion regular que comprueba si son numeros
			resultado = true;
		return resultado;
	}
	
	/***
	 * Comprueba si una cadena es float (detecta el punto)
	 * @param cadena
	 * @return
	 */
	public boolean isFloat(String cadena) {
		boolean resultado = false;
		if(cadena.matches("\\d*\\.\\d*") || cadena.matches("\\d*\\,\\d*"))	//esto es una expresion regular que comprueba si son numeros
			resultado = true;
		cadena = cadena.replaceAll(",", ".");
		return resultado;
	}
}
