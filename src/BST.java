import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;



public class BST<T extends Comparable<? super T>> implements BSTInterface<T> {
    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private BSTNode<T> root;
    private int size;

    /**
     * A no argument constructor that should initialize an empty BST
     */
    public BST() {
        root = null;
        size = 0;

    }

    /**
     * Initializes the BST with the data in the Collection. The data in the BST
     * should be added in the same order it is in the Collection.
     *
     * @param data the data to add to the tree
     * @throws java.lang.IllegalArgumentException if data or any element
     * in data is null
     */
    public BST(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Collection is null");
        }
        for (T info: data) {
            add(info);
        }

    }
    /**
     *  Helps the add method
     *  @param node the current node
     *  @param data the data to be added
     *  @return the previous node
     */
    private BSTNode<T> addHelper(BSTNode<T> node, T data) {
        if (node == null) {
            size++;
            return new BSTNode<>(data);
        }
        if (node.getData().compareTo(data) < 0) {
            node.setRight(addHelper(node.getRight(), data));
        } else if (node.getData().compareTo(data) > 0) {
            node.setLeft(addHelper(node.getLeft(), data));
        }
        return node;
    }

    @Override
    public void add(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("Cannot insert "
                    + "null data into data structure");
        }

        root = addHelper(root, data);
    }
    /**
     *  Helps the remove method
     *  @param node the current node
     *  @param data the data to be removed
     *  @param aux a node that will return the data removed
     *  @return the previous node
     */
    private BSTNode<T> removeHelper(BSTNode<T> node, T data, BSTNode<T> aux) {
        if (node == null) {
            throw new java.util.NoSuchElementException("Data is not "
                    + "in the tree");
        }

        if (node.getData().compareTo(data) < 0) {
            node.setRight(removeHelper(node.getRight(), data, aux));
        } else if (node.getData().compareTo(data) > 0) {
            node.setLeft(removeHelper(node.getLeft(), data, aux));
        } else {
            aux.setData(node.getData());
            if (node.getRight() == null && node.getLeft() == null) {
                return null;
            } else if (node.getRight() == null) {
                return node.getLeft();
            } else if (node.getLeft() == null) {
                return node.getRight();
            } else {
                BSTNode<T> aux2 = new BSTNode<T>(null);
                BSTNode<T> pred = getPredHelper(node.getLeft(), aux2);
                node.setData(aux2.getData());
                node.setLeft(pred);
            }

        }
        return node;

    }
    /**
     *  Finds the predecessor
     *  @param node the current node
     *  @param aux2 the data of the predecessor when it is found
     *  @return the subtree without the predecessor
     */

    private BSTNode<T> getPredHelper(BSTNode<T> node, BSTNode<T> aux2) {
        if (node.getRight() != null) {
            node.setRight(getPredHelper(node.getRight(), aux2));
        } else {
            aux2.setData(node.getData());
            return node.getLeft();
        }
        return node;

    }

    @Override
    public T remove(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("Cannot remove null "
                    + "data into data structure");
        }

        BSTNode<T> aux = new BSTNode<T>(null);
        root = removeHelper(root, data, aux);
        size--;
        return aux.getData();






    }
    /**
     *  Helps the get method
     *  @param node the current node
     *  @param data the data you are trying to get
     *  @return the previous node
     */
    private BSTNode<T> getHelper(BSTNode<T> node, T data) {
        if (node == null) {
            throw new java.util.NoSuchElementException("Data is not in "
                    + "the tree");
        }

        if (node.getData().compareTo(data) < 0) {
            return (getHelper(node.getRight(), data));
        } else if (node.getData().compareTo(data) > 0) {
            return (getHelper(node.getLeft(), data));
        } else {
            return node;
        }
    }


    @Override
    public T get(T data) { //needs to be recursive
        if (data == null) {
            throw new java.lang.IllegalArgumentException("Data cannot "
                    + "be null");
        }
        if (root == null) {
            throw new java.util.NoSuchElementException("Data is not "
                    + "in the tree");
        }
        BSTNode<T> helped = getHelper(root, data);
        T gotData = helped.getData();
        return (gotData);


    }

    /**
     *  Helps the contains method
     *  @param node the current node
     *  @param data the data you are trying to get
     *  @return the previous node
     */
    private BSTNode<T> containsHelper(BSTNode<T> node, T data) {
        if (node == null) {
            return new BSTNode<T>(null);
        }
        if (node.getData().compareTo(data) < 0) {
            return (containsHelper(node.getRight(), data));
        } else if (node.getData().compareTo(data) > 0) {
            return (containsHelper(node.getLeft(), data));
        } else {
            return node;
        }
    }


    @Override
    public boolean contains(T data) { //needs to be recursive
        if (data == null) {
            throw new java.lang.IllegalArgumentException("Data cannot "
                    + "be null");
        }

        BSTNode<T> helped = containsHelper(root, data);
        if (helped.getData() == null) {
            return false;
        }
        return helped.getData().equals(data);


    }


    @Override
    public int size() {
        return size;

    }

    @Override
    public List<T> preorder() {
        List<T> newList = new ArrayList<T>();
        BSTNode<T> current = root;
        preorderHelp(current, newList);
        return newList;



    }
    /**
     *  Helps the preorder method
     *  @param node the current node
     *  @param list list that will be returned by the preorder function
     */

    private void preorderHelp(BSTNode<T> node, List<T> list) {
        if (node != null) {
            list.add(node.getData());
            preorderHelp(node.getLeft(), list);
            preorderHelp(node.getRight(), list);
        }



    }

    @Override
    public List<T> postorder() {
        List<T> newList = new ArrayList<T>();
        BSTNode<T> current = root;
        postorderHelp(current, newList);
        return newList;

    }
    /**
     *  Helps the postorder method
     *  @param node the current node
     *  @param list list that will be returned by the post function
     */

    private void postorderHelp(BSTNode<T> node, List<T> list) {
        if (node != null) {
            postorderHelp(node.getLeft(), list);
            postorderHelp(node.getRight(), list);
            list.add(node.getData());
        }



    }

    @Override
    public List<T> inorder() {
        List<T> newList = new ArrayList<T>();
        BSTNode<T> current = root;
        inorderHelp(current, newList);
        return newList;

    }
    /**
     *  Helps the inorder method
     *  @param node the current node
     *  @param list the list that will be returned by the inorder function
     */

    private void inorderHelp(BSTNode<T> node, List<T> list) {
        if (node != null) {
            inorderHelp(node.getLeft(), list);
            list.add(node.getData());
            inorderHelp(node.getRight(), list);

        }



    }

    @Override
    public List<T> levelorder() {
        List<T> list = new ArrayList<T>();
        Queue<BSTNode<T>> queue = new LinkedList<BSTNode<T>>();
        if (root == null) {
            return list; //??
        }
        BSTNode<T> current = root;
        queue.add(current);
        while (!queue.isEmpty()) {

            BSTNode<T> removed = queue.remove();
            if (removed != null) {
                queue.add(removed.getLeft());
                queue.add(removed.getRight());
                list.add(removed.getData());
            }

        }
        return list;


    }

    @Override
    public void clear() {
        root = null;
        size = 0;

    }
    /**
     *  Helps the height method
     *  @param node the current node
     *  @return the height as an int
     */

    private int height(BSTNode<T> node) {
        if (node == null) {
            return -1;
        } else {

            return (1 + Math.max(height(node.getLeft()),
                    height(node.getRight())));
        }
    }

    @Override
    public int height() {
        return (height(root));


    }

    /**
     * THIS METHOD IS ONLY FOR TESTING PURPOSES.
     * DO NOT USE IT IN YOUR CODE
     * DO NOT CHANGE THIS METHOD
     *
     * @return the root of the tree
     */
    public BSTNode<T> getRoot() {
        return root;
    }
}
