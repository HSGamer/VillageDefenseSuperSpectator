package me.hsgamer.villagedefensesuperspectator;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import plugily.projects.villagedefense.api.event.game.VillageGameJoinAttemptEvent;
import plugily.projects.villagedefense.api.event.game.VillageGameStartEvent;
import plugily.projects.villagedefense.user.User;

public class GameListener implements Listener {
    private final VillageDefenseSuperSpectator instance;

    public GameListener(VillageDefenseSuperSpectator instance) {
        this.instance = instance;
    }

    @EventHandler
    public void onJoin(VillageGameJoinAttemptEvent event) {
        User user = instance.getParentPlugin().getUserManager().getUser(event.getPlayer());
        if (instance.getManager().isSpectator(user.getUniqueId())) {
            user.setPermanentSpectator(true);
        }
    }

    @EventHandler
    public void onStart(VillageGameStartEvent event) {
        Bukkit.getScheduler().runTaskLater(instance,
                () -> event.getArena().getPlayers().stream()
                        .filter(player -> instance.getManager().isSpectator(player.getUniqueId()))
                        .map(player -> instance.getParentPlugin().getUserManager().getUser(player))
                        .forEach(user -> user.getPlayer().setHealth(0)), 10);
    }
}
