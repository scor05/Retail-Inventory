package retailinventory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class RetailTest {

    
    @Test
    public void shouldInsertIntoTree() {
        BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<>();
        bst.insert(1, 1);
        bst.insert(2, 2);
        bst.insert(-1, 4);
        assertEquals(bst.search(1), 1);
        assertEquals(bst.search(-1), 4);
        assertEquals(bst.search(2), 2);
    }

    @Test
    public void shouldReturnDuplicateKeyError() {
        BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<>();
        bst.insert(1, 1);
        try{
            bst.insert(1, 2);
            bst.insert(1, 95);
        }catch(RuntimeException e){
            assertEquals(e.getMessage(), "La clave ingresada ya está en el árbol.");
            return;
        }
        assertTrue(false);
    }

    @Test
    public void shouldSearchInTree(){
        BinarySearchTree<Integer, Integer> bst = new BinarySearchTree<>();
        bst.setRoot(1, 1);
        assertEquals(bst.search(1), 1);
    }
}
