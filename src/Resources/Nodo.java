package Resources;

public class Nodo {
    private int codigo;
    private String nombre;
    private Nodo siguiente;
    
    public Nodo(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.siguiente = null;
    }
    
    public Nodo getSiguiente() {
        return siguiente;
    }
    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
}
