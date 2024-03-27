package com.veronica.nodamageparticles.permission;
import org.bukkit.permissions.Permission;
public enum NoDamageParticlesPermission {
    USE(new Permission("nodamageparticles.use")),
    TOGGLE_COMMAND(new Permission("nodamageparticles.toggle")),
    RELOAD_COMMAND(new Permission("nodamageparticles.reload"));
    final Permission permission;
    NoDamageParticlesPermission(Permission permission) {
        this.permission = permission;
    }
    public Permission getPermission() {
        return this.permission;
    }
}