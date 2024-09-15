import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class TablaSimbolo {
     private final List<Simbolo> tablaSimbolo = new ArrayList<>();
     int i=0;

     public TablaSimbolo() {
           
     }
     
     // busca un simbolo por nombre en la TS
     public Simbolo buscar(String nombre){
         Iterator<Simbolo> it= tablaSimbolo.iterator();
             for(; it.hasNext();){
                Simbolo a =it.next();
                //Iteratator para recorrer una lista de elementos y para realizar esto dispone de hasNext que nos permite devolver si existe un siguiente elemento a la hora de iterar   
                 if( a.getNombre().equals(nombre)){
                     return a; // existe el simbolo en la TS y lo retorna
                    
                 }
             }
             return null; // no existe el simbolo entonces retona null
     }
     
     // inserta un simbolo si no existe en la tabla de simbolos (no puede insertar dos simbolos ocn el mismo nombre)
     public Simbolo insertar(Simbolo s){
         if (this.buscar(s.getNombre())==null){
             tablaSimbolo.add(s);  // no existe entonces lo inserta
             return s;
         }else 
             return null; // existe entonces retorna null para informar que no pudo insertar
     }
     
     public String toString(){
		  String result = ""; 
		  Iterator<Simbolo> it= tablaSimbolo.iterator();
             for(; it.hasNext();){
				Simbolo a =it.next(); 
                result+=a.toString();           
             }
		 return result;
	}     
}
