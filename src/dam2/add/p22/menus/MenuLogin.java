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
 *
 * @author David
 */
public class MenuLogin {

	public Usuario hacerLoggin() throws IOException, InterruptedException {

		UserService lib = new UserService(); // recibimos usuario y devolvemos otro
		Scanner teclado = new Scanner(System.in);
		Usuario user = new Usuario();
		String password;
		String nombre;

		int contador = 1;
		boolean existe = false;
		boolean userBien = false;

		boolean validador = false;
		boolean bloqueado = false;
		boolean bloqueado2 = false;

		do {

			System.out.print("Introduce tu Nombre: "); // leemos nombre
			nombre = teclado.nextLine();

			userBien = lib.comprobarNombre(nombre); // comprobamos que existe el usuario por nombre
			bloqueado2 = lib.comprobarBloqueado(nombre);

			if (userBien == false) {
				System.out.println();
				System.out.println("El usuario introducido no existe");
				System.out.println();
			}

			else if (bloqueado2 == true) { // si el usuario esta bloqueado sigue pidiendo nombre
				System.out.println();
				System.out.println("El usuario introducido está bloqueado.");
				System.out.println();
				userBien = false;
			}

		} while (userBien != true);
		
		user.setNombre(nombre);

		do {

			System.out.print("Introduce tu Password: "); // leemos clave
			user.setPassword(teclado.nextLine());

			existe = lib.comprobarLogin(user); // comprobamos que coincide usuario y password

			if (existe == false) { // si no hace login bien

				if (user.isBloqueado() == false) { // si no esta bloquado sigue

					if (contador == 3) { // si mete mal las claves se bloquea y se sale

						System.out.println();
						System.out
								.println("Has introducido los datos mal en 3 ocasiones. Tu usuario ha sido bloqueado.");
						System.out.println();

						lib.bloquearUsuario(user);

						user = null;

						break;
					}

					else {
						if (!user.getNombre().equalsIgnoreCase("admin")) { // no deja bloquear el admin
							if (contador == 1) {
								System.out.println();
								System.out.println("Clave mal introducida, te quedan 2 intentos.");
								System.out.println();
							} else if (contador == 2) {
								System.out.println();
								System.out.println("Clave mal introducida, te quedan 1 intentos.");
								System.out.println();
							}
							contador++; // aumenta el contador cada fallo
						}
					}
				}

			}

			else
				validador = true; // si hace login bien se sale

		} while (validador != true);

		return user; // devolvemos el usuario, si vuelve null hay que seguir con un break

	}

}
