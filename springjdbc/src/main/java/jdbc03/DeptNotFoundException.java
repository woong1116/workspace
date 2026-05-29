package jdbc03;

public class DeptNotFoundException extends RuntimeException{
    public DeptNotFoundException(String message){
        super(message);
    }
}