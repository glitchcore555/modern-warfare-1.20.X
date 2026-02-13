package net.glitchcore.modernwarfare.item;

import net.glitchcore.modernwarfare.ModernWarfareMod;
import net.glitchcore.modernwarfare.item.drinks.Alcohol;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ModernWarfareMod.MOD_ID);

    public static final RegistryObject<Item>BULLET = ITEMS.register("bullet",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item>ALCOHOL = ITEMS.register("alcohol",
            ()-> new Alcohol(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item>MOLOTOV = ITEMS.register("molotov",
            ()-> new MolotovCocktail(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item>BOMB_SPAWN_EGG = ITEMS.register("bombspawnegg",
            ()-> new MolotovCocktail(new Item.Properties().stacksTo(1)));

    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}
