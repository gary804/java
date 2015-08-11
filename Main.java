public class Main
{
   public ObservableValue ov = null;
   public Main()
   {
      //ObservableValue ov = new ObservableValue(0);
      ov = new ObservableValue(0);
      TextObserver to = new TextObserver(ov);
      TextObserver to1 = new TextObserver(ov);
      ov.addObserver(to);
      ov.addObserver(to1);
      //ov.setValue(10);
      //ov.setValue(100);
   }
   public static void main(String [] args)
   {
      Main m = new Main();
      m.ov.setValue(10);
      m.ov.setValue(100);
      m.ov.setMessage("Attention, here is new updating:");
      m.ov.setValue(1000);
   }
}