package ru.signup;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;

import com.jberry.dto.User;
import com.jberry.services.user.UserService;
import com.jberry.services.user.UserServiceFactory;

/**
 * Created by Sindri on 20/08/14.
 */
public class loginAdapter{

    public static boolean login(String email, String password) throws Exception {
        UserService service = UserServiceFactory.getUserService();
        return service.login(email, password);
    }

}
