package com.tinqin.cms.enums;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum Type {
    LOGIN("Login in the system!"),
    WRONG_PASSWORD("Wrong password!"),
    SEND_PASSWORD_CHANGE("Password change email send!"),
    PROFILE_ACTIVATE("Profile activated!"),
    LOGIN_INACTIVE_PROFILE("Tried to login in inactive profile!"),
    ALREADY_EXISTING_PROFILE("Tried to register already registered profile!"),
    ACTIVATION_LINK_SEND("Activation link send!"),
    PROFILE_CREATED("Registration created!"),
    UPDATED_OWNER_INFORMATION("Updated owner information."),
    OWNER_CREATED("Owner created.");

    private final String label;

    public static final Map<String, Type> BY_LABEL = new HashMap<>();

    static {
        for (Type e : values()) {
            BY_LABEL.put(e.label.toUpperCase(), e);
        }
    }

    Type(String label) {
        this.label = label;
    }
}
