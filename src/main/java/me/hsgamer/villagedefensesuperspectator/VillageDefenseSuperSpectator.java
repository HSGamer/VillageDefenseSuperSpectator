package me.hsgamer.villagedefensesuperspectator;

import me.hsgamer.hscore.bukkit.baseplugin.BasePlugin;
import me.hsgamer.hscore.bukkit.utils.MessageUtils;
import org.bukkit.plugin.java.JavaPlugin;
import plugily.projects.villagedefense.Main;

public final class VillageDefenseSuperSpectator extends BasePlugin {
    private final SpectatorManager manager = new SpectatorManager();
    private Main parentPlugin;

    @Override
    public void enable() {
        parentPlugin = JavaPlugin.getPlugin(Main.class);

        MessageUtils.setPrefix("");
        registerListener(new GameListener(this));
        registerCommand(new MainCommand(this));
    }

    @Override
    public void disable() {
        manager.clear();
    }

    public Main getParentPlugin() {
        return parentPlugin;
    }

    public SpectatorManager getManager() {
        return manager;
    }
}
