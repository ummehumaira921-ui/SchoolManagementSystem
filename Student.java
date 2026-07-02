package SchoolManagementSystem;

import java.io.Serializable;



public class Student implements Serializable {

    private static final long serialVersionUID = 2L;

    private String id;
    private String firstName;
    private String surname;
    private String address;
    private String gender;
    private String mobile;

    private String maths;
    private String science;
    private String physics;
    private String chemistry;
    private String biology;
    private String bangla;
    private String business;
    private String english;

    public Student(String id, String firstName, String surname, String address,
                   String gender, String mobile,
                   String maths, String science, String physics, String chemistry,
                   String biology, String bangla, String business, String english) {

        this.id = id;
        this.firstName = firstName;
        this.surname = surname;
        this.address = address;
        this.gender = gender;
        this.mobile = mobile;

        this.maths = maths;
        this.science = science;
        this.physics = physics;
        this.chemistry = chemistry;
        this.biology = biology;
        this.bangla = bangla;
        this.business = business;
        this.english = english;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public String getAddress() {
        return address;
    }

    public String getGender() {
        return gender;
    }

    public String getMobile() {
        return mobile;
    }

    public String getMaths() {
        return maths;
    }

    public String getScience() {
        return science;
    }

    public String getPhysics() {
        return physics;
    }

    public String getChemistry() {
        return chemistry;
    }

    public String getBiology() {
        return biology;
    }

    public String getBangla() {
        return bangla;
    }

    public String getBusiness() {
        return business;
    }

    public String getEnglish() {
        return english;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setMaths(String maths) {
        this.maths = maths;
    }

    public void setScience(String science) {
        this.science = science;
    }

    public void setPhysics(String physics) {
        this.physics = physics;
    }

    public void setChemistry(String chemistry) {
        this.chemistry = chemistry;
    }

    public void setBiology(String biology) {
        this.biology = biology;
    }

    public void setBangla(String bangla) {
        this.bangla = bangla;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getFullName() {
        return firstName + " " + surname;
    }

    @Override
    public String toString() {
        return id + " | " + getFullName();
    }
}