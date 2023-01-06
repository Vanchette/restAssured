package user.create;

import static org.assertj.core.api.Assertions.assertThat;

import dto.UserDTO;
import org.junit.jupiter.api.Test;
import services.UserApi;

public class CreateUserTest {

  @Test
  public void checkCreateUser() {

    UserApi userApi = new UserApi();

    UserDTO user1 = UserDTO.builder()
        .userStatus(Long.parseLong(System.getProperty("userStatus","100")))
        .email(System.getProperty("email", "emailIvan@rambler.ru"))
        .id(Long.parseLong(System.getProperty("id","300")))
        .firstName(System.getProperty("firstName","Ivan"))
        .lastName(System.getProperty("lastName","Petrov"))
        .phone(System.getProperty("phone","9379992"))
        .password(System.getProperty("password","qwertyuiop1234"))
        .username(System.getProperty("username","ivan2000"))
        .build();

    userApi.createUser(user1);

    UserDTO returnedUser1 = userApi.getUser(user1.getUsername());

    assertThat(returnedUser1.equals(user1))
        .as(String.format("The returned user must match the one created %s", user1.getUsername()))
        .isTrue();

    userApi.deleteUser(user1.getUsername());

  }
}
