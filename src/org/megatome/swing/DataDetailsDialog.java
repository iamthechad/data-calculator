package org.megatome.swing;

/*
 * Copyright 2007 Megatome Technologies 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at 
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *      
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 */

import java.awt.BorderLayout;
import java.awt.Point;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import org.megatome.util.BrowserLauncher;
import org.megatome.util.ImageLoader;
import org.megatome.util.Messages;
import org.megatome.util.SimpleBrowserLauncher;

public final class DataDetailsDialog extends JDialog {
    private static final long serialVersionUID = -5882169225857039416L;
    private JLabel imageLabel = null;
    private JEditorPane detailsArea = null;
    private JScrollPane jsp = null;

    public DataDetailsDialog(final JFrame parent) {
        super(parent, Messages.getString("DataDetailsDialog.title"), false); //$NON-NLS-1$
        this.setSize(220, 345);
        Point parentLocation = parent.getLocation();
        int parentWidth = parent.getWidth();
        this.setLocation(parentLocation.x + parentWidth, parentLocation.y);
        initialize();
    }

    private void initialize() {
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.getContentPane().setLayout(new BorderLayout());

        this.imageLabel = new JLabel();
        this.getContentPane().add(this.imageLabel, BorderLayout.NORTH);

        this.detailsArea = new JEditorPane();
        this.detailsArea.setContentType("text/html"); //$NON-NLS-1$
        this.detailsArea.setEditable(false);
        this.detailsArea.addHyperlinkListener(new DetailsHyperlinkListener());
        this.jsp = new JScrollPane(this.detailsArea);
        
        this.getContentPane().setBackground(this.detailsArea.getBackground());
        
        this.getContentPane().add(this.jsp, BorderLayout.CENTER);
    }

    public void clearDetails() {
        this.detailsArea.setText(""); //$NON-NLS-1$
        this.imageLabel.setIcon(null);
    }

    public void updateDetails(final String imagePath, final String detailsString) {
        this.imageLabel.setIcon(ImageLoader.getImageIcon(imagePath));
        this.detailsArea.setText(detailsString);
        this.detailsArea.setCaretPosition(0);
    }

    public static class DetailsHyperlinkListener implements HyperlinkListener {
        public void hyperlinkUpdate(HyperlinkEvent evt) {
            if (evt.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                //try {
                    //BrowserLauncher.openURL(evt.getURL().toString());
                	SimpleBrowserLauncher.openURL(evt.getURL().toString());
                //} catch (IOException e) {
                //    e.printStackTrace();
                //}
            }
        }
    }
}