/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam2.add.p22.lib;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
import org.jasypt.util.password.StrongPasswordEncryptor;
import dam2.add.p22.Main;
import dam2.add.p22.UsuarioDAO;
import dam2.add.p22.menus.MenuAlta;
import dam2.add.p22.modelo.Provincia;
import dam2.add.p22.modelo.Usuario;

/**
 *
 * @author David
 */
public class UserService {

	public boolean comprobarLogin(Usuario user) throws IOException { // COMPROBAMOS USUARIO Y CLAVE PARA HACER LOGIN

		List<Usuario> lista = UsuarioDAO.recuperar_listado();

		// metodo que recibe un nombre y comprueba el login de usuario y clave
		
		String name2 = user.getNombre();
		String pass2 = user.getPassword();
		boolean existe = false;

		for (Usuario var : lista) {
													
			if (name2.equalsIgnoreCase(var.getNombre()) && Encriptacion.desencriptador(pass2, var.getPassword()) == true )
				// se comprueba uno a uno si coinciden nombre y clave desemcruptada
				existe = true;
		}

		return existe;
	}

	public boolean comprobarBloqueado(String nombre) throws IOException { // para usuario y clave del login

		List<Usuario> lista = UsuarioDAO.recuperar_listado();

		// metodo que recibe un usuario y comprueba si esta bloqueado
		
		boolean bloqueado = false;

		for (Usuario var : lista) {

			if (nombre.equalsIgnoreCase(var.getNombre())) 

				if (var.isBloqueado() == true) {

					bloqueado = true;

				}
		}

		return bloqueado;
	}



	public boolean comprobarNombre(String nombre) throws IOException { // para nombre

		List<Usuario> lista = UsuarioDAO.recuperar_listado();

		// metodo que recibe un nombre y comprueba si esta en la BBDD

		boolean existe = false;

		for (Usuario var : lista) {

			if (nombre.equalsIgnoreCase(var.getNombre())) 

				existe = true;
		}

		return existe;
	}

	public Usuario altaUsuario() throws IOException { // crea usuario admin

		MenuAlta menu = new MenuAlta(); // se crea un menu de alta y uno de usuario
		Usuario nuevo = new Usuario();
		UserService lib = new UserService();
		Provincia prov = new Provincia();

		boolean existe;

		// se lanza el menu que solicita nuevos datos del usuario
		nuevo.setNombre(menu.estableceNombre()); 
		nuevo.setPassword(menu.estableceClave());
		prov = menu.estableceProvincia();
		nuevo.setProvincia(prov.getNombre());
		nuevo.setMunicipio(menu.estableceMunicipio(prov).getNombre());
		nuevo.setBloqueado(false);

		existe = lib.comprobarLogin(nuevo); // se comprueba si existe

		if (existe == false) {

			UsuarioDAO.anadir_usuario(nuevo);; // se añade al fichero de usuarios

		}

		else {

			System.out.print("El usuario ya existe."); // si existe se avisa
			nuevo = null;
		}
		return nuevo;

	}

	public static void mostrarUsuarios() throws IOException { // metodo que imprime en consola los usuarios

		List<Usuario> lista = UsuarioDAO.recuperar_listado(); // se trae el listado de usuarios a un List

		try {
			// se itera el array
			Iterator<Usuario> it = lista.iterator();

			while (it.hasNext()) {
				// se imprime la cadena
				System.out.println(it.next().getNombre());
			}

		} catch (IndexOutOfBoundsException e) {
			System.out.println("Datos fuera de límites");
		}

	}

	public int contadorBloqueados() throws IOException { // metodo que cuenta los bloqueados

		List<Usuario> lista = UsuarioDAO.recuperar_listado(); // se trae el listado de usuarios a un List
		int numero = 0;
		
		try {
			// se itera el array
			for (int i = 0; i < lista.size(); i++) {
				Usuario user = lista.get(i);
				// se imprime la cadena solo de bloqueados
				if (user.isBloqueado() == true) {
					numero++;
				}
			}

		} catch (IndexOutOfBoundsException e) {
			System.out.println("Datos fuera de límites");
		}
		
		return numero;
	}
	
	
	public void mostrarUsuariosBloqueados() throws IOException { // metodo que imprime en consola los usuarios bloqueados

		List<Usuario> lista = UsuarioDAO.recuperar_listado(); // se trae el listado de usuarios a un List

		try {
			// se itera el array
			for (int i = 0; i < lista.size(); i++) {
				Usuario user = lista.get(i);
				// se imprime la cadena solo de bloqueados
				if (user.isBloqueado() == true) {
					System.out.println(user.getNombre());
				}
			}

		} catch (IndexOutOfBoundsException e) {
			System.out.println("Datos fuera de límites");
		}

	}

	public void bloquearUsuario(Usuario activo) throws IOException { // metodo que bloquea al usuario

		List<Usuario> lista = UsuarioDAO.recuperar_listado(); // se trae el listado de usuarios a un List

		for (int i = 0; i < lista.size(); i++) {

			Usuario user = lista.get(i);

			if (user.getNombre().equalsIgnoreCase(activo.getNombre())) {

				int id = user.getId();
				
				UsuarioDAO.bloqueo_usuario(id, true);
			}
		}


	}
	
	public String eligeNombre() {

		Scanner teclado = new Scanner(System.in);
		boolean correcto = false;
		String nombre;
		
		do { // se escoge por el admin la persona que se va a desbloquear con un mini menu
			System.out.print("Introduce nombre: ");
			nombre = teclado.nextLine();
			if (Library.isNumeric(nombre) == true) {
				correcto = false;
			} else {
				// nombre = UserService.formatoNombre(nombre); EVITAMOS EL CAMBIO DE LAS
				// INICIALES A MAYUSCULAS
				correcto = true;
			}
		} while (correcto != true);

		return nombre;
	}
	
	
	

	public void desbloquearUsuario() throws IOException { // metodo para desbloquear al usuario

		String elegido = eligeNombre(); // se crea un string que guarda el nombre del usuario a desbloquear

		List<Usuario> lista = UsuarioDAO.recuperar_listado(); // se trae el array con la lista

		boolean existe = false;

		// se comprueba que el elegido existe
		existe = comprobarNombre(elegido);

		if (existe == false) {
			// si no existe se avisa
			System.out.println("El usuario no existe.");
			System.out.println();
		}

		else {

			for (int i = 0; i < lista.size(); i++) {

				Usuario user = lista.get(i);

				if (user.getNombre().equalsIgnoreCase(elegido)) {

					if (user.isBloqueado() == false) {
						System.out.println("El usuario no esta bloqueado.");
						System.out.println();

					}

					else {
						int id = user.getId();
						System.out.println("Se ha desbloqueado el usuario.");
						System.out.println();
						UsuarioDAO.bloqueo_usuario(id, false); 

					}
				}
			}

		}

	}

	
}
