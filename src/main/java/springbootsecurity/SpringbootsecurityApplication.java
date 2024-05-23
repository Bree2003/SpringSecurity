package springbootsecurity;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import springbootsecurity.persistence.entity.PermissionEntity;
import springbootsecurity.persistence.entity.RoleEntity;
import springbootsecurity.persistence.entity.RoleEnum;
import springbootsecurity.persistence.entity.UserEntity;
import springbootsecurity.persistence.repository.UserRepository;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class SpringbootsecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootsecurityApplication.class, args);
	}

	//poblar tablas cuando se corra el proyecto
	@Bean
	CommandLineRunner init(UserRepository userRepository) {
		return args -> {
			//primero vamos a crear los permisos
			PermissionEntity createPermission = PermissionEntity.builder()
					.name("CREATE").build();
			PermissionEntity readPermission = PermissionEntity.builder()
					.name("READ").build();
			PermissionEntity updatePermission = PermissionEntity.builder()
					.name("UPDATE").build();
			PermissionEntity deletePermission = PermissionEntity.builder()
					.name("DELETE").build();
			PermissionEntity refactorPermission = PermissionEntity.builder()
					.name("REFACTOR").build();
			//segundo crear los roles
			RoleEntity roleAdmin = RoleEntity.builder()
					.roleEnum(RoleEnum.ADMIN)
					.permissions(Set.of(createPermission, readPermission, updatePermission, deletePermission))
					.build();
			RoleEntity roleUser = RoleEntity.builder()
					.roleEnum(RoleEnum.USER)
					.permissions(Set.of(createPermission, readPermission))
					.build();
			RoleEntity roleInvited = RoleEntity.builder()
					.roleEnum(RoleEnum.INVITED)
					.permissions(Set.of(readPermission))
					.build();
			RoleEntity roleDeveloper = RoleEntity.builder()
					.roleEnum(RoleEnum.DEVELOPER)
					.permissions(Set.of(createPermission, readPermission, updatePermission, deletePermission, refactorPermission))
					.build();
			//tercero crear usuarios
			UserEntity userSantiago = UserEntity.builder()
					.username("santiago")
					.password(new BCryptPasswordEncoder().encode("1234"))
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleAdmin))
					.build();
			UserEntity userDaniel = UserEntity.builder()
					.username("daniel")
					.password(new BCryptPasswordEncoder().encode("1234"))
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleUser))
					.build();
			UserEntity userAndrea = UserEntity.builder()
					.username("andrea")
					.password(new BCryptPasswordEncoder().encode("1234"))
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleInvited))
					.build();
			UserEntity userAnyi = UserEntity.builder()
					.username("anyi")
					.password(new BCryptPasswordEncoder().encode("1234"))
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleDeveloper))
					.build();

			userRepository.saveAll(List.of(userSantiago, userDaniel, userAndrea, userAnyi));
		};
	}
}
