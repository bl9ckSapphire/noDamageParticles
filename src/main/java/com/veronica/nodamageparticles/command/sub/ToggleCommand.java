package com.veronica.nodamageparticles.command.sub;

import com.veronica.nodamageparticles.NoDamageParticles;
import com.veronica.nodamageparticles.command.SubCommand;
import com.veronica.nodamageparticles.config.ConfigHandler;
import com.veronica.nodamageparticles.config.Message;
import com.veronica.nodamageparticles.permission.NoDamageParticlesPermission;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ToggleCommand extends SubCommand {
    private static final MiniMessage miniMessage = NoDamageParticles.getMiniMessage();

    public ToggleCommand() {
        super(NoDamageParticlesPermission.TOGGLE_COMMAND.getPermission());
    }

    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(Component.text("").color(TextColor.color(0xff0000)));
            sender.sendMessage(miniMessage.deserialize(Message.NOT_A_PLAYER.getMessage()));
            return;
        }
        if (!((player.hasPermission(NoDamageParticlesPermission.TOGGLE_COMMAND.getPermission())) && player.hasPermission(NoDamageParticlesPermission.USE.getPermission()))) {
            player.sendMessage(miniMessage.deserialize(Message.NO_PERMISSION.getMessage(), Placeholder.parsed("prefix", Message.PREFIX.getMessage())));
            return;
        }

        // getConfigIsUse() 는 콘피그에 설정되어있는 boolean 값을 가져옴
        if(ConfigHandler.getInstance().getConfigIsUse()){
            // 콘피그에 use-remove-damage-particle 설정이 true 인 경우 >> false 로 콘피그정보를 변경
            ConfigHandler.getInstance().setConfigUseOption(false);
            player.sendMessage(miniMessage.deserialize(Message.TOGGLE_OFF.getMessage(), Placeholder.parsed("prefix", Message.PREFIX.getMessage())));
        }
        else {
            // 콘피그에 use-remove-damage-particle 설정이 true 가 아닐경우 >> true 로 콘피그정보를 변경
            ConfigHandler.getInstance().setConfigUseOption(true);
            player.sendMessage(miniMessage.deserialize(Message.TOGGLE_ON.getMessage(), Placeholder.parsed("prefix", Message.PREFIX.getMessage())));
        }
    }
}

