/*
 * Copyright 2010 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.ippon.formation.gwt.shared;

import com.google.gwt.i18n.client.Constants;

/**
 * The constants used in this Content Widget.
 */
public interface LocalizedConstants extends Constants {
	@Key("contact.database.categories")
	String[] contactDatabaseCategories();

	@Key("last.name")
	String lastName();

	@Key("first.name")
	String firstName();

	@Key("age")
	String age();

	@Key("address")
	String address();

	@Key("category")
	String category();

	@Key("nothing.to.display")
	String nothingToDisplay();
}