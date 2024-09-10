

public class Principal {
     static public void main(String argv[]){
        
        
/*
 * int x = 4;
 * bool y = true;
 * x = x+1;
 * return x;
 * */        
        
        Simbolo simbolo  = new Simbolo(Etiqueta.VARIABLE,"x",Tipos.INT,0,1); // var x, tipo int, valor?
        Simbolo simbolo2 = new Simbolo(Etiqueta.VARIABLE,"y",Tipos.BOOL,0,2); // var y, tipo bool, valor?
        Simbolo simbolo3 = new Simbolo(Etiqueta.VALORENTERO,"",Tipos.INT,4,1); // valor entero 4
        Simbolo simbolo4 = new Simbolo(Etiqueta.VALORBOOLEANO, "",Tipos.BOOL,1,2); // valor booleano true
        Simbolo simbolo5 = new Simbolo(Etiqueta.VALORENTERO,"",Tipos.INT,1,3); // valor entero 1
      
        Simbolo progSim  = new Simbolo(Etiqueta.PROGRAMA); 
        
        Simbolo declsSim = new Simbolo(Etiqueta.DECLS);
        Simbolo declxSim = new Simbolo(Etiqueta.DECLARACION,"",Tipos.NOTTYPE,0,1);
        Simbolo declySim = new Simbolo(Etiqueta.DECLARACION,"",Tipos.NOTTYPE,0,2);
             
        Simbolo sentsSim = new Simbolo(Etiqueta.SENTS);
        Simbolo asigxSim = new Simbolo(Etiqueta.ASIGNACION,"",Tipos.NOTTYPE,0,3);
        Simbolo sumaSim  = new Simbolo(Etiqueta.SUMA,"",Tipos.NOTTYPE,0,3);
        Simbolo retxSim  = new Simbolo(Etiqueta.RETURN,"",Tipos.NOTTYPE,0,4);
        
        Arbol hijoI = new Arbol(simbolo); // nodo arbol de x
        Arbol hijoD = new Arbol(simbolo3); //nodo arbol de 4
        Arbol padre = new Arbol(declxSim, hijoI,hijoD); // arbol de int x = 4;
        
        hijoI = new Arbol(simbolo2); // nodo arbol de x
        hijoD = new Arbol(simbolo4); //nodo arbol de 4
        Arbol padre1 = new Arbol(declySim, hijoI,hijoD); // arbol de bool y = true;
        
        Arbol padreDec = new Arbol (progSim, new Arbol(declsSim,padre,padre1),     // arbol de las declaraciones
                                             new Arbol(sentsSim, new Arbol(asigxSim,new Arbol(simbolo),new Arbol(sumaSim,new Arbol(simbolo),new Arbol(simbolo5))), // arbol de x=x+1;
                                                                 new Arbol(retxSim,null,new Arbol(simbolo)))    // arbol de return x; 
                                   );
        
        
        
        
        System.out.println(padreDec);
      
      
      
      
      
      
      
      
      
      
      
      
      
      
        TablaSimbolo tabla= new TablaSimbolo();

        if ( tabla.insertar(simbolo) == null ){ System.out.println("Variable declarada: "+simbolo.getNombre()+" (Línea "+simbolo.getNroLinea()+").\n"); }    
        if ( tabla.insertar(simbolo2) == null ){ System.out.println("Variable declarada: "+simbolo2.getNombre()+" (Línea "+simbolo2.getNroLinea()+").\n");}
        

        Simbolo s = tabla.buscar("z");
        if (s==null) {System.out.println("Variable no declarada: z.\n");}
        else{ System.out.println("Simbolo:  "+s);}
        
        s = tabla.buscar("x");
        if (s==null) {System.out.println("Variable no declarada: x.\n");
        }else{ System.out.println("Simbolo:  "+s);
        }
        
        
        System.out.println(tabla);
        
     }
 
}
