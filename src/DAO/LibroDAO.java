// Integrantes: Andres Pulgarin, Isabella Perez y Camila Duque | Ficha: 3412768 | Programa: ADSO - SENA
package DAO;

import ConexionDb.ConexionDB;
import Modelo.Libro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class LibroDAO {

    
    public void agregar(Libro libro) {
        String sql = "INSERT INTO libros (titulo, autor, anio, genero) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, libro.getTitulo());
            ps.setString(2, libro.getAutor());
            ps.setInt(3, libro.getAnio());
            ps.setString(4, libro.getGenero());

            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Libro registrado exitosamente.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar libro: " + e.getMessage());
        }
    }

    
    public String verTodos() {
        String sql = "SELECT * FROM libros";
        StringBuilder sb = new StringBuilder("=== CATALOGO DE BIBLIOTECA ===\n\n");
        boolean hayRegistros = false;

        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                hayRegistros = true;
                sb.append("ID: ").append(rs.getInt("id"))
                  .append(" | Titulo: ").append(rs.getString("titulo"))
                  .append(" | Autor: ").append(rs.getString("autor"))
                  .append(" | Anio: ").append(rs.getInt("anio"))
                  .append(" | Genero: ").append(rs.getString("genero"))
                  .append("\n");
            }
        } catch (SQLException e) {
            return "Error al consultar libros: " + e.getMessage();
        }

        return hayRegistros ? sb.toString() : "No hay libros registrados en la base de datos.";
    }

   
    public void actualizar(Libro libro) {
        String sql = "UPDATE libros SET titulo = ?, autor = ?, anio = ?, genero = ? WHERE id = ?";
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, libro.getTitulo());
            ps.setString(2, libro.getAutor());
            ps.setInt(3, libro.getAnio());
            ps.setString(4, libro.getGenero());
            ps.setInt(5, libro.getId());

            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "Libro con ID " + libro.getId() + " actualizado correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontro ningun libro con ID " + libro.getId());
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar libro: " + e.getMessage());
        }
    }

   
    public void eliminar(int id) {
        String sql = "DELETE FROM libros WHERE id = ?";
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "Libro eliminado correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No existe un libro con el ID " + id);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar libro: " + e.getMessage());
        }
    }
}
