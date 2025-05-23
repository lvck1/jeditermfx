package com.techsenger.jeditermfx.ui.settings;

import org.jetbrains.annotations.NotNull;
import javafx.scene.text.Font;
import com.techsenger.jeditermfx.core.HyperlinkStyle;
import com.techsenger.jeditermfx.core.TerminalColor;
import com.techsenger.jeditermfx.core.TextStyle;
import com.techsenger.jeditermfx.core.emulator.ColorPalette;
import com.techsenger.jeditermfx.core.model.TerminalTypeAheadSettings;

public interface UserSettingsProvider {

    ColorPalette getTerminalColorPalette();

    Font getTerminalFont();

    float getTerminalFontSize();

    /**
     * @return vertical scaling factor
     */
    default float getLineSpacing() {
        return 1.0f;
    }

    default boolean shouldDisableLineSpacingForAlternateScreenBuffer() {
        return false;
    }

    default boolean shouldFillCharacterBackgroundIncludingLineSpacing() {
        return true;
    }

    default @NotNull TerminalColor getDefaultForeground() {
        return TerminalColor.BLACK;
    }

    default @NotNull TerminalColor getDefaultBackground() {
        return TerminalColor.WHITE;
    }

    @NotNull
    TextStyle getSelectionColor();

    @NotNull
    TextStyle getFoundPatternColor();

    TextStyle getHyperlinkColor();

    HyperlinkStyle.HighlightMode getHyperlinkHighlightingMode();

    default boolean enableTextBlinking() {
        return false;
    }

    default int slowTextBlinkMs() {
        return 1000;
    }

    default int rapidTextBlinkMs() {
        return 500;
    }

    boolean useInverseSelectionColor();

    boolean copyOnSelect();

    boolean pasteOnMiddleMouseClick();

    boolean emulateX11CopyPaste();

    boolean useAntialiasing();

    int maxRefreshRate();

    boolean audibleBell();

    boolean enableMouseReporting();

    int caretBlinkingMs();

    boolean scrollToBottomOnTyping();

    boolean DECCompatibilityMode();

    boolean forceActionOnMouseReporting();

    int getBufferMaxLinesCount();

    boolean altSendsEscape();

    boolean ambiguousCharsAreDoubleWidth();

    @NotNull
    TerminalTypeAheadSettings getTypeAheadSettings();

    boolean sendArrowKeysInAlternativeMode();
}
