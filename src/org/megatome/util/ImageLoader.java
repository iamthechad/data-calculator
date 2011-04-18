package org.megatome.util;

/*
 * Copyright 2011 Megatome Technologies
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

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

public final class ImageLoader {
    private static final String imagePrefix = "/org/megatome/swing/images/"; //$NON-NLS-1$
    private static final Map<String, ImageIcon> imageMap = new HashMap<String, ImageIcon>();
    private static final ImageLoader instance = new ImageLoader();

    private ImageLoader() { // Non-public ctor
    }
    
    public static ImageIcon getImageIcon(final String imagePath) {
        String fullImagePath = imagePrefix + imagePath;
        ImageIcon icon = imageMap.get(fullImagePath);
        if (icon == null) {
            icon = new ImageIcon(instance.getClass().getResource(fullImagePath));
            imageMap.put(fullImagePath, icon);
        }
        
        return icon;
    }
    
    public static Image getImage(final String imagePath) {
        return getImageIcon(imagePath).getImage();
    }

}