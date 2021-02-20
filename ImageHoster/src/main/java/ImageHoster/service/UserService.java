package ImageHoster.service;

import ImageHoster.model.User;
import ImageHoster.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //Call the registerUser() method in the UserRepository class to persist the user record in the database
    public void registerUser(User newUser) {
        userRepository.registerUser(newUser);
    }

    //Since we did not have any user in the database, therefore the user with username 'upgrad' and password 'password' was hard-coded
    //This method returned true if the username was 'upgrad' and password is 'password'
    //But now let us change the implementation of this method
    //This method receives the User type object
    //Calls the checkUser() method in the Repository passing the username and password which checks the username and password in the database
    //The Repository returns User type object if user with entered username and password exists in the database
    //Else returns null
    public User login(User user) {
        User existingUser = userRepository.checkUser(user.getUsername(), user.getPassword());
        if (existingUser != null) {
            return existingUser;
        } else {
            return null;
        }
    }

    //Method Used to Validate the password before registration
    public boolean isValidPassword(String password) {
        //regular expression for atleast 1 alphabet 1number and 1 special character
        String regex = "^(?=.*[a-z]|[A-Z])"
                +"(?=.*[0-9])"
                +"(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{3,16}$";

        //Compiling Regular Expression
        Pattern passwordPattern = Pattern.compile(regex);

        //returns null if no password entered in the text box
        if (password == null) {
            return false;
        }

        // Pattern class contains matcher() method to find matching between given password
        // and regular expression.
        Matcher matchRegex = passwordPattern.matcher(password);

        // Return if the password matches the ReGex
        return matchRegex.matches();

    }
}
