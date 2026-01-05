package org.kunhezzz.allPlayersTeleport;

import io.papermc.paper.plugin.lifecycle.event.LifecycleEvent;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.plugin.java.JavaPlugin;
import org.kunhezzz.allPlayersTeleport.Commands.PluginCmd;

public final class AllPlayersTeleport extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        // Register commands
        this.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS,
                cmds -> {
                    cmds.registrar().register(PluginCmd.buildCmd);
                }
        );

        this.getLogger().info("[AllPlayersTeleport] Plugin loaded successfully");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        this.getLogger().info("[AllPlayersTeleport] Plugin quited successfully");

    }
}
