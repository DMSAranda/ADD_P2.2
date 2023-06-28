package dam2.add.p22.modelo;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Provincia {

	@SerializedName("id")     //para recoger la variable id del json
	@Expose
	private String id;
	@SerializedName("nm")	  //para recoger la variable nombre del json
	@Expose
	private String nombre;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
