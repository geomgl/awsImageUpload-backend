package com.geomgl.awsImageUpload;

import com.amazonaws.services.appstream.model.User;
import com.geomgl.awsImageUpload.auth.model.AppUser;
import com.geomgl.awsImageUpload.auth.model.Role;
import com.geomgl.awsImageUpload.auth.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication()
public class AwsImageUploadApplication {

	public static void main(String[] args) {
		SpringApplication.run(AwsImageUploadApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run (UserService userService) {
		return args -> {
			userService.saveRole(new Role(null, "ROLE_USER"));
			userService.saveRole(new Role(null, "ROLE_MANAGER"));
			userService.saveRole(new Role(null, "ROLE_ADMIN"));
			userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

			userService.saveUser((new AppUser(null, "Lebron", "James", "lbj", "password", new ArrayList<Role>())));
			userService.saveUser((new AppUser(null, "Barack", "Obama", "obama", "password", new ArrayList<Role>())));
			userService.saveUser((new AppUser(null, "John ", "Doe", "jdoe", "password", new ArrayList<Role>())));
			userService.saveUser((new AppUser(null, "Clark", "Kent", "ckent", "password", new ArrayList<Role>())));

			userService.addRoleToUser("lbj", "ROLE_SUPER_ADMIN" );
			userService.addRoleToUser("obama", "ROLE_MANAGER" );
			userService.addRoleToUser("jdoe", "ROLE_ADMIN" );
			userService.addRoleToUser("jdoe", "ROLE_USER" );
			userService.addRoleToUser("ckent", "ROLE_ADMIN" );
			userService.addRoleToUser("ckent", "ROLE_SUPER_ADMIN" );

		};
	}
}





