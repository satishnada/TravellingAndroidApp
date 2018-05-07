package com.moderndeveloper.fllawi.permissionManagerViews;

public interface PermissionManagerListener {
    void permissionCallback(String[] permissions, Permission[] grantResults, boolean allGranted);
}
