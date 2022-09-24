package binTree;
/**
 * Реализовывали бинарное дерево
 * скобочная запись
 * root(A(C(G,H),D(I,J)),B(E(K,L),F(M,N)))
 */
public class binTree {
    public static void main(String[] args) {

        //создаем экземпляры класса Node
        Node root = new Node("root");
        Node a = new Node("A");
        Node b = new Node("B");
        Node c = new Node("C");
        Node d = new Node("D");
        Node e = new Node("E");
        Node f = new Node("F");
        Node g = new Node("G");
        Node h = new Node("H");
        Node i = new Node("I");
        Node j = new Node("J");
        Node k = new Node("K");
        Node l = new Node("L");
        Node m = new Node("M");
        Node n = new Node("N");


        //строим структуру дерева
        root.left = a;
        root.right = b;
        a.left = c; 
        a.right = d;
        b.left = e;
        b.right = f;
        c.left = g;
        c.right = h;
        d.left = i;
        d.right = j;
        e.left = k;
        e.right = l;
        f.left = m;
        f.right = n;

        System.out.print("Прямой обход в глубину: ");
        Iterr.recPreOrder(root);
        System.out.println();

        System.out.print("Центрированный обход в глубину: ");
        Iterr.recInOrder(root);
        System.out.println();

        System.out.print("Обратный обход в глубину: ");
        Iterr.recPostOrder(root);
        System.out.println();

        // root(A(C(G,H),D(I,J)),B(E(K,L),F(M,N)))
        System.out.print("Скобочная запись дерева: ");
        StringBuilder tree = new StringBuilder();
        Iterr.View(root, "", tree);
        Iterr.setBrackets(tree);
        System.out.println(tree);
    }
    
}

class Node {
    /**
     * Создает экземпляр класса Node
     * @param v - имя узла дерева
     */
    public Node(String v) {
        value = v;
    }

    String value;
    Node left;
    Node right; 
}


class Iterr {
    public static int i = 0;
    /**
     * Скобочный вывод бинарного дерева
     */
    static void View(Node n, String space, StringBuilder sb) {
        if (n != null) {
            sb.append(String.format("%s%s", space, n.value));

            if (n.left != null) {
                View(n.left, "(", sb);
            }
            if (n.right != null) {
                i++;
                if (i == 1) {
                    View(n.right, ",", sb);
                }
                if (i == 2) { 
                    i = 0;
                    View(n.right, "),", sb);
                }
            }
        }
    }

    /**
     * Добавляет недостающие скобочки в конец дерева
     * @param n
     */
    static void setBrackets(StringBuilder sb) {
        int i = 0, j = 0;
        char str[] = sb.toString().toCharArray();
        for (char c : str) {
            if (c == '(') i++;
            if (c == ')') j++;
        }
        for (int k = 0; k < i - j; k++) {
            sb.append(")");
        }
    }

    /**
     * Прямой обход дерева в глубину
     * @param n - корень
     */
    static void recPreOrder(Node n) {
        if (n != null) { //если текущий экземпляр существует
            System.out.printf(" %s", n.value);
            if (n.left != null) recPreOrder(n.left); //если в дереве есть дочерний экз. слева уходим в рекурсию
            if (n.right != null) recPreOrder(n.right);
        }
    }
    
    /**
     * Центрированный обход дерева в глубину
     * @param n - корень
     */
    static void recInOrder(Node n) {
        if (n != null) {
            if (n.left != null) recInOrder(n.left);
            System.out.printf(" %s", n.value);
            if (n.right != null) recInOrder(n.right);
        }
    }

    /**
     * Обратный обход дерева в глубину
     * @param n - корень
    */
    static void recPostOrder(Node n) {
        if (n != null) {
            if (n.left != null) recInOrder(n.left);
            if (n.right != null) recInOrder(n.right);
            System.out.printf(" %s", n.value);
        }
    }
}

