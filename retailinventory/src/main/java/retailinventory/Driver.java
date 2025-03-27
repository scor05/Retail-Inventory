package retailinventory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        ArrayList<Producto> productos = new ArrayList<>();
        try {
            productos = readCSV("inventario_ropa_deportiva_30.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        BinarySearchTree<Integer, Producto> bstSKU = new BinarySearchTree<>();
        BinarySearchTree<String, Producto> bstName = new BinarySearchTree<>();
        for (Producto p : productos) {
            bstSKU.insert(p.getSKU(), p);
            bstName.insert(p.getName(), p);
        }
        boolean loop = true;
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println("\033[H\033[2J");
            System.out.println("-".repeat(50));
            System.out.println("\t\tInventario de tienda de Retail");
            System.out.println("-".repeat(50));

            System.out.println("Las funciones disponibles son: \n");
            System.out.println("1. Agregar un producto");
            System.out.println("2. Editar un producto");
            System.out.println("3. Listar inventario");
            System.out.println("4. Buscar un producto (por SKU o nombre)");
            System.out.println("5. Eliminar un producto");
            System.out.println("6. Salir");

            System.out.print("Ingrese la opción deseada: \nR/ ");
            int op = sc.nextInt();
            sc.nextLine();
            switch(op){
                case 1:
                    System.out.println("Ingrese el SKU del producto a agregar:  ");
                    int SKU = sc.nextInt();
                    System.out.println("Ingrese el nombre del producto a agregar:  ");
                    String name = sc.nextLine();
                    System.out.println("Ingrese la descripción del producto a agregar:  ");
                    String desc = sc.nextLine();
                    ArrayList<String> tallasArr = new ArrayList<>();
                    ArrayList<Integer> cantidades = new ArrayList<>();
                    HashMap<String, Integer> tallas = new HashMap<>();
                    System.out.println("Ingrese las tallas disponibles del producto (Ingrese un espacio para terminar):   ");
                    while (true){
                        if (!sc.nextLine().equals(" ")){
                            tallasArr.add(sc.nextLine());
                        }else{
                            break;
                        }
                    }
                    System.out.println("Ingrese las cantidades de producto disponibles para cada talla (en el mismo órden que ingresó las tallas) (Ingrese un espacio para terminar):   ");
                    while (true){
                        if (!sc.nextLine().equals(" ")){
                            cantidades.add(Integer.parseInt(sc.nextLine()));
                        }else{
                            break;
                        }
                    }
                    for (int i = 0; i < tallasArr.size(); i++) {
                        tallas.put(tallasArr.get(i), cantidades.get(i));
                    }
                    Producto producto = new Producto(SKU, name, desc, tallas);
                    bstSKU.insert(SKU, producto);
                    bstName.insert(name, producto);
                    productos.add(producto);
                    break;
                case 2:
                    System.out.println("Ingrese el SKU o el nombre del producto a editar: \nR/");
                    String key = sc.nextLine();
                    Producto editar = null;
                    try{
                        Integer aux = Integer.parseInt(key.trim());
                        editar = bstSKU.search(aux);
                    }catch(NumberFormatException e){
                        key = key.trim();
                        editar = bstName.search(key);
                    }

                    if (editar == null){
                        System.out.println("El producto no existe en el inventario.");
                        break;
                    }

                    System.out.println("Los campos disponibles para editar son: \n");
                    System.out.println("1. Descripción");
                    System.out.println("2. Tallas (o cantidad por talla)");

                    System.out.println("Ingrese el número correspondiente al campo que desea editar:  ");
                    int campo = sc.nextInt();
                    sc.nextLine();
                    switch (campo){
                        case 1:
                            System.out.println("Ingrese la nueva descripción del producto:  ");
                            String newDesc = sc.nextLine();
                            editar.setDesc(newDesc);
                            break;
                        case 2:
                            System.out.println("Tallas actuales del producto: " + editar.getTallas());
        
                            System.out.println("¿Qué desea hacer con las tallas?");
                            System.out.println("1. Agregar nueva talla");
                            System.out.println("2. Modificar cantidad de una talla existente");
                            System.out.println("3. Eliminar una talla");
                            
                            System.out.print("Ingrese su opción:  ");
                            int opcionTallas = sc.nextInt();
                            sc.nextLine();
                            
                            switch (opcionTallas) {
                                case 1:
                                    System.out.println("Ingrese la nueva talla:  ");
                                    String nuevaTalla = sc.nextLine();
                                    System.out.println("Ingrese la cantidad para esta talla:  ");
                                    int cantidadNuevaTalla = sc.nextInt();
                                    
                                    if (editar.getTallas().containsKey(nuevaTalla)) {
                                        System.out.println("La talla ya existe. Use la opción de modificar cantidad.");
                                    } else {
                                        editar.getTallas().put(nuevaTalla, cantidadNuevaTalla);
                                        System.out.println("Talla agregada exitosamente.");
                                    }
                                    break;
                                
                                case 2:
                                    System.out.println("Ingrese la talla a modificar:  ");
                                    String tallaModificar = sc.nextLine();
                                    
                                    if (!editar.getTallas().containsKey(tallaModificar)) {
                                        System.out.println("La talla no existe en el producto.");
                                    } else {
                                        System.out.println("Talla actual: " + tallaModificar + ", Cantidad actual: " + editar.getTallas().get(tallaModificar));
                                        System.out.println("Ingrese la nueva cantidad:  ");
                                        int nuevaCantidad = sc.nextInt();
                                        
                                        editar.getTallas().put(tallaModificar, nuevaCantidad);
                                        System.out.println("Cantidad actualizada exitosamente.");
                                    }
                                    break;
                                
                                case 3:
                                    System.out.println("Ingrese la talla a eliminar:  ");
                                    String tallaEliminar = sc.nextLine();
                                    
                                    if (!editar.getTallas().containsKey(tallaEliminar)) {
                                        System.out.println("La talla no existe en el producto.");
                                    } else {
                                        editar.getTallas().remove(tallaEliminar);
                                        System.out.println("Talla eliminada exitosamente.");
                                    }
                                    break;
                                
                                default:
                                    System.out.println("Opción no válida.");
                                    break;
                            }
                            break;
                    }
                    break;
                case 3:
                    System.out.println("Los productos del inventario son: \n");
                    for (Producto p : productos){
                        System.out.println("SKU: " + p.getSKU() + "\n\t Nombre: " + p.getName() + "\n\tDescripción: " + p.getDesc() + "\n\tTallas: " + p.getTallas());
                    }
                case 4:
                    System.out.println("Ingrese el SKU o el nombre del producto a buscar:  ");
                    Producto buscado = null;
                    String k = sc.nextLine();
                    try{
                        Integer aux = Integer.parseInt(k.trim());
                        buscado = bstSKU.search(aux);
                    }catch(NumberFormatException e){
                        k = k.trim();
                        buscado = bstName.search(k);
                    }

                    if (buscado == null){
                        System.out.println("No se ha encontrado el producto en el inventario.");
                        break;
                    }
                    System.out.println("El producto buscado es: \nSKU: " + buscado.getSKU() + "\n\tNombre: " + buscado.getName() + "\n\tDescripción: " + buscado.getDesc() + "\n\tTallas: " + buscado.getTallas());
                    break;
                case 5:
                    System.out.println("Ingrese el SKU o el nombre del producto a eliminar:  ");
                    Producto eliminar = null;
                    key = sc.nextLine();
                    try{
                        Integer aux = Integer.parseInt(key.trim());
                        eliminar = bstSKU.search(aux);
                    }catch(NumberFormatException e){
                        key = key.trim();
                        eliminar = bstName.search(key);
                    }
                    if (eliminar == null){
                        System.out.println("El producto no existe en el inventario.");
                        break;
                    }
                    bstSKU.delete(eliminar.getSKU());
                    bstName.delete(eliminar.getName());
                    productos.remove(eliminar);
                    break;
                case 6:
                    loop = false;
                    System.out.println("Gracias por usar mi inventario :D");
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
            System.out.print("Ingrese cualquier tecla para continuar...");
            sc.nextLine();
        }while (loop);
    }

    public static ArrayList<Producto> readCSV(String fileName) throws IOException {
        ArrayList<Producto> productos = new ArrayList<>();
        @SuppressWarnings("resource")
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = br.readLine()) != null) {
            String[] datos = line.split(",");
            for (int i = 0; i < datos.length; i++) {
                datos[i] = datos[i].trim();
            }
            
            if (!datos[0].equals("SKU")) {
                int SKU = Integer.parseInt(datos[0]);
                String name = datos[1];
                String desc = datos[2];
                String tallasString = datos[3];
    
                HashMap<String, Integer> tallas = new HashMap<>();
                String[] tallasSeparadas = tallasString.split("\\|");
                
                for (String tallaInfo : tallasSeparadas) {
                    String[] partes = tallaInfo.split(":");
                    String talla = partes[0].trim();
                    int cantidad = Integer.parseInt(partes[1].trim());
                    tallas.put(talla, cantidad);
                }

                productos.add(new Producto(SKU, name, desc, tallas));
            }
        }
        return productos;
    }
}
