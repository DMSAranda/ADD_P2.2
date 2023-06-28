package dam2.add.p22;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dam2.add.p22.lib.Encriptacion;
import dam2.add.p22.lib.UserService;
import dam2.add.p22.modelo.Municipio;
import dam2.add.p22.modelo.Provincia;
import dam2.add.p22.modelo.Usuario;



// Esta clase implementa el acceso a hibernate para las operaciones basicas CRUD (añadir, consultar, ...)

// La base de datos debe estar ejecutandose
public class UsuarioDAO {
	
	
	//INSERT
	public static void anadir_usuario(Usuario a) {
		anadir_usuario(a.getId(), a.getNombre(), a.getPassword(), a.getProvincia(), a.getMunicipio(), a.isBloqueado());
	}
	
	// Añade un objeto nuevo a la base de datos (persistencia)
	
	public static void anadir_usuario(int id, String nombre, String password, String provincia, String municipio, boolean bloqueado) {
		//Transaction tx = null;
		
		Session session = HibernateManager.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction(); // Se crea una transaccion
		
		Usuario a = null;
		if(id == 0) {
			a = new Usuario(nombre, Encriptacion.encriptador(password), provincia, municipio, bloqueado);
		}
		else {
			a = new Usuario(id, nombre, Encriptacion.encriptador(password), provincia, municipio, bloqueado);
		}
		//Album a = new Album();
		//a.setAutor(aut);
		//a.setTitulo(tit);
		//a.setId(id);
		int i = (int)session.save(a);// Guarda el objeto creado en la BBDD.
		System.out.println("Usuario creado con el id: " + i);
		tx.commit(); // Materializa la transaccion
		session.close();
	}

	//SELECT
	// Recupera un objeto cuyo id se pasa como parametro
	
	public static Usuario recuperar_usuario(int id) {
		
		Session session = HibernateManager.getSessionFactory().openSession();
		session.beginTransaction();
		
		Usuario a = (Usuario) session.get(Usuario.class, id);
		if(a == null) {
			System.out.println("No existe el usuario");
		}
		else {
			System.out.println("Nombre: " + a.getNombre());
		}
		session.close();
		return a;
	}
	
	public static List<Usuario> recuperar_listado() {			//EXTRAEMOS TODOS LOS USUARIOS EN UN LIST
		
		Session session = HibernateManager.getSessionFactory().openSession();
		session.beginTransaction();
		
		Query hqlQuery = session.createQuery("FROM Usuario");
		List<Usuario> listado =  hqlQuery.list();
		
		session.close();
		return listado;
	}
	
	
	//UPDATE
	public static void modificar_usuario(Usuario a) {
		modificar_usuario(a.getId(),a.getNombre(), a.getPassword(), a.getProvincia(), a.getMunicipio(), a.isBloqueado());
	}
	
	// Modifica un objeto cuyo id se pasa como parametro
	
	public static void modificar_usuario(int id, String nombre, String password, String provincia, String municipio, boolean bloqueado) {
		
		Session session = HibernateManager.getSessionFactory().openSession();
		session.beginTransaction();
		
		Usuario a = recuperar_usuario(id);//new Album(id);
		if(a == null) {
			System.out.println("No existe el usuario");
		}
		else {
			//a.setId(id);
			a.setNombre(nombre);;
			a.setPassword(Encriptacion.encriptador(password));
			a.setMunicipio(municipio);
			a.setProvincia(provincia);
			a.setBloqueado(bloqueado);
			session.update(a);// Modifica el objeto con Id indicado
			session.getTransaction().commit(); // Materializa la transaccion
		} 
		session.close();
	}
	
	// bloquea un usuario cuyo id se pasa como parametro				(USADO PARA BLOQUEAR USUARIOS CON SU CODIGO)
    public static void bloqueo_usuario(int id, boolean bloqueado) {
		
		Session session = HibernateManager.getSessionFactory().openSession();
		session.beginTransaction();
		
		Usuario a = recuperar_usuario(id);//new Album(id);
		if(a == null) {
			System.out.println("No existe el usuario");
		}
		else {
			//a.setId(id);
			
			a.setBloqueado(bloqueado);
			session.update(a);// Modifica el objeto con Id indicado
			session.getTransaction().commit(); // Materializa la transaccion
		} 
		session.close();
	}

	// Borrar un objeto cuyo id se pasa como parametro
	
	public static void borrar_usuario(int id) {
		
		Session session = HibernateManager.getSessionFactory().openSession();
		session.beginTransaction();
		Usuario a = recuperar_usuario(id);//new Album(id);
		if(a == null) {
			System.out.println("No existe el usuario");
		}
		else {
			session.delete(a);
			System.out.println("Usuario borrado");
			session.getTransaction().commit(); // Materializa la transaccion
		}
		session.close();
	}

	// Ejecuta una consulta cuando el resultado se devuelve como un objeto 
		
	public static void consulta(String c) {
		
		Session session = HibernateManager.getSessionFactory().openSession();
		session.beginTransaction();
		
		Query q = session.createQuery(c);
		List<Usuario> results = q.list();

		for(Usuario user : results) {
			System.out.println(" Usuario - " + user.getNombre() + ", Provincia - " + user.getProvincia() + ", Municipio - " + user.getMunicipio());
		}
		session.close();
	}

	// Ejecuta una consulta cuando el resultado se devuelve como String
	
	public static void consulta2(String c) { 
		
		Session session = HibernateManager.getSessionFactory().openSession();
		session.beginTransaction();
		
		Query q = session.createQuery(c);
		List<String> results = q.list();

		for(String cadena : results) {
			System.out.println(" - " + cadena);
		}
		session.close();
	}
	
	// Ejecuta una consulta cuando el resultado se devuelve como INT 
	
	//(LO HE USADO PARA CONTAR LAS FILAS DE LA TABLA PARA VER SI ESTA VACIA)
	
		public static Integer consulta4(String c) { 
			
			Session session = HibernateManager.getSessionFactory().openSession();
			session.beginTransaction();
			int contador=0;
			
			Query hqlQuery = session.createQuery("FROM Usuario");
			List<Usuario> listado =  hqlQuery.list();
			
			for(Usuario x: listado) {
				contador++;
			}
			
			session.close();
			
			return contador;
		}
	
	// Ejecuta una consulta cuando el resultado se devuelve como Array de Objetos de los campos consultados
	
	public static void consulta3(String c) { 
		
		Session session = HibernateManager.getSessionFactory().openSession();
		session.beginTransaction();
		
		Query q = session.createQuery(c);
		List<Object[]> results = q.list();

		for(Object[] cadena : results) {
			System.out.println(" - " + cadena[0].toString() + ": " + cadena[1].toString());
		}
		session.close();
	}
	
	
	//pruebas
	
	public static void main (String...args) {
		
		    int a  = consulta4("SELECT * FROM usuario");
			
			System.out.println(a);
		
		
	}

}
