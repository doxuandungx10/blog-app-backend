package gr1.demo.blogapp.exception;

public class PostNotFoundException extends RuntimeException {
    public  PostNotFoundException(String mess){
        super(mess);
    }
}
