package com.techsenger.jeditermfx.core.model;

import com.techsenger.jeditermfx.core.TerminalColor;
import com.techsenger.jeditermfx.core.TextStyle;
import java.util.concurrent.TimeUnit;

public final class TerminalTypeAheadSettings {

    public static final TerminalTypeAheadSettings DEFAULT = new TerminalTypeAheadSettings(true,
            TimeUnit.MILLISECONDS.toNanos(100), new TextStyle(new TerminalColor(8), null));

    private final boolean myEnabled;

    private final long myLatencyThreshold;

    private final TextStyle myTypeAheadStyle;

    public TerminalTypeAheadSettings(boolean enabled, long latencyThreshold, TextStyle typeAheadColor) {
        myEnabled = enabled;
        myLatencyThreshold = latencyThreshold;
        myTypeAheadStyle = typeAheadColor;
    }

    public boolean isEnabled() {
        return myEnabled;
    }

    public long getLatencyThreshold() {
        return myLatencyThreshold;
    }

    public TextStyle getTypeAheadStyle() {
        return myTypeAheadStyle;
    }
}
