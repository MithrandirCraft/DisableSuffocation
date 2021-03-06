package es.mithrandircraft.disablesuffocation;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class DisableSuffocation extends JavaPlugin implements Listener {

    double overworldWorldborderRadius;
    double netherWorldborderRadius;
    double endWorldborderRadius;

    @Override
    public void onEnable() {
        //Events:
        getServer().getPluginManager().registerEvents(this, this);

        overworldWorldborderRadius = Bukkit.getWorlds().get(0).getWorldBorder().getSize() / 2d;
        netherWorldborderRadius = Bukkit.getWorlds().get(1).getWorldBorder().getSize() / 2d;
        endWorldborderRadius = Bukkit.getWorlds().get(2).getWorldBorder().getSize() / 2d;
    }

    //Block suffocation
    @EventHandler
    public void EntityDamageEvent(EntityDamageEvent ev)
    {                                                                                                               //Outside border exclusion
        if(ev.getEntityType() == EntityType.PLAYER && ev.getCause() == EntityDamageEvent.DamageCause.SUFFOCATION && CheckWithinWorldborder(ev.getEntity().getWorld(), ev.getEntity().getLocation()))
        {
            ev.setCancelled(true);
        }
    }

    public Boolean CheckWithinWorldborder(World world, Location location)
    {
        switch(world.getEnvironment())
        {
            case NORMAL:
            {
                return CheckWithinRadius(overworldWorldborderRadius, location);
            }
            case NETHER:
            {
                return CheckWithinRadius(netherWorldborderRadius, location);
            }
            case THE_END:
            {
                return CheckWithinRadius(endWorldborderRadius, location);
            }
            default:
            {
                return true;
            }
        }
    }

    public Boolean CheckWithinRadius(double radius, Location location)
    {
        return !(location.getBlockX() > radius) && !(location.getBlockZ() > radius) && !(location.getBlockX() < -radius) && !(location.getBlockZ() < -radius);
    }
}