package com.example.ShopDemo.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.example.ShopDemo.entity.UserEntity;

@Component
public class UserValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return UserEntity.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {		
		UserEntity userEntity = (UserEntity) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.require");
		if(userEntity.getPassword() == null || userEntity.getUsername().length() < 6)
		{
			errors.rejectValue("password", "password.require");
		}
		if(userEntity.getPassword() == null || userEntity.getUsername().length() < 6 || userEntity.getUsername().length() > 10)
		{
			errors.rejectValue("username", "username.require");
		}
	}

}
