import java.util.Arrays;

public class ArrayCopyTest {

    public static void main(String[] args) {

        int a[] = {1, 2, 3};
        int b[] = new int[a.length];
        System.arraycopy(a, 0, b, 0, a.length);
        b[0] = 9;
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));
        // now trying with object references
        Object[] obj1 = {new Integer(3), new StringBuffer("hello")};
        Object[] obj2 = new Object[obj1.length];
        System.arraycopy(obj1, 0, obj2, 0, obj1.length);
        obj1[1] = new StringBuffer("world");
        System.out.println(Arrays.toString(obj1));
        System.out.println(Arrays.toString(obj2));

    }
}
