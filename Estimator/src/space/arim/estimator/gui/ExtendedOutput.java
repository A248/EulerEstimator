package space.arim.estimator.gui;

import java.io.OutputStream;

import javax.swing.JTextArea;

public class ExtendedOutput extends OutputStream {
    private final JTextArea textArea;

    public ExtendedOutput(JTextArea textArea) {
        this.textArea = textArea;
    }

    public void write( int b ) {
        textArea.append( String.valueOf( ( char )b ) );
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }

    public void write(char[] cbuf, int off, int len) {
        textArea.append(new String(cbuf, off, len));
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }
}
