package com.veronica.nodamageparticles.command.sub;

import com.veronica.nodamageparticles.NoDamageParticles;
import com.veronica.nodamageparticles.command.SubCommand;
import com.veronica.nodamageparticles.permission.NoDamageParticlesPermission;
import com.veronica.nodamageparticles.config.Message;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.CommandSender;

public class HelpCommand extends SubCommand {
    MiniMessage miniMessage = NoDamageParticles.getMiniMessage();

    public HelpCommand() {
        super(NoDamageParticlesPermission.USE.getPermission());
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender.hasPermission(NoDamageParticlesPermission.USE.getPermission())) {
            sender.sendMessage(miniMessage.deserialize((
                    Message.PREFIX.getMessage() + "\n" +
                    Message.HELP_MAIN.getMessage() + "\n" +
                    Message.HELP_RELOAD.getMessage() + "\n" +
                    Message.HELP_TOGGLE.getMessage())));
            return;
        }
        sender.sendMessage(miniMessage.deserialize(Message.NO_PERMISSION.getMessage()));
    }
}
