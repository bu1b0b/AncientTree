package ru.ancienttree.main;

public abstract class PaneControlUtil {

	private static AncientTreePaneController AncientTreePaneController;

	public static AncientTreePaneController getAncientTreePaneController() {
		return AncientTreePaneController;
	}

	public static void setAncientTreePaneController(AncientTreePaneController AncientTreePaneController) {
		PaneControlUtil.AncientTreePaneController = AncientTreePaneController;
	}

} // class end
