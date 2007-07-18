package org.megatome.data;

import java.text.NumberFormat;

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

final class DataTypeFactory {
	private final static String CURRENCY_SYMBOL = NumberFormat.getCurrencyInstance().getCurrency()
    .getSymbol();
	
	private DataTypeFactory() {
		// Not public
	}
	
	public static IDataType getDataType(final String resourcePrefix, final ImageType imageType) {
		DataType dt = new DataType();
		dt.setImagePath(imageType.getImagePath());
        dt.setDataTypeName(DataTypeResourceLoader.getDataTypeName(resourcePrefix));
        dt.setDataValue(DataTypeResourceLoader.getDataValue(resourcePrefix));
        dt.setDescription(DataTypeResourceLoader.getDescription(resourcePrefix));
        dt.setProbableSource(DataTypeResourceLoader.getProbableSource(resourcePrefix));
        dt.setCommercialSource(DataTypeResourceLoader.getCommercialSource(resourcePrefix));
        dt.setNote(DataTypeResourceLoader.getNote(resourcePrefix));
        dt.setButtonText(DataTypeResourceLoader.getButtonText(resourcePrefix));
        dt.setCurrencySymbol(DataTypeFactory.CURRENCY_SYMBOL);
        
        return dt;
	}
}
