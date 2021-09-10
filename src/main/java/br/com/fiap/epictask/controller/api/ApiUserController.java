package br.com.fiap.epictask.controller.api;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fiap.epictask.model.User;
import br.com.fiap.epictask.repository.UserRepository;

@RestController
@RequestMapping("/api/user")
public class ApiUserController {

	@Autowired
	UserRepository repository;
	
	@GetMapping
	@Cacheable("users")
	public Page<User> index(@PageableDefault Pageable pageable) {
		return repository.findAll(pageable);
	}
	
	@PostMapping
	@CacheEvict(value="users", allEntries = true)
	public ResponseEntity<User> create(@RequestBody @Valid User user, UriComponentsBuilder uriBuilder) {
		repository.save(user);
		URI uri = uriBuilder.path("/api/user/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(user);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<User> show(@PathVariable Long id) {
		return ResponseEntity.of(repository.findById(id));
	}
	
	@DeleteMapping("{id}")
	@CacheEvict(value="users", allEntries = true)
	public ResponseEntity<User> destroy(@PathVariable Long id) {
		Optional<User> user = repository.findById(id);
		if(user.isPresent()) {
			repository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("{id}")
	@CacheEvict(value="users", allEntries = true)
	public ResponseEntity<User> update(@PathVariable Long id, @RequestBody @Valid User newUser) {
		Optional<User> optional = repository.findById(id);
		if(optional.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		User user = optional.get();
		newUser.setId(user.getId());
		return ResponseEntity.ok(repository.save(newUser));
	}
}