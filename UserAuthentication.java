import java.util.HashMap;
import java.util.Map;

public class UserAuthentication {
    private Map<String, String> users;

    public UserAuthentication() {
        users = new HashMap<>();
        users.put("user1", "password1");
        users.put("user2", "password2");
    }

    public boolean authenticate(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }

    public void addUser(String username, String password) {
        users.put(username, password);
    }
}
