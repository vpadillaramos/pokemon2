package com.vpr.pokemon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.vpr.pokemon.Pokemon.Tipo;
import com.vpr.pokemon.ui.Login;
import com.vpr.pokemon.util.Util;;

public class Controlador implements ActionListener, ListSelectionListener{
	
	//Atributos
	private Vista vista;
	private Modelo modelo;
	private boolean modificarPokemon = false;
	
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
		//iniciarSesion();
		
		//Muestro los componentes la ventana de pokemon
		poblarDesplegableTipos(); //relleno el desplegable
		refrescarLista();
		vista.hacerVisible(true);
		
		addListeners(); //añado los listeners
		modoEdicion(false); //deshabilito el modo edicion al inicio de la aplicaicon
		
	}
	
	//Metodos
	
	public void refrescarLista() {
		vista.modelPokemon.removeAllElements();
		
		try {
			for(Pokemon p: modelo.getPokemones())
				vista.modelPokemon.addElement(p);
			
		} catch (SQLException e) {
			Util.mensajeError("Error", "No se pudo mostrar la lista de pokemon");
			e.printStackTrace();
		}
	}
	
	public void poblarDesplegableTipos() {
		for(Tipo tipo: Tipo.values())
			vista.cbTipo.addItem(tipo);
	}
	
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
			
			vista.listPokemon.setEnabled(!b);
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
			
			vista.listPokemon.setEnabled(!b);
			vista.listPokemon.clearSelection();
		}
	}
	
	public void addListeners() {
		
		//Botones
		vista.btNuevo.addActionListener(this);
		vista.btEditar.addActionListener(this);
		vista.btGuardar.addActionListener(this);
		vista.btCancelar.addActionListener(this);
		vista.btBorrar.addActionListener(this);
		
		//Lista que muestra los pokemon
		vista.listPokemon.addListSelectionListener(this);
	}
	
	public void limpiarTexto() {
		vista.tfNombre.setText("");
		vista.cbTipo.setSelectedIndex(0);
		vista.tfNivel.setText("");
		vista.tfPeso.setText("");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		//hecho
		case "nuevo":
			limpiarTexto();
			modoEdicion(true);
			break;
			
		case "editar":
			modoEdicion(true);
			modificarPokemon = true;
			
			break;
			
		//hecho
		case "guardar":
			
			//Nombre obligatorio
			if(vista.tfNombre.getText().equals("")) {
				Util.mensajeError("Error", "El nombre es obligatorio");
				return;
			}
			
			if(vista.tfNivel.getText().equals(""))
				vista.tfNivel.setText("0");
			if(!modelo.isInt(vista.tfNivel.getText())) {
				Util.mensajeError("Error", "El nivel debe ser un número");
				vista.tfNivel.selectAll();
				vista.tfNivel.requestFocus();
				return;
			}
			
			if(vista.tfPeso.getText().equals(""))
				vista.tfPeso.setText("0.0");
			if(!modelo.isFloat(vista.tfPeso.getText())) {
				Util.mensajeError("Error", "El peso debe ser un número");
				vista.tfPeso.selectAll();
				vista.tfPeso.requestFocus();
				return;
			}
			
			//recogida de datos
			String nombre = vista.tfNombre.getText();
			Tipo tipo = (Tipo)vista.cbTipo.getSelectedItem();
			int nivel = Integer.parseInt(vista.tfNivel.getText());
			float peso = Float.parseFloat(vista.tfPeso.getText());
			
			Pokemon pokemon = new Pokemon(nombre, tipo, nivel, peso);
			
			if(modificarPokemon) {
				try {
					pokemon.setId(vista.listPokemon.getSelectedValue().getId()); //cojo el id del pokemon seleccionado
					modelo.modificarPokemon(pokemon);
					Util.mensajeInformacion("Hecho", "Pokemon modificado correctamente");
					modificarPokemon = false;
				} catch (SQLException e1) {
					Util.mensajeError("Error", "No se pudo modificar el Pokemon");
					e1.printStackTrace();
				}
			}
			else {
				try {
					modelo.guardarPokemon(pokemon);
					Util.mensajeInformacion("Hecho", "Pokemon guardado correctamente");
				} catch (SQLException sqle) {
					Util.mensajeError("Error", "No se pudo guardar el Pokemon");
					sqle.printStackTrace();
				}
			}
			
			refrescarLista();
			limpiarTexto();
			modoEdicion(false);
			
			break;
		
			//hecho
		case "cancelar":
			limpiarTexto();
			modoEdicion(false);
			break;
			
		case "borrar":
			try {
				modelo.eliminarPokemon(vista.listPokemon.getSelectedValue());
				Util.mensajeInformacion("Hecho", vista.listPokemon.getSelectedValue().getNombre() + " eliminado con éxito");
			} catch (SQLException e1) {
				Util.mensajeError("Error", "No se pudo eliminar el pokemon "+vista.listPokemon.getSelectedValue().toString());
				e1.printStackTrace();
			}
			
			refrescarLista();
			limpiarTexto();
			modoEdicion(false);
			
			break;
			
		default:
			
			break;
		}
	}
	
	//Metodo para que, seleccionando en el ListView, se muestren los datos del pokemon
	@Override
	public void valueChanged(ListSelectionEvent lse) {
		//si no hay nada seleccionado
		if(vista.listPokemon.getSelectedIndex() == -1)
			return;

		vista.tfNombre.setText(vista.listPokemon.getSelectedValue().getNombre());
		vista.cbTipo.setSelectedItem(vista.listPokemon.getSelectedValue().getTipo());
		vista.tfNivel.setText(String.valueOf(vista.listPokemon.getSelectedValue().getNivel()));
		vista.tfPeso.setText(String.valueOf(vista.listPokemon.getSelectedValue().getPeso()));
		vista.btEditar.setEnabled(true);
		vista.btBorrar.setEnabled(true);
		
	}
}
