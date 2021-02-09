class Employee {

    String name;
    int salary;
    String address;
    private final String unknown = "unknown";

    Employee() {
        name = unknown;
        salary = 0;
        address = unknown;
    }

    Employee(String name, int salary) {
        this.name = name;
        this.salary = salary;
        address = unknown;
    }

    Employee(String name, int salary, String address) {
        this.name = name;
        this.salary = salary;
        this.address = address;
    }
}