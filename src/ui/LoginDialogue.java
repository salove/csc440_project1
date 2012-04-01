package ui;

import action.Login;

import common.Session;
import common.Settings;

import exception.LoginFailedException;

public class LoginDialogue {
    static void login(UI ui) {

        for (boolean isValid = false; !isValid;) {
            ui.clear();
            ui.write("\nUser ID: ");
            String userId = ui.readLine();
            ui.statusUpdate("Read user id " + userId + " from UI");
            ui.write("\n");
            ui.write("Password: ");
            ui.setObfuscated(true);
            String pw = ui.readLine();
            try {
                Session s = Login.login(ui, userId, pw,
                        Settings.checkUnitTest());
                ui.setSession(s);
                isValid=true;
            } catch (LoginFailedException e) {
                ui.statusUpdate("User " + userId + " is not valid for login.");
            }
        }
    }

}
