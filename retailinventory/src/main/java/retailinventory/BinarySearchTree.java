package retailinventory;

public class BinarySearchTree<K extends Comparable<K>, V> {
    private Node<K, V> root;

    public BinarySearchTree() {
        this.root = null;
    }

    public void insert(K key, V value){
        if (this.root == null){
            this.root = new Node<K, V>(key, value);
            return;
        }
        insert(key, value, this.root);
    }

    public void insert(K key, V value, Node<K, V> node){
        if (node.getKey().compareTo(key) == 0) {
            throw new RuntimeException("La clave ingresada ya está en el árbol.");
        }
    
        if (node.getKey().compareTo(key) > 0) {
            if (node.getRight() != null){
                insert(key, value, node.getRight());
            } else {
                node.setRight(new Node<K, V>(key, value));
            }
        }else{
            if (node.getLeft() != null){
                insert(key, value, node.getLeft());
            } else {
                node.setLeft(new Node<K, V>(key, value));
            }
        }
    }

    public V search(K key) {
        if (this.root == null){
            return null;
        }
        return search(key, this.root);
    }

    public V search(K key, Node<K, V> node){
        if (node.getKey().compareTo(key) == 0){
            return node.getValue();
        }
        if (node.getKey().compareTo(key) < 0){
            if (node.getLeft() == null){
                return null;
            }
            return search(key, node.getLeft());
        }
        if (node.getKey().compareTo(key) > 0){
            if (node.getRight() == null){
                return null;
            }
            return search(key, node.getRight());
        }
        return null;
    }

    public void delete(K key) {
        if (this.root == null){
            return;
        }
        delete(key, this.root);
    }

    // Implementación del más izquierdo de la derecha
    public void delete(K key, Node<K, V> node){
        if (node.getKey().compareTo(key) < 0){
            if (node.getLeft() != null){
                delete(key, node.getLeft());
            }
        }else if (node.getKey().compareTo(key) > 0){
            if (node.getRight() != null){
                delete(key, node.getRight());
            }
        }else{
            if (node.getLeft() == null && node.getRight() == null){ // Hoja
                node = null;
            // Es un camino
            }else if (node.getLeft() == null){
                node = node.getRight();
            }else if (node.getRight() == null){
                node = node.getLeft();
            }else{ // Es un subárbol
                Node<K, V> current = node.getRight();
                while (current.getLeft() != null){
                    current = current.getLeft();
                }
                Node<K, V> aux = current;
                current.setValue(node.getValue());
                current.setKey(node.getKey());
                node.setValue(aux.getValue());
                node.setKey(aux.getKey());
                current = null;
            }
        }
    }

    public void setRoot(K key, V value) {
        this.root = new Node<K, V>(key, value);
    }
}