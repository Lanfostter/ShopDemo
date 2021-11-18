package com.example.ShopDemo.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.List;

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
public class UserController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	UserValidator userValidator;
	@Autowired
	PasswordEncoder encoder;

	@GetMapping("/admin/index")
	public String index() {
		return "admin/index";
	}

	@GetMapping("/admin/list-user")
	public String listUser(Model model) {
		List<UserEntity> users = userRepository.findAll();
		model.addAttribute("users", users);
		return "admin/admin-view";
	}

	@GetMapping("/admin/add-user")
	public String addUser(Model model) {
		model.addAttribute("user", new UserEntity());
		return "admin/add-user";
	}

	@PostMapping("/admin/add-user")
	public String addUser(@ModelAttribute("user") UserEntity userEntity, @RequestParam("bdate") String birthday,
			@RequestParam("imagefile") MultipartFile imagefile, BindingResult bindingResult) throws Exception {
		userValidator.validate(userEntity, bindingResult);
		if (bindingResult.hasErrors()) {
			return "admin/add-user";
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
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			userEntity.setBirthday(simpleDateFormat.parse(birthday));
			userRepository.save(userEntity);
			return "redirect:/admin/list-user";
		}
	}

	@GetMapping(value = "/download")
	public void download(HttpServletResponse response, @RequestParam("image") String image) {
		final String uploadFolder = "D:\\ShopDemo\\src\\main\\resources\\static\\img\\";// tao thu muc luu anh
		File file = new File(uploadFolder + File.separator + image);
		if (file.exists()) {
			try {
				Files.copy(file.toPath(), response.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@GetMapping("/admin/update-user")
	public String updateUser(Model model, @RequestParam("id") int id) {
		model.addAttribute("newuser", userRepository.getById(id));
		return "admin/update-user";
	}

	@PostMapping("/admin/update-user")
	public String updateUser(@ModelAttribute("newuser") UserEntity userEntity, @RequestParam("bdate") String birthday,
			@RequestParam("imagefile") MultipartFile imagefile, BindingResult bindingResult) throws Exception {
		userValidator.validate(userEntity, bindingResult);
		if (bindingResult.hasErrors()) {
			return "admin/update-user";
		} else {
			String originalFilename = imagefile.getOriginalFilename();
			int lastIndex = originalFilename.lastIndexOf(".");
			String ext = originalFilename.substring(lastIndex);
			String avatarFilename = System.currentTimeMillis() + ext;
			File newfile = new File("D:\\ShopDemo\\src\\main\\resources\\static\\img\\"+avatarFilename);
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
			String encoderPass = encoder.encode(userEntity.getPassword());
			userEntity.setPassword(encoderPass);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			userEntity.setBirthday(simpleDateFormat.parse(birthday));
			userEntity.setAvatar(avatarFilename);
			userRepository.save(userEntity);
			return "redirect:/admin/list-user";
		}
	}

	@GetMapping("/admin/delete-user")
	public String deleteUser(@RequestParam("id") int id) {
		userRepository.deleteById(id);
		;
		return "redirect:/admin/list-user";
	}
}
