package modelo;

import java.io.Serializable;

public class ControladorLibro implements Serializable {
    private String isbn;
    private String titulo;
    private String genero;
    private boolean leido;

    // Constructor
    public ControladorLibro(String isbn, String titulo, String genero, boolean leido) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.genero = genero;
        this.leido = leido;
    }

    // Getters y Setters
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }
    public boolean isLeido() { return leido; }
    public void setLeido(boolean leido) { this.leido = leido; }
}
