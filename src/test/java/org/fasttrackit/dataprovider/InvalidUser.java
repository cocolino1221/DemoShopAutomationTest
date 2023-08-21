package org.fasttrackit.dataprovider;

public class InvalidUser extends User{
    private final String greetingsMessage;
    private final String errorMessage;
    public InvalidUser (String userName , String password, String errorMessage){
        super(userName,password);
        this.greetingsMessage = "Hello guest!";
        this.errorMessage = errorMessage;
    }
    public String getErrorValidationMessage(){
        return errorMessage;
    }

    @Override
    public String getGreetingsMessage() {
        return greetingsMessage ;
    }
}
