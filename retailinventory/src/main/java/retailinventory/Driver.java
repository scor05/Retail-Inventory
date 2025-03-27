package retailinventory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Driver {
    public static void main(String[] args) {
        ArrayList<Producto> productos = new ArrayList<>();
        try {
            productos = readCSV("inventario_ropa_deportiva_30.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
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
