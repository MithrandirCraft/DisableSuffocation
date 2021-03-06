package es.mithrandircraft.disablesuffocation;

import org.bukkit.WorldBorder;
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

    //Block suffocation
    @EventHandler
    public void EntityDamageEvent(EntityDamageEvent ev)
    {
        if(ev.getEntityType() == EntityType.PLAYER && ev.getCause() == EntityDamageEvent.DamageCause.SUFFOCATION)
        {
            //Outside border exclusion
            double worldDiameter = ev.getEntity().getWorld().getWorldBorder().getSize()/2;
            if(ev.getEntity().getLocation().getBlockX() > worldDiameter || ev.getEntity().getLocation().getBlockZ() > worldDiameter ||
               ev.getEntity().getLocation().getBlockX() < -worldDiameter || ev.getEntity().getLocation().getBlockZ() < -worldDiameter)
                    ev.setCancelled(true);
        }
    }
}