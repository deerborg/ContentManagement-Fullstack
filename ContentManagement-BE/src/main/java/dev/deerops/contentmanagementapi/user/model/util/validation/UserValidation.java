package dev.deerops.contentmanagementapi.user.model.util.validation;

import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UserValidation {

    public void checkAllParameterForRequestClass(Object object) {

        Field[] fields = object.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);

            try {
                Object value = field.get(object);

                if (value == null || (value instanceof String && ((String) value).trim().isEmpty())) {
                    throw new IllegalAccessException(field.getName() + " is null or empty");
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Field '" + field.getName() + "' is null or empty", e);
            } catch (Exception e) {
                throw new RuntimeException("Error while checking field: " + field.getName(), e);
            }
        }
    }

    public void emailFormatValidation(String email) {

        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format");
        }
    }

    public void phoneFormatValidation(String phone) {

        if (!isValidPhone(phone)) {
            throw new IllegalArgumentException("Invalid phone format");
        }
    }

    private boolean isValidEmail(String email) {

        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email is null or empty");
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
