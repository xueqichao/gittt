import java.util.Scanner;

public class sanjiaoxing
{
  public static void main(String[] args)
  {   int a;
      System.out.print("请输入要打印的行数:");
      Scanner sc = new Scanner(System.in);
      a = sc.nextInt();
      for (int i = 1;i <= a;i ++)
      {
          for (int j = 0; j < a-i; j ++)
          {
              System.out.print(' ');
          }
          for (int k = 0; k < 2 * i - 1 ; k ++)
          {
              System.out.print('*');
          }
          System.out.print('\n');
      }
  }
}
