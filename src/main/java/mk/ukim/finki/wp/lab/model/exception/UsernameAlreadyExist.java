package mk.ukim.finki.wp.lab.model.exception;

public class UsernameAlreadyExist extends RuntimeException{

    public UsernameAlreadyExist(String username) {
        super(String.format("Username %s already exist", username));
    }
}
