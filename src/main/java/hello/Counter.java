package hello;

public class Counter {
    public int countLetters(String name) {
        int count = 0;
        if(name.length() == 0){
            count = 404;
        }

        else {
            for (int i = 0; i < name.length(); i++) {
                if (name.charAt(i) != ' ')
                    count++;
            }
        }

        return count;
    }
}