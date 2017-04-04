package ru.kononov.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import ru.kononov.objects.User;

@Controller
public class FileController {

	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public String uploadFile(@ModelAttribute(name = "multipartFile") MultipartFile multipartFile, ModelMap modelMap) {
		
		String message = null;
		/*HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "text/html;charset=UTF-8");*/
		
		String name = null;
		if (!multipartFile.isEmpty()) {
			try {
				byte[] bytes = multipartFile.getBytes();
				name = multipartFile.getOriginalFilename();
				
				String pathToSave = System.getProperty("catalina.home");
				File dir = new File(pathToSave + File.separator + "tmpFile");
				if (!dir.exists()) {
					dir.mkdirs();
				}
				
				File uploadedFile = new File(dir.getAbsolutePath() + File.separator + name);
				System.out.println("Файл загружен в " + uploadedFile.getAbsolutePath());
				
				BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(uploadedFile));
				bos.write(bytes);
				bos.flush();
				bos.close();
			} catch (Exception e) {
				/*return new ResponseEntity<>("Загрузка файла " + name + " завершилась неудачей Сообщение об ошибке " + e.getMessage(), headers, HttpStatus.OK);*/
				message = "Загрузка файла " + name + " завершилась неудачей Сообщение об ошибке " + e.getMessage();
				modelMap.addAttribute("message", message);
				return "redirect:/resultFileUploading";
			}
			/*return new ResponseEntity<>("Файл " + name + " успешно загружен на сервер", headers, HttpStatus.OK);*/
			message = "Файл " + name + " успешно загружен на сервер";
			modelMap.addAttribute("message", message);
			return "redirect:/resultFileUploading";
			
		} else {
			/*return new ResponseEntity<>("Загрузка файла завершилась неудачей потому что файл пуст", headers, HttpStatus.OK);*/
			message = "Загрузка файла завершилась неудачей потому что файл пуст";
			modelMap.addAttribute("message", message);
			return "redirect:/resultFileUploading";
		}
	}
	
	@RequestMapping(value = "/resultFileUploading", method = RequestMethod.GET)
	public ModelAndView resultFileUploading(@ModelAttribute(name = "message") String message) {
		return new ModelAndView("resultFileUploading", "message", message);
	}
	
	@RequestMapping(value = "/uploadingFile", method = RequestMethod.GET)
	public String anotherPage(HttpServletRequest request) {
		Map <String, ?> map = RequestContextUtils.getInputFlashMap(request);
		if (map != null) {
			System.out.println("redirect");
		} else {
			System.out.println("update");
		}
		return "uploadingFile";
	}
	
	@RequestMapping(value = "/get-json-user/{name}/{admin}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public User getJsonUser(@PathVariable("name") String name, @PathVariable("admin") boolean admin) {
		User user = new User();
		user.setName(name);
		user.setPassword("password");
		user.setAdmin(admin);
		return user;
	}
	
}