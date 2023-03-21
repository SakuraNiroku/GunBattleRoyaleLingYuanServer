package com.moon404.gbr.item.skill;

import com.moon404.gbr.init.GunBattleRoyaleEffects;
import com.moon404.gbr.struct.ClassType;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class Scan extends SkillItem
{
    public Scan(Properties properties)
    {
        super(properties, ClassType.SCOUT);
    }

    @Override
    public boolean onToss(Player player)
    {
        if (ClassType.getClass(player) != this.classType) return false;
        if (player.hasEffect(GunBattleRoyaleEffects.SILENCE.get())) return false;
        double mindis = Float.MAX_VALUE;
        Player nearest = null;
        for (Player target : player.level.players())
        {
            if (!target.isSpectator() && target.getTeam() != player.getTeam())
            {
                double dis = target.distanceTo(player);
                if (dis < mindis)
                {
                    mindis = dis;
                    nearest = target;
                }
            }
        }
        if (nearest == null)
        {
            Component component = Component.literal("未扫描到任何敌人");
            player.displayClientMessage(component, true);
        }
        else
        {
            String message = "最近的敌人在 " + (int)mindis + " 格外，坐标: " + (int)nearest.getX() + ", " + (int)nearest.getZ();
            Component component = Component.literal(message);
            player.displayClientMessage(component, true);
        }
        return true;
    }
}
