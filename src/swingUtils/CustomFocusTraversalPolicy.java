package swingUtils;

import java.awt.Component;
import java.awt.Container;
import java.awt.FocusTraversalPolicy;
 
import java.util.ArrayList;
 
public class CustomFocusTraversalPolicy extends FocusTraversalPolicy {
 
  private ArrayList<Component> order;
 
	public CustomFocusTraversalPolicy(ArrayList<Component> order) {
		this.order = order;
	}
 
	@Override
	public Component getComponentAfter(Container container, Component component) {
		int index = (order.indexOf(component) + 1) % order.size();
		Component after = order.get(index);
		while (index < order.size() && !(after.isEnabled() && after.isVisible())) {
			index++;
			after = order.get(index);
		}
		return after;
	}
 
	@Override
	public Component getComponentBefore(Container container, Component component) {
		int index = (order.indexOf(component) - 1);
		if (index < 0) {
			index = order.size() - 1;
		}
		Component before = order.get(index);
		while (index >= 0 && !(before.isEnabled() && before.isVisible())) {
			index --;
			before = order.get(index);
		}
		return before;
	}
 
	@Override
	public Component getFirstComponent(Container container) {
		int index = 0;
		Component first = order.get(index);
		while (index < order.size() && !(first.isEnabled() && first.isVisible())) {
			index++;
			first = order.get(index);
		}
		return first;
	}
 
	@Override
	public Component getLastComponent(Container container) {
		int index = order.size() - 1;
		Component last = order.get(index);
		while (index >= 0 && !(last.isEnabled() && last.isVisible())) {
			index--;
			last = order.get(index);
		}
		return last;
	}
 
	@Override
	public Component getDefaultComponent(Container container) {
		return getFirstComponent(container);
	}
}
