class Problem {
    public static void main(String[] args) {
        String mode = "default";
        for (int i = 0; i < args.length - 1; i++) {
            if ("mode".equals(args[i])) {
                mode = args[i + 1];
                break;
            }
        }
        System.out.println(mode);
    }
}