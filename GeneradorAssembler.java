import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GeneradorAssembler {
    public static void generar(List<Codigo3D> listaC3D) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("assembly.txt"))) {

            writer.write("main:\n");
            writer.write("pushq %rbp\n");
            writer.write("movq %rsp, %rbp\n");

            for (Codigo3D c3d : listaC3D) {
                switch (c3d.codOP) {
                    case SUMA:
                        sumar(writer, c3d);
                        break;

                    case ASIGNACION:
                        asignar(writer, c3d);
                        break;

                    case MULT:
                        multiplicar(writer, c3d);
                        break;

                    case RETURN:
                        retornar(writer, c3d);
                        break;

                    case AND:
                    case OR:
                        logicos(writer, c3d);
                        break;

                    case DECLARACION:
                        writer.write("subq $8, %rsp\n"); // Reserva espacio en la pila
                        writer.write("movq $" + c3d.op1.getValor() + ", 0(%rsp)\n"); // Inicializa si es necesario
                        break;

                    case PROGRAMA:
                        // Si necesitas realizar alguna inicialización para el programa
                        writer.write("## Inicio del programa ##\n");
                        break;

                    default:
                        writer.write("Operación no soportada: " + c3d.codOP + "\n");
                        break;
                }
            }
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo: " + e.getMessage());
        }
    }

    private static void sumar(BufferedWriter writer, Codigo3D c3d) throws IOException {
        String c1 = getOperandValue(c3d.op1);
        String c2 = getOperandValue(c3d.op2);
        writer.write("movq " + c1 + ", %rax\n");
        writer.write("addq " + c2 + ", %rax\n");
        writer.write("movq %rax, " + c3d.res.getNombre() + "\n");
    }

    private static void asignar(BufferedWriter writer, Codigo3D c3d) throws IOException {
        String c1 = c3d.op1.getNombre();

        writer.write("movq " + c1 + ", " + c3d.res.getNombre() + "\n");

    }

    private static void multiplicar(BufferedWriter writer, Codigo3D c3d) throws IOException {
        String c1 = getOperandValue(c3d.op1);
        String c2 = getOperandValue(c3d.op2);
        writer.write("movq " + c1 + ", %rax\n");
        writer.write("imulq " + c2 + ", %rax\n");
        writer.write("movq %rax, " + c3d.res.getNombre() + "\n");
    }

    private static void retornar(BufferedWriter writer, Codigo3D c3d) throws IOException {
        writer.write("movq " + c3d.res.getNombre() + ", %rax\n");
        writer.write("popq %rbp\n");
        writer.write("ret\n");
    }

    public static void logicos(BufferedWriter writer, Codigo3D c3d) throws IOException {
        if (c3d.op1.getEtiqueta() != Etiqueta.VALORBOOLEANO || c3d.op2.getEtiqueta() != Etiqueta.VALORBOOLEANO) {
            writer.write("Error: Ambos operandos deben ser booleanos para la operación " + c3d.codOP + "\n");
            return;
        }

        String c1 = getOperandValue(c3d.op1);
        String c2 = getOperandValue(c3d.op2);

        // Operación AND
        if (c3d.codOP == Etiqueta.AND) {
            // Cargar el primer operando en %eax
            writer.write("movl " + c1 + ", %eax\n");
            writer.write("testl %eax, %eax\n"); // Verificar si el primer operando es verdadero
            writer.write("jz .Lfalse" + c3d.res.getNombre() + "\n"); // Si es 0, saltar a falso

            // Cargar el segundo operando
            writer.write("movl " + c2 + ", %eax\n");
            writer.write("testl %eax, %eax\n"); // Verificar si el segundo operando es verdadero
            writer.write("jz .Lfalse" + c3d.res.getNombre() + "\n"); // Si es 0, saltar a falso

            // Ambos son verdaderos, entonces el resultado es verdadero
            writer.write("movl $1, " + c3d.res.getNombre() + "\n"); // Guardar 1 (verdadero)
            writer.write("jmp .Lend" + c3d.res.getNombre() + "\n"); // Saltar al final

            // Label para el caso falso
            writer.write(".Lfalse" + c3d.res.getNombre() + ":\n");
            writer.write("movl $0, " + c3d.res.getNombre() + "\n"); // Guardar 0 (falso)

            // Label de finalización
            writer.write(".Lend" + c3d.res.getNombre() + ":\n");

            // Operación OR
        } else if (c3d.codOP == Etiqueta.OR) {
            // Cargar el primer operando en %eax
            writer.write("movl " + c1 + ", %eax\n");
            writer.write("testl %eax, %eax\n"); // Verificar si el primer operando es verdadero
            writer.write("jnz .Ltrue" + c3d.res.getNombre() + "\n"); // Si es verdadero, saltar a verdadero

            // Cargar el segundo operando
            writer.write("movl " + c2 + ", %eax\n");
            writer.write("testl %eax, %eax\n"); // Verificar si el segundo operando es verdadero
            writer.write("jz .Lfalse" + c3d.res.getNombre() + "\n"); // Si es 0, saltar a falso

            // Al menos uno es verdadero
            writer.write(".Ltrue" + c3d.res.getNombre() + ":\n");
            writer.write("movl $1, " + c3d.res.getNombre() + "\n"); // Guardar 1 (verdadero)
            writer.write("jmp .Lend" + c3d.res.getNombre() + "\n"); // Saltar al final

            // Label para el caso falso
            writer.write(".Lfalse" + c3d.res.getNombre() + ":\n");
            writer.write("movl $0, " + c3d.res.getNombre() + "\n"); // Guardar 0 (falso)

            // Label de finalización
            writer.write(".Lend" + c3d.res.getNombre() + ":\n");
        }
    }

    private static String getOperandValue(Simbolo op) {
        return (op.getEtiqueta() == Etiqueta.VALORENTERO || op.getEtiqueta() == Etiqueta.VALORBOOLEANO)
                ? String.valueOf(op.getValor())
                : op.getNombre();
    }
}
