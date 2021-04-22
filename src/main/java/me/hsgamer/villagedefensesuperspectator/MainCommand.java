package me.hsgamer.villagedefensesuperspectator;

import me.hsgamer.hscore.bukkit.utils.MessageUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;

import java.util.UUID;

public class MainCommand extends Command {
    public static final Permission PERMISSION = new Permission("villagedefense.superspectator", PermissionDefault.OP);
    private final VillageDefenseSuperSpectator instance;

    public MainCommand(VillageDefenseSuperSpectator instance) {
        super("superspectator");
        this.instance = instance;

        setPermission(PERMISSION.getName());
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!testPermission(sender)) {
            return false;
        }
        if (!(sender instanceof Player)) {
            MessageUtils.sendMessage(sender, "&cOnly player can do that");
            return false;
        }
        UUID uuid = ((Player) sender).getUniqueId();
        instance.getManager().toggle(uuid);
        MessageUtils.sendMessage(sender, "&aSuper Spectator Mode: " + instance.getManager().isSpectator(uuid));
        return true;
    }
}
