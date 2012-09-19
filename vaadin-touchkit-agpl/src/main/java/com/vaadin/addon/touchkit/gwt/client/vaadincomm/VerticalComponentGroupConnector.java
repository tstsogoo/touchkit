package com.vaadin.addon.touchkit.gwt.client.vaadincomm;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.addon.touchkit.gwt.client.VerticalComponentGroupWidget;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.client.ComponentConnector;
import com.vaadin.client.ConnectorHierarchyChangeEvent;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractComponentContainerConnector;
import com.vaadin.client.ui.AbstractLayoutConnector;
import com.vaadin.shared.communication.URLReference;
import com.vaadin.shared.ui.Connect;

@SuppressWarnings("serial")
@Connect(VerticalComponentGroup.class)
public class VerticalComponentGroupConnector extends
        AbstractLayoutConnector {

    @Override
    public VerticalComponentGroupState getState() {
        return (VerticalComponentGroupState) super.getState();
    }

    @Override
    public void onStateChanged(StateChangeEvent stateChangeEvent) {
        super.onStateChanged(stateChangeEvent);
    }

    @Override
    public void onConnectorHierarchyChange(ConnectorHierarchyChangeEvent event) {
        
        for (ComponentConnector oldChild : event.getOldChildren()) {
        	if (oldChild.getParent() != this) {
        		getWidget().remove(oldChild.getWidget());
        	}
        }
        
        List<ComponentConnector> children = getChildComponents();
        
        for (int i = 0; i < children.size(); ++i) {
        	ComponentConnector connector = children.get(i);
        	Widget widget = connector.getWidget();
        	getWidget().addOrMove(widget, i);
        	updateCaption(connector);
        	//FIXME
        	//getWidget().setIcon(widget, connector.getState().icon);
        }
        
        super.onConnectorHierarchyChange(event);
    }

    @Override
    protected VerticalComponentGroupWidget createWidget() {
        return GWT.create(VerticalComponentGroupWidget.class);
    }
    
    @Override
    public VerticalComponentGroupWidget getWidget() {
    	return (VerticalComponentGroupWidget)super.getWidget();
    }

    @Override
    public void updateCaption(ComponentConnector connector) {
    	String caption = null;
    	
    	if (connector.delegateCaptionHandling()) {
	    	caption = connector.getState().caption;
    	}
    	
    	getWidget().setCaption(connector.getWidget(), caption);
    }
}
