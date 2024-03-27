package com.veronica.nodamageparticles;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.veronica.nodamageparticles.command.CommandHandler;
import com.veronica.nodamageparticles.command.sub.HelpCommand;
import com.veronica.nodamageparticles.command.sub.ReloadCommand;
import com.veronica.nodamageparticles.command.sub.ToggleCommand;
import com.veronica.nodamageparticles.config.ConfigHandler;
import com.veronica.nodamageparticles.listener.DamageParticleListener;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class NoDamageParticles extends JavaPlugin implements Listener {

    private static NoDamageParticles instance;
    private static Logger logger;
    private static final MiniMessage miniMessage = MiniMessage.miniMessage();

    @Override
    public void onEnable() {

        if (!getServer().getPluginManager().isPluginEnabled("ProtocolLib")) {
            getLogger().severe("[noDamageParticles] ProtocolLib 플러그인이 설치되어 있지 않아 플러그인이 비활성화됩니다.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        instance = this;
        logger = this.getLogger();


        // MiniMessage 사용을 위한 의존성 검사 try-catch
        try {
            Class.forName("net.kyori.adventure.text.minimessage.MiniMessage");
            Class.forName("net.kyori.adventure.text.Component");
        } catch (ClassNotFoundException e) {
            this.getLogger().severe("실행을 위해서는 Adventure 라이브러리가 필요합니다.");
            this.getLogger().warning("net.kyori.adventure.text.minimessage.MiniMessage 클래스가 없습니다.");
            this.getLogger().warning("net.kyori.adventure.text.Component 클래스가 없습니다.");
            this.getServer().getPluginManager().disablePlugin(this);
        }

        getServer().getConsoleSender().sendMessage("[noDamageParticles] "+ChatColor.BLUE + "----------------------------------");
        getServer().getConsoleSender().sendMessage("[noDamageParticles] "+ChatColor.BLUE + "대미지 파티클 제거 플러그인 활성화");
        getServer().getConsoleSender().sendMessage("[noDamageParticles] "+ChatColor.BLUE + "----------------------------------");



        // config.yml 파일을 플러그인 폴더에 생성하는 bukkitAPI (폴더에 config.yml이 존재하지 안은 경우에만 생성)
        this.saveDefaultConfig();

        // ConfigHandler 클래스에 구현해 둔 config 정보 리로드 메서드
        ConfigHandler.getInstance().reloadConfigOptions();

        // 플러그인의 커맨드 등록
        registerCommands();


        // listener 패키지에서 패킷 이벤트를 구현한 DamageParticleListener 클래스를 등록
        ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();
        protocolManager.addPacketListener(new DamageParticleListener(this));

    }

    private void registerCommands() {
        // CommandHandler 인스턴스 생성
        CommandHandler commandHandler = new CommandHandler();

        // 플러그인의 커맨드 등록
        commandHandler.registerSubCommand("toggle", new ToggleCommand());
        commandHandler.registerSubCommand("reload", new ReloadCommand());
        commandHandler.registerSubCommand("help", new HelpCommand());

        // PluginManager에 CommandExecutor로 등록
        getCommand("nodamageparticles").setExecutor(commandHandler);
    }

    public static Plugin getInstance() {
        return instance;
    }
    public static Logger getNoDamageParticlesLogger() {
        return logger;
    }
    public static MiniMessage getMiniMessage() {
        return miniMessage;
    }

}
