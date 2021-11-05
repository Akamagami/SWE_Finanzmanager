package constants;

public enum ClassType {
	NUTZER("Nutzer");
	
	
	private String displayName;
	
	ClassType(String displayName) {
		this.displayName = displayName;
	}
	public String getDisplayName() {
		return displayName;
	}
}
