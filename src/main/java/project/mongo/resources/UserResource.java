package project.mongo.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import project.mongo.domain.User;
import project.mongo.dto.UserDto;
import project.mongo.services.UserService;
import project.mongo.services.exceptions.ObjectNotFoundException;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> findAll() {
        List<User> list = userService.findAll();
        List<UserDto> dtoList = list.stream().map(UserDto::new).toList();
        return ResponseEntity.ok().body(dtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable String id) {
        User user = userService.findById(id);
        return ResponseEntity.ok().body(new UserDto(user));
    }

    @PostMapping
    public ResponseEntity<UserDto> insert(@RequestBody UserDto dto, UriComponentsBuilder builder) {
        User user = new User(null, dto.getName(), dto.getEmail());
        user = userService.insert(user);
        URI uri = builder.path("/users/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new UserDto(user));
    }

    @DeleteMapping
    public ResponseEntity<UserDto> delete(@RequestBody UserDto dto) {
        User user = new User(dto.getId(), dto.getName(), dto.getEmail());
        userService.delete(user);
        return ResponseEntity.ok().body(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDto> deleteById(@PathVariable String id) {
        userService.deleteById(id);
        return ResponseEntity.ok().body(null);
    }
}
