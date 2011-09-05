package com.vshershnev.client.presenter;

import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.BasePresenter;
import com.vshershnev.client.Mvp4gReaderEventBus;
import com.vshershnev.client.view.RootTemplateView;
import com.vshershnev.client.widget.interfaces.IWidget;

@Presenter( view = RootTemplateView.class )
public class RootTemplatePresenter extends BasePresenter<RootTemplatePresenter.IRootTemplateView, Mvp4gReaderEventBus> {
	
	public interface IRootTemplateView {

		 void setTopWidget( IWidget widget );

	}

	public void onChangeTopWidget( IWidget widget ) {
		view.setTopWidget( widget );
	}
	
}
