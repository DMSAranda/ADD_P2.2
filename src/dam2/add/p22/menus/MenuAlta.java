/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam2.add.p22.menus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import dam2.add.p22.json.LeerJsonUrlMunicipio;
import dam2.add.p22.json.LeerJsonUrlProvincia;
import dam2.add.p22.lib.Library;
import dam2.add.p22.lib.UserService;
import dam2.add.p22.modelo.Municipio;
import dam2.add.p22.modelo.Provincia;

/**
 *
 * @author David
 */
public class MenuAlta {

	Scanner teclado = new Scanner(System.in);
	String nombre;
	String pass1;
	String pass2;

	public String estableceNombre() { // metodo que establece nombre

		boolean correcto = false;
		boolean existe = false;

		UserService lib = new UserService();

		do {
			System.out.print("Introduce nombre: ");
			nombre = teclado.nextLine();
			System.out.println();
			if (Library.isNumeric(nombre) == true) {
				correcto = false;
			} else {
				// nombre = UserService.formatoNombre(nombre); EVITAMOS EL CAMBIO DE LAS INICIALES A
				// MAYUSCULAS
				try {
					existe = lib.comprobarNombre(nombre);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (existe == true) {
					System.out.println();
					System.out.println("El usuario introducido ya existe.");
					System.out.println();
					correcto = false;
				} else
					correcto = true;
			}
		} while (correcto != true);

		return nombre;
	}

	public String estableceClave() { // metodo que establece clave

		boolean correcto = false;

		System.out.print("Introduce nueva contraseña: ");
		pass1 = teclado.nextLine();
		System.out.println();

		do {

			System.out.print("Vuelve a introducir tu nueva contraseña: ");
			pass2 = teclado.nextLine();
			System.out.println();

			if (pass1.equals(pass2)) {
				correcto = true;

			}
			
			else {
				System.out.println("Las contraseñas no coinciden.");
				System.out.println();
			}

		} while (correcto != true);

		return pass1;

	}

	public Provincia estableceProvincia() { // metodo que establece provincia

		boolean correcto = false;
		String num;
		Provincia provincia = new Provincia();

		System.out.println("Elige tu provincia.");
		System.out.println();
		
		ArrayList<Provincia> aux = LeerJsonUrlProvincia.leerUrl();

		for (Provincia p : aux) {

			System.out.println(p.getId() + " - " + p.getNombre());
		}
		do {
			System.out.println();
			System.out.print("Escribe tu elección y pulsa enter: ");
			num = teclado.nextLine();

			int vernum = 0;

			try {
				vernum = Integer.parseInt(num);

			} catch (NumberFormatException excepcion) {
				System.out.println("Error, elige un numero.");
				correcto = false;
			}

			if (vernum > 0 && vernum < 53) {

				correcto = true;
			}

		} while (correcto != true);

		for (Provincia p : aux) {

			if (p.getId().equals(num)) {
				provincia = p;
			}
		}

		return provincia;

	}

	public Municipio estableceMunicipio(Provincia prov) { // metodo que establece munciipio

		boolean correcto = false;
		String num;
		Municipio municipio = new Municipio();

		System.out.println("Elige tu municipio.");
		System.out.println();

		ArrayList<Municipio> aux = LeerJsonUrlMunicipio.leerUrl();
		
		ArrayList<Municipio> aux2 = new ArrayList<Municipio>();
		
		 // recorro el array aux con todos los municipios de España y solo los que coinciden con los dos primero numeros 
		 // de la provincia elegida son añadidos al array aux2
		
		for (Municipio m2 : aux) {            

			if(prov.getId().contentEquals(m2.getId().substring(0,2)) ) {
				
				aux2.add(m2);
			}
		}
		
		//muestro el array con solo los municipios de la provincia
		
		for (Municipio m : aux2) {

			System.out.println(m.getId() + " - " + m.getNombre());
		}
		
		do {
			System.out.println();
			System.out.print("Escribe tu elección y pulsa enter: ");
			num = teclado.nextLine();

			int vernum = 0;

			try {
				vernum = Integer.parseInt(num);

			} catch (NumberFormatException excepcion) {
				System.out.println("Error, elige un numero.");
				correcto = false;
			}
			int a = Integer.parseInt(aux2.get(0).getId());
			int b = Integer.parseInt(aux2.get(aux2.size() - 1).getId());
			
			if (vernum >= a && vernum <= b ) {

				correcto = true;
			}

		} while (correcto != true);
		
		for (Municipio m3 : aux2) {

			if (m3.getId().equals(num)) {
				municipio = m3;
			}
		}

		return municipio;

	}

}