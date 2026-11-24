// Integrantes: Andres Pulgarin, Isabella Perez y Camila Duque | Ficha: 3412768 | Programa: ADSO - SENA
// Actividad: Guia 2 (3.2) - Ejercicios Practicos de Try-Catch

import javax.swing.JOptionPane;

public class EjerciciosTryCatch {

    public static void main(String[] args) {
        ejecutarMenuTryCatch();
    }

    public static void ejecutarMenuTryCatch() {
        String opcion = "";
        while (opcion != null && !opcion.equals("0")) {
            opcion = JOptionPane.showInputDialog(
                "=== GUIA 2: EJERCICIOS DE TRY-CATCH ===\n" +
                "1. Division entera (NumberFormat / Arithmetic)\n" +
                "2. Conversion Celsius a Fahrenheit (NumberFormat)\n" +
                "3. Calculadora de IMC (throw ArithmeticException)\n" +
                "4. Acceso a arreglo por indice (ArrayIndexOutOfBounds)\n" +
                "5. Validacion en bucle 1-100 (IllegalArgumentException)\n" +
                "6. Cancelacion de dialogo (NullPointerException)\n" +
                "0. Volver / Salir\n\n" +
                "Selecciona un ejercicio:"
            );

            if (opcion == null || opcion.equals("0")) break;

            switch (opcion) {
                case "1": ej1Division(); break;
                case "2": ej2Temperatura(); break;
                case "3": ej3IMC(); break;
                case "4": ej4Arreglo(); break;
                case "5": ej5Bucle(); break;
                case "6": ej6Null(); break;
                default: JOptionPane.showMessageDialog(null, "Opcion invalida.");
            }
        }
    }

    private static void ej1Division() {
        try {
            int a = Integer.parseInt(JOptionPane.showInputDialog("Dividendo:"));
            int b = Integer.parseInt(JOptionPane.showInputDialog("Divisor:"));
            JOptionPane.showMessageDialog(null, "Resultado: " + (a / b));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: ingresa solo numeros enteros.");
        } catch (ArithmeticException e) {
            JOptionPane.showMessageDialog(null, "Error: no se puede dividir entre cero.");
        }
    }

    private static void ej2Temperatura() {
        try {
            double c = Double.parseDouble(JOptionPane.showInputDialog("Temperatura en Celsius:"));
            double f = (c * 9.0 / 5) + 32;
            JOptionPane.showMessageDialog(null, c + " C = " + f + " F");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Ingresa un numero valido. Ejemplo: 36.5");
        }
    }

    private static void ej3IMC() {
        try {
            double peso = Double.parseDouble(JOptionPane.showInputDialog("Peso (kg):"));
            double altura = Double.parseDouble(JOptionPane.showInputDialog("Estatura (m):"));
            if (altura == 0) {
                throw new ArithmeticException("La estatura no puede ser 0");
            }
            double imc = peso / (altura * altura);
            JOptionPane.showMessageDialog(null, "IMC: " + String.format("%.2f", imc));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Dato invalido. Usa punto decimal. Ej: 1.70");
        } catch (ArithmeticException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    private static void ej4Arreglo() {
        String[] nombres = {"Ana", "Luis", "Maria", "Pedro", "Sofia"};
        try {
            int i = Integer.parseInt(JOptionPane.showInputDialog("Posicion (0 a 4):"));
            JOptionPane.showMessageDialog(null, "Nombre: " + nombres[i]);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Ingresa un numero entero.");
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "Posicion invalida. Solo existen posiciones 0 a 4.");
        }
    }

    private static void ej5Bucle() {
        int numero = 0;
        boolean valido = false;
        while (!valido) {
            try {
                String input = JOptionPane.showInputDialog("Numero entre 1 y 100:");
                if (input == null) return;
                numero = Integer.parseInt(input);
                if (numero < 1 || numero > 100) {
                    throw new IllegalArgumentException("Fuera de rango");
                }
                valido = true;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Solo numeros enteros. Intenta de nuevo.");
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, "El numero debe estar entre 1 y 100.");
            }
        }
        JOptionPane.showMessageDialog(null, "Numero aceptado: " + numero);
    }

    private static void ej6Null() {
        try {
            String nombre = JOptionPane.showInputDialog("Escribe tu nombre (o presiona Cancelar):");
            JOptionPane.showMessageDialog(null, "Hola, " + nombre.toUpperCase() + "!");
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Cancelaste la operacion. El nombre es requerido.");
        }
    }
}
