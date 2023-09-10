package ro.uaic.fii.UserService.service;

import org.springframework.stereotype.Service;
import ro.uaic.fii.UserService.exceptions.BadRequestException;
import ro.uaic.fii.UserService.exceptions.NotFoundException;
import ro.uaic.fii.UserService.model.User;
import ro.uaic.fii.UserService.repository.UserRepository;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository repository;
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User save(User user)
    {
        try {
            return repository.save(user);
        }
        catch (Exception e)
        {
            throw new BadRequestException(e.getMessage());
        }
    }

    public List<User> getAll() {
        return repository.findAll();
    }

    public User getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with ID: " + id + " not found."));
    }

    public User update(UUID id, User updatedUser) throws NotFoundException {
        User existingUser = getById(id);
        existingUser.setDomainId(updatedUser.getDomainId());
        existingUser.setRole(updatedUser.getRole());
        existingUser.setAccount(updatedUser.getAccount());
        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setNotes(updatedUser.getNotes());
        existingUser.setActive(updatedUser.getActive());
        return repository.save(existingUser);
    }

    public void deleteById(UUID id) {
        User user = getById(id);
        repository.delete(user);
    }
}
