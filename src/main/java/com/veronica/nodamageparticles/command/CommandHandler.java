package com.veronica.nodamageparticles.command;

import com.veronica.nodamageparticles.NoDamageParticles;
import com.veronica.nodamageparticles.config.Message;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandHandler implements CommandExecutor, TabExecutor {


    private final Map<String, SubCommand> subcommandList = new HashMap<>();
    private final MiniMessage miniMessage = NoDamageParticles.getMiniMessage();

    public void registerSubCommand(String commandName, SubCommand subCommand) {
        subcommandList.put(commandName.toLowerCase(), subCommand);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (args.length == 0) {
            executeCommand(sender, "toggle", args);
            return true;
        }
        executeCommand(sender, args[0], args);
        return true;
    }

    private void executeCommand(CommandSender sender, String command, String[] args) {
        SubCommand subCommand = subcommandList.get(command.toLowerCase());
        if (subCommand != null && sender.hasPermission(subCommand.getPermission())) {
            subCommand.execute(sender, args.length > 1 ? args : new String[0]);
        } else {
            sender.sendMessage(miniMessage.deserialize(Message.UNKNOWN_COMMAND.getMessage()));
        }
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, String[] args) {
        if (args.length == 1) {
            List<String> tabCompleteCommands = new ArrayList<>();
            for (String cmd : subcommandList.keySet()) {
                if (sender.hasPermission(subcommandList.get(cmd).getPermission())) {
                    tabCompleteCommands.add(cmd);
                }
            }
            return tabCompleteCommands;
        }
        return null;
    }
}
