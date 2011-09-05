package com.vshershnev.client;

import com.mvp4g.client.annotation.Event;
import com.mvp4g.client.annotation.Events;
import com.mvp4g.client.annotation.Start;
import com.mvp4g.client.event.EventBus;

import com.vshershnev.client.presenter.FileListPresenter;
import com.vshershnev.client.presenter.RootTemplatePresenter;
import com.vshershnev.client.view.RootTemplateView;
import com.vshershnev.client.widget.interfaces.IWidget;

@Events( startView = RootTemplateView.class )
public interface Mvp4gReaderEventBus extends EventBus {

	@Event( handlers = RootTemplatePresenter.class )
	public void changeTopWidget( IWidget widget );

	@Start
	@Event( handlers = FileListPresenter.class)
	public void start();
}