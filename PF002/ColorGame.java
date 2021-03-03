import java.util.*;
public class ColorGame {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter command");
        while(true) {
            String command = sc.nextLine();
            command = command.trim();
        }
    }

    public static class ObjectFactory {
        public Object getObject(String name) {
            SubscribeCommand subscribe = new SubscribeCommand();
            UnsubscribeCommand unsubscribe = new UnsubscribeCommand();
            switch(name) {
                case "+frog":
                    subscribe.execute(new Frog());
                    break;
                case "+banana":
                    subscribe.execute(new Banana());
                    break;
                case "+salt":
                    subscribe.execute(new Salt());
                    break;
                case "+apple":
                    subscribe.execute(new Apple());
                    break;
                case "+blood":
                    subscribe.execute(new Blood());
                    break;
                case "+grass":
                    subscribe.execute(new Grass());
                    break;
                case "-frog":
                    unsubscribe.execute(new Frog());
                    break;
                case "-banana":
                    unsubscribe.execute(new Banana());
                    break;
                case "-salt":
                    unsubscribe.execute(new Salt());
                    break;
                case "-apple":
                    unsubscribe.execute(new Apple());
                    break;
                case "-blood":
                    unsubscribe.execute(new Blood());
                    break;
                case "-grass":
                    unsubscribe.execute(new Grass());
                    break;
                default:
                    throw new RuntimeException("Invalid Object");
            }
        }
    }

    public interface SubscribeUnsubscribe {
        public void subscribe(String s);
        public void unsubscribe(String s);
    }

    public class Object implements SubscribeUnsubscribe {
        @override
        public void subscribe(Objects s) {
            objects.add(s);
        }

        @override
        public void unsubscribe(Objects s) {
            objects.remove(s);
        }
    }

    public interface Command {
        public void execute(SubscribeUnsubscribe command);
    }

    public class SubscribeCommand implements Command {
        @override
        public void execute(SubscribeUnsubscribe command) {
            command.subscribe();
        }
    }

    public class UnsubscribeCommand implements Command {
        @override
        public void execute(SubscribeUnsubscribe command) {
            command.unsubscribe();
        }
    }

    private Set<Objects> objects = new HashSet<>();

    public abstract class Objects {
        public String getColor1();
        public String getColor2(); 
    }

    public class Banana extends Objects {
        @override
        public String getColor1() {
            return "yellow";
        }
    }

    public class Salt extends Objects {
        @override
        public String getColor1() {
            return "white";
        }
    }

    public class Apple extends Objects {
        @override
        public String getColor1() {
            return "red";
        }
    }

    public class Blood extends Objects {
        @override
        public String getColor1() {
            return "red";
        }
    }

    public class Frog extends Objects {
        @override
        public String getColor1() {
            return "green";
        }

        @override
        public String getColor2() {
            return "yellow";
        }
    }

    public class Grass extends Objects {
        @override
        public String getColor1() {
            return "green";
        }
    }
}