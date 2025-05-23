package cn.chengzhiya.mhdfoptimize.util.config;

import cn.chengzhiya.mhdfoptimize.Main;
import cn.chengzhiya.mhdfoptimize.text.TextComponent;
import cn.chengzhiya.mhdfoptimize.text.TextComponentBuilder;
import cn.chengzhiya.mhdfoptimize.util.message.ColorUtil;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public final class LangUtil {
    private static final File file = new File(Main.instance.getDataFolder(), "lang.yml");
    private static YamlConfiguration data;

    /**
     * 保存初始语言文件
     */
    public static void saveDefaultLang() {
        FileUtil.saveResource("lang.yml", "lang_zh.yml", false);
    }

    /**
     * 加载语言文件
     */
    public static void reloadLang() {
        data = YamlConfiguration.loadConfiguration(file);
    }

    /**
     * 根据指定key获取语言文件对应文本
     */
    public static @NotNull String getString(String key) {
        if (data == null) {
            reloadLang();
        }
        String value = data.getString(key);
        return value != null ? value : "";
    }

    /**
     * 根据指定key获取语言文件对应文本列表
     */
    public static @NotNull List<String> getStringList(String key) {
        if (data == null) {
            reloadLang();
        }
        return data.getStringList(key);
    }

    /**
     * 根据指定key获取语言文件对应文本并处理颜色
     */
    public static @NotNull TextComponent i18n(String key) {
        if (data == null) {
            reloadLang();
        }
        return ColorUtil.color(getString(key));
    }

    /**
     * 根据指定key获取语言文件对应文本列表并处理颜色
     */
    public static @NotNull List<TextComponent> i18nList(String key) {
        if (data == null) {
            reloadLang();
        }
        return getStringList(key).stream()
                .map(ColorUtil::color)
                .toList();
    }

    /**
     * 获取指定key下的keys
     */
    public static @NotNull Set<String> getKeys(String key) {
        if (data == null) {
            reloadLang();
        }
        return Objects.requireNonNull(data.getConfigurationSection(key)).getKeys(false);
    }

    /**
     * 获取所有命令帮助
     *
     * @return 命令帮助文本
     */
    public static @NotNull TextComponent getHelpList(String commandKey) {
        TextComponentBuilder textComponentBuilder = new TextComponentBuilder();

        List<String> keys = new ArrayList<>(LangUtil.getKeys("commands." + commandKey + ".sub-commands"));
        for (String key : keys) {
            textComponentBuilder.append(
                    LangUtil.i18n("commands." + commandKey + ".sub-commands.help.commandFormat")
                            .replace("{usage}",
                                    LangUtil.i18n("commands." + commandKey + ".sub-commands." + key + ".usage")
                            )
                            .replace("{description}",
                                    LangUtil.i18n("commands." + commandKey + ".sub-commands." + key + ".description")
                            )
            );
            if (!key.equals(keys.get(keys.size() - 1))) {
                textComponentBuilder.appendNewline();
            }
        }

        return textComponentBuilder.build();
    }
}
