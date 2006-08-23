package org.megatome.data;

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

import java.text.NumberFormat;

import org.megatome.util.ImageType;

public enum DataType {
    ADDRESS("address", ImageType.INFORMATION), //$NON-NLS-1$
    ZIPCODE("zipcode", ImageType.INFORMATION), //$NON-NLS-1$
    PAST_ADDRESSES("pastAddresses", ImageType.INFORMATION), //$NON-NLS-1$
    DATE_OF_BIRTH("dateOfBirth", ImageType.INFORMATION), //$NON-NLS-1$
    MARRIAGE("marriage", ImageType.INFORMATION), //$NON-NLS-1$
    DIVORCE("divorce", ImageType.INFORMATION), //$NON-NLS-1$
    EDUCATION("education", ImageType.INFORMATION), //$NON-NLS-1$
    EMPLOYMENT("employment", ImageType.INFORMATION), //$NON-NLS-1$
    PUBLISHED_PHONE("publishedPhone", ImageType.INFORMATION), //$NON-NLS-1$
    UNPUBLISHED_PHONE("unpublishedPhone", ImageType.INFORMATION), //$NON-NLS-1$
    CELLULAR_PHONE("cellularPhone", ImageType.INFORMATION), //$NON-NLS-1$
    PAST_PHONES("pastPhones", ImageType.INFORMATION), //$NON-NLS-1$
    RELATIVES("relatives", ImageType.INFORMATION), //$NON-NLS-1$
    NEIGHBORS("neighbors", ImageType.INFORMATION), //$NON-NLS-1$
    REGISTERED_URL("registeredURL", ImageType.INFORMATION), //$NON-NLS-1$
    SSN("ssn", ImageType.INFORMATION), //$NON-NLS-1$
    CREDIT("credit", ImageType.FINANCIAL), //$NON-NLS-1$
    REAL_ESTATE("realEstate", ImageType.FINANCIAL), //$NON-NLS-1$
    BANKRUPTCY("bankruptcy", ImageType.FINANCIAL), //$NON-NLS-1$
    WORKERS_COMP("workersComp", ImageType.FINANCIAL), //$NON-NLS-1$
    ASSETS("assets", ImageType.FINANCIAL), //$NON-NLS-1$
    ASSETS_SEIZED("assetsSeized", ImageType.FINANCIAL), //$NON-NLS-1$
    SHAREHOLDER("shareholder", ImageType.FINANCIAL), //$NON-NLS-1$
    EXECUTIVE_AFFILIATION("executiveAffiliation", ImageType.FINANCIAL), //$NON-NLS-1$
    OWN_AIRCRAFT("ownAircraft", ImageType.FINANCIAL), //$NON-NLS-1$
    OWN_BOAT("ownBoat", ImageType.FINANCIAL), //$NON-NLS-1$
    OWN_VEHICLE("ownVehicle", ImageType.FINANCIAL), //$NON-NLS-1$
    OWN_BUSINESS("ownBusiness", ImageType.FINANCIAL), //$NON-NLS-1$
    LAWSUITS("lawsuits", ImageType.LEGAL), //$NON-NLS-1$
    FELONY("felony", ImageType.LEGAL), //$NON-NLS-1$
    MISDEMEANOR("misdemeanor", ImageType.LEGAL), //$NON-NLS-1$
    SEX_OFFENDER("sexOffender", ImageType.LEGAL), //$NON-NLS-1$
    DRIVER_LICENSE("driverLicense", ImageType.LICENSE), //$NON-NLS-1$
    MOTOR_VEHICLE("motorVehicle", ImageType.LICENSE), //$NON-NLS-1$
    LIST_OF_VEHICLES("listOfVehicles", ImageType.LICENSE), //$NON-NLS-1$
    ACCIDENT_RECORD("accidentRecord", ImageType.LICENSE), //$NON-NLS-1$
    AIRCRAFT_LICENSE("aircraftLicense", ImageType.LICENSE), //$NON-NLS-1$
    DEA_LICENSE("deaLicense", ImageType.LICENSE), //$NON-NLS-1$
    HUNT_FISH_LICENSE("huntFishLicense", ImageType.LICENSE), //$NON-NLS-1$
    PROFESSIONAL_LICENSE("professionalLicense", ImageType.LICENSE), //$NON-NLS-1$
    INDUST_ACCRED("industAccred", ImageType.LICENSE), //$NON-NLS-1$
    MERCHANT_VESSEL("merchantVessel", ImageType.LICENSE), //$NON-NLS-1$
    CONCEAL_WEAPON("concealWeapon", ImageType.LICENSE), //$NON-NLS-1$
    FIREARMS_LICENSE("firearmsLicense", ImageType.LICENSE), //$NON-NLS-1$
    VOTER_REGISTRATION("voterRegistration", ImageType.POLITICAL), //$NON-NLS-1$
    MILITARY_RECORD("militaryRecord", ImageType.POLITICAL); //$NON-NLS-1$
    
