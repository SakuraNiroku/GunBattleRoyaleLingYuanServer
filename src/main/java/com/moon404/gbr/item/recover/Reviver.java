package com.moon404.gbr.item.recover;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.ServerFunctionManager;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class Reviver extends RecoverItem
{
    public Reviver(Item.Properties properties)
    {
        super(properties);
    }

    public int getUseDuration(ItemStack stack)
    {
        return 200;
    }

    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity)
    {
        if (!level.isClientSide && entity instanceof ServerPlayer player)
        {
            MinecraftServer server = player.getServer();
            ServerFunctionManager manager = server.getFunctions();
            CommandDispatcher<CommandSourceStack> dispatcher = manager.getDispatcher();
            CommandSourceStack sourceStack = new CommandSourceStack(CommandSource.NULL, player.getPosition(1), player.getRotationVector(), (ServerLevel)player.level, 2, null, null, server, null);
            sourceStack = sourceStack.withEntity(player);
            try {
                dispatcher.execute("function gbr:game/revive_user", sourceStack);
            } catch (CommandSyntaxException e) {
                e.printStackTrace();
            }
        }
        stack.setDamageValue(0);
        return this.isEdible() ? entity.eat(level, stack) : stack;
    }
}
