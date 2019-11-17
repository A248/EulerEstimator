/* 
 * EulerEstimator
 * Copyright © 2019 Anand Beh <https://www.arim.space>
 * 
 * EulerEstimator is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * EulerEstimator is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with EulerEstimator. If not, see <https://www.gnu.org/licenses/>
 * and navigate to version 3 of the GNU General Public License.
 */
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
