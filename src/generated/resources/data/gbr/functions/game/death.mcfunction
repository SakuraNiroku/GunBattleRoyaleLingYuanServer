# 生存模式玩家death!=0时触发
scoreboard players set @s death -1
scoreboard players remove @s rank 1
scoreboard players set @a[scores={rank=-2147483648..-1}] rank 0
scoreboard players add @s stat_death 1
gamemode spectator @s
execute at @p[gamemode=adventure, scores={death=0}] run function gbr:game/tp_killer
execute as @a[scores={kills=1..2147483647}] run function gbr:game/kill