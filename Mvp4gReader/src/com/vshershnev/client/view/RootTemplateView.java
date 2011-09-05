package com.vshershnev.client.view;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.vshershnev.client.presenter.RootTemplatePresenter.IRootTemplateView;
import com.vshershnev.client.widget.interfaces.IWidget;

public class RootTemplateView extends Composite implements IRootTemplateView {

	private FlexTable table = new FlexTable();

	public RootTemplateView() {

		table.getFlexCellFormatter().setColSpan( 0, 0, 2 );

		initWidget( table );

	}

	public void setTopWidget( IWidget widget ) {
		table.setWidget( 0, 0, widget.getMyWidget() );
	}

}
