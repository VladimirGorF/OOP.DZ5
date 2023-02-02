package personal.controllers;

import personal.model.Fields;
import personal.model.Repository;
import personal.model.User;
import personal.utils.Validate;

import java.util.List;

public class UserController {
    private final Repository repository;
    private final Validate validate;
    public UserController(Repository repository, Validate validate) {
        this.repository = repository;
        this.validate = validate;
    }

    public void saveUser(User user) throws Exception {
        validate.checkNumber(user.getPhone());
        repository.CreateUser(user);
    }

    public void saveOldFormat() throws Exception {
        repository.saveOld();
    }


    public void updateUser(User user, Fields field, String param) throws Exception {
        if(field == Fields.TELEPHONE) {
            validate.checkNumber(param);
        }
        repository.UpdateUser(user, field, param);

    }
    public User readUser(String userId) throws Exception {
        List<User> users = repository.getAllUsers();
        for (User user : users) {
            if (user.getId().equals(userId)) {
                return user;
            }
        }

        throw new Exception("User not found");
    }

    public User deleteUser(String userId) throws Exception { //   удаление из списка Юзеров
        List<User> users = repository.getAllUsers();
        System.out.println("Работает контроллер: запись удаляется из списка...");
        for (User user : users) {
            if (user.getId().equals(userId)) {
                users.remove(user);
                repository.deleteUser(users);  // вызываем интрефейс репозиторий
                return user;
            }
        }

        throw new Exception("User not found");
    }




    public List<User> getUsers() throws Exception {
        return repository.getAllUsers();
    }
}
