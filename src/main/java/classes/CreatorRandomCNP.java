package classes;

import com.github.javafaker.Faker;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreatorRandomCNP {
    Faker faker = new Faker();

    public String RandomCNP() throws ParseException {
        int firstDigitCNP = faker.number().numberBetween(1, 9);
        String birthDayDigits = BirthDayDigits(firstDigitCNP);

        String areaCode = String.valueOf(faker.number().numberBetween(101, 152)).substring(1);
        String orderNumber = String.valueOf(faker.number().numberBetween(1001, 1999)).substring(1);

        String cnp12 = firstDigitCNP + birthDayDigits + areaCode + orderNumber;
        String cnp = CreateCNP13(cnp12);

        return cnp;
    }

    private String CreateCNP13(String cnp12) {
        int sum = 0;
        for (int i = 1; i <= 12; i++) {
            sum += Integer.parseInt(cnp12.substring(i - 1, i)) * i;
        }

        int num13 = sum % 11;
        num13 = num13 == 10 ? 1 : num13;

        return cnp12 + num13;
    }

    private String BirthDayDigits(int firstDigitCNP) throws ParseException {
        String date = RandomBirtData(firstDigitCNP);
        String[] dates = date.split("/");

        String year = dates[2].substring(2);
        String month = dates[1];
        String day = dates[0];

        return year + month + day;
    }
    private String RandomBirtData(int firstDigit) throws ParseException {

        if (firstDigit == 1 || firstDigit == 2) {
            return formatDate(generateRandomDate("1900-01-01", "1999-12-31", "yyyy-MM-dd"), "dd/MM/yyyy");
        } else if (firstDigit == 3 || firstDigit == 4) {
            return formatDate(generateRandomDate("1800-01-01", "1899-12-31", "yyyy-MM-dd"), "dd/MM/yyyy");
        } else if (firstDigit == 5 || firstDigit == 6) {
            return formatDate(generateRandomDate("2000-01-01", "2099-12-31", "yyyy-MM-dd"), "dd/MM/yyyy");
        } else {
            return formatDate(generateRandomDate("1800-01-01", "2099-12-31", "yyyy-MM-dd"), "dd/MM/yyyy");
        }
    }

    private static Date generateRandomDate(String startDate, String endDate, String dateFormat) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Date start = sdf.parse(startDate);
        Date end = sdf.parse(endDate);
        long startTime = start.getTime();
        long endTime = end.getTime();
        long randomTime = startTime + (long) (Math.random() * (endTime - startTime));
        return new Date(randomTime);
    }

    private static String formatDate(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

}
