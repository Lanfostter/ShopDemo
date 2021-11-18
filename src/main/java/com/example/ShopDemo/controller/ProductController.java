package com.example.ShopDemo.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.ShopDemo.entity.ProductEntity;
import com.example.ShopDemo.entity.Translation;
import com.example.ShopDemo.repository.ProductRepository;
import com.example.ShopDemo.repository.TranslationRepository;

@Controller
public class ProductController {
	@Autowired
	ProductRepository productRepository;
	@Autowired
	TranslationRepository translationRepository;
	@GetMapping("/admin/list-product")
	public String listProduct(Model model) {
		model.addAttribute("products", productRepository.findAll());
		return "product/list-product";
	}
	@GetMapping("/admin/add-product")
	public String addProduct(Model model1,Model model2) {
		List<Translation> translations = translationRepository.findAll();
		model2.addAttribute("translations", translations);
		model1.addAttribute("product", new ProductEntity());
		return "product/add-product";
	}

	@PostMapping("/admin/add-product")
	public String addProduct(@ModelAttribute("product") ProductEntity productEntity,
			@RequestParam("bdate") String importdate, 
			@RequestParam("edate") String expirydate,
			@RequestParam("imagefile") MultipartFile imagefile) throws ParseException {
		String originalFilename = imagefile.getOriginalFilename();
		int lastIndex = originalFilename.lastIndexOf(".");
		String ext = originalFilename.substring(lastIndex);
		String imagineFilename = System.currentTimeMillis() + ext;
		File newfile = new File("D:\\ShopDemo\\src\\main\\resources\\static\\img\\" + imagineFilename);
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
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		productEntity.setImportdate(simpleDateFormat.parse(importdate));
		productEntity.setExpirydate(simpleDateFormat.parse(expirydate));
		productEntity.setImg(imagineFilename);
		productRepository.save(productEntity);
		return "redirect:/admin/list-product";
	}

}
