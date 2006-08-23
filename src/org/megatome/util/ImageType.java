package org.megatome.util;

/*
 * Copyright 2006 Megatome Technologies 
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

import org.megatome.data.DataTypeResourceLoader;

public enum ImageType {
    POLITICAL (DataTypeResourceLoader.getString("image.political")), //$NON-NLS-1$
    INFORMATION (DataTypeResourceLoader.getString("image.info")), //$NON-NLS-1$
    FINANCIAL (DataTypeResourceLoader.getString("image.financial")), //$NON-NLS-1$
    LICENSE (DataTypeResourceLoader.getString("image.license")), //$NON-NLS-1$
    LEGAL   (DataTypeResourceLoader.getString("image.legal")); //$NON-NLS-1$
	
	private String iconPath;
	
	private ImageType(final String iconPath) {
	    this.iconPath = iconPath;
	}
	
    public String getImagePath() {
        return this.iconPath;
    }
}
