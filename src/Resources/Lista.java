package Resources;

public class Lista {
    private Nodo inicio;

    public Lista() {
       this.inicio = null;
    }

    public Nodo getInicio() {
        return inicio;
    }

    public void setInicio(Nodo inicio) {
        this.inicio = inicio;
    }

    public int longitud(){
        Nodo ptr = inicio;
        int num = 0;
        while(ptr != null){
            num += 1;
            ptr = ptr.getSiguiente();
        }
        return num;
    }

    public boolean existe(int codigo){
        Nodo ptr = inicio;
        while(ptr != null && ptr.getCodigo() != codigo){
            ptr = ptr.getSiguiente();
        }
        return ptr != null;
    }

    public void agregaInicio(int codigo, String nombre){
        if(!existe(codigo)){
            Nodo nuevo = new Nodo(codigo, nombre);
            nuevo.setSiguiente(inicio);
            inicio = nuevo;
        }
    }

    public void agregarFinal(int codigo, String nombre){
        if(!existe(codigo)){
            Nodo nuevo = new Nodo(codigo, nombre);
            if(inicio == null)
                inicio = nuevo;
            else{
                Nodo ptr = inicio;
                while(ptr.getSiguiente() != null){
                    ptr = ptr.getSiguiente();
                }
                ptr.setSiguiente(nuevo);
            }
        }
    }

    public boolean insertarNodo(int codigo, String nombre, int posicion){
        int num = longitud();
        if (posicion <= num){
            if(!existe(codigo)){
                Nodo nuevo = new Nodo(codigo, nombre);
                Nodo ptr = inicio;
                Nodo qtr = ptr;
                int k = 1;
                while(k < posicion){
                    qtr = ptr;
                    ptr = ptr.getSiguiente();
                    k += 1;
                }
                if(ptr == inicio){
                    nuevo.setSiguiente(inicio);
                    inicio = nuevo;
                }
                else{
                    nuevo.setSiguiente(ptr);
                    qtr.setSiguiente(nuevo);
                }
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean eliminarNodo(int codigo){
        if(existe(codigo)){
            Nodo ptr = inicio;
            Nodo qtr = ptr;
            while(ptr.getCodigo() != codigo){
                qtr = ptr;
                ptr = ptr.getSiguiente();
            }
            if(ptr == inicio){
                inicio = inicio.getSiguiente();
            }else
                qtr.setSiguiente(ptr.getSiguiente());
            ptr = null;
            return true;
        }
        return false;
    }

    public String muestraLista(){
        String cadena="";
        Nodo ptr = inicio;
        while(ptr != null){
            cadena += ("Codigo: "+ ptr.getCodigo() + "\nNombre: "+ ptr.getNombre() +"\n" );
            ptr = ptr.getSiguiente();
        }
        return cadena;
    }

    public boolean liberarLista(){
        Nodo ptr = inicio;
        while(ptr != null){
            Nodo aux = ptr;
            ptr = ptr.getSiguiente();
            aux = null;
        }
        return true;
    }
}   

