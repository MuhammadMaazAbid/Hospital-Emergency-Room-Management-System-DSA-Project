package models;

public class Patient {
    private String id;
    private String name;
    private int age;
    private String bloodGroup;
    private String contact;

    public Patient(String id, String name, int age, String bloodGroup, String contact) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.bloodGroup = bloodGroup;
        this.contact = contact;
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getBloodGroup() { return bloodGroup; }
    public String getContact() { return contact; }

    // Setters
    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup; }
    public void setContact(String contact) { this.contact = contact; }
}