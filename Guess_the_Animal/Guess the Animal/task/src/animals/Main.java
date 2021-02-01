package animals;


public class Main {
    public static void main(String[] args){
        String mapperType = "json";
        if (args.length >= 2 && "-type".equals(args[0])) {
            mapperType = args[1];
        }
        new GuessTheAnimal(mapperType).run();
    }
}
