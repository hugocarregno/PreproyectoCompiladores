import java.util.Iterator;

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

    @Override
    public String toString() {
        return " "+ codOP + " " + op1 + " " + op2 + " " + res;
    }
   
}