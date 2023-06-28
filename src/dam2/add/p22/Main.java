package dam2.add.p22;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import dam2.add.p22.jdbc.OperacionesBDTotal;
import dam2.add.p22.lib.UserService;
import dam2.add.p22.menus.MenuPrincipal;
import dam2.add.p22.modelo.Usuario;

public class Main {
	
	
//	public static ArrayList<Usuario> directorio = UserService.leerUsuarios();;

	public static void main(String... args) throws IOException, InterruptedException {
	
			
		try {
			OperacionesBDTotal.crearBaseDeDatos();	//solo si no existe
			OperacionesBDTotal.crearTabla();		//solo si no existe	
			
			if(OperacionesBDTotal.consultarVacia()==true) {  //solo la primera vez si no hay datos de demo
				
				UsuarioDAO.anadir_usuario(1, "admin", "admin", "Burgos", "Aranda de Duero", false);
				UsuarioDAO.anadir_usuario(2, "Mar", "1234", "Burgos", "Aranda de Duero", false);
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
		
		
		try {
			UserService.mostrarUsuarios();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		MenuPrincipal menu = new MenuPrincipal(); // objeto menu
		

		menu.arrancar();

	}

}