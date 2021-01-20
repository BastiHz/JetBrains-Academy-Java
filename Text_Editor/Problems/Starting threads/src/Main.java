public class Main {

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            Thread t = new Thread(new RunnableWorker(), "worker-" + i);
            t.start();
        }
    }
}

// Don't change the code below       
class RunnableWorker implements Runnable {

    @Override
    public void run() {
        final String name = Thread.currentThread().getName();

        if (name.startsWith("worker-")) {
            System.out.println("too hard calculations...");
        }
    }
}