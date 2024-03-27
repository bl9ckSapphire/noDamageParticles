package com.veronica.nodamageparticles.config;

import com.veronica.nodamageparticles.NoDamageParticles;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.plugin.Plugin;

public enum Message {
    CONSOLE_PREFIX("[대미지 파티클]"),
    PREFIX("<gold><bold>[</bold><yellow>대미지 파티클</yellow><bold>]<reset>"),
    TOGGLE_ON("<prefix><white>대미지 파티클이 <red>보이지 않도록 <white>설정되었습니다."),
    TOGGLE_OFF("<prefix><white>대미지 파티클이 <yellow>보이도록 <white>설정되었습니다."),
    UNKNOWN_COMMAND("<white>알 수 없는 명령어입니다."),
    CONFIG_RELOAD("<prefix><gold>대미지 파티클 config 리로드 완료."),
    NO_PERMISSION("<white>권한이 없습니다."),
    NOT_A_PLAYER("<yellow>이 명령어는 오직 플레이어를 통해서만 사용 가능합니다."),
    HELP_MAIN("<white>대미지 파티클의 가시성을 설정하는 플러그인입니다."),
    HELP_TOGGLE("<yellow>/nodamageparticle toggle \n<white>• 대미지파티클 상태를 토글"),
    HELP_RELOAD("<yellow>/nodamageparticle reload \n<white>• 대미지파티클 config 리로드");

    Component message;

    Message(String message) {
        this.message = LegacyComponentSerializer.legacySection().deserialize(message);
    }

    public String getMessage() {
        return LegacyComponentSerializer.legacySection().serialize(this.message);
    }

    private void setMessage(String message) {
        this.message = LegacyComponentSerializer.legacySection().deserialize(message);
    }

    public static void reloadConfigMessage() {
        Plugin instance = NoDamageParticles.getInstance();
        CONSOLE_PREFIX.setMessage("[대미지 파티클]");
        PREFIX.setMessage(instance.getConfig().getString("prefix"));
        TOGGLE_ON.setMessage(instance.getConfig().getString("toggle-on"));
        TOGGLE_OFF.setMessage(instance.getConfig().getString("toggle-off"));
        UNKNOWN_COMMAND.setMessage(instance.getConfig().getString("unknown-command"));
        CONFIG_RELOAD.setMessage(instance.getConfig().getString("config-reload"));
        NO_PERMISSION.setMessage(instance.getConfig().getString("no-permission"));
        NOT_A_PLAYER.setMessage(instance.getConfig().getString("not-a-player"));
        HELP_MAIN.setMessage(instance.getConfig().getString("help-main"));
        HELP_TOGGLE.setMessage(instance.getConfig().getString("help-toggle"));
        HELP_RELOAD.setMessage(instance.getConfig().getString("help-reload"));
    }
}