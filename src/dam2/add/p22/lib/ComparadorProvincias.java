package dam2.add.p22.lib;

import java.util.Comparator;
import dam2.add.p22.modelo.Provincia;

/**
 *
 * @author david     compara los id de provincias para ordenarlos posteriormente
 */

public class ComparadorProvincias implements Comparator<Provincia>{
     
     @Override
     public int compare(Provincia p1, Provincia p2){
    	
    	int a1 = Integer.parseInt(p1.getId());
    	
    	int a2 = Integer.parseInt(p2.getId());
    	 
        if(a1 < a2 ){
            return -1;
        }else if(a1 == a2){
            return 0;
        }else{
            return 1;
        }
    }
     
     
}