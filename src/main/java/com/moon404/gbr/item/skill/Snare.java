package com.moon404.gbr.item.skill;

import com.moon404.gbr.entity.SnareEntity;
import com.moon404.gbr.init.GunBattleRoyaleEffects;
import com.moon404.gbr.init.GunBattleRoyaleEntities;

import net.minecraft.world.entity.player.Player;

public class Snare extends SkillItem
{
    public Snare(Properties properties)
    {
        super(properties);
    }

    @Override
    public boolean onToss(Player player)
    {
        if (player.hasEffect(GunBattleRoyaleEffects.SILENCE.get())) return false;
        SnareEntity snare = new SnareEntity(GunBattleRoyaleEntities.SNARE.get(), player.level);
        snare.setPos(player.getEyePosition());
        snare.shootFromRotation(player, player.getXRot(), player.getYRot(), 0, 1, 0);
        player.level.addFreshEntity(snare);
        return true;
    }
}