package com.moderndeveloper.fllawi.enums;

public enum WEB_VIEW_CONTENT_TYPE {
    PRIVACY_POLICY(0),
    TERMS_AND_CONDITIONS(1),
    HELP_AND_SUPPORT(2),
    OVERVIEW(3);
    public int value;

    WEB_VIEW_CONTENT_TYPE(int value) {
        this.value = value;
    }
}
