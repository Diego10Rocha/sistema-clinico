package date;

public class MyDateTime {

	public static int compareTo(String time1, String time2) {

		int horaTime1 = parseHora(time1);
		int horaTime2 = parseHora(time2);

		System.out.println(horaTime1 + "->" + horaTime2);

		if (horaTime1 == horaTime2)

			return compareMinutos(time1, time2);

		else
			return compareHora(time1, time2);

	}

	private static int compareMinutos(String time1, String time2) {

		int minutoTime1 = parseMinutos(time1);
		int minutoTime2 = parseMinutos(time2);

		if (minutoTime1 < minutoTime2)

			return -1;

		else if (minutoTime1 > minutoTime2)

			return 1;

		return 0;
	}

	private static int parseMinutos(String time) {

		if (time.length() == 5)
			return Integer.parseInt(time.substring(3));

		try {

			if (parseHora(time) < 10)
				return Integer.parseInt(time.substring(2));

			return Integer.parseInt(time.substring(3));

		} catch (NumberFormatException e) {

			return Integer.parseInt(time.substring(2));
		}
	}

	private static int compareHora(String time1, String time2) {

		int horaTime1 = parseHora(time1);
		int horaTime2 = parseHora(time2);

		if (horaTime1 < horaTime2)

			return -1;

		else if (horaTime1 > horaTime2)

			return 1;

		return 0;
	}

	private static int parseHora(String time) {

		try {

			return Integer.parseInt(time.substring(0, 2));

		} catch (NumberFormatException e) {

			return Integer.parseInt(time.substring(0, 1));
		}

	}
}
