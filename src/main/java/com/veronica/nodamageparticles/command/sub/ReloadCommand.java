package com.veronica.nodamageparticles.command.sub;

import com.veronica.nodamageparticles.NoDamageParticles;
import com.veronica.nodamageparticles.command.SubCommand;
import com.veronica.nodamageparticles.permission.NoDamageParticlesPermission;
import com.veronica.nodamageparticles.config.ConfigHandler;
import com.veronica.nodamageparticles.config.Message;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReloadCommand extends SubCommand {
    MiniMessage miniMessage = NoDamageParticles.getMiniMessage();
    public ReloadCommand() {
        super(NoDamageParticlesPermission.RELOAD_COMMAND.getPermission());
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player) || sender.hasPermission(NoDamageParticlesPermission.RELOAD_COMMAND.getPermission())) {

            NoDamageParticles.getInstance().reloadConfig();
            ConfigHandler.getInstance().reloadConfigOptions();

            sender.sendMessage(miniMessage.deserialize(Message.CONFIG_RELOAD.getMessage(), Placeholder.parsed("prefix", Message.PREFIX.getMessage())));
            return;
        }
        sender.sendMessage(miniMessage.deserialize(Message.NO_PERMISSION.getMessage()));
    }
}
