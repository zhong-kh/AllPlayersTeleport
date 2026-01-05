package org.kunhezzz.allPlayersTeleport.Commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.command.brigadier.argument.ArgumentTypes;
import io.papermc.paper.command.brigadier.argument.resolvers.selector.PlayerSelectorArgumentResolver;
import org.bukkit.entity.Player;

public class PluginCmd {

    public static LiteralCommandNode<CommandSourceStack> buildCmd;

    public static void commandRegister() {

        // root node
        LiteralArgumentBuilder<CommandSourceStack> root = Commands.literal("ptp");

        root.then(
                Commands.argument("Player", ArgumentTypes.player())
                        .executes(
                        ctx -> {

                            // Test if command sender is a player
                            if (! (ctx.getSource().getExecutor() instanceof Player)) {
                                ctx.getSource().getSender().sendMessage("[AllPlayersTeleport] Only players could execute this command!");
                                return Command.SINGLE_SUCCESS;
                            }

                            final PlayerSelectorArgumentResolver targetResolver = ctx.getArgument("Player", PlayerSelectorArgumentResolver.class);
                            final Player destPlayer = targetResolver.resolve(ctx.getSource()).getFirst();
                            ctx.getSource().getExecutor().teleportAsync(destPlayer.getLocation());
                            ctx.getSource().getExecutor().sendMessage("[AllPlayersTeleport] You successfully teleported!");

                            return Command.SINGLE_SUCCESS;
                        })
        );

        buildCmd = root.build();

    }

}
