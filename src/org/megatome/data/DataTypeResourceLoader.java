package org.megatome.data;

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

import java.util.MissingResourceException;
import java.util.ResourceBundle;

final class DataTypeResourceLoader {

    private static final String BUNDLE_NAME = "org.megatome.data.datatype"; //$NON-NLS-1$

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
            .getBundle(BUNDLE_NAME);

    /**
     * 
     */
    private DataTypeResourceLoader() { // Non-public ctor
    }

    /**
     * @param key
     * @return
     */
    public static String getString(final String key) {
        try {
            return RESOURCE_BUNDLE.getString(key);
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }
    }

	public static String getDataTypeName(String resourcePrefix) {
		return getString(resourcePrefix + DataTypeSupport.DATA_TYPE_NAME);
	}

	public static String getDataValue(String resourcePrefix) {
		return getString(resourcePrefix + DataTypeSupport.DATA_VALUE);
	}

	public static String getDescription(String resourcePrefix) {
		return getString(resourcePrefix + DataTypeSupport.DESCRIPTION);
	}

	public static String getProbableSource(String resourcePrefix) {
		return getString(resourcePrefix + DataTypeSupport.PROBABLE_SOURCE);
	}

	public static String getCommercialSource(String resourcePrefix) {
		return getString(resourcePrefix + DataTypeSupport.COMMERCIAL_SOURCE);
	}

	public static String getNote(String resourcePrefix) {
		return getString(resourcePrefix + DataTypeSupport.NOTE);
	}

	public static String getButtonText(String resourcePrefix) {
		return getString(resourcePrefix + DataTypeSupport.BUTTON_TEXT);
	}
}