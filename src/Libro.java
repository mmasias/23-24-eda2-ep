class Libro {
    private int id;
    private String titulo;
    private int añoPublicacion;
    private final String tipo;

    public Libro(int id, String titulo, int añoPublicacion, String tipo) {
        this.id = id;
        this.titulo = titulo;
        this.añoPublicacion = añoPublicacion;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", Titulo ='" + titulo + '\'' +
                ", año publicación=" + añoPublicacion +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}