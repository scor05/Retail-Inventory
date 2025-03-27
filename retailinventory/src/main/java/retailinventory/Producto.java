package retailinventory;
/**
 * Clase que tiene los datos de cada producto
 */

import java.util.HashMap;

public class Producto implements Comparable<Producto>{
    private int SKU;
    private String name;
    private String desc;
    private HashMap<String, Integer> tallas;

    public Producto(int SKU, String name, String desc, HashMap<String, Integer> tallas) {
        this.SKU = SKU;
        this.name = name;
        this.desc = desc;
        this.tallas = tallas;
    }

    public int getSKU() {
        return SKU;
    }

    public void setSKU(int SKU) {
        this.SKU = SKU;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public HashMap<String, Integer> getTallas() {
        return tallas;
    }

    public void setTallas(HashMap<String, Integer> tallas) {
        this.tallas = tallas;
    }

    @Override
    public int compareTo(Producto producto) {
        return (this.SKU > producto.getSKU()) ? 1 : ((this.SKU < producto.getSKU()) ? -1 : 0);
    }
}


