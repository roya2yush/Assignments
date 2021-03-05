//It isn't correct. Still working on it. Need to add command design pattern too.
//Updated code wil be present in my git, if i am able to do it.
//https://github.com/roya2yush/Assignments/tree/main/PF002

import java.util.*;
public class ColorGame {

    public interface SubscribeUnsubscribe {
        public void subscribe(Objects object);
    
        public void unsubscribe(Objects object);
    
        public Set<Objects> getList();
      }

    abstract class Objects implements SubscribeUnsubscribe{
        abstract String getColor();

        @Override
        public void subscribe(Objects object) {
            subscribed.add(object);
        }

        @Override
        public void unsubscribe(Objects object) {
            subscribed.remove(object);
        }

        @Override
        public Set<Objects> getList() {
            return subscribed;
        }
    }

    private static Set<Objects> subscribed = new HashSet<>();

    public class Blood extends Objects {
        
        public String getColor() {
            return "Red";
        }
    }

    public class Apple extends Objects {
        
        public String getColor() {
            return "Red";
        }
    }

    public class Salt extends Objects {
        
        public String getColor() {
            return "White";
        }
    }

    public class Grass extends Objects {
        
        public String getColor() {
            return "Green";
        }
    }

    public class Banana extends Objects {
        
        public String getColor() {
            return "Yellow";
        }
    }

    public class Frog extends Objects {
        
        public String getColor() {
            return "Yellow";
        }

        public String getColoe2() {
            return "Green";
        }
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a command");
        String command = sc.nextLine();
        command = command.trim();
        command = command.toLowerCase();

    }

    public static class CommandExecute {
        public void execute(String command) {
            switch(command) {
                case "+frog":
                    Frog f = new Frog();
                    f.subscribe(f);
                    break;
                case "+salt":
                    Salt s = new Salt();
                    s.subscribe(s);
                    break;
                case "+grass":
                    Grass g = new Grass();
                    g.subscribe(g);
                    break;
                case "+blood":
                    Blood b = new Blood();
                    b.subscribe(b);
                    break;
                case "+banana":
                    Banana b = new Banana();
                    b.subscribe(b);
                    break;
                case "+apple":
                    Apple a = new Apple();
                    a.subscribe(a);
                    break;
                case "-frog":
                    Frog f = new Frog();
                    f.unsubscribe(f);
                    break;
                case "-salt":
                    Salt s = new Salt();
                    s.unsubscribe(s);
                    break;
                case "-grass":
                    Grass g = new Grass();
                    g.unsubscribe(g);
                    break;
                case "-blood":
                    Blood b = new Blood();
                    b.unsubscribe(b);
                    break;
                case "-banana":
                    Banana b = new Banana();
                    b.unsubscribe(b);
                    break;
                case "-apple":
                    Apple a = new Apple();
                    a.unsubscribe(a);
                    break;
                default:
                    Color c = new Color();
                    c.getColor(command);
            }
        }
    }

    public static class Color {
        public static void getColor(String command) {
            for(Objects o : subscribed) {
                if(o.getColor.equalsIgnoreCase(command)) {
                    try {
                        if(o.getColoe2) {
                            System.out.println("Hi i am "+o.getClass()+". I am "+command+" today.")
                        }
                    } catch(Exception e) {
                        System.out.println("Hi i am "+o.getClass()+". I am sometimes "+command+".");
                    }
                }
            }
        }
    }

}