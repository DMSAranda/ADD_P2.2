package dam2.add.p22.modelo;

public class Usuario {

	private int id;
	private String nombre; // objeto usuario con atributos y metodos
	private String password;
	private String provincia;
	private String municipio;
	private boolean bloqueado = false;

	public String cadena() {

		String cadena;
		cadena = "Nombre: " + nombre + " / Contraseña: " + password;
		return cadena;
	}

	public Usuario(int id, String nombre, String password, String provincia, String municipio, boolean bloqueado) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.password = password;
		this.provincia = provincia;
		this.municipio = municipio;
		this.bloqueado = bloqueado;
	}

	public Usuario(String nombre, String password, String provincia, String municipio, boolean bloqueado) {
		super();
		this.nombre = nombre;
		this.password = password;
		this.provincia = provincia;
		this.municipio = municipio;
		this.bloqueado = bloqueado;
	}

	public Usuario(int id) {
		super();
		this.id = id;
	}

	public Usuario() {
		super();
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isBloqueado() {
		return bloqueado;
	}

	public void setBloqueado(boolean bloqueado) {
		this.bloqueado = bloqueado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}