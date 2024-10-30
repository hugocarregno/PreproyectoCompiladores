class Codigo3D{
    Etiqueta codOP;
    Simbolo op1;
    Simbolo op2;
    Simbolo res;

    public Codigo3D(Etiqueta codOP, Simbolo op1, Simbolo op2, Simbolo res){
        this.codOP = codOP;
        this.op1 = op1;
        this.op2 = op2;
        this.res = res;
    }

    public static String generarLinea(Etiqueta codOP, Simbolo op1, Simbolo op2, Simbolo res){
        String c1, c2, r;
        r = res.getNombre();
        String salidaString;

        if(codOP == Etiqueta.SUMA || codOP == Etiqueta.MULT || codOP == Etiqueta.AND || codOP == Etiqueta.OR){
            c1 = (op1.getEtiqueta() == Etiqueta.VALORENTERO || op1.getEtiqueta() == Etiqueta.VALORBOOLEANO) ? String.valueOf(op1.getValor()) : op1.getNombre();
            c2 = (op2.getEtiqueta() == Etiqueta.VALORENTERO || op2.getEtiqueta() == Etiqueta.VALORBOOLEANO) ? String.valueOf(op2.getValor()) : op2.getNombre();
            salidaString = codOP + " " + c1 + " " + c2 + " " + r;
        }else if(codOP == Etiqueta.ASIGNACION){
            c1 = op1.getNombre();
            salidaString = codOP + " " + c1 + "#" + r; //MOV
        }else if(codOP == Etiqueta.RETURN){
            salidaString = codOP + " # # " + r;
        }else{
            salidaString = "¡error, codOP erroneo!.";
        }
        return salidaString;
    }

    @Override
    public String toString() {
        return " "+ codOP + " " + op1 + " " + op2 + " " + res;
    }
   
}