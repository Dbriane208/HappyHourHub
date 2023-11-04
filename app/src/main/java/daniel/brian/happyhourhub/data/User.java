package daniel.brian.happyhourhub.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Objects;

public class User {
    // This fields hold the data that i want to encapsulate in the class
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String imagePath;

    //Default Constructor that firebase will use when creating new objects.
    // They can be empty during object creation
    public User (){
        this.firstName = "";
        this.lastName = "";
        this.phoneNumber = "";
        this.email = "";
        this.imagePath = "";
    }

    // Parameterized constructors - allows you to create a User object and set its fields with specific values when you pass arguments
    public User(String firstName,String lastName, String phoneNumber, String email, String imagePath){
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.imagePath = imagePath;
    }

    //Getter methods - this primarily allows the external code to retrieve the values
    // Provide controlled read access to the User object's data by returning the value of its fields
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getImagePath() {
        return imagePath;
    }

    //setter methods - allows external code to modify the private fields of the User.
    // Provide a controlled way to change the state of the object
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    // Equals method for comparing objects
    @Override
    public boolean equals(@Nullable Object obj) {
        // this - object being compared
        if(this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        // this line casts the obj to a User object so that you can compare the fields of the two User objects
        User user = (User) obj;
        return firstName.equals(user.firstName) &&
                lastName.equals(user.lastName) &&
                phoneNumber.equals(user.phoneNumber)&&
                email.equals(user.email) &&
                imagePath.equals(user.imagePath);
    }

    // Hash code method for generating hash codes
    // This method is used when storing objects in data structures like hash tables
    @Override
    public int hashCode() {
    // This method simplifies the process of generating a composite hash code based on multiple fields
        return Objects.hash(firstName,lastName,phoneNumber,email,imagePath);
    }

    // toString method for representing the object as a string
    @NonNull
    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}