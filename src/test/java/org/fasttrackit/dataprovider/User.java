package org.fasttrackit.dataprovider;

public class User {
    private final String userName;
    private final String password;
    private final String greetingsMessage;

    public User(String name, String password){
        this.userName = name;
        this.password = password;
        this.greetingsMessage = String.format("Hi %s!" , userName);

    }
    public String getUserName(){
        return userName;
    }
    public String getGreetingsMessage(){
        return greetingsMessage;
    }
    public String getPassword(){
        return password;
    }


    @Override
    public String toString() {
        return userName;
    }
}
