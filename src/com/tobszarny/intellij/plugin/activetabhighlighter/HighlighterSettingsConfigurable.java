package com.tobszarny.intellij.plugin.activetabhighlighter;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.colors.EditorColorsScheme;
import com.intellij.openapi.options.SearchableConfigurable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class HighlighterSettingsConfigurable implements SearchableConfigurable {

    public static final String PREFERENCE_HIGHLIGHTER_SETTINGS_CONFIGURABLE = "preference.HighlighterSettingsConfigurable";
    public static final String ACTIVE_TAB_HIGHLIGHTER_PLUGIN_DISPLAY_NAME = "Active Tab Highlighter Plugin";
    private static final Logger LOGGER = Logger.getInstance(HighlighterSettingsConfigurable.class);
    private final HighlighterSettingsConfig config;
    private final EditorColorsScheme editorColorsScheme;
    private ColorAndFontDescriptionPanel colorAndFontDescriptionPanel;

    public HighlighterSettingsConfigurable() {
        this.config = HighlighterSettingsConfig.getInstance();
        this.editorColorsScheme = EditorColorsManager.getInstance().getGlobalScheme();
    }

    @NotNull
    @Override
    public String getId() {
        return PREFERENCE_HIGHLIGHTER_SETTINGS_CONFIGURABLE;
    }

    @Nls
    @Override
    public String getDisplayName() {
        return ACTIVE_TAB_HIGHLIGHTER_PLUGIN_DISPLAY_NAME;
    }

    @Nullable
    @Override
    public String getHelpTopic() {
        return PREFERENCE_HIGHLIGHTER_SETTINGS_CONFIGURABLE;
    }

    @Nullable
    @Override
    public Runnable enableSearch(String s) {
        return null;
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        LOGGER.info("***** createComponent() ");
//        TextAttributes attributes = new TextAttributes();
//        attributes.setBackgroundColor(config.getBackgroundColor());
//        attributes.setFontType(Font.PLAIN);
//        attributes.setEffectType(EffectType.BOXED);
        colorAndFontDescriptionPanel = new ColorAndFontDescriptionPanel();
        return colorAndFontDescriptionPanel.getPanel();
    }

    @Override
    public boolean isModified() {
        LOGGER.info("***** isModified() ");
        HighlightedTabTextAttributesDescription attributesDescription = config.getAttributesDescription();

//        colorAndFontDescriptionPanel.is
//        return settingsGUI.isModified();
        return true;
    }

    @Override
    public void apply() {
        LOGGER.info("***** apply() ");
//        settingsGUI.apply();
        config.storeBackgroundColorInformation(colorAndFontDescriptionPanel.isBackgroundColorEnabled(), colorAndFontDescriptionPanel.getSelectedBackgroundColor());
        HighlightedTabTextAttributesDescription attributesDescription = config.getAttributesDescription();
        colorAndFontDescriptionPanel.apply(attributesDescription, editorColorsScheme);
//        config.storeBackgroundColor(colorAndFontDescriptionPanel.getSelectedBackgroundColor());
    }

    @Override
    public void reset() {
        colorAndFontDescriptionPanel.reset(config.getAttributesDescription());
    }

    @Override
    public void disposeUIResources() {
        LOGGER.info("***** disposeUIResources() ");
        colorAndFontDescriptionPanel = null;
    }

}
