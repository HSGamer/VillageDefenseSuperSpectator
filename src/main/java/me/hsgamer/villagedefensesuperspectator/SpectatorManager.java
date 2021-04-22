package me.hsgamer.villagedefensesuperspectator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SpectatorManager {
    private final List<UUID> list = new ArrayList<>();

    public void toggle(UUID uuid) {
        if (list.contains(uuid)) {
            list.remove(uuid);
        } else {
            list.add(uuid);
        }
    }

    public boolean isSpectator(UUID uuid) {
        return list.contains(uuid);
    }

    public void clear() {
        list.clear();
    }
}
