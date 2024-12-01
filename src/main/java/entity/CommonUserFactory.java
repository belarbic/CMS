
package entity;

import java.util.ArrayList;

/**
 * Factory for creating CommonUser objects.
 */
public class CommonUserFactory implements UserFactory {

    @Override
    public User create(String name, String password) {
        final ArrayList<ChatRoom> chatRooms = new ArrayList<>();
        return new CommonUser(name, password, chatRooms);
    }
}