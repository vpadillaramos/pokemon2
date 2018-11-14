package com.vpr.pokemon;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class Vista extends JFrame {
	public JLabel lblNombre;
	public JLabel lblTipo;
	public JLabel lblNivel;
	public JLabel lblPeso;
	public JTextField tfNombre;
	public JTextField tfNivel;
	public JTextField tfPeso;
	public JLabel lbImagen;
	public JScrollPane scrollPane;
	public JList listPokemon;
	public JComboBox cbTipo;
	public JButton btNuevo;
	public JButton btEditar;
	public JButton btGuardar;
	public JButton btCancelar;
	public JButton btBorrar;

	public Vista() {
		getContentPane().setLayout(null);
		setSize(450,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 27, 46, 14);
		getContentPane().add(lblNombre);
		
		lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(10, 64, 46, 14);
		getContentPane().add(lblTipo);
		
		lblNivel = new JLabel("Nivel");
		lblNivel.setBounds(10, 99, 46, 14);
		getContentPane().add(lblNivel);
		
		lblPeso = new JLabel("Peso");
		lblPeso.setBounds(10, 137, 46, 14);
		getContentPane().add(lblPeso);
		
		tfNombre = new JTextField();
		tfNombre.setActionCommand("nombre");
		tfNombre.setBounds(78, 24, 105, 20);
		getContentPane().add(tfNombre);
		tfNombre.setColumns(10);
		
		tfNivel = new JTextField();
		tfNivel.setActionCommand("nivel");
		tfNivel.setBounds(78, 96, 105, 20);
		getContentPane().add(tfNivel);
		tfNivel.setColumns(10);
		
		tfPeso = new JTextField();
		tfPeso.setActionCommand("peso");
		tfPeso.setBounds(78, 134, 105, 20);
		getContentPane().add(tfPeso);
		tfPeso.setColumns(10);
		
		lbImagen = new JLabel("");
		lbImagen.setBorder(new TitledBorder(null, "Imagen", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		lbImagen.setBounds(193, 18, 67, 60);
		getContentPane().add(lbImagen);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(270, 18, 139, 156);
		getContentPane().add(scrollPane);
		
		listPokemon = new JList();
		scrollPane.setViewportView(listPokemon);
		
		cbTipo = new JComboBox();
		cbTipo.setBounds(78, 61, 105, 20);
		getContentPane().add(cbTipo);
		
		btNuevo = new JButton("Nuevo");
		btNuevo.setActionCommand("nuevo");
		btNuevo.setBounds(26, 185, 89, 23);
		getContentPane().add(btNuevo);
		
		btEditar = new JButton("Editar");
		btEditar.setActionCommand("editar");
		btEditar.setBounds(133, 185, 89, 23);
		getContentPane().add(btEditar);
		
		btGuardar = new JButton("Guardar");
		btGuardar.setActionCommand("guardar");
		btGuardar.setBounds(26, 227, 89, 23);
		getContentPane().add(btGuardar);
		
		btCancelar = new JButton("Cancelar");
		btCancelar.setActionCommand("cancelar");
		btCancelar.setBounds(133, 227, 89, 23);
		getContentPane().add(btCancelar);
		
		btBorrar = new JButton("Borrar");
		btBorrar.setActionCommand("borrar");
		btBorrar.setBounds(299, 185, 89, 23);
		getContentPane().add(btBorrar);
		
		repaint();
	}
	
	//Metodos
	public void hacerVisible(boolean b) {
		setVisible(b);
	}
}
