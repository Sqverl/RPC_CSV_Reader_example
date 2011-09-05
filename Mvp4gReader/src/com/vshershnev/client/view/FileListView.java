package com.vshershnev.client.view;

import java.util.ArrayList;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.extjs.gxt.ui.client.store.ListStore;
import com.vshershnev.client.presenter.FileListPresenter.IFileListView;
import com.extjs.gxt.ui.client.widget.form.TextField;

@SuppressWarnings("rawtypes")
public class FileListView extends LayoutContainer implements IFileListView{

	private Button add = new Button( "Search" );
	private Grid grid_results;
	private TextField<String> txtfield_search = new TextField<String>();
	
	@SuppressWarnings("unchecked")
	public FileListView() {
		//Constructing search form
	    txtfield_search.setLabelSeparator("");
	    txtfield_search.setValue("Input Search Here");
	    txtfield_search.focus();
	    txtfield_search.selectAll();
		
		FormPanel form = new FormPanel();
		form.setStyleName( "mainForm" );
		form.setHeading( "mvp4g CSV Reader" );		
		form.add(txtfield_search);
		form.addButton( add );
		form.setButtonAlign( HorizontalAlignment.CENTER );
		add( form );
		//end of search form construction
		
		//Grid construction
	    ContentPanel p = new ContentPanel();
	    p.setHeading("");
	    p.setWidth(-1);
	    p.setHeight(-1);
	    
	    ArrayList<ColumnConfig> cols = new ArrayList<ColumnConfig>();
	    ColumnConfig tcc = new ColumnConfig("", "", 150);
	    cols.add(tcc);
	    ColumnModel cm = new ColumnModel(cols);
	   
		final ListStore store = new ListStore();
	    ArrayList storeList = new ArrayList();
	    store.add(storeList);
	    
	    grid_results = new Grid(store, cm);
	    grid_results.setAutoHeight(true);
	    p.add(grid_results);	 
	    
	    add( p );
		// End Of grid construction
		
	}

	public void displayError( String error ) {
		Window.alert( error );
	}
	
	public Grid getGrid(){
		return grid_results;
	}

	public Button getAddButton() {
		return add;
	}

	public Widget getMyWidget() {
		return this;
	}

	public TextField getSearchField() {
		return txtfield_search;
	}

}

