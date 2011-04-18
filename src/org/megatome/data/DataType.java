package org.megatome.data;

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


final class DataType implements IDataType {
    private String imagePath;
    private String dataTypeName;
    private String dataValue;
    private String description;
    private String probableSource;
    private String commercialSource;
    private String note;
    private String currencySymbol;
    private String buttonText;

    public String toHTMLString() {
        StringBuffer sb = new StringBuffer();

        sb.append("<b>"); //$NON-NLS-1$
        sb.append(this.dataTypeName);
        sb.append("</b><br>"); //$NON-NLS-1$
        sb.append(DataTypeSupport.REQUEST_HEADER);
        sb.append("<b>"); //$NON-NLS-1$
        sb.append(this.currencySymbol);
        sb.append(this.dataValue);
        sb.append("</b>"); //$NON-NLS-1$
        sb.append("<br><br>"); //$NON-NLS-1$

        if (!this.description.equalsIgnoreCase(DataTypeResourceLoader
                .getString("DataType.emptyValue"))) { //$NON-NLS-1$
            sb.append("<b>"); //$NON-NLS-1$
            sb.append(DataTypeSupport.DESCRIPTION_HEADER);
            sb.append("</b><br>"); //$NON-NLS-1$
            sb.append(this.description);
            sb.append("<br><br>"); //$NON-NLS-1$
        }

        sb.append("<b>"); //$NON-NLS-1$
        sb.append(DataTypeSupport.PROBABLE_SOURCE_HEADER);
        sb.append("</b><br>"); //$NON-NLS-1$
        sb.append(this.probableSource);
        sb.append("<br><br><b>"); //$NON-NLS-1$
        sb.append(DataTypeSupport.COMMERCIAL_SOURCE_HEADER);
        sb.append("</b><br>"); //$NON-NLS-1$
        sb.append("<a href=\""); //$NON-NLS-1$
        sb.append(this.commercialSource);
        sb.append("\">"); //$NON-NLS-1$
        sb.append(this.commercialSource);
        sb.append("</a>"); //$NON-NLS-1$

        if (!this.note.equalsIgnoreCase(DataTypeResourceLoader
                .getString("DataType.emptyValue"))) { //$NON-NLS-1$
            sb.append("<br><br><b>"); //$NON-NLS-1$
            sb.append(DataTypeSupport.NOTE_HEADER);
            sb.append("</b><br>"); //$NON-NLS-1$
            sb.append(this.note);
        }

        return sb.toString();
    }

    public String getCommercialSource() {
        return this.commercialSource;
    }

    public String getDataTypeName() {
        return this.dataTypeName;
    }

    public String getDataValue() {
        return this.dataValue;
    }

    public String getDescription() {
        return this.description;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public String getNote() {
        return this.note;
    }

    public String getProbableSource() {
        return this.probableSource;
    }
    
    public String getButtonText() {
        return this.buttonText;
    }

	void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	void setDataTypeName(String dataTypeName) {
		this.dataTypeName = dataTypeName;
	}

	void setDataValue(String dataValue) {
		this.dataValue = dataValue;
	}

	void setDescription(String description) {
		this.description = description;
	}

	void setProbableSource(String probableSource) {
		this.probableSource = probableSource;
	}

	void setCommercialSource(String commercialSource) {
		this.commercialSource = commercialSource;
	}

	void setNote(String note) {
		this.note = note;
	}

	void setButtonText(String buttonText) {
		this.buttonText = buttonText;
	}

	void setCurrencySymbol(String currencySymbol) {
		this.currencySymbol = currencySymbol;
	}
}