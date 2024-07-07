damage = 0.25
distance = 6.4
def dmg1(stage):
    return damage * (4 + stage)
def dmg2(stage):
    return damage * (11 - stage)
for i in range(5, 10):
    s = '{"distance": %.1f, "damage": %.2f},' % (distance * i, dmg2(i - 4))
    print(s)
print('{"distance": "infinite", "damage": %.2f},' % (dmg2(6)))
