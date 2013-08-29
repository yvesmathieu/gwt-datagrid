package com.ippon.formation.gwt.client;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.ippon.formation.gwt.shared.ContactDatabase;
import com.ippon.formation.gwt.shared.ContactDatabase.Category;
import com.ippon.formation.gwt.shared.ContactInfo;

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
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.AbstractDataProvider;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MySampleDataGrid extends Composite {

	interface Binder extends UiBinder<Widget, MySampleDataGrid> {
	}

	private static Binder uiBinder = GWT.create(Binder.class);

	@UiField(provided = true)
	DataGrid<ContactInfo> dataGrid;

	@UiField(provided = true)
	SimplePager pager;

	public MySampleDataGrid() {
		initDataGrid();
		initWidget(uiBinder.createAndBindUi(this));
	}

	public Widget asWidget() {
		return this;
	}

	private void initDataGrid() {
		dataGrid = new DataGrid<ContactInfo>(ContactInfo.KEY_PROVIDER);
		dataGrid.setWidth("1000px");
		dataGrid.setHeight("800px");
		dataGrid.setEmptyTableWidget(new Label("Nothing to show !"));

		// SortHandler
		ListHandler<ContactInfo> sortHandler = new ListHandler<ContactInfo>(
				ContactDatabase.get().getDataProvider().getList());
		dataGrid.addColumnSortHandler(sortHandler);

		// Pager
		pager = new SimplePager(TextLocation.CENTER, false, 0, true);
		pager.setDisplay(dataGrid);

		// Init columns.
		initColumns(sortHandler);

		// Add the CellList to the adapter in the database.
		ContactDatabase.get().addDataDisplay(dataGrid);
	}

	private void initColumns(ListHandler<ContactInfo> sortHandler) {
		// First name.
		Column<ContactInfo, String> firstNameColumn = new Column<ContactInfo, String>(
				new EditTextCell()) {
			@Override
			public String getValue(ContactInfo object) {
				return object.getFirstName();
			}
		};
		firstNameColumn.setSortable(true);
		sortHandler.setComparator(firstNameColumn,
				new Comparator<ContactInfo>() {
					public int compare(ContactInfo o1, ContactInfo o2) {
						return o1.getFirstName().compareTo(o2.getFirstName());
					}
				});
		dataGrid.addColumn(firstNameColumn, "First name");
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
		sortHandler.setComparator(lastNameColumn,
				new Comparator<ContactInfo>() {
					public int compare(ContactInfo o1, ContactInfo o2) {
						return o1.getLastName().compareTo(o2.getLastName());
					}
				});
		dataGrid.addColumn(lastNameColumn, "Last name");
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
		sortHandler.setComparator(ageColumn, new Comparator<ContactInfo>() {
			public int compare(ContactInfo o1, ContactInfo o2) {
				return o1.getBirthday().compareTo(o2.getBirthday());
			}
		});
		dataGrid.addColumn(ageColumn,
				new SafeHtmlHeader(SafeHtmlUtils.fromSafeConstant("Age")));
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
		dataGrid.addColumn(categoryColumn, "Category");
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
			public String getValue(ContactInfo object) {
				return object.getAddress();
			}
		};
		addressColumn.setSortable(true);
		sortHandler.setComparator(addressColumn, new Comparator<ContactInfo>() {
			public int compare(ContactInfo o1, ContactInfo o2) {
				return o1.getAddress().compareTo(o2.getAddress());
			}
		});
		dataGrid.addColumn(addressColumn, "Address");
		dataGrid.setColumnWidth(addressColumn, 55, Unit.PCT);
	}

	protected void createContactInfo() {
//		ContactInfo contactInfo = new ContactInfo()
	}
}
