
public class Test1 //二维数组的遍历
{
    public static void main(String[] args)
    {
        int[][] a;
        a = new int[][]{{5,6,7,8,9},{6,6,6},{8,8,8}};
        for(int[] b : a)                //for(元素类型+形参+":"+该元素所在的数组名)
        {   for(int c : b)
            System.out.print(c+" ");
            System.out.println();
        }
    }
}
