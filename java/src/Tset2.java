import java.awt.*;
public class Tset2
{
    public static void main(String[] args) {
        Frame f = new Frame();
        Panel p = new Panel();
        p.add(new TextField("ţ"));
        p.add(new Button("����"));
        f.add(p);
        f.setBounds(100,100,500,300);
        f.setVisible(true);
    }
}
