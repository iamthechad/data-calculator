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

public interface IDataType {
	public String toHTMLString();
	public String getCommercialSource();
	public String getDataTypeName();
	public String getDataValue();
	public String getDescription();
	public String getImagePath();
	public String getNote();
	public String getProbableSource();
	public String getButtonText();
}
