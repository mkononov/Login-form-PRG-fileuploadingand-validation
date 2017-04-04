package ru.kononov.objects;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {
	
	/*@Size(min = 6, message = "Имя должно быть больше 6 знаков")*/
	@Size(min = 6, message = "{name.size.error}")
	private String name;
	
	/*@Size(min = 5, max = 10, message = "Пароль должен быть от 5 до 10 знаков")*/
	@Size(min = 5, max = 10, message = "{password.size.error}")
	private String password;
	private boolean admin;
	
	public boolean getAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
