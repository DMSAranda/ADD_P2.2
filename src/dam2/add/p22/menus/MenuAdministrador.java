/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam2.add.p22.menus;

import java.io.IOException;
import java.util.Scanner;

import dam2.add.p22.lib.UserService;
import dam2.add.p22.modelo.Usuario;

/**
 * @author David
 */
public class MenuAdministrador {

	public void arrancar(Usuario activo) throws IOException {

		UserService lib = new UserService();

		Scanner teclado = new Scanner(System.in);
		int eleccion;

		do {
			do {

				System.out.println("Que opción eliges"); /* opciones del admin en un menu */
				System.out.println();
				System.out.println("1 - Desbloquear usuario");
				System.out.println("2 - Salir");
				System.out.println();
				System.out.print("Opcion elegida: "); /* pasamos a su opcion */

				String eleccion2 = teclado.nextLine();
				
				try {
				eleccion = Integer.parseInt(eleccion2);
				
				} catch (NumberFormatException excepcion) {
					
					System.out.println();
					System.out.println("Introduce un valor numérico.");
					System.out.println();
					eleccion=0;
				}
				System.out.println();

			} while (eleccion < 1 || eleccion > 2);

			switch (eleccion) {

			case 1:
				if (lib.contadorBloqueados() > 0) {
					lib.mostrarUsuariosBloqueados(); // mostramos listado de usuarios
					 								// elegimos uno para desbloquearle
					System.out.println();
					lib.desbloquearUsuario();
				}
				else System.out.println("No hay ningun usuario bloqueado");
				System.out.println();
				break;

			case 2:
				
				System.out.println("Saliendo del menu de administrador.");
				System.out.println();
				break;

			}

		} while (eleccion != 2);

	}

}
