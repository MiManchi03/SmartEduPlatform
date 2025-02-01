public class Student {
    private String id;
    private String name;
    private String age;
    private String home;

    public Student() {
    }

    public Student(String id, String name, String age, String home) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.home = home;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

}
