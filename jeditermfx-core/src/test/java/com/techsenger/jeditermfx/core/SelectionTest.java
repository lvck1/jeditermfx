package com.techsenger.jeditermfx.core;

import com.techsenger.jeditermfx.core.RequestOrigin;
import com.techsenger.jeditermfx.core.compatibility.Point;
import com.techsenger.jeditermfx.core.util.TermSize;
import com.techsenger.jeditermfx.core.model.JediTerminal;
import com.techsenger.jeditermfx.core.model.SelectionUtil;
import com.techsenger.jeditermfx.core.model.StyleState;
import com.techsenger.jeditermfx.core.model.TerminalTextBuffer;
import com.techsenger.jeditermfx.core.util.BackBufferDisplay;
import com.techsenger.jeditermfx.core.util.TestSession;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * @author traff
 */
public class SelectionTest {

    @Test
    public void testMultilineSelection() {
        StyleState state = new StyleState();
        TerminalTextBuffer terminalTextBuffer = new TerminalTextBuffer(15, 5, state);
        JediTerminal terminal = new JediTerminal(new BackBufferDisplay(terminalTextBuffer), terminalTextBuffer, state);
        terminal.writeString("  1. line ");
        terminal.newLine();
        terminal.carriageReturn();
        terminal.writeString("  2. line2");
        terminal.newLine();
        terminal.carriageReturn();
        assertEquals("line \n" +
                "  2. line", SelectionUtil.getSelectedText(new Point(5, 0), new Point(9, 1), terminalTextBuffer));
    }

    @Test
    public void testSingleLineSelection() {
        StyleState state = new StyleState();
        TerminalTextBuffer terminalTextBuffer = new TerminalTextBuffer(15, 5, state);
        JediTerminal writer = new JediTerminal(new BackBufferDisplay(terminalTextBuffer), terminalTextBuffer, state);
        writer.writeString("   line   ");
        writer.newLine();
        writer.carriageReturn();
        assertEquals(" line  ", SelectionUtil.getSelectedText(new Point(2, 0), new Point(9, 0), terminalTextBuffer));
    }

    @Test
    public void testSelectionOutOfTheScreen() {
        TestSession session = new TestSession(20, 5);
        TerminalTextBuffer textBuffer = session.getTerminalTextBuffer();
        JediTerminal terminal = session.getTerminal();
        terminal.writeString("text to select ");
        terminal.crnl();
        terminal.writeString("and copy");
        terminal.crnl();
        session.assertCursorPosition(1, 3);
        terminal.resize(new TermSize(8, 10), RequestOrigin.User);
        //text to
        //select
        //and copy
        assertEquals("text to select \nand copy",
                SelectionUtil.getSelectedText(new Point(0, 0), new Point(8, 2), textBuffer));
        session.assertCursorPosition(1, 4);
    }

    @Test
    public void testSelectedTheLastLine() {
        StyleState state = new StyleState();
        TerminalTextBuffer terminalTextBuffer = new TerminalTextBuffer(15, 5, state);
        JediTerminal writer = new JediTerminal(new BackBufferDisplay(terminalTextBuffer), terminalTextBuffer, state);
        writer.writeString("first line");
        writer.newLine();
        writer.carriageReturn();
        writer.writeString("last line");
        assertEquals("last line",
                SelectionUtil.getSelectedText(new Point(0, 1), new Point(9, 1), terminalTextBuffer));
    }

    @Test
    public void testMultilineSelectionWithLastLine() {
        StyleState state = new StyleState();
        TerminalTextBuffer terminalTextBuffer = new TerminalTextBuffer(15, 5, state);
        JediTerminal writer = new JediTerminal(new BackBufferDisplay(terminalTextBuffer), terminalTextBuffer, state);
        writer.writeString("first line");
        writer.newLine();
        writer.carriageReturn();
        writer.writeString("second line");
        writer.newLine();
        writer.carriageReturn();
        writer.writeString("last line");
        assertEquals("second line\nlast line",
                SelectionUtil.getSelectedText(new Point(0, 1), new Point(9, 2), terminalTextBuffer));
    }

    @Test
    public void testSelectionFromScrollBuffer() {
        StyleState state = new StyleState();
        TerminalTextBuffer terminalTextBuffer = new TerminalTextBuffer(5, 3, state);
        JediTerminal writer = new JediTerminal(new BackBufferDisplay(terminalTextBuffer), terminalTextBuffer, state);
        writer.writeString("12");
        writer.newLine();
        writer.carriageReturn();
        writer.writeString("34");
        writer.newLine();
        writer.carriageReturn();
        writer.writeString("56");
        writer.newLine();
        writer.carriageReturn();
        writer.writeString("78");
        writer.newLine();
        writer.carriageReturn();
        writer.writeString("90");
        assertEquals("12\n" +
                "34\n" +
                "56\n" +
                "78", SelectionUtil.getSelectedText(new Point(0, -2), new Point(2, 1), terminalTextBuffer));
    }

    @Test
    public void testDoubleWidth() {
        StyleState state = new StyleState();
        TerminalTextBuffer terminalTextBuffer = new TerminalTextBuffer(10, 2, state);
        JediTerminal terminal = new JediTerminal(new BackBufferDisplay(terminalTextBuffer), terminalTextBuffer, state);
        terminal.writeString("生活習慣病");
        assertEquals("生活習慣病", SelectionUtil.getSelectedText(new Point(0, 0), new Point(10, 0), terminalTextBuffer));
    }
}
