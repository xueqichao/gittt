class Point
{ int x;
  int y;
 public Point(){}
 public Point(int x,int y)
   {this.x = x;
    this.y = y;
   }
 public void move_point(int x,int y)
   {this.x = x;
    this.y = y;
   }
}
public class Test3
{public static void main(String[] args)
  {Point p1 = new Point(1,2);
   Point p2 = new Point(3,4);
   System.out.println("p1的坐标是："+p1.x+","+p1.y);
   System.out.println("p2的坐标是："+p2.x+","+p2.y);
   p1.move_point(5,6);
   p2.move_point(7,8);
   System.out.println("移动后p1的坐标是："+p1.x+","+p1.y);
   System.out.println("移动后p2的坐标是："+p2.x+","+p2.y);
  }
}
