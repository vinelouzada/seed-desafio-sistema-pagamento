package vinelouzada.yfood.user;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vinelouzada.yfood.user.dto.CreateUserRequest;

@RestController
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/user/create")
    public void save(@RequestBody @Valid CreateUserRequest request){
        userRepository.save(request.toUser());
    }
}