package com.techsenger.jeditermfx.core;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.EnumSet;
import java.util.Objects;

public class TextStyle {

    private static final EnumSet<Option> NO_OPTIONS = EnumSet.noneOf(Option.class);

    public static final TextStyle EMPTY = new TextStyle();

    private final TerminalColor myForeground;

    private final TerminalColor myBackground;

    private final EnumSet<Option> myOptions;

    public TextStyle() {
        this(null, null, NO_OPTIONS);
    }

    public TextStyle(@Nullable TerminalColor foreground, @Nullable TerminalColor background) {
        this(foreground, background, NO_OPTIONS);
    }

    public TextStyle(@Nullable TerminalColor foreground, @Nullable TerminalColor background,
                     @NotNull EnumSet<Option> options) {
        myForeground = foreground;
        myBackground = background;
        myOptions = options.clone();
    }

    @Nullable
    public TerminalColor getForeground() {
        return myForeground;
    }

    @Nullable
    public TerminalColor getBackground() {
        return myBackground;
    }

    public TextStyle createEmptyWithColors() {
        return new TextStyle(myForeground, myBackground);
    }

    public boolean hasOption(final Option option) {
        return myOptions.contains(option);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextStyle textStyle = (TextStyle) o;
        return Objects.equals(myForeground, textStyle.myForeground) &&
                Objects.equals(myBackground, textStyle.myBackground) &&
                myOptions.equals(textStyle.myOptions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(myForeground, myBackground, myOptions);
    }

    @NotNull
    public Builder toBuilder() {
        return new Builder(this);
    }

    public enum Option {

        BOLD,

        ITALIC,

        SLOW_BLINK,

        RAPID_BLINK,

        DIM,

        INVERSE,

        UNDERLINED,

        HIDDEN;

        private void set(@NotNull EnumSet<Option> options, boolean val) {
            if (val) {
                options.add(this);
            } else {
                options.remove(this);
            }
        }
    }

    public static class Builder {

        private TerminalColor myForeground;

        private TerminalColor myBackground;

        private final EnumSet<Option> myOptions;

        public Builder(@NotNull TextStyle textStyle) {
            myForeground = textStyle.myForeground;
            myBackground = textStyle.myBackground;
            myOptions = textStyle.myOptions.clone();
        }

        public Builder() {
            myForeground = null;
            myBackground = null;
            myOptions = EnumSet.noneOf(Option.class);
        }

        @NotNull
        public Builder setForeground(@Nullable TerminalColor foreground) {
            myForeground = foreground;
            return this;
        }

        @NotNull
        public Builder setBackground(@Nullable TerminalColor background) {
            myBackground = background;
            return this;
        }

        @NotNull
        public Builder setOption(@NotNull Option option, boolean val) {
            option.set(myOptions, val);
            return this;
        }

        @NotNull
        public TextStyle build() {
            return new TextStyle(myForeground, myBackground, myOptions);
        }
    }
}
