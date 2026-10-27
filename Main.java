import java.util.Scanner;

class Servidor {
    String nombre; //nombre servidor
    String origen; //interno o externo
    String critico; //si o no
    int intentosTotales;
    int intentosFallidos; 

    boolean datosValidos() {
        return !nombre.trim().isEmpty()
            && (origen.trim().equalsIgnoreCase("interno")
                || origen.trim().equalsIgnoreCase("externo"))
            && (critico.trim().equalsIgnoreCase("si")
                || critico.trim().equalsIgnoreCase("no"))
            && intentosTotales > 0
            && intentosFallidos >= 0
            && intentosFallidos <= intentosTotales;

    }

    String evaluarEstado() {
        if (!datosValidos()) {
            return "DATOS INVALIDOS";
        }

        boolean externo = origen.trim().equalsIgnoreCase("externo");
        boolean esCritico = critico.trim().equalsIgnoreCase("si");

        if (intentosFallidos >=5 || (esCritico && externo && intentosFallidos>=3)) {
            return "BLOQUEADO";
        }

        if ((intentosFallidos >=3 && intentosFallidos <=4) || (externo && intentosFallidos >=2)) {
                return "ALERTA";
        }

        return "NORMAL";
    }

    double porcentajeFallos() {
        return intentosFallidos * 100.0 / intentosTotales;
    }

    void mostrarResumen() {
        System.out.print(nombre.trim() + " | " + intentosFallidos + "/" + intentosTotales + " | ");

        if (datosValidos()) {
            System.out.printf(java.util.Locale.US, "%.1f%%", porcentajeFallos());
        } else {

            System.out.print("N/D");
        }
        
        System.out.println(" | " + evaluarEstado());
    }
}

public class Main {
    static void leerServidor(Scanner teclado, Servidor servidor) {
        System.out.print("Nombre: ");
        servidor.nombre = teclado.nextLine();
        System.out.print("Origen (Interno/Externo): ");
        servidor.origen = teclado.nextLine();
        System.out.print("Critico (Si/no): ");
        servidor.critico = teclado.nextLine();
        System.out.print("Intentos Totales: ");
        servidor.intentosTotales = teclado.nextInt();
        System.out.print("Intentos Fallidos: ");
        servidor.intentosFallidos = teclado.nextInt();

        teclado.nextLine();
    }

    static int nivelPrioridad(Servidor servidor) {
        String estado = servidor.evaluarEstado();
        if (estado.equals("BLOQUEADO")) {
            return 2;
        }

        if (estado.equals("ALERTA")) {
            return 1;
        }

        if (estado.equals("NORMAL")) {
            return 0;
        }

        return -1;
    }

    static void compararServidores(Servidor primero, Servidor segundo) {
        int nivelPrimero = nivelPrioridad(primero);
        int nivelSegundo = nivelPrioridad(segundo);

        if (nivelPrimero == -1 || nivelSegundo == -1){
            System.out.println("COMPARACION NO DISPONIBLE");
        } else if (nivelPrimero == nivelSegundo) {
            System.out.println("IGUAL PRIORIDAD");
        } else if (nivelPrimero > nivelSegundo) {
            System.out.println("ATENCION PRIORITARIA: " + primero.nombre.trim());
        } else {
            System.out.println("ATENCION PRIORITARIA: " + segundo.nombre.trim());
        }
    }

    public static void main(String [] args) {
        Scanner teclado = new Scanner(System.in);

        Servidor primero = new Servidor();
        Servidor segundo = new Servidor();

        System.out.println("DATOS PRIMERO SERVIDOR: ");
        leerServidor(teclado, primero);
        System.out.println("DATOS SEGUNDO SERVIDOR: ");
        leerServidor(teclado, segundo);

        System.out.println("========== REPORTE DE SEGURIDAD ==========");
        primero.mostrarResumen();
        segundo.mostrarResumen();
        compararServidores(primero, segundo);
        teclado.close();
    }
}


/*
1. ¿Por qué dos objetos de la misma clase pueden almacenar valores diferentes?

Porque cada objeto tiene sus propios atributos de instancia. La clase define qué datos y
métodos tendrán los objetos, pero cada objeto almacena sus propios valores.

Esto es posible porque los atributos de Servidor no se han declarado static.

¿Qué diferencia existe entre Servidor b = a; y Servidor b = new Servidor();?

La instrucción Servidor b = a; copia la información que esta almacenada en a. No crea
otro objeto, ambas variables pertenecen al mismo servidor.
En cambio, Servidor b = new Servidor(); crea otro objeto independiente

¿Por qué es importante validar antes de calcular el porcentaje?

Porque el cálculo solo tiene sentido si los datos son correctos. Un total igual a cero provo-
caría una división entre cero, por otro lado, los valores negativos o los fallos superiores al total
generarían porcentajes imposibles, que no tendrían ningún tipo de sentido.

Por este motivo, el método mostrarResumen() comprueba primero la validez.
De esta manera el método porcentajeFallos() solo se ejecuta cuando todos los datos cumplen las reglas.

*/