import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine().trim();
        reader.close();
        if (s.isEmpty()) {
            System.out.println("0");
        } else {
            System.out.println(s.split("\\s+").length);
        }
    }
}