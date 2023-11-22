package daniel.brian.happyhourhub.util;

// Result - it represents the result of an operation and can hold data value and an optional message
public abstract class Result <T> {
 private final T data ; // stores the data
 private final String message; // stores an optional message

 // private constructor that initializes the data and message parameters and assigns them to data and message values
 private Result(T data, String message){
     this.data = data;
     this.message = message;
 }

 // returns an instance of the Loading state class
 public Result <T> loading(){
     return new Loading<>();
 }

 // returns an instance of the Success state class with data and null
 public Result <T> success(T data){
     return new Success<>(data);
 }

 // returns an instance of the Error state class with null and message
 public Result <T> error(String message){
     return new Error<> (message);
 }

 // returns the data value stored in data parameter
 public T getData(){
     return data;
 }

 // returns the message stored in the message parameter
 public String getMessage(){
     return message;
 }


 public static  class Loading <T> extends Result <T>{
     public Loading(){
         super(null,null);
     }
 }

  // Returns the value of the data fields
 public static class Success <T> extends Result <T>{
     public Success(T data){
         super(data,null);
     }
 }

 // Returns the value of the message fields
 public static class Error <T> extends Result <T>{
     public Error (String message){
         super(null,message);
     }
 }

}
