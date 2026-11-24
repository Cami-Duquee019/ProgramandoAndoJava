// Integrantes: Andres Pulgarin, Isabella Perez y Camila Duque | Ficha: 3412768 | Programa: ADSO - SENA
package ConexionDb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InicializarDB {

    public static void inicializar() {
        crearTablas();
        insertarDatosIniciales();
    }

    private static void crearTablas() {
        String sqlUsuarios = "CREATE TABLE IF NOT EXISTS usuarios (" +
                             "id       INTEGER PRIMARY KEY AUTOINCREMENT," +
                             "username TEXT NOT NULL UNIQUE," +
                             "password TEXT NOT NULL)";

        String sqlLibros = "CREATE TABLE IF NOT EXISTS libros (" +
                           "id     INTEGER PRIMARY KEY AUTOINCREMENT," +
                           "titulo TEXT NOT NULL," +
                           "autor  TEXT NOT NULL," +
                           "anio   INTEGER NOT NULL," +
                           "genero TEXT NOT NULL)";

        try (Connection conn = ConexionDB.getConexion();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sqlUsuarios);
            stmt.execute(sqlLibros);
        } catch (SQLException e) {
            System.out.println("Error creando tablas: " + e.getMessage());
        }
    }

    private static void insertarDatosIniciales() {
        insertarUsuarioSiNoExiste("admin", "admin123");
        insertarUsuarioSiNoExiste("empleado", "emp456");

        if (contarLibros() == 0) {
            insertarLibro("Cien anos de soledad", "Gabriel Garcia Marquez", 1967, "Realismo magico");
            insertarLibro("Clean Code", "Robert C. Martin", 2008, "Ingenieria de Software");
            insertarLibro("El principito", "Antoine de Saint-Exupery", 1943, "Fabula");
        }
    }

    private static void insertarUsuarioSiNoExiste(String username, String password) {
        String sql = "INSERT OR IGNORE INTO usuarios (username, password) VALUES (?, ?)";
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error insertando usuario inicial: " + e.getMessage());
        }
    }

    private static int contarLibros() {
        String sql = "SELECT COUNT(*) FROM libros";
        try (Connection conn = ConexionDB.getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            System.out.println("Error contando libros: " + e.getMessage());
        }
        return 0;
    }

    private static void insertarLibro(String titulo, String autor, int anio, String genero) {
        String sql = "INSERT INTO libros (titulo, autor, anio, genero) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, titulo);
            ps.setString(2, autor);
            ps.setInt(3, anio);
            ps.setString(4, genero);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error insertando libro inicial: " + e.getMessage());
        }
    }
}
