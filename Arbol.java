
public class Arbol {
     
     private Simbolo info;
     private Arbol HI;
     private Arbol HD;
     private int id;
     
     static int idNro = 0;
     
     public Arbol(Simbolo s){
		 
	    info = s;
	    HI = null;
	    HD = null;	
	    id = idNro;
	    idNro+=1; 
	 }
     
     public Arbol(Simbolo s, Arbol hi, Arbol hd){
	    info = s;
	    HI = hi;
	    HD = hd;	 
	    id = idNro;
	    idNro+=1;
	 }
     
     public int getId() {
        return id;
     }
     public Simbolo getInfo() {
        return info;
     }

     public Arbol getHD() {
        return HD;
     }
     
     public Arbol getHI() {
        return HI;
     }
      
     
     public String toString(){
		String result = "( Nodo: "+id +" - "+info.toString()+" - ";
		if (HI!=null) result = result + "HI : "+ HI.getId()+" - ";
		if (HD!=null) result = result + "HD : "+ HD.getId(); 
		result+=" )\n";
		if (HI!=null) result = result + HI.toString();
		if (HD!=null) result = result + HD.toString(); 
  		return result;
	}     
}
