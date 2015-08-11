import java.util.Observable;
public class ObservableValue extends Observable
{
   private int n = 0;
   private String message = "";
   public ObservableValue(int n)
   {
      this.n = n;
   }
   public void setValue(int n)
   {
      this.n = n;
      setChanged();
      //notifyObservers();
      //notifyObservers("Special Message from Observable\n");
      notifyObservers(message+"\n");
   }
   public int getValue()
   {
      return n;
   }
   public void setMessage(String s)
   {
      this.message = s;
   }
   public String getMessage()
   {
      return message;
   }

}