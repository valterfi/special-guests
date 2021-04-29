package com.valterfi.specialguests.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.valterfi.specialguests.model.SpecialGuest;
import com.valterfi.specialguests.service.SpecialGuestService;
import com.valterfi.specialguests.utils.ImageUtils;

@RestController
@CrossOrigin(origins = {"*"})
public class SpecialGuestController {

	@Autowired
	private SpecialGuestService specialGuestService;

	@GetMapping("/specialguests")
	public List<SpecialGuest> list() {
		return specialGuestService.listAll();
	}

	@GetMapping("/specialguests/{id}")
	public ResponseEntity<SpecialGuest> get(@PathVariable Integer id) {
		SpecialGuest product = specialGuestService.get(id);
		if (product !=  null) {
			return new ResponseEntity<SpecialGuest>(product, HttpStatus.OK);
		} else {
			return new ResponseEntity<SpecialGuest>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/specialguests")
	public ResponseEntity<SpecialGuest> singleFileUpload(@RequestParam("image") MultipartFile file,
			@RequestParam("name") String name,
			@RequestParam("gender") String gender) {
		SpecialGuest product = new SpecialGuest();
		try {
			if (!file.isEmpty()) {
				String base64image = ImageUtils.convertToBase64(file);
				product.setName(name);
				product.setGender(gender);
				product.setBase64image(base64image);
				System.out.println(base64image);
				specialGuestService.save(product);
			}
		} catch (IOException e) {
			return new ResponseEntity<SpecialGuest>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<SpecialGuest>(product, HttpStatus.CREATED);
	}

	@PutMapping("/specialguests/{id}")
	public ResponseEntity<SpecialGuest> update(@RequestParam("image") MultipartFile file,
			@RequestParam("name") String name,
			@RequestParam("gender") String gender,
			@PathVariable Integer id) {
		SpecialGuest existProduct = specialGuestService.get(id);
		if (existProduct != null) {
			existProduct.setName(name);
			existProduct.setGender(gender);
			if (!file.isEmpty()) {
				String base64image;
				try {
					base64image = ImageUtils.convertToBase64(file);
				} catch (IOException e) {
					return new ResponseEntity<SpecialGuest>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
				existProduct.setBase64image(base64image);
			}
			specialGuestService.save(existProduct);
			return new ResponseEntity<SpecialGuest>(HttpStatus.OK);
		} else {
			return new ResponseEntity<SpecialGuest>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/specialguests/{id}")
	public void delete(@PathVariable Integer id) {
		specialGuestService.delete(id);
	}

}
