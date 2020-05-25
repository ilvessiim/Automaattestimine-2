package hello;

public class Greeter {
    public String sayHello(String name) {
        Counter counter = new Counter();
        return "Hi! Your name has " + counter.countLetters(name) + " letters in it.";
    }
}