// Integrantes: Andres Pulgarin, Isabella Perez y Camila Duque | Ficha: 3412768 | Programa: ADSO - SENA
// Actividad: Guia 1 - Condicionales, Ciclos y Arreglos con JOptionPane

import javax.swing.JOptionPane;

public class Guia1JOptionPane {

    public static void main(String[] args) {
        ejecutarMenuGuia1();
    }

    public static void ejecutarMenuGuia1() {
        String opcion = "";
        while (opcion != null && !opcion.equals("0")) {
            opcion = JOptionPane.showInputDialog(
                "=== GUIA 1: CONDICIONALES Y CICLOS ===\n" +
                "1. Numero Positivo, Negativo o Cero (if-else)\n" +
                "2. Calculadora Basica (switch + validacion cero)\n" +
                "3. Anio Bisiesto (operadores logicos)\n" +
                "4. Simulacion Login Basico (equals)\n" +
                "5. Numeros Impares del 1 al 50 (ciclo while)\n" +
                "6. Tablas de Multiplicar del 1 al 5 (for anidado)\n" +
                "7. Arreglo: Suma, Mayor y Menor (for + arrays)\n" +
                "0. Salir de Guia 1\n\n" +
                "Elige una opcion:"
            );

            if (opcion == null || opcion.equals("0")) break;

            try {
                switch (opcion) {
                    case "1":
                        int num = Integer.parseInt(JOptionPane.showInputDialog("Ingresa un numero entero:"));
                        if (num > 0) {
                            JOptionPane.showMessageDialog(null, "El numero " + num + " es POSITIVO.");
                        } else if (num < 0) {
                            JOptionPane.showMessageDialog(null, "El numero " + num + " es NEGATIVO.");
                        } else {
                            JOptionPane.showMessageDialog(null, "El numero es CERO.");
                        }
                        break;

                    case "2":
                        double n1 = Double.parseDouble(JOptionPane.showInputDialog("Primer numero:"));
                        double n2 = Double.parseDouble(JOptionPane.showInputDialog("Segundo numero:"));
                        String op = JOptionPane.showInputDialog("Operador (+, -, *, /):");
                        if (op == null) break;
                        switch (op) {
                            case "+": JOptionPane.showMessageDialog(null, "Resultado: " + (n1 + n2)); break;
                            case "-": JOptionPane.showMessageDialog(null, "Resultado: " + (n1 - n2)); break;
                            case "*": JOptionPane.showMessageDialog(null, "Resultado: " + (n1 * n2)); break;
                            case "/":
                                if (n2 == 0) {
                                    JOptionPane.showMessageDialog(null, "Error: No se puede dividir entre cero.");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Resultado: " + (n1 / n2));
                                }
                                break;
                            default: JOptionPane.showMessageDialog(null, "Operador no valido.");
                        }
                        break;

                    case "3":
                        int anio = Integer.parseInt(JOptionPane.showInputDialog("Ingresa un anio:"));
                        boolean bisiesto = (anio % 4 == 0 && anio % 100 != 0) || (anio % 400 == 0);
                        JOptionPane.showMessageDialog(null, anio + (bisiesto ? " ES bisiesto." : " NO es bisiesto."));
                        break;

                    case "4":
                        String u = JOptionPane.showInputDialog("Usuario (admin):");
                        String p = JOptionPane.showInputDialog("Contrasena (1234):");
                        if ("admin".equals(u) && "1234".equals(p)) {
                            JOptionPane.showMessageDialog(null, "Bienvenido (validado con .equals())");
                        } else {
                            JOptionPane.showMessageDialog(null, "Credenciales incorrectas.");
                        }
                        break;

                    case "5":
                        StringBuilder impares = new StringBuilder("Impares del 1 al 50 (while):\n");
                        int i = 1;
                        while (i <= 50) {
                            if (i % 2 != 0) impares.append(i).append(" ");
                            i++;
                        }
                        JOptionPane.showMessageDialog(null, impares.toString());
                        break;

                    case "6":
                        StringBuilder tablas = new StringBuilder("Tablas del 1 al 5 (for anidado):\n");
                        for (int t = 1; t <= 5; t++) {
                            tablas.append("Tabla del ").append(t).append(": ");
                            for (int m = 1; m <= 10; m++) {
                                tablas.append(t * m).append(" ");
                            }
                            tablas.append("\n");
                        }
                        JOptionPane.showMessageDialog(null, tablas.toString());
                        break;

                    case "7":
                        int[] numeros = {18, 45, 7, 92, 31, 14, 60, 3, 27, 50};
                        int suma = 0, mayor = numeros[0], menor = numeros[0];
                        StringBuilder lista = new StringBuilder("Arreglo: ");
                        for (int k = 0; k < numeros.length; k++) {
                            lista.append(numeros[k]).append(" ");
                            suma += numeros[k];
                            if (numeros[k] > mayor) mayor = numeros[k];
                            if (numeros[k] < menor) menor = numeros[k];
                        }
                        JOptionPane.showMessageDialog(null,
                            lista + "\nSuma total: " + suma + "\nMayor: " + mayor + "\nMenor: " + menor);
                        break;

                    default:
                        JOptionPane.showMessageDialog(null, "Opcion invalida.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada invalida: debes ingresar un numero.");
            }
        }
    }
}
