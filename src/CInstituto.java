import Resources.Lista;
import Resources.Nodo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CInstituto extends JFrame {

    private Lista listaAlumnos;
    private DefaultListModel<String> modeloLista;
    private JList<String> jListAlumnos;
    private JTextField codigoTexto;
    private JTextField nombreTexto;
    private JTextField posicionTexto;

    public CInstituto() {
        // Inicializa la lista de alumnos y el modelo de la lista
        listaAlumnos = new Lista();
        modeloLista = new DefaultListModel<>();

        // Configurar UI
        setTitle("Gestión de Instituto");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Configurar componentes de UI
        codigoTexto = new JTextField(15);
        nombreTexto = new JTextField(15);
        posicionTexto = new JTextField(15);
        jListAlumnos = new JList<>(modeloLista);
        jListAlumnos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Panel para entradas
        JPanel panelEntradas = new JPanel();
        panelEntradas.add(new JLabel("Código:"));
        panelEntradas.add(codigoTexto);
        panelEntradas.add(new JLabel("Nombre:"));
        panelEntradas.add(nombreTexto);
        panelEntradas.add(new JLabel("Posición:"));
        panelEntradas.add(posicionTexto);
        

        // Panel para botones
        JPanel panelBotones = new JPanel();
        JButton agregaInicioBoton = new JButton("Agregar al Inicio");
        JButton agregaFinalBoton = new JButton("Agregar al Final");
        JButton insertaBoton = new JButton("Insertar Nodo");
        JButton eliminaBoton = new JButton("Eliminar Nodo");
        // JButton muestraBoton = new JButton("Mostrar Lista");
        JButton liberaBoton = new JButton("Liberar Lista");
         JButton buscarCodigoBtn = new JButton("Buscar por Código");
        JButton buscarNombreBtn = new JButton("Buscar por Nombre");
        JButton modificarBtn = new JButton("Modificar");
        JButton eliminarPosBtn = new JButton("Eliminar por Posición");

        panelBotones.add(agregaInicioBoton);
        panelBotones.add(agregaFinalBoton);
        panelBotones.add(insertaBoton);
        panelBotones.add(eliminaBoton);
        //panelBotones.add(muestraBoton);
        panelBotones.add(liberaBoton);
         panelBotones.add(buscarCodigoBtn);
        panelBotones.add(buscarNombreBtn);
        panelBotones.add(modificarBtn);
        panelBotones.add(eliminarPosBtn);
        //panelBotones.add(muestraBoton);
        // panelBotones.add(liberaBoton);
        JTextField codigoField = new JTextField();
        JTextField nombreField = new JTextField();
        JTextField posicionField = new JTextField();
        JTextArea outputArea = new JTextArea();

    

        // Agregar paneles al JFrame
        add(panelEntradas, BorderLayout.NORTH);
        add(new JScrollPane(jListAlumnos), BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        // Configurar escuchas para botones
        agregaInicioBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregaInicioBotonAlClic();
            }
        });
        
            buscarCodigoBtn.addActionListener(e -> {
    int codigo = Integer.parseInt(codigoField.getText());
    String resultado = listaAlumnos.buscarPorCodigo(codigo);
    outputArea.setText(resultado);
});

// Listener for "Buscar por Nombre" button
buscarNombreBtn.addActionListener(e -> {
    String nombre = nombreField.getText();
    int codigo = listaAlumnos.buscarPorNombre(nombre);
    if(codigo != -1) {
        outputArea.setText("Código encontrado: " + codigo);
    } else {
        outputArea.setText("Nombre no encontrado en la lista.");
    }
});

// Listener for "Modificar" button
modificarBtn.addActionListener(e -> {
    int codigo = Integer.parseInt(codigoField.getText());
    String nuevoNombre = nombreField.getText();
    listaAlumnos.modificar(codigo, nuevoNombre);
    outputArea.setText("Nodo modificado.");
    // Refresh the list display if needed
});

