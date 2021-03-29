public class Dog
{ String name;
  public void jump()
  {
      run();
      System.out.println(name+"正在执行jump方法");
  }
  public void run()
  {

      System.out.println(name+"正在执行run方法");
  }
  public void Mingming(String n)
  {
      this.name = n;
      System.out.println(this.name);
  }
}
