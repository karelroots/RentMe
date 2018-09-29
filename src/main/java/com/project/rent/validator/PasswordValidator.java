package com.project.rent.validator;

import com.project.rent.model.User;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


public class PasswordValidator implements Validator{

    @Override
    public boolean supports(Class<?> clazz) {
        //Valideerime kasutaja
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
                "required.password", "Field name is required.");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword",
                "required.confirmPassword", "Field name is required.");

        User user = (User)target;

        if(!(user.getPassword().equals(user.getConfirmPassword()))){
            errors.rejectValue("password", "notmatch.password");
        }

    }

}
