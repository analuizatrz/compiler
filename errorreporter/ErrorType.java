package errorreporter;
public enum ErrorType{
	EOF(-1),
	MFT(-2),
	UNT(-3),
	UEOF(-4);

	public int errorValue;
	ErrorType(int value) {
		errorValue = value;
	}

	@Override
  public String toString() {
    return this.name();
  }

}
