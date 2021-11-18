package com.example.ShopDemo.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.ShopDemo.entity.UserEntity;
import com.example.ShopDemo.repository.UserRepository;
import com.example.ShopDemo.validator.UserValidator;

@Controller
public class LoginController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	UserValidator userValidator;

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("user", new UserEntity());
		return "register";
	}

	@PostMapping("/register")
	public String register(@ModelAttribute("user") UserEntity userEntity, @RequestParam("bdate") String birthday,
			@RequestParam("imagefile") MultipartFile imagefile, BindingResult bindingResult) throws Exception {
		userValidator.validate(userEntity, bindingResult);
		if (bindingResult.hasErrors()) {
			return "register";
		} else {
			if (imagefile != null) {
				String originalFilename = imagefile.getOriginalFilename();
				int lastIndex = originalFilename.lastIndexOf(".");
				String ext = originalFilename.substring(lastIndex);
				String avatarFilename = System.currentTimeMillis() + ext;
				File newfile = new File("D:\\ShopDemo\\src\\main\\resources\\static\\img\\" + avatarFilename);
				FileOutputStream fileOutputStream;
				try {
					fileOutputStream = new FileOutputStream(newfile);
					fileOutputStream.write(imagefile.getBytes());
					fileOutputStream.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				userEntity.setAvatar(avatarFilename);

			}
			String encoderPass = encoder.encode(userEntity.getPassword());
			userEntity.setPassword(encoderPass);
			userEntity.setRole("ROLE_MEMBER");
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			userEntity.setBirthday(simpleDateFormat.parse(birthday));
			userRepository.save(userEntity);
			return "redirect:/login";
		}

	}

}
