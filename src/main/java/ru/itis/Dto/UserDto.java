package ru.itis.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.model.State;
import ru.itis.model.User;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserDto {

    private String login;
    private String email;
    private State state;
    private int id;
    public static UserDto from(User user) {
        return UserDto.builder().id(user.getId())
                .login(user.getLogin())
                .state(user.getState())
                .email(user.getEmail())
                .build();
    }
}