// Listener for "Eliminar por Posición" button
eliminarPosBtn.addActionListener(e -> {
    int posicion = Integer.parseInt(posicionField.getText());
    boolean resultado = listaAlumnos.eliminarPorPosicion(posicion);
    if(resultado) {
        outputArea.setText("Nodo eliminado en la posición " + posicion);
    } else {
        outputArea.setText("Posición inválida o fuera de rango.");
    }
    // Refresh the list display if needed
});
        
        agregaFinalBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregaFinalBotonAlClic();
            }
        });
        
         insertaBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertarNodoBotonClic();
            }
        });
        
                 
         liberaBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearList();
            }
        });
         
           eliminaBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteNode();
            }
        });
        
             
        
        buscarCodigoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteNode();
            }
        });
        
        buscarNombreBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // buscarPorNombre();
            }
        });
        
                
        modificarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //modificar();
            }
        });
        
           
           
        
        // ... (Repite para los demás botones y sus correspondientes acciones)

        setVisible(true);
    }

      private void deleteNode() {
        try {
            int codigo = Integer.parseInt(codigoTexto.getText()); // Get the code from the text field
            boolean deleted = listaAlumnos.eliminarNodo(codigo); // Try to delete the node
            if (deleted) {
                actualizarListaUI(); // Update the UI if the node was deleted
                JOptionPane.showMessageDialog(this, "Node deleted successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Node with the code " + codigo + " not found.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid code number.");
        }
        codigoTexto.setText(""); // Clear the text field after deletion attempt
    }
    
    private void agregaInicioBotonAlClic() {
        try {
            int codigo = Integer.parseInt(codigoTexto.getText());
            String nombre = nombreTexto.getText();
            if (!listaAlumnos.existe(codigo)) {
                listaAlumnos.agregaInicio(codigo, nombre);
                modeloLista.add(0, nombre + " (Código: " + codigo + ")");
            } else {
                JOptionPane.showMessageDialog(this, "Un nodo con ese código ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El código debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void agregaFinalBotonAlClic() {
    try {
        int codigo = Integer.parseInt(codigoTexto.getText());
        String nombre = nombreTexto.getText();
        if (!listaAlumnos.existe(codigo)) {
            listaAlumnos.agregarFinal(codigo, nombre);
            modeloLista.addElement(nombre + " (Código: " + codigo + ")");
        } else {
            JOptionPane.showMessageDialog(this, "Un nodo con ese código ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "El código debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
    private void actualizarListaUI() {
    modeloLista.clear();
    Nodo actual = listaAlumnos.getInicio();
    while (actual != null) {
        modeloLista.addElement(actual.getNombre() + " (Código: " + actual.getCodigo() + ")");
        actual = actual.getSiguiente();
    }
}

    
    
    private void insertarNodoBotonClic() {
    try {
        int codigo = Integer.parseInt(codigoTexto.getText());
        String nombre = nombreTexto.getText();
        int posicion = Integer.parseInt(posicionTexto.getText()); // assuming you have a text field for position
        if (posicion < 1 || posicion > listaAlumnos.longitud() + 1) {
            JOptionPane.showMessageDialog(this, "Posición inválida.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        boolean insertado = listaAlumnos.insertarNodo(codigo, nombre, posicion);
        if (insertado) {
            // Actualizar la interfaz de usuario si es necesario, por ejemplo, actualizando un JList.
            actualizarListaUI(); // Deberás implementar este método
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo insertar el nodo. Puede que el código ya exista.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "El código y la posición deben ser números enteros.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

     private void clearList() {
        listaAlumnos.liberarLista(); // Call the method to clear the list
        actualizarListaUI(); // Call the method to update the UI (clear the display)
    }
     
    /* private void buscarPorNombre(String nombre) {
    // Assuming 'listaAlumnos' has a method 'buscarPorNombre' that returns a list of Nodos with the given name
    List<Nodo> nodosEncontrados = listaAlumnos.buscarPorNombre(nombre);
    if (!nodosEncontrados.isEmpty()) {
        StringBuilder sb = new StringBuilder("Nodos encontrados:\n");
        for (Nodo nodo : nodosEncontrados) {
            sb.append(nodo.toString()).append("\n");
        }
        outputArea.setText(sb.toString());
    } else {
        outputArea.setText("No se encontraron nodos con el nombre: " + nombre);
    }
}

private void modificar(int codigo, String nuevoNombre) {
    // Assuming 'listaAlumnos' has a method 'modificarNodo' to update the name of a Nodo by its code
    boolean modificado = listaAlumnos.modificarNodo(codigo, nuevoNombre);
    if (modificado) {
        outputArea.setText("Nodo con código " + codigo + " ha sido modificado.");
        actualizarListaUI(); // Refresh the list to show updated data
    } else {
        outputArea.setText("Nodo con código " + codigo + " no encontrado.");
    }
}

private void eliminarPorPosicion(int posicion) {
    // Assuming 'listaAlumnos' has a method 'eliminarPorPosicion' to delete a Nodo by its position in the list
    boolean eliminado = listaAlumnos.eliminarPorPosicion(posicion);
    if (eliminado) {
        outputArea.setText("Nodo en posición " + posicion + " ha sido eliminado.");
        actualizarListaUI(); // Refresh the list to show the remaining data
    } else {
        outputArea.setText("Posición inválida o fuera de rango: " + posicion);
    }
}*/

// Refresh the JList with the current data
/*private void actualizarListaUI() {
    modeloLista.clear();
    Nodo current = listaAlumnos.getHead(); // Assuming 'listaAlumnos' has a 'getHead' method for its head node
    while (current != null) {
        modeloLista.addElement(current.toString()); // Assuming Nodo has a 'toString' method for its representation
        current = current.getNext(); // Assuming Nodo has a 'getNext' method to traverse the list
    }
}
*/

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CInstituto();
            }
        });
    }
}
