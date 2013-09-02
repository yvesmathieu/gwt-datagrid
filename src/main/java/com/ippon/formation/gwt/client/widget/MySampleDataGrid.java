package com.ippon.formation.gwt.client.widget;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.cell.client.SelectionCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.SafeHtmlHeader;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.Range;
import com.ippon.formation.gwt.shared.ContactDatabase;
import com.ippon.formation.gwt.shared.ContactDatabase.Category;
import com.ippon.formation.gwt.shared.ContactInfo;
import com.ippon.formation.gwt.shared.LocalizedConstants;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MySampleDataGrid extends Composite {

	interface Binder extends UiBinder<Widget, MySampleDataGrid> {
	}

	private static Binder uiBinder = GWT.create(Binder.class);

	LocalizedConstants constants = GWT.create(LocalizedConstants.class);

	@UiField(provided = true)
	DataGrid<ContactInfo> dataGrid;

	/**
	 * Default constructor.
	 */
	public MySampleDataGrid() {
		initDataGrid();
		initWidget(uiBinder.createAndBindUi(this));
	}

	/**
	 * Initialize datagrid.
	 */
	private void initDataGrid() {
		dataGrid = new DataGrid<ContactInfo>(ContactInfo.KEY_PROVIDER);
		dataGrid.setEmptyTableWidget(new Label(constants.nothingToDisplay()));

		dataGrid.setVisibleRange(new Range(0, 1000));

		// Init columns.
		initColumns();

		// Add the Datagrid to the adapter in the database.
		ContactDatabase.get().addDataDisplay(dataGrid);
	}

	/**
	 * Initialize table columns.
	 */
	private void initColumns() {
		// First name.
		Column<ContactInfo, String> firstNameColumn = new Column<ContactInfo, String>(
				new EditTextCell()) {
			@Override
			public String getValue(ContactInfo object) {
				return object.getFirstName();
			}
		};
		firstNameColumn.setSortable(true);
		dataGrid.addColumn(firstNameColumn, constants.firstName());
		firstNameColumn
				.setFieldUpdater(new FieldUpdater<ContactInfo, String>() {
					public void update(int index, ContactInfo object,
							String value) {
						// onValueChange in cell
						object.setFirstName(value);
						ContactDatabase.get().refreshDisplays();
					}
				});
		dataGrid.setColumnWidth(firstNameColumn, 20, Unit.PCT);

		// Last name.
		Column<ContactInfo, String> lastNameColumn = new Column<ContactInfo, String>(
				new EditTextCell()) {
			@Override
			public String getValue(ContactInfo object) {
				return object.getLastName();
			}
		};
		lastNameColumn.setSortable(true);
		dataGrid.addColumn(lastNameColumn, constants.lastName());
		lastNameColumn.setFieldUpdater(new FieldUpdater<ContactInfo, String>() {
			public void update(int index, ContactInfo object, String value) {
				// onValueChange in cell
				object.setLastName(value);
				ContactDatabase.get().refreshDisplays();
			}
		});
		dataGrid.setColumnWidth(lastNameColumn, 20, Unit.PCT);

		// Age.
		Column<ContactInfo, Number> ageColumn = new Column<ContactInfo, Number>(
				new NumberCell()) {
			@Override
			public Number getValue(ContactInfo object) {
				return object.getAge();
			}
		};
		ageColumn.setSortable(true);
		dataGrid.addColumn(ageColumn,
				new SafeHtmlHeader(SafeHtmlUtils.fromSafeConstant(constants.age())));
		dataGrid.setColumnWidth(ageColumn, 5, Unit.PCT);

		// Category.
		final Category[] categories = ContactDatabase.get().queryCategories();
		List<String> categoryNames = new ArrayList<String>();
		for (Category category : categories) {
			categoryNames.add(category.getDisplayName());
		}
		SelectionCell categoryCell = new SelectionCell(categoryNames);
		Column<ContactInfo, String> categoryColumn = new Column<ContactInfo, String>(
				categoryCell) {
			@Override
			public String getValue(ContactInfo object) {
				return object.getCategory().getDisplayName();
			}
		};
		dataGrid.addColumn(categoryColumn, constants.category());
		categoryColumn.setFieldUpdater(new FieldUpdater<ContactInfo, String>() {
			public void update(int index, ContactInfo object, String value) {
				for (Category category : categories) {
					if (category.getDisplayName().equals(value)) {
						object.setCategory(category);
					}
				}
				ContactDatabase.get().refreshDisplays();
			}
		});
		dataGrid.setColumnWidth(categoryColumn, 10, Unit.PCT);

		// Address.
		Column<ContactInfo, String> addressColumn = new Column<ContactInfo, String>(
				new TextCell()) {
			@Override
			public String getValue(ContactInfo object) {
				return object.getAddress();
			}
		};
		addressColumn.setSortable(true);
		dataGrid.addColumn(addressColumn, constants.address());
		dataGrid.setColumnWidth(addressColumn, 38, Unit.PCT);
	}

}
