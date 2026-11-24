// Integrantes: Andres Pulgarin, Isabella Perez y Camila Duque | Ficha: 3412768 | Programa: ADSO - SENA
// Sistema de Gestion de Biblioteca Digital - Control de Acceso y CRUD con SQLite

import ConexionDb.InicializarDB;
import DAO.LibroDAO;
import DAO.UsuarioDAO;
import Modelo.Libro;
import Modelo.Usuario;
import javax.swing.JOptionPane;

public class Main {

    private static Usuario usuarioActivo = null;
    private static final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private static final LibroDAO libroDAO = new LibroDAO();

    public static void main(String[] args) {
        InicializarDB.inicializar();

        boolean ejecutando = true;
        while (ejecutando) {
            if (usuarioActivo == null) {
                boolean autenticado = iniciarSesion();
                if (!autenticado) {
                    ejecutando = false;
                }
            } else {
                ejecutando = mostrarMenuPrincipal();
            }
        }
        JOptionPane.showMessageDialog(null, "Sistema finalizado. Hasta pronto!");
    }

    private static boolean iniciarSesion() {
        int intentos = 0;
        final int MAX_INTENTOS = 3;

        while (intentos < MAX_INTENTOS) {
            String user = JOptionPane.showInputDialog(
                "=== INICIO DE SESION - BIBLIOTECA DIGITAL ===\n" +
                "Intento " + (intentos + 1) + " de " + MAX_INTENTOS + "\n\n" +
                "Usuario:"
            );
            if (user == null) return false;

            String pass = JOptionPane.showInputDialog("Contrasena:");
            if (pass == null) return false;

            Usuario encontrado = usuarioDAO.autenticar(user.trim(), pass.trim());

            if (encontrado != null) {
                usuarioActivo = encontrado;
                JOptionPane.showMessageDialog(null, "Bienvenido, " + usuarioActivo.getUsername() + "!");
                return true;
            } else {
                intentos++;
                int restantes = MAX_INTENTOS - intentos;
                if (restantes > 0) {
                    JOptionPane.showMessageDialog(null,
                        "Credenciales incorrectas. Te quedan " + restantes + " intento(s).");
                } else {
                    JOptionPane.showMessageDialog(null,
                        "Has superado el maximo de 3 intentos. El programa se cerrara.");
                }
            }
        }
        return false;
    }

    private static boolean mostrarMenuPrincipal() {
        String opcion = JOptionPane.showInputDialog(
            "=== SISTEMA BIBLIOTECA DIGITAL ===\n" +
            "Sesion activa: " + usuarioActivo.getUsername() + "\n\n" +
            "1. [CREATE] Agregar nuevo libro\n" +
            "2. [READ]   Ver todos los libros\n" +
            "3. [UPDATE] Actualizar libro por ID\n" +
            "4. [DELETE] Eliminar libro por ID\n" +
            "-------------------------------------\n" +
            "5. Demo Guia 1 (Condicionales y Ciclos)\n" +
            "6. Demo Guia 2 (Ejercicios Try-Catch)\n" +
            "-------------------------------------\n" +
            "7. Cerrar Sesion (cambiar de usuario)\n" +
            "0. Salir del programa\n\n" +
            "Selecciona una opcion:"
        );

        if (opcion == null || opcion.equals("0")) {
            return false;
        }

        switch (opcion) {
            case "1":
                agregarLibro();
                break;
            case "2":
                JOptionPane.showMessageDialog(null, libroDAO.verTodos());
                break;
            case "3":
                actualizarLibro();
                break;
            case "4":
                eliminarLibro();
                break;
            case "5":
                Guia1JOptionPane.ejecutarMenuGuia1();
                break;
            case "6":
                EjerciciosTryCatch.ejecutarMenuTryCatch();
                break;
            case "7":
                JOptionPane.showMessageDialog(null, "Cerrando sesion de " + usuarioActivo.getUsername() + "...");
                usuarioActivo = null;
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opcion no valida.");
        }
        return true;
    }

    private static void agregarLibro() {
        try {
            String titulo = JOptionPane.showInputDialog("Titulo del libro:");
            if (titulo == null || titulo.isBlank()) return;

            String autor = JOptionPane.showInputDialog("Autor del libro:");
            if (autor == null || autor.isBlank()) return;

            int anio = Integer.parseInt(JOptionPane.showInputDialog("Anio de publicacion:"));

            String genero = JOptionPane.showInputDialog("Genero literario:");
            if (genero == null || genero.isBlank()) return;

            Libro nuevo = new Libro(titulo.trim(), autor.trim(), anio, genero.trim());
            libroDAO.agregar(nuevo);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: el anio debe ser un numero entero valido.");
        }
    }

    private static void actualizarLibro() {
        try {
            String listado = libroDAO.verTodos();
            int id = Integer.parseInt(JOptionPane.showInputDialog(listado + "\nIngresa el ID del libro a actualizar:"));

            String titulo = JOptionPane.showInputDialog("Nuevo titulo:");
            if (titulo == null || titulo.isBlank()) return;

            String autor = JOptionPane.showInputDialog("Nuevo autor:");
            if (autor == null || autor.isBlank()) return;

            int anio = Integer.parseInt(JOptionPane.showInputDialog("Nuevo anio de publicacion:"));

            String genero = JOptionPane.showInputDialog("Nuevo genero:");
            if (genero == null || genero.isBlank()) return;

            Libro actualizado = new Libro(id, titulo.trim(), autor.trim(), anio, genero.trim());
            libroDAO.actualizar(actualizado);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: el ID y el anio deben ser numeros enteros.");
        }
    }

    private static void eliminarLibro() {
        try {
            String listado = libroDAO.verTodos();
            int id = Integer.parseInt(JOptionPane.showInputDialog(listado + "\nIngresa el ID del libro a eliminar:"));

            int confirmacion = JOptionPane.showConfirmDialog(
                null,
                "Estas seguro de eliminar el libro con ID " + id + "?",
                "Confirmar eliminacion",
                JOptionPane.YES_NO_OPTION
            );

            if (confirmacion == JOptionPane.YES_OPTION) {
                libroDAO.eliminar(id);
            } else {
                JOptionPane.showMessageDialog(null, "Eliminacion cancelada.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: debes ingresar un ID numerico valido.");
        }
    }
}
