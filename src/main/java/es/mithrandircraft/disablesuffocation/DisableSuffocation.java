package es.mithrandircraft.disablesuffocation;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class DisableSuffocation extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        //Events:
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void EntityDamageEvent(EntityDamageEvent ev)
    {
        if(ev.getEntityType() == EntityType.PLAYER && ev.getCause() == EntityDamageEvent.DamageCause.SUFFOCATION)
        {
            ev.setCancelled(true);
        }
    }
}