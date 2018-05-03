package edu.nyu.cs9053.homework4;

public enum OlympicGame {
	I(1) {
		@Override public String getHostCountry() {
			return "France";
		}
	},
	II(2) {
		@Override public String getHostCountry() {
			return "Switzerland";
		}
	},
	III(3) {
		@Override public String getHostCountry() {
			return "United States";
		}
	},
	IV(4) {
		@Override public String getHostCountry() {
		    return "Germany";
		}
	},
	V(5) {
		@Override public String getHostCountry() {
			return "Switzerland";
		}
	},
	VI(6) {
		@Override public String getHostCountry() {
			return "Norway";
		}
	},
	VII(7) {
		@Override public String getHostCountry() {
			return "Italy";
		}
	},
	VIII(8) {
		@Override public String getHostCountry() {
			return "United States";
		}
	},
	IX(9) {
		@Override public String getHostCountry() {
			return "Austria";
		}
	},
	X(10) {
		@Override public String getHostCountry() {
			return "France";
		}
	},
	XI(11) {
		@Override public String getHostCountry() {
			return "Japan";
		}
	},
	XII(12) {
		@Override public String getHostCountry() {
			return "Austria";
		}
	},
	XIII(13) {
		@Override public String getHostCountry() {
			return "United States";
		}
	},
	XIV(14) {
		@Override public String getHostCountry() {
			return "Yugoslavia";
		}
	},
	XV(15) {
		@Override public String getHostCountry() {
			return "Canada";
		}
	},
	XVI(16) {
		@Override public String getHostCountry() {
			return "France";
		}
	},
	XVII(17) {
		@Override public String getHostCountry() {
			return "Norway";
		}
	},
	XVIII(18) {
		@Override public String getHostCountry() {
			return "Japan";
		}
	},
	XIX(19) {
		@Override public String getHostCountry() {
			return "United States";
		}
	},
	XX(20) {
		@Override public String getHostCountry() {
			return "Italy";
		}
	},
	XXI(21) {
		@Override public String getHostCountry() {
			return "Canada";
		}
	},
	XXII(22) {
		@Override public String getHostCountry() {
			return "Russia";
		}
	},
	XXIII(23) {
		@Override public String getHostCountry() {
			return "South Korea";
		}
	};

	private final int value;

	public abstract String getHostCountry();

	private OlympicGame(int value) {
		this.value = value;
	}

	public int getRank() {
		return value;
	}

	public static void print(OlympicGame ... games) {
		for (OlympicGame game : games) {
			System.out.println(game.getHostCountry());
		}
	}

}