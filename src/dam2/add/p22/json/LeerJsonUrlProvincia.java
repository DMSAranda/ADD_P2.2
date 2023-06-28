package dam2.add.p22.json;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import com.google.gson.Gson;
import dam2.add.p22.modelo.Provincia;
import dam2.add.p22.lib.ComparadorProvincias;

public class LeerJsonUrlProvincia {

	public static void main(String[] args) {

		ArrayList<Provincia> aux = leerUrl(); 

		for (Provincia p : aux) {
			String nombre = p.getNombre();
			System.out.println(nombre);
		}
	}

	public static ArrayList<Provincia> leerUrl() {
		
		String cadenaJson = "";
		
		try {
			URL url = new URL("https://raw.githubusercontent.com/IagoLast/pselect/master/data/provincias.json");

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			// conn.setRequestProperty("Accept", "application/dam2.add.p22.json");

			if (conn.getResponseCode() != 200) {
				// si la respuesta del servidor es distinta al codigo 200 lanzaremos una
				// Exception
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			// creamos un StringBuilder para almacenar la respuesta del web service
			StringBuilder sb = new StringBuilder();
			int cp;
			while ((cp = br.read()) != -1) {
				sb.append((char) cp);
			}
			// en la cadena output almacenamos toda la respuesta del servidor
			cadenaJson = sb.toString();
			// System.out.println(output);
			
			

			conn.disconnect();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Provincia[] provincias = new Gson().fromJson(cadenaJson, Provincia[].class);
		
		ArrayList<Provincia> aux = new ArrayList<Provincia>();
		
		//para ordenar por codigo de provincia
		
		Collections.addAll(aux, provincias);  //METER ARRAY EN ARRAYLIST
		
		Collections.sort(aux, new ComparadorProvincias());  //ORDENAR ARRAYLIST
		
		return aux;	
	}

}
