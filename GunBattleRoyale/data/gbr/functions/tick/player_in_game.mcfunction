execute as @s[tag=jumping] if score @s height matches 0..100 at @s run function gbr:game/jumping
execute if score @s death matches 1..2147483647 run function gbr:game/death
