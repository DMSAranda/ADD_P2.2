
package dam2.add.p22.menus;

import java.io.IOException;
import java.util.Scanner;
import dam2.add.p22.UsuarioDAO;
import dam2.add.p22.lib.UserService;
import dam2.add.p22.modelo.Usuario;

/**
 * hola
 * 
 * @author David
 */
public class MenuPrincipal {

	Scanner teclado = new Scanner(System.in);
	String opcion2;
	int opcion;

	public void arrancar() {

		do {
			do {

				System.out.println("Elige una opción");
				System.out.println();
				System.out.println("1 - Menu Login");
				System.out.println("2 - Alta de usuario");
				System.out.println("3 - Salir");
				System.out.println();
				System.out.print("Opcion elegida: ");

				opcion2 = teclado.nextLine();

				try {
					opcion = Integer.parseInt(opcion2);

				} catch (NumberFormatException excepcion) {

					System.out.println();
					System.out.println("Introduce un valor numérico.");
					System.out.println();
					opcion = 0;
				}
				System.out.println();

			} while (opcion < 1 || opcion > 3);

			switch (opcion) {

			case 1:

				Usuario activo = new Usuario();
				// se crea un usuario activo y se pasa a login
				MenuLogin log = new MenuLogin();

				try {
					activo = log.hacerLoggin();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (activo != null) {

					if (activo.getNombre().equalsIgnoreCase("admin")) { // si es el admin se saluda y se pasa a su menu

						System.out.println();
						System.out.println("Hola " + activo.getNombre() + ", bienvenid@ al sistema.");
						MenuAdministrador menu = new MenuAdministrador();

						try {
							menu.arrancar(activo);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					} // sino se saluda

					else
					System.out.println();
					System.out.println("Hola " + activo.getNombre() + ", bienvenid@ al sistema.");
					System.out.println();
				}
				
				break;

			

			case 2:

				UserService nuevo = new UserService(); // se crea un objeto usuario y se pasa a menu de alta
				Usuario user;
				try {
					user = nuevo.altaUsuario();
										
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Alta correcta.");
				System.out.println();

				break;

			case 3: // salimos

				System.out.print("Adiós, vuelva pronto.");
				break;
			}

		} while (opcion != 3);

	}

}
