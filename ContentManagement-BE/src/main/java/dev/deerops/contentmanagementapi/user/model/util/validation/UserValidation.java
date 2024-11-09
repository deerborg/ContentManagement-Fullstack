package dev.deerops.contentmanagementapi.user.model.util.validation;

import dev.deerops.contentmanagementapi.user.model.util.exception.EmptyOrNullFieldForUserException;
import dev.deerops.contentmanagementapi.user.model.util.exception.InvalidMailFormatException;
import dev.deerops.contentmanagementapi.user.model.util.exception.InvalidPhoneFormatException;
import dev.deerops.contentmanagementapi.user.model.util.exception.UnUniqueUsernameException;
import dev.deerops.contentmanagementapi.user.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UserValidation {

    private final UserRepository userRepository;

    public UserValidation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void checkAllParameterForRequestClass(Object object) {

        Field[] fields = object.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);

            try {
                Object value = field.get(object);

                if (value == null || (value instanceof String && ((String) value).trim().isEmpty())) {
                    throw new EmptyOrNullFieldForUserException();
                }
            } catch (Exception e) {
                throw new EmptyOrNullFieldForUserException();
            }
        }
    }

    public void emailFormatValidation(String email) {

        if (!isValidEmail(email)) {
            throw new InvalidMailFormatException();
        }
    }

    public void phoneFormatValidation(String phone) {

        if (!isValidPhone(phone)) {
            throw new InvalidPhoneFormatException();
        }
    }

    public void uniqueUserNameValidation(String userName) {
        if(userRepository.existsByUsername(userName)) {
            throw new UnUniqueUsernameException();
        }
    }

    private boolean isValidEmail(String email) {

        if (email == null || email.trim().isEmpty()) {
            throw new EmptyOrNullFieldForUserException();
        }

        String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    private boolean isValidPhone(String phone) {

        return phone != null && !phone.trim().isEmpty() && phone.matches("\\d+");
    }
}
