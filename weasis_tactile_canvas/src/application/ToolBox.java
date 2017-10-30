package application;

public class ToolBox {
	public static final String SCROLL = "scroll";
	public static final String ZOOM = "zoom";
	public static final String TRANSLATE = "translate";
	public static final String CONTRASTE = "contraste";

	private static String selectedToolOneFinger = TRANSLATE;
	private static String selectedToolTwoFingers = TRANSLATE;

	public static void setTool(String id) {
		String NbFinger = id.split("_")[1];

		switch (NbFinger) {
		case "1":
			selectedToolOneFinger = id.split("_")[0];
			break;
		case "2":
			selectedToolTwoFingers = id.split("_")[0];
			break;
		}
	}

	public static void outTool() {
		System.out.println("1 finger  = "+selectedToolOneFinger);
		System.out.println("2 fingers = "+selectedToolTwoFingers);
	}
	
	public static String getToolOneFinger() {
		return selectedToolOneFinger;
	}
	public static String getToolTwoFinger() {
		return selectedToolTwoFingers;
	}
}
