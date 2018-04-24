package lexer;
public enum Tag{
	//reserved words
	PROGRAM(256), //program
	DECLARE(257), //declare
	BEGIN(258), //begin
	END(259), //end
	INT(260), //int
	FLOAT(261), //float
	CHAR(262), //char
	IF(263), //if
	THEN(264), //then
	ELSE(265), //end
	REPEAT(266), //repeat
	UNTIL(267), //untilSS
	WHILE(268), //while
	DO(269), //do
	IN(270), //in
	OUT(271), //out

	AND(272), //&&
	OR(273), //||


	//operators and punctation
	SCL(274), //;
	TDT(275), //:
	CMA(276), //),
	ATB(277), //=
	OP(278), //(
	CP(279), //)
	EXC(280), //!
	MNS(281), //-
	PLS(282), //+
	EQ(283), //==
	GT(284), //>
	LT(285), //<
	GE(286), //<=
	LE(287), //>=
	NEQ(288), //!=
	MUL(289), //*
	DIV(290), ///
	DOT(291), //.
	SQ(292), //'
	QT(293), //"
	UDS(294), //_
	NOT(295), //!

	//Others
	ID(296),
	CINT(297),
	CFLOAT(298),
	CCHAR(299),
	CSTRING(300),

	EOF(-1),
	MFT(-2),
	UNT(-3),
	UEOF(-4);

	public int tagValue;
	 Tag(int value) {
			 tagValue = value;
	 }

	@Override
  public String toString() {
    return this.name();
  }

}
