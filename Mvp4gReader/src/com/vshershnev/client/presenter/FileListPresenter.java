package com.vshershnev.client.presenter;

import java.util.ArrayList;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.*;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.BasePresenter;
import com.vshershnev.client.Mvp4gReaderEventBus;
import com.vshershnev.client.UserServiceAsync;
import com.vshershnev.client.view.FileListView;
import com.vshershnev.client.widget.interfaces.IWidget;
import com.extjs.gxt.ui.client.widget.form.TextField;

@Presenter( view = FileListView.class )
public class FileListPresenter extends BasePresenter<FileListPresenter.IFileListView, Mvp4gReaderEventBus> {
	@SuppressWarnings("rawtypes")
	public interface IFileListView extends IWidget {
		
		public Button getAddButton();
		
		public TextField getSearchField();

		public Grid getGrid();

		public void displayError( String error );

	}

	@Inject
	private UserServiceAsync service = null;

	@Override
	public void bind() {
		Button add = view.getAddButton();
		
		add.addListener( Events.Select, new Listener<ButtonEvent>() {

			@SuppressWarnings("unchecked")
			public void handleEvent( ButtonEvent be ) {
				service.getFile(((TextField<String>)view.getSearchField()).getValue(), new AsyncCallback<String>(){
					public void onFailure( Throwable caught ) {
						Window.alert(caught.getLocalizedMessage());
					}

					@SuppressWarnings("rawtypes")
					public void onSuccess( String result ) {		
						//Grid editing start
						ArrayList newStore = new ArrayList();
						ArrayList newCols = new ArrayList();
						//Creating Columns
						int colsToAdd = 0, lastIndex = 0;
						ColumnConfig title = new ColumnConfig("title", "No", 50);
						newCols.add(title);
						do{
							lastIndex = result.indexOf(";", lastIndex)+1;
							colsToAdd++;
							ColumnConfig tempCol = new ColumnConfig("col"+colsToAdd, "col"+colsToAdd, 150);
							newCols.add(tempCol);
						}while(lastIndex>0 && lastIndex < result.indexOf("<br>"));

						//Filling up the table
						String[] rows = result.split("<br>");						
						for(int i=0; i < rows.length; i++){
							BaseModel bm = new BaseModel();
							bm.set("title", i+" ");
							
							String[] buf = rows[i].split(";");
							for(int k = 0; k < buf.length; k++){
								bm.set("col"+(k+1), buf[k]);
							}
							newStore.add(bm);
						}
						//Empty all rows from existing grid and fill up new ones.
						view.getGrid().getStore().removeAll();
						view.getGrid().getStore().add(newStore);
						
						view.getGrid().reconfigure(view.getGrid().getStore(),new ColumnModel(newCols));
					    //Grid edited 
					}
				});
			}

		} );
	}

	public void onStart() {
		eventBus.changeTopWidget( view );
	}
	
	public void setUserService( UserServiceAsync service ) {
		this.service = service;
	}
}
