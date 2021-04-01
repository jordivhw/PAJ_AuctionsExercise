package be.pxl.auctions.service;

import be.pxl.auctions.model.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserServiceCreateUserTest {

    @Test
    public void idkwat() {
        User user = new User();
        user.setFirstName("Mark");
        user.setLastName("Zuckerberg");
        user.setDateOfBirth(LocalDate.of(1989, 5, 3));
        user.setEmail("mark@facebook.com");
    }
}
