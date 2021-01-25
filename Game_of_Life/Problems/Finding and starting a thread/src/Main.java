class ThreadProcessor {
    public static void findAndStartThread(Thread... threads) throws InterruptedException {
        for (Thread t : threads) {
            if (t.getState() == Thread.State.NEW) {
                t.start();
                t.join();
                break;
            }
        }
    }
}