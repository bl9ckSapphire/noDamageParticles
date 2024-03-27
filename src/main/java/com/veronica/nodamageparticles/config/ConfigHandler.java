package com.veronica.nodamageparticles.config;

import com.veronica.nodamageparticles.NoDamageParticles;
import org.bukkit.configuration.file.FileConfiguration;
import java.util.logging.Logger;


public class ConfigHandler {
    private static ConfigHandler instance;
    private boolean isUse;
    private final FileConfiguration config;
    private final Logger logger = NoDamageParticles.getNoDamageParticlesLogger();

    private ConfigHandler() {
        config = NoDamageParticles.getInstance().getConfig();

        // 뒤의 false는, 콘피그에 아무 값도 써있지 않다면 기본값으로 false로 설정된다는 뜻임
        // 그게 아니라면, 콘피그의 경로에 있는 값을 가져오는 것
        isUse = config.getBoolean("use-remove-damage-particle", false);
    }

    public static ConfigHandler getInstance() {
        if (instance == null) instance = new ConfigHandler();
        return instance;
    }

    // 업데이트 된 콘피그의 정보를 변수에 저장하는 메서드
    public void reloadConfigOptions() {
        Message.reloadConfigMessage(); // Message 클래스에 따로 분리해놓은 것들
        FileConfiguration config = NoDamageParticles.getInstance().getConfig();
        isUse = config.getBoolean("use-remove-damage-particle");
    }


    // use-remove-damage-particle 값을 true 또는 false 로 설정하고 저장하는 메서드
    public void setConfigUseOption(boolean isUse) {
        // config.yml에 값을 저장하는 코드
        config.set("use-remove-damage-particle", isUse);
        NoDamageParticles.getInstance().saveConfig(); // 변경된 config.yml 저장
        this.isUse = isUse; // 현재 ConfigHandler 클래스의 isUse 에 새로운 값을 할당
    }

    public boolean getConfigIsUse() { return isUse; }
}
