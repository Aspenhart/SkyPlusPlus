package com.anotherpillow.skyplusplus.config;

import dev.isxander.yacl.api.ConfigCategory;
import dev.isxander.yacl.api.Option;
import dev.isxander.yacl.api.OptionGroup;
import dev.isxander.yacl.api.YetAnotherConfigLib;
import dev.isxander.yacl.config.ConfigEntry;
import dev.isxander.yacl.gui.controllers.TickBoxController;
import dev.isxander.yacl.config.ConfigInstance;
import dev.isxander.yacl.config.GsonConfigInstance;
import dev.isxander.yacl.gui.controllers.slider.IntegerSliderController;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;

public class SkyPlusPlusConfig {
    public static GsonConfigInstance<SkyPlusPlusConfig> configInstance = new GsonConfigInstance<>(SkyPlusPlusConfig.class, FabricLoader.getInstance().getConfigDir().resolve("skyplusplus.json"));

    @ConfigEntry public int traderX = 16;
    @ConfigEntry public int traderY = 16;
    @ConfigEntry public boolean enableTraderFinder = true;

    @ConfigEntry public boolean hideVisitingMessages = false;
    @ConfigEntry public boolean hideSkyblockMessages = false;
    @ConfigEntry public boolean hideUnscramblingMessages = false;
    @ConfigEntry public boolean hideAFKMessages = false;
    @ConfigEntry public boolean hideNewUserMessages = false;
    @ConfigEntry public boolean hideDeathsMessages = false;

    public static Screen getConfigScreen(Screen parentScreen) {
        return YetAnotherConfigLib.create(configInstance, ((defaults, config, builder) -> builder
                .title(Text.literal("Sky++ Config"))
                .category(ConfigCategory.createBuilder()
                        .name(Text.literal("Trader Notifications"))
                        .option(Option.createBuilder(boolean.class)
                                .name(Text.literal("Enable"))
                                .tooltip(Text.literal("Enable Wandering Trader Notifications"))
                                .binding(defaults.enableTraderFinder, () -> config.enableTraderFinder, v -> config.enableTraderFinder = v)
                                .controller(TickBoxController::new)
                                .build())
                        .option(Option.createBuilder(int.class)
                                .name(Text.literal("Notification X"))
                                .tooltip(Text.literal("X"))
                                .binding(defaults.traderX, () -> config.traderX, v -> config.traderX = v)
                                .controller(opt -> new IntegerSliderController(opt, 0, 4000, 16))
                                .build())
                        .option(Option.createBuilder(int.class)
                                .name(Text.literal("Notification Y"))
                                .tooltip(Text.literal("Y"))
                                .binding(defaults.traderY, () -> config.traderY, v -> config.traderY = v)
                                .controller(opt -> new IntegerSliderController(opt, 0, 4000, 16))
                                .build())
                        .build())
                .category(ConfigCategory.createBuilder()
                        .name(Text.literal("Filter Chat"))
                        .option(Option.createBuilder(boolean.class)
                                .name(Text.literal("Hide Visiting Messages"))
                                .tooltip(Text.literal("Hide messages when you visit other islands"))
                                .binding(defaults.hideVisitingMessages, () -> config.hideVisitingMessages, v -> config.hideVisitingMessages = v)
                                .controller(TickBoxController::new)
                                .build())
                        .option(Option.createBuilder(boolean.class)
                                .name(Text.literal("Hide Skyblock Messages"))
                                .tooltip(Text.literal("Hide [Skyblock] messages"))
                                .binding(defaults.hideSkyblockMessages, () -> config.hideSkyblockMessages, v -> config.hideSkyblockMessages = v)
                                .controller(TickBoxController::new)
                                .build())
                        .option(Option.createBuilder(boolean.class)
                                .name(Text.literal("Hide Unscrambling Messages"))
                                .tooltip(Text.literal("Hide Hover for word/letter unscrambling messages"))
                                .binding(defaults.hideUnscramblingMessages, () -> config.hideUnscramblingMessages, v -> config.hideUnscramblingMessages = v)
                                .controller(TickBoxController::new)
                                .build())
                        .option(Option.createBuilder(boolean.class)
                                .name(Text.literal("Hide AFK Messages"))
                                .tooltip(Text.literal("Hide staff going AFK messages"))
                                .binding(defaults.hideAFKMessages, () -> config.hideAFKMessages, v -> config.hideAFKMessages = v)
                                .controller(TickBoxController::new)
                                .build())
                        .option(Option.createBuilder(boolean.class)
                                .name(Text.literal("Hide New User Messages"))
                                .tooltip(Text.literal("Hide new user messages"))
                                .binding(defaults.hideNewUserMessages, () -> config.hideNewUserMessages, v -> config.hideNewUserMessages = v)
                                .controller(TickBoxController::new)
                                .build())
                        .option(Option.createBuilder(boolean.class)
                                .name(Text.literal("Hide Death Count Messages"))
                                .tooltip(Text.literal("Hide daily death count messages"))
                                .binding(defaults.hideDeathsMessages, () -> config.hideDeathsMessages, v -> config.hideDeathsMessages = v)
                                .controller(TickBoxController::new)
                                .build())
                        .build())
        )).generateScreen(parentScreen);
    }
}
