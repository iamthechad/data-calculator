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

public final class DataTypeSupport {
    private DataTypeSupport() {
        // Empty
    }
    
    static final String DATA_TYPE_NAME = DataTypeResourceLoader
            .getString("DataType.0"); //$NON-NLS-1$

    static final String DATA_VALUE = DataTypeResourceLoader.getString("DataType.1"); //$NON-NLS-1$

    static final String DESCRIPTION = DataTypeResourceLoader.getString("DataType.2"); //$NON-NLS-1$

    static final String PROBABLE_SOURCE = DataTypeResourceLoader
            .getString("DataType.3"); //$NON-NLS-1$

    static final String COMMERCIAL_SOURCE = DataTypeResourceLoader
            .getString("DataType.4"); //$NON-NLS-1$

    static final String NOTE = DataTypeResourceLoader.getString("DataType.5"); //$NON-NLS-1$
    static final String BUTTON_TEXT = DataTypeResourceLoader.getString("DataType.6"); //$NON-NLS-1$
    
    static final String REQUEST_HEADER = DataTypeResourceLoader.getString("DataType.requestHeader"); //$NON-NLS-1$
    static final String DESCRIPTION_HEADER =DataTypeResourceLoader.getString("DataType.descriptionHeader"); //$NON-NLS-1$
    static final String PROBABLE_SOURCE_HEADER = DataTypeResourceLoader.getString("DataType.probableSourceHeader"); //$NON-NLS-1$
    static final String COMMERCIAL_SOURCE_HEADER = DataTypeResourceLoader.getString("DataType.commercialSourceHeader"); //$NON-NLS-1$
    static final String NOTE_HEADER = DataTypeResourceLoader.getString("DataType.noteHeader"); //$NON-NLS-1$
}
