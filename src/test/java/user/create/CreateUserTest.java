package user.create;

import static org.assertj.core.api.Assertions.assertThat;

import dto.UserDTO;
import org.junit.jupiter.api.Test;
import services.UserApi;

public class CreateUserTest {

  @Test
  public void checkCreateUser() {

    String testMail = "@rambler.ru";

    UserApi userApi = new UserApi();

    UserDTO user1 = UserDTO.builder()
        .userStatus(100l)
        .email("emailIvan@rambler.ru")
        .id(300l)
        .firstName("Ivan")
        .lastName("Petrov")
        .phone("9379992")
        .password("qwertyuiop1234")
        .username("ivan2000")
        .build();

    UserDTO user2 = UserDTO.builder()
        .userStatus(100l)
        .email("Petya1990@rambler.ru")
        .id(200l)
        .firstName("Petr")
        .lastName("Ivanov")
        .phone("9379992")
        .password("VeRyH@RdP@Ss")
        .username("pet1990")
        .build();

    userApi.createUser(user1);
    userApi.createUser(user2);

    UserDTO returnedUser1 = userApi.getUser(user1.getUsername());
    UserDTO returnedUser2 = userApi.getUser(user2.getUsername());

    assertThat(returnedUser1.getEmail().endsWith(testMail))
        .as(String.format("Email should be ends with %s", testMail))
        .isTrue();

    assertThat(returnedUser2.getEmail().endsWith(testMail))
        .as(String.format("Email should be ends with %s", testMail))
        .isTrue();

  }
}
