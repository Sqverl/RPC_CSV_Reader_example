package com.vshershnev.client.view.view_interface;

//import java.util.List;

import com.mvp4g.client.view.ReverseViewInterface;
import com.vshershnev.client.widget.interfaces.IWidget;

public interface IFileListView extends IWidget, ReverseViewInterface<IFileListView.IUserRolePresenter> {

	public interface IUserRolePresenter {
		void onAddButtonClicked();
	}

	void displayError( String error );
}
