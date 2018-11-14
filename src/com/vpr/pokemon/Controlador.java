package com.vpr.pokemon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import com.vpr.pokemon.ui.Login;;

public class Controlador implements ActionListener{
	
	//Atributos
	private Vista vista;
	private Modelo modelo;
	
	public Controlador(Modelo modelo, Vista vista) {
		this.modelo = modelo;
		this.vista = vista;
		
		//Conecto con la base de datos
		try {
			modelo.conectarDb();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Error al conectar con la base de datos");
			e.printStackTrace();
		}
		
		//Inicio sesion
		iniciarSesion();
		
		//Muestro los componentes la ventana de pokemon
		vista.hacerVisible(true);
		
		addListeners(); //añado los listeners
		modoEdicion(false); //deshabilito el modo edicion al inicio de la aplicaicon
		
		//Desconecto de la base de datos
		try {
			modelo.desconectarDb();
		} catch (SQLException e) {
			System.out.println("Error al desconectar con la base de datos");
			e.printStackTrace();
		}
	}
	
	//Metodos
	public void iniciarSesion() {
		boolean autenticado = false;
		Login login = new Login();
		
		do {
			login.hacerVisible(true);
			String usuario = login.getUsuario();
			String contrasena = login.getContrasena();
			try {
				autenticado = modelo.iniciarSesion(usuario, contrasena);
				if(!autenticado) {
					login.mensajeError("Error en el usuario o contraseña");
					continue;
				}
					
			} catch (SQLException e) {
				System.out.println("Error al intentar iniciar sesión");
				e.printStackTrace();
			}
			
		}while(!autenticado);
	}
	
	public void modoEdicion(boolean b) {
		if(b) {
			vista.btNuevo.setEnabled(!b);
			vista.btEditar.setEnabled(!b);
			vista.btGuardar.setEnabled(b);
			vista.btCancelar.setEnabled(b);
			vista.btBorrar.setEnabled(!b);
			
			vista.tfNombre.setEditable(b);
			vista.tfNivel.setEditable(b);
			vista.tfPeso.setEditable(b);
		}
		else {
			vista.btNuevo.setEnabled(!b);
			vista.btEditar.setEnabled(b);
			vista.btGuardar.setEnabled(b);
			vista.btCancelar.setEnabled(b);
			vista.btBorrar.setEnabled(b);
			
			vista.tfNombre.setEditable(b);
			vista.tfNivel.setEditable(b);
			vista.tfPeso.setEditable(b);
		}
	}
	
	public void addListeners() {
		vista.btNuevo.addActionListener(this);
		vista.btEditar.addActionListener(this);
		vista.btGuardar.addActionListener(this);
		vista.btCancelar.addActionListener(this);
		vista.btBorrar.addActionListener(this);
	}
	
	public void limpiarTexto() {
		vista.tfNombre.setText("");
		vista.tfNivel.setText("");
		vista.tfPeso.setText("");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "nuevo":
			modoEdicion(true);
			break;
			
		case "editar":
			
			break;
			
		case "guardar":
			
			break;
			
		case "cancelar":
			modoEdicion(false);
			break;
			
		case "borrar":
			
			break;
			
		default:
			
			break;
		}
	}
}