    private String imagePath;
    private String dataTypeName;
    private String dataValue;
    private String description;
    private String probableSource;
    private String commercialSource;
    private String note;
    private String currencySymbol;
    private String buttonText;

    DataType(final String resourcePrefix, final ImageType imageType) {
        this.imagePath = imageType.getImagePath();
        this.dataTypeName = DataTypeResourceLoader.getString(resourcePrefix + DataTypeSupport.DATA_TYPE_NAME);
        this.dataValue = DataTypeResourceLoader.getString(resourcePrefix + DataTypeSupport.DATA_VALUE);
        this.description = DataTypeResourceLoader.getString(resourcePrefix + DataTypeSupport.DESCRIPTION);
        this.probableSource = DataTypeResourceLoader.getString(resourcePrefix
                + DataTypeSupport.PROBABLE_SOURCE);
        this.commercialSource = DataTypeResourceLoader.getString(resourcePrefix
                + DataTypeSupport.COMMERCIAL_SOURCE);
        this.note = DataTypeResourceLoader.getString(resourcePrefix + DataTypeSupport.NOTE);
        this.buttonText = DataTypeResourceLoader.getString(resourcePrefix + DataTypeSupport.BUTTON_TEXT);
        this.currencySymbol = NumberFormat.getCurrencyInstance().getCurrency()
                .getSymbol();
    }

    

    public String getRequestHeader() {
        return DataTypeResourceLoader.getString("DataType.requestHeader"); //$NON-NLS-1$
    }

    public String getDescriptionHeader() {
        return DataTypeResourceLoader.getString("DataType.descriptionHeader"); //$NON-NLS-1$
    }

    public String getProbableSourceHeader() {
        return DataTypeResourceLoader.getString("DataType.probableSourceHeader"); //$NON-NLS-1$
    }

    public String getCommercialSourceHeader() {
        return DataTypeResourceLoader.getString("DataType.commercialSourceHeader"); //$NON-NLS-1$
    }

    public String getNoteHeader() {
        return DataTypeResourceLoader.getString("DataType.noteHeader"); //$NON-NLS-1$
    }

    public String toHTMLString() {
        StringBuffer sb = new StringBuffer();

        sb.append("<b>"); //$NON-NLS-1$
        sb.append(this.dataTypeName);
        sb.append("</b><br>"); //$NON-NLS-1$
        sb.append(getRequestHeader());
        sb.append("<b>"); //$NON-NLS-1$
        sb.append(this.currencySymbol);
        sb.append(this.dataValue);
        sb.append("</b>"); //$NON-NLS-1$
        sb.append("<br><br>"); //$NON-NLS-1$

        if (!this.description.equalsIgnoreCase(DataTypeResourceLoader
                .getString("DataType.emptyValue"))) { //$NON-NLS-1$
            sb.append("<b>"); //$NON-NLS-1$
            sb.append(getDescriptionHeader());
            sb.append("</b><br>"); //$NON-NLS-1$
            sb.append(this.description);
            sb.append("<br><br>"); //$NON-NLS-1$
        }

        sb.append("<b>"); //$NON-NLS-1$
        sb.append(getProbableSourceHeader());
        sb.append("</b><br>"); //$NON-NLS-1$
        sb.append(this.probableSource);
        sb.append("<br><br><b>"); //$NON-NLS-1$
        sb.append(getCommercialSourceHeader());
        sb.append("</b><br>"); //$NON-NLS-1$
        sb.append("<a href=\""); //$NON-NLS-1$
        sb.append(this.commercialSource);
        sb.append("\">"); //$NON-NLS-1$
        sb.append(this.commercialSource);
        sb.append("</a>"); //$NON-NLS-1$

        if (!this.note.equalsIgnoreCase(DataTypeResourceLoader
                .getString("DataType.emptyValue"))) { //$NON-NLS-1$
            sb.append("<br><br><b>"); //$NON-NLS-1$
            sb.append(getNoteHeader());
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
}