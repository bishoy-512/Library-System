package System.Library;
abstract public class Person {
    private String name;
    private final int id;
    private String phone;
    private String password;
    public Person(String name, int id, String phone, String password) {
        this.name = name;
        this.id = id;
        this.phone = phone;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }
    
    public abstract void displayInfo();
